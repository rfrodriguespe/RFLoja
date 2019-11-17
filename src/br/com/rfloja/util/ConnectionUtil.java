package br.com.rfloja.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionUtil {

	public static Connection getConnection() {
		Properties properties = new Properties();
		try {
			ResourceBundle resources = ResourceBundle.getBundle("br.com.rfrodrigues.util.DatabaseProperties");
			for (@SuppressWarnings("rawtypes")
			Enumeration keys = resources.getKeys(); keys.hasMoreElements();) {
				final String key = (String) keys.nextElement();
				final String value = resources.getString(key);
				properties.put(key, value);
			}
			Class.forName(properties.getProperty("jdbcDriver")).newInstance();
			return (Connection) DriverManager.getConnection(properties.getProperty("url"));
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
			Logger.getLogger(ConnectionUtil.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;

	}
        
            /**
     *
     * @param conn
     */
    public static void closeConnection(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param conn
     * @param stmt
     */
    public static void closeConnection(Connection conn, PreparedStatement stmt) {
        closeConnection(conn);
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param conn
     * @param stmt
     * @param rs
     */
    public static void closeConnection(Connection conn, PreparedStatement stmt, ResultSet rs) {
        closeConnection(conn, stmt);
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}