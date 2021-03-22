/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServicesPublication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import pi.*;

/**
 *
 * @author HP I5
 */
public class ServicesPublication implements Services{
ConnectionDB cnx = new ConnectionDB();
public static final int USER_ID= 1;
public String getusername(int id)
{
 try{
        Connection cn = cnx.getInstance().getConn();
        PreparedStatement posted = cn.prepareStatement("SELECT * FROM `utilisateur_simple` where id_user= ?");
        posted.setInt(1,id);
        ResultSet result = posted.executeQuery();
        while(result.next())
        {
                return result.getString("username");
        }
           }
           
        catch(SQLException e)
        {
            System.out.println(e);
        }
           return null;
}


    @Override
    public ArrayList select_all(Object t) {
           try{
        Connection cn = cnx.getInstance().getConn();
        PreparedStatement posted = cn.prepareStatement("SELECT * From publication;");
        ResultSet result = posted.executeQuery();
        ArrayList array= new ArrayList<Publication>();
        int i=0;
        while(result.next())
        {
            Publication pub = new Publication();
            pub.setId(result.getInt("id_pub"));
            pub.setUser_id(result.getInt("id_user"));
            pub.setNb_react(result.getInt("nb_reaction"));
            pub.setText(result.getString("texte"));
            pub.setDate(result.getString("date_pub"));
            array.add(pub);
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
           Publication p = (Publication) t;
            try{
        Connection cn = cnx.getInstance().getConn();
        PreparedStatement posted = cn.prepareStatement("UPDATE `publication` SET `texte` = ? WHERE `id_pub` =?;");
        posted.setString(1, p.getText());
        posted.setInt(2, p.getId());
        posted.executeUpdate();
        System.out.println("Update complete !");
        }
        catch(SQLException e)
        {
            System.out.println(e);
                    
        }
    }

    @Override
    public void insert(Object t) {
           Publication p = (Publication) t;
                   try{    
        Connection cn = cnx.getInstance().getConn();
        PreparedStatement posted = cn.prepareStatement("INSERT INTO `publication` (`id_pub`, `id_user`, `nb_reaction`, `texte`) VALUES (NULL,1,0,?);",Statement.RETURN_GENERATED_KEYS);
        posted.setString(1,p.getText());
        int affected_row = posted.executeUpdate();
        if(affected_row == 0)
        {
            throw new SQLException("Publication n'est pas crée");
        }
        try(ResultSet generatedKeys = posted.getGeneratedKeys())
        {
            if(generatedKeys.next())
            {
                p.setId(generatedKeys.getInt(1));
            }
        }
        System.out.println("Insertion complete !");
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
    }

    @Override
    public void delete(Object t) {
        Publication p = (Publication) t;
 try{
        Connection cn = cnx.getInstance().getConn();
        ServicesTags STAG = new ServicesTags();
        STAG.delete_relation(p);
        Photo ph = new Photo(0);
        ServicesPhoto Sph = new ServicesPhoto();
        ph.setId(Sph.get_IdPhFromPub(p.getId()));
        ServicesPhoto sph = new ServicesPhoto();
        sph.delete(ph);
        
        PreparedStatement posted = cn.prepareStatement("DELETE FROM `publication` WHERE `id_pub` =?;");
        posted.setInt(1,p.getId());
        posted.executeUpdate();
        System.out.println("Suppression complete !");
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
    }    
        public ArrayList Get_Pub(int id) {
           try{
        Connection cn = cnx.getInstance().getConn();
        PreparedStatement posted = cn.prepareStatement("SELECT * From publication where id_pub = ? ;");
        posted.setInt(1,id);
        ResultSet result = posted.executeQuery();
        ArrayList<String> array= new ArrayList<String>();
        int i=0;
        while(result.next())
        {
            array.add(result.getString("id_pub"));
            array.add(result.getString("id_user"));
            array.add(result.getString("nb_reaction"));
            array.add(result.getString("texte"));
            array.add(result.getString("date_pub"));
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
        
        public void like_post(Object t, int id_user)
{
            Publication p = (Publication) t;
            if(check_if_liked(p,id_user) == false){
     try{
        Connection cn = cnx.getInstance().getConn();
        PreparedStatement posted = cn.prepareStatement("UPDATE `publication` SET `nb_reaction`=`nb_reaction`+1 where id_pub= ?;");
        posted.setInt(1,p.getId());
        posted.executeUpdate();
        System.out.println("Update Likes complete !");
        track_likes(p,id_user);
        }
        catch(SQLException e)
             {
            System.out.println(e);
                    
             }
            }
            else
            {
     try{
        Connection cn = cnx.getInstance().getConn();
        PreparedStatement posted = cn.prepareStatement("UPDATE `publication` SET `nb_reaction`=`nb_reaction`-1 where id_pub= ?;");
        posted.setInt(1,p.getId());
        posted.executeUpdate();
        System.out.println("Update Likes complete !");
        track_likes(p,id_user);
        }
        catch(SQLException e)
             {
            System.out.println(e);
                    
             }
     release_like(p,id_user);
            }
            
}
        public boolean check_if_liked(Object t, int id_user)
        {
            Publication p = (Publication) t;
            Connection cn = cnx.getInstance().getConn();
        try{    
        PreparedStatement posted = cn.prepareStatement("SELECT * FROM pub_like_tracks where id_user= ? AND id_pub=?;");
        posted.setInt(1, id_user);
        posted.setInt(2,p.getId());
        ResultSet result = posted.executeQuery();
        if(result.first())
        {
         return true;
        }
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        return false;
        }
        public void track_likes(Object t, int id_user)
        {
              Publication p = (Publication) t;
     try{
        Connection cn = cnx.getInstance().getConn();
        PreparedStatement posted = cn.prepareStatement("INSERT INTO `pub_like_tracks` (`id_user`,`id_pub`) VALUES (?,?);");
        posted.setInt(1,id_user);
        posted.setInt(2,p.getId());
        posted.executeUpdate();
        System.out.println("Update Likes complete !");
        }
        catch(SQLException e)
             {
            System.out.println(e);
                    
             }
        }
        public void release_like(Object t, int id_user)
        {
                         Publication p = (Publication) t;
     try{
        Connection cn = cnx.getInstance().getConn();
        PreparedStatement posted = cn.prepareStatement("DELETE FROM `pub_like_tracks` WHERE `id_user` = ? AND `id_pub` = ?");
        posted.setInt(1,id_user);
        posted.setInt(2,p.getId());
        posted.executeUpdate();
        System.out.println("Track Likes complete !");
        }
        catch(SQLException e)
             {
            System.out.println(e);
                    
             }
        }
        
                public void drop_track(Object t)
        {
                         Publication p = (Publication) t;
     try{
        Connection cn = cnx.getInstance().getConn();
        PreparedStatement posted = cn.prepareStatement("DELETE FROM `pub_like_tracks` WHERE `id_pub` = ?");
        posted.setInt(1,p.getId());
        posted.executeUpdate();
        System.out.println("Track Likes complete !");
        }
        catch(SQLException e)
             {
            System.out.println(e);
                    
             }
        }
}
    

