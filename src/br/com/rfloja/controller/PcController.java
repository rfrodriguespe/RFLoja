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

import br.com.rfloja.interfaces.PcDAO;
import br.com.rfloja.model.Pc;
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
public class PcController implements PcDAO {

    @Override
    public boolean createPc(Pc pc) {
        Connection conn = ConnectionUtil.getConnection();
        PreparedStatement stmt = null;
        String sql = "INSERT INTO pc (modelo, velocidadeGhz, tamanhoRAM, tamanhoHD, preço, cod_fabricante) VALUES (?, ?, ?, ?, ?, ?);";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, pc.getModelo());
            stmt.setFloat(2, pc.getVelocidadeGhz());
            stmt.setInt(3, pc.getTamanhoRAM());
            stmt.setInt(4, pc.getTamanhoHD());
            stmt.setInt(5, pc.getPreco());
            stmt.setInt(6, pc.getCodFabricante());
            stmt.executeUpdate();
            return true;

        } catch (SQLException ex) {
            return false;
        } finally {
            ConnectionUtil.closeConnection(conn, stmt);
        }
    }

    @Override
    public ArrayList<Pc> readPc() {
        Connection conn = ConnectionUtil.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Pc> listaPc = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT * FROM pc");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Pc pc = new Pc();
                pc.setCod(rs.getInt("cod"));
                pc.setModelo(rs.getString("modelo"));
                pc.setVelocidadeGhz(rs.getFloat("velocidadeGhz"));
                pc.setTamanhoRAM(rs.getInt("tamanhoRAM"));
                pc.setTamanhoHD(rs.getInt("tamanhoHD"));
                pc.setPreco(rs.getInt("preço"));
                pc.setCodFabricante(rs.getInt("cod_fabricante"));
                listaPc.add(pc);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Consultar os dados" + ex);
        } finally {
            ConnectionUtil.closeConnection(conn, stmt, rs);
        }
        return listaPc;
    }

    @Override
    public boolean updatePc(Pc pc) {
        Connection conn = ConnectionUtil.getConnection();
        PreparedStatement stmt = null;
        String sql = "UPDATE `pc` SET `modelo`=?,`velocidadeGhz`=?, `tamanhoRAM`=?, `tamanhoHD`=?, `preço`=?, `cod_fabricante`=? WHERE cod=?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, pc.getModelo());
            stmt.setFloat(2, pc.getVelocidadeGhz());
            stmt.setInt(3, pc.getTamanhoRAM());
            stmt.setInt(4, pc.getTamanhoHD());
            stmt.setInt(5, pc.getPreco());
            stmt.setInt(6, pc.getCodFabricante());
            stmt.setInt(7, pc.getCod());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        } finally {
            ConnectionUtil.closeConnection(conn, stmt);
        }
    }

    @Override
    public boolean deletePc(Pc pc) {
        Connection conn = ConnectionUtil.getConnection();
        PreparedStatement stmt = null;
        String sql = "DELETE from pc WHERE cod=?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, pc.getCod());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        } finally {
            ConnectionUtil.closeConnection(conn, stmt);
        }
    }

}
