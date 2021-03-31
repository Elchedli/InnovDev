/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIClass;
import PIUtils.MyConnection;
import PIServices.ServicesPublication;
import java.util.Date;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author HP I5
 */
public class Photo{
    private int id;
    private int id_pub;
    private String url;

    public Photo() {
    }

    public int getId_pub() {
        return id_pub;
    }

    public void setId_pub(int id_pub) {
        this.id_pub = id_pub;
    }
    
    public Photo(int id, int id_pub, String url) {
        this.id = id;
        this.id_pub = id_pub;
        this.url = url;
    }

    public Photo(int id) {
        this.id = id;
    }

    public Photo(int id_pub, String url) {
        this.id_pub = id_pub;
        this.url = url;
    }
//public static ArrayList<String> select_byId_photopub(int id,MyConnection cnx)
//        {
//           try{
//        Connection cn = cnx.getInstance().getConn();
//        PreparedStatement posted = cn.prepareStatement("SELECT * From photo_publications where id_pub="+ id + ";");
//        ResultSet result = posted.executeQuery();
//        ArrayList<String> array= new ArrayList<String>();
//        while(result.next())
//        {
//            System.out.print("ID_Photo: "+result.getString("id_ph")+"/// ");
//            System.out.print("ID_Publication: "+result.getString("id_pub")+"/// ");
//            System.out.println("Lien: "+result.getString("lien")+"///");
//            array.add(result.getString("id_pub"));
//            array.add(result.getString("id_ph"));
//            array.add(result.getString("lien"));
//        }
//        return array;
//           }
//           
//           
//        catch(SQLException e)
//        {
//            System.out.println(e);
//        }
//           return null;
//        }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
/*
    @Override
    public ArrayList select_all(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Object t) {
        ConnectionDB cnx = (ConnectionDB) t;
        try{
        Connection cn = cnx.getInstance().getConn();
        PreparedStatement posted = cn.prepareStatement("UPDATE `photo` SET `lien` ="+"'"+url+"'"+" WHERE `id_ph` ="+id+"AND `id_pub`="+id_pub+";");
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
        ConnectionDB cnx = (ConnectionDB) t;
         try{
        Connection cn = cnx.getInstance().getConn();
        PreparedStatement posted = cn.prepareStatement("INSERT INTO `photo_publications` (`id_pub`, `lien`) VALUES ("+id_pub+","+"'"+url+"'"+");");
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
        ConnectionDB cnx = (ConnectionDB) t;
        try{
        Connection cn = cnx.getInstance().getConn();
        PreparedStatement posted = cn.prepareStatement("DELETE FROM `photo_publications` WHERE `id_ph` ="+id+";");
        posted.executeUpdate();
        System.out.println("suppression d'image complete !");
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
    }*/

    @Override
    public String toString() {
        return "Photo{" + "id=" + id + ", id_pub=" + id_pub + ", url=" + url + '}';
    }
    
}
