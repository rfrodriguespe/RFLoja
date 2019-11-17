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

import br.com.rfloja.interfaces.FabricanteDAO;
import br.com.rfloja.model.Fabricante;
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
public class FabricanteController implements FabricanteDAO {

    @Override
    public boolean createFabricante(Fabricante fabricante) {
        Connection conn = ConnectionUtil.getConnection();
        PreparedStatement stmt = null;
        String sql = "INSERT INTO fabricante (fabricante, obs) VALUES (?, ?);";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, fabricante.getFabricante());
            stmt.setString(2, fabricante.getObs());
            stmt.executeUpdate();
            return true;

        } catch (SQLException ex) {
            return false;
        } finally {
            ConnectionUtil.closeConnection(conn, stmt);
        }
    }

    @Override
    public ArrayList<Fabricante> readFabricante() {
        Connection conn = ConnectionUtil.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Fabricante> listaFabricantes = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT * FROM fabricante");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Fabricante fabricante = new Fabricante();
                fabricante.setCod(rs.getInt("cod"));
                fabricante.setFabricante(rs.getString("fabricante"));
                fabricante.setObs(rs.getString("obs"));
                listaFabricantes.add(fabricante);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Consultar os dados" + ex);
        } finally {
            ConnectionUtil.closeConnection(conn, stmt, rs);
        }
        return listaFabricantes;
    }

    @Override
    public boolean updateFabricante(Fabricante fabricante) {
        Connection conn = ConnectionUtil.getConnection();
        PreparedStatement stmt = null;
        String sql = "UPDATE `fabricante` SET `fabricante`=?,`obs`=? WHERE cod=?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, fabricante.getFabricante());
            stmt.setString(2, fabricante.getObs());
            stmt.setInt(3, fabricante.getCod());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        } finally {
            ConnectionUtil.closeConnection(conn, stmt);
        }
    }

    @Override
    public boolean deleteFabricante(Fabricante fabricante) {
        Connection conn = ConnectionUtil.getConnection();
        PreparedStatement stmt = null;
        String sql = "DELETE from usuarios WHERE cod=?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, fabricante.getCod());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        } finally {
            ConnectionUtil.closeConnection(conn, stmt);
        }
    }

}
