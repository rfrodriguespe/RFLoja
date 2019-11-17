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

import br.com.rfloja.interfaces.TipoImpressoraDAO;
import br.com.rfloja.model.TipoImpressora;
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
public class TipoImpressoraController implements TipoImpressoraDAO {

    @Override
    public boolean createTipoImpressora(TipoImpressora tipoImpressora) {
        Connection conn = ConnectionUtil.getConnection();
        PreparedStatement stmt = null;
        String sql = "INSERT INTO tipoImpressora (tipo, obs) VALUES (?, ?);";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, tipoImpressora.getTipo());
            stmt.setString(2, tipoImpressora.getObs());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        } finally {
            ConnectionUtil.closeConnection(conn, stmt);
        }
    }

    @Override
    public ArrayList<TipoImpressora> readTipoImpressora() {
        Connection conn = ConnectionUtil.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<TipoImpressora> listaTipoImpressora = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT * FROM tipoImpressora");
            rs = stmt.executeQuery();
            while (rs.next()) {
                TipoImpressora tipoImressora = new TipoImpressora();
                tipoImressora.setCod(rs.getInt("cod"));
                tipoImressora.setTipo(rs.getString("tipo"));
                tipoImressora.setObs(rs.getString("obs"));
                listaTipoImpressora.add(tipoImressora);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Consultar os dados" + ex);
        } finally {
            ConnectionUtil.closeConnection(conn, stmt, rs);
        }
        return listaTipoImpressora;
    }

    @Override
    public boolean updateTipoImpressora(TipoImpressora tipoImpressora) {
        Connection conn = ConnectionUtil.getConnection();
        PreparedStatement stmt = null;
        String sql = "UPDATE `tipoImpressora` SET `tipo`=?,`obs`=? WHERE cod=?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, tipoImpressora.getTipo());
            stmt.setString(2, tipoImpressora.getObs());
            stmt.setInt(3, tipoImpressora.getCod());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        } finally {
            ConnectionUtil.closeConnection(conn, stmt);
        }
    }

    @Override
    public boolean deleteTipoImpressora(TipoImpressora tipoImpressora) {
        Connection conn = ConnectionUtil.getConnection();
        PreparedStatement stmt = null;
        String sql = "DELETE from tipoImpressora WHERE cod=?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, tipoImpressora.getCod());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        } finally {
            ConnectionUtil.closeConnection(conn, stmt);
        }
    }

}
