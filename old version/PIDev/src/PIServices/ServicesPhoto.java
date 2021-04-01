/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIServices;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import PIClass.*;
import PIUtils.MyConnection;

import java.util.ArrayList;

/**
 *
 * @author HP I5
 */
public class ServicesPhoto implements IservicesA{
    MyConnection cnx = new MyConnection();

    @Override
    public ArrayList select_all(Object t) {
        int id = (int) t;
  try{
        Connection cn = cnx.getInstance().getCnx();
        PreparedStatement posted = cn.prepareStatement("SELECT * From photo_publications where id_pub= ?;");
        posted.setInt(1, id);
        ResultSet result = posted.executeQuery();
        ArrayList array= new ArrayList<Photo>();
        int i=0;
        while(result.next())
        {
            Photo ph = new Photo();
            ph.setId(result.getInt("id_ph"));
            ph.setId_pub(result.getInt("id_pub"));;
            ph.setUrl(result.getString("lien"));
            array.add(ph);
            i++;
        }
        return array;
           }
           
        catch(SQLException e)
        {
            System.out.println(e);
        }
           return null;
    }

    @Override
    public void update(Object t) {
         Photo ph = (Photo) t;
                try{
        Connection cn = cnx.getInstance().getCnx();
        PreparedStatement posted = cn.prepareStatement("UPDATE `photo` SET `lien` ="+"'"+ph.getUrl()+"'"+" WHERE `id_ph` ="+ph.getId()+"AND `id_pub`="+ph.getId_pub()+";");
        ResultSet result = posted.executeQuery();
        posted.executeUpdate();
        System.out.println("Update Photo complete !");
           }
           
        catch(SQLException e)
        {
            System.out.println(e);
        }
    }

    @Override
    public void insert(Object t) {
         Photo ph = (Photo) t;
        try{
        Connection cn = cnx.getInstance().getCnx();
        PreparedStatement posted = cn.prepareStatement("INSERT INTO `photo_publications` (`id_pub`,`lien`) VALUES ("+ph.getId_pub()+","+"'"+ph.getUrl()+"'"+");");
        posted.executeUpdate();
        System.out.println("Insertion d'image complete !");
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
    }

    @Override
    public void delete(Object t) {
         Photo ph = (Photo) t;
        try{
        Connection cn = cnx.getInstance().getCnx();
        PreparedStatement posted = cn.prepareStatement("DELETE FROM `photo_publications` WHERE `id_ph` ="+ph.getId()+";");
        posted.executeUpdate();
        System.out.println("suppression d'image complete !");
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        
    }
    public int get_IdPhFromPub(int id)
        {
            int test = -1;
           try{
        Connection cn = cnx.getInstance().getCnx();
        PreparedStatement posted = cn.prepareStatement("SELECT id_ph From photo_publications where id_pub="+ id + ";");
        ResultSet result = posted.executeQuery();
        ArrayList<String> array= new ArrayList<String>();
        while(result.next())
        {
            test = result.getInt("id_ph");
        }
        return test;
           }
           
           
        catch(SQLException e)
        {
            System.out.println(e);
        }
           return test;
        }
    
    public ArrayList get_LinksByIdPub(int id_pub)
    {
        ArrayList<String> links = new ArrayList(); 
        try{ 
        Connection cn = cnx.getInstance().getCnx();
        PreparedStatement posted = cn.prepareStatement("SELECT * From photo_publications where id_pub=?;");
        posted.setInt(1, id_pub);
        ResultSet result = posted.executeQuery();
        ArrayList<String> array= new ArrayList<String>();
        while(result.next())
        {
            links.add(result.getString("lien"));
        }
        return links;
           }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        return null;
    }
    
}
