/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author atulb
 */
public class DBConnection {

    private static Connection conn;

    static {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@//DESKTOP-3GG4R6I:1521/xe", "test", "verma");
            //JOptionPane.showMessageDialog(null, "Sucessfully connected");
        } catch (Exception e) {
            e.printStackTrace();
            //JOptionPane.showMessageDialog(null, "failed to connect");

        }

    }

    public static Connection getConnection() {
        return conn;
    }

    public static void closeConnection() {
        try {
            conn.close();
            //JOptionPane.showMessageDialog(null, "Sucessfully close connection");
        } catch (Exception e) {
            e.printStackTrace();
            //JOptionPane.showMessageDialog(null, "Failed to disconnect with connection");
        }
    }

}
