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

import br.com.rfloja.interfaces.NotebookDAO;
import br.com.rfloja.model.Notebook;
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
public class NotebookController implements NotebookDAO {

    @Override
    public boolean createNotebook(Notebook notebook) {
        Connection conn = ConnectionUtil.getConnection();
        PreparedStatement stmt = null;
        String sql = "INSERT INTO notebook (modelo, velocidadeGhz, tamanhoRAM, tamanhoHD, preço, cod_fabricante) VALUES (?, ?, ?, ?, ?, ?);";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, notebook.getModelo());
            stmt.setFloat(2, notebook.getVelocidadeGhz());
            stmt.setInt(3, notebook.getTamanhoRAM());
            stmt.setInt(4, notebook.getTamanhoHD());
            stmt.setInt(5, notebook.getPreco());
            stmt.setInt(6, notebook.getCodFabricante());
            stmt.executeUpdate();
            return true;

        } catch (SQLException ex) {
            return false;
        } finally {
            ConnectionUtil.closeConnection(conn, stmt);
        }
    }

    @Override
    public ArrayList<Notebook> readNotebook() {
        Connection conn = ConnectionUtil.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Notebook> listaNotebook = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT * FROM notebook");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Notebook notebook = new Notebook();
                notebook.setCod(rs.getInt("cod"));
                notebook.setModelo(rs.getString("modelo"));
                notebook.setVelocidadeGhz(rs.getFloat("velocidadeGhz"));
                notebook.setTamanhoRAM(rs.getInt("tamanhoRAM"));
                notebook.setTamanhoHD(rs.getInt("tamanhoHD"));
                notebook.setPreco(rs.getInt("preço"));
                notebook.setCodFabricante(rs.getInt("cod_fabricante"));
                listaNotebook.add(notebook);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Consultar os dados" + ex);
        } finally {
            ConnectionUtil.closeConnection(conn, stmt, rs);
        }
        return listaNotebook;
    }

    @Override
    public boolean updateNotebook(Notebook notebook) {
        Connection conn = ConnectionUtil.getConnection();
        PreparedStatement stmt = null;
        String sql = "UPDATE `notebook` SET `modelo`=?,`velocidadeGhz`=?, `tamanhoRAM`=?, `tamanhoHD`=?, `preço`=?, `cod_fabricante`=? WHERE cod=?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, notebook.getModelo());
            stmt.setFloat(2, notebook.getVelocidadeGhz());
            stmt.setInt(3, notebook.getTamanhoRAM());
            stmt.setInt(4, notebook.getTamanhoHD());
            stmt.setInt(5, notebook.getPreco());
            stmt.setInt(6, notebook.getCodFabricante());
            stmt.setInt(7, notebook.getCod());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        } finally {
            ConnectionUtil.closeConnection(conn, stmt);
        }
    }

    @Override
    public boolean deleteNotebook(Notebook notebook) {
        Connection conn = ConnectionUtil.getConnection();
        PreparedStatement stmt = null;
        String sql = "DELETE from notebook WHERE cod=?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, notebook.getCod());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        } finally {
            ConnectionUtil.closeConnection(conn, stmt);
        }
    }
}
