/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIClass;

/**
 *
 * @author HP
 */
public class userclient {
    static private String username="";
    static private String password="";
    static private String type="psy";
    public static String getType() {
        return type;
    }
    
    
    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        userclient.username = username;
    }

    public static void setType(String type) {
        userclient.type = type;
    }

    public static String getPassword() {
        return password;
    }
    
}
