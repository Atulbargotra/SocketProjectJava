/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dbutil.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import pojo.ChatClient;

/**
 *
 * @author atulb
 */
public class ChatClientDao {

    public static boolean findClient(String username) throws SQLException {
        boolean f = false;
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("select * from chatclients where username = ?");
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            f = true;
        }
        return f;
    }

    public static boolean addClient(ChatClient obj) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("insert into chatclients values(?,?)");
        ps.setString(1, obj.getUsername());
        ps.setString(2, obj.getPassword());
        int c = ps.executeUpdate();
        return c == 1;
    }
    public static boolean checkPassword(String username,String password) throws SQLException{
        boolean f = false;
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("select * from chatclients where username = ? and password = ?");
        ps.setString(1, username);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            f = true;
        }
        return f;
    }
}
