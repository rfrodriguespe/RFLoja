/*
 * The MIT License
 *
 * Copyright 2019 Rodrigo Ferreira Rodrigues <https://github.com/rfrodriguespe>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package br.com.rfloja.controller;

import br.com.rfloja.interfaces.ImpressoraDAO;
import br.com.rfloja.model.Impressora;
import br.com.rfloja.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Rodrigo Ferreira Rodrigues <https://github.com/rfrodriguespe>
 */
public class ImpressoraController implements ImpressoraDAO {

    @Override
    public boolean createImpressora(Impressora impressora) {
        Connection conn = ConnectionUtil.getConnection();
        PreparedStatement stmt = null;
        String sql = "INSERT INTO impressora (modelo, preço, cod_tipo, cod_fabricante) VALUES (?, ?, ?, ?);";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, impressora.getModelo());
            stmt.setInt(2, impressora.getPreco());
            stmt.setInt(3, impressora.getCodTipo());
            stmt.setInt(4, impressora.getCodFabricante());
            stmt.executeUpdate();
            return true;

        } catch (SQLException ex) {
            return false;
        } finally {
            ConnectionUtil.closeConnection(conn, stmt);
        }
    }

    @Override
    public ArrayList<Impressora> readImpressora() {
        Connection conn = ConnectionUtil.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Impressora> listaImpressora = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT * FROM impressora");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Impressora impressora = new Impressora();
                impressora.setCod(rs.getInt("cod"));
                impressora.setModelo(rs.getString("modelo"));
                impressora.setPreco(rs.getInt("preço"));
                impressora.setCodTipo(rs.getInt("cod_tipo"));
                impressora.setCodFabricante(rs.getInt("cod_fabricante"));
                listaImpressora.add(impressora);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Consultar os dados" + ex);
        } finally {
            ConnectionUtil.closeConnection(conn, stmt, rs);
        }
        return listaImpressora;
    }

    @Override
    public boolean updateImpressora(Impressora impressora) {
        Connection conn = ConnectionUtil.getConnection();
        PreparedStatement stmt = null;
        String sql = "UPDATE `impressora` SET `modelo`=?,`preço`=?, `cod_tipo`=?, `cod_fabricante`=? WHERE cod=?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, impressora.getModelo());
            stmt.setInt(2, impressora.getPreco());
            stmt.setInt(3, impressora.getCodTipo());
            stmt.setInt(4, impressora.getCodFabricante());
            stmt.setInt(5, impressora.getCod());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        } finally {
            ConnectionUtil.closeConnection(conn, stmt);
        }
    }

    @Override
    public boolean deleteImpressora(Impressora impressora) {
        Connection conn = ConnectionUtil.getConnection();
        PreparedStatement stmt = null;
        String sql = "DELETE from impressora WHERE cod=?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, impressora.getCod());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        } finally {
            ConnectionUtil.closeConnection(conn, stmt);
        }
    }

}
