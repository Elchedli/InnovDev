/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIClass;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
/**
 *
 * @author HP
 */


public class simple extends user
{
    

    public simple(int id_user, String username, String password, String mail, Date date_n) 
    {
        super(id_user, username, password, mail, date_n);
    }

    public simple(String username, String password, String mail, Date date_n) {
        super(username, password, mail, date_n);
    }

    
    public simple()
    {}

    @Override
    public String toString() 
    {
        return super.toString();
    }

    public int getId_user() 
    {
        return id_user;
    }

    public void setId_user(int id_user) 
    {
        this.id_user = id_user;
    }

    public String getUsername() 
    {
        return username;
    }

    public void setUsername(String username) 
    {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getMail() 
    {
        return mail;
    }

    public void setMail(String mail) 
    {
        this.mail = mail;
    }

    public Date getDate_n() 
    {
        return date_n;
    }

    public void setDate_n(Date date_n) 
    {
        this.date_n = date_n;
    }
            
}
