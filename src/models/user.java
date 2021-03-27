/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author shidono
 */
public class user {
    static private String username="ahmed";
    static private String password="idk";
    static private String type="psy";

    public static String getType() {
        return type;
    }
    
    
    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        user.username = username;
    }

    public static void setType(String type) {
        user.type = type;
    }

    public static String getPassword() {
        return password;
    }
    
}
