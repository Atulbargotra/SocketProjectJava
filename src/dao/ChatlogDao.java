/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dbutil.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import pojo.Chatlog;

/**
 *
 * @author atulb
 */
public class ChatlogDao {

    public static  boolean addChatLog(Chatlog obj) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("insert into chatlogs values(?,?,?)");
        ps.setString(1, obj.getUsername());
        ps.setString(2, obj.getMessage());
        ps.setString(3, obj.getMsgtime());
        int c = ps.executeUpdate();
        return c == 1;
    }
}
