/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi;
import java.sql.*;
/**
 *
 * @author HP I5
 */
public class ConnectionDB {
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String CONN_STRING = "jdbc:mysql://127.0.0.1/spirity";
    private static java.sql.Connection conn;
    private static ConnectionDB instance;

    public static ConnectionDB getInstance(){
            if (instance == null)
            {
            instance = new ConnectionDB();
            }
            return instance;
    }
    public static Connection getConn() {
        return conn;
    }
    
   
     public static String getUSERNAME() {
        return USERNAME;
    }

    public static String getPASSWORD() {
        return PASSWORD;
    }

    public static String getCONN_STRING() {
        return CONN_STRING;
    }
    public boolean establish_conn()
    {
        try{
            
            conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
            System.out.println("Connection established !");
            return true;
        }
        catch(SQLException e){
            System.err.println(e);
            return false;
        }
    }
}
