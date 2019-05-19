/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

/**
 *
 * @author atulb
 */
public class Chatlog {
   String username;
   String message;
   String msgtime;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMsgtime() {
        return msgtime;
    }

    public void setMsgtime(String msgtime) {
        this.msgtime = msgtime;
    }

    public Chatlog() {
    }

    @Override
    public String toString() {
        return "Chatlog{" + "username=" + username + ", message=" + message + ", msgtime=" + msgtime + '}';
    }

    public Chatlog(String username, String message, String msgtime) {
        this.username = username;
        this.message = message;
        this.msgtime = msgtime;
    }
   
}
