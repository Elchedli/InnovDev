/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIClass;

import PIUtils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author HP
 */
public class userclient {
    Connection cnx = MyConnection.getInstance().getCnx();
    static private String username = "";
    static private String type="";
    static private String language="fr";
    static private int id=9999999;
    public static String getUsername() {
        return username;
    }

    public static String getType() {
        return type;
    }

    public static void setType(String type) {
        userclient.type = type;
    }

    public static String getLanguage() {
        return language;
    }

    public static void setLanguage(String language) {
        userclient.language = language;
    }

    public static void setUsername(String username) {
        userclient.username = username;
    }
    
    

    public static int getId() {
        return id;
    }

    public static void setId() {
        id = this.getrequest();
    }
}
