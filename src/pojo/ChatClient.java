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
public class ChatClient {
    String username;
    String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ChatClient() {
    }

    @Override
    public String toString() {
        return "ChatClient{" + "username=" + username + ", password=" + password + '}';
    }

    public ChatClient(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
}
