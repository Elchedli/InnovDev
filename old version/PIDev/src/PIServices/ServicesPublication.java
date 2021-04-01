/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIServices;

import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import PIClass.*;
import PIUtils.*;
import PIServices.ServicesPhoto;


/**
 *
 * @author HP I5
 */
public class ServicesPublication implements IservicesA{
MyConnection cnx = new MyConnection();
public static final int USER_ID= 1;
public String getusernamePsy(int id)
{
 try{
        Connection cn = cnx.getInstance().getCnx();
        PreparedStatement posted = cn.prepareStatement("SELECT * FROM `psycho` where id_user= ?");
        posted.setInt(1,id);
        ResultSet result = posted.executeQuery();
        if(result.first())
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
public String getusernameNutri(int id)
{
 try{
        Connection cn = cnx.getInstance().getCnx();
        PreparedStatement posted = cn.prepareStatement("SELECT * FROM `nutri` where id_user=?");
        posted.setInt(1,id);
        ResultSet result = posted.executeQuery();
        if(result.first())
        {
              System.out.println("Username du nutritionist"+result.getString("username"));
                return result.getString("username");
              
        }
           }
           
        catch(SQLException e)
        {
            System.out.println(e);
        }
           return null;
}
public String getusernameCoach(int id)
{
 try{
        Connection cn = cnx.getInstance().getCnx();
        PreparedStatement posted = cn.prepareStatement("SELECT * FROM `coach` where id_user= ?");
        posted.setInt(1,id);
        ResultSet result = posted.executeQuery();
        if(result.first())
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
public String getusernameAdmin(int id)
{
 try{
        Connection cn = cnx.getInstance().getCnx();
        PreparedStatement posted = cn.prepareStatement("SELECT * FROM `admin` where id_user= ?");
        posted.setInt(1,id);
        ResultSet result = posted.executeQuery();
       if(result.first())
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

public String getUsername(int id)
{
   String usercoach = getusernameCoach(id);
   String userpsy = getusernamePsy(id);
   String usernutri = getusernameNutri(id);
   String useradmin = getusernameAdmin(id);
    System.out.println(usercoach);
    System.out.println(userpsy);
    System.out.println(usernutri);
    System.out.println(useradmin);
   if(useradmin != null)
       return useradmin;
    if(usercoach != null)
       return usercoach;
    if(userpsy != null)
       return userpsy;
    if(usernutri != null)
       return usernutri;
   else return "-1";
   
}




public int getId(String username)
{
 try{
        Connection cn = cnx.getInstance().getCnx();
        PreparedStatement posted = cn.prepareStatement("SELECT * FROM `simple` where username= ?");
        posted.setString(1,username);
        ResultSet result = posted.executeQuery();
        while(result.next())
        {
                return result.getInt("id_user");
        }
           }
           
        catch(SQLException e)
        {
            System.out.println(e);
        }
           return -1;
}


    @Override
    public ArrayList select_all(Object t) {
           try{
        Connection cn = cnx.getInstance().getCnx();
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
        Connection cn = cnx.getInstance().getCnx();
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
        Connection cn = cnx.getInstance().getCnx();
        PreparedStatement posted = cn.prepareStatement("INSERT INTO `publication` (`id_pub`, `id_user`, `nb_reaction`, `texte`) VALUES (NULL,?,0,?);",Statement.RETURN_GENERATED_KEYS);
        posted.setInt(1,userclient.getId());
        posted.setString(2,p.getText());
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
        Connection cn = cnx.getInstance().getCnx();
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
    
     public ArrayList return_Pub(int id) {
           try{
        Connection cn = cnx.getInstance().getCnx();
        Publication p = new Publication();
        PreparedStatement posted = cn.prepareStatement("SELECT * From publication where id_pub = ? ;");
        posted.setInt(1,id);
        ResultSet result = posted.executeQuery();
        ArrayList<Publication> array= new ArrayList<>();
        while(result.next())
        {
            p.setId(result.getInt("id_pub"));
            p.setUser_id(result.getInt("id_user"));
            p.setText(result.getString("texte"));
            p.setNb_react(result.getInt("nb_reaction"));
            p.setDate(result.getString("date_pub"));
           /* array.add(result.getString("id_pub"));
            array.add(result.getString("id_user"));
            array.add(result.getString("nb_reaction"));
            array.add(result.getString("texte"));
            array.add(result.getString("date_pub"));*/
            array.add(p);
        }
        return array;
           }
           
        catch(SQLException e)
        {
            System.out.println(e);
        }
           return null;
    }
        
        public ArrayList Get_Pub(int id) {
           try{
        Connection cn = cnx.getInstance().getCnx();
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
        Connection cn = cnx.getInstance().getCnx();
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
        Connection cn = cnx.getInstance().getCnx();
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
            Connection cn = cnx.getInstance().getCnx();
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
        Connection cn = cnx.getInstance().getCnx();
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
        Connection cn = cnx.getInstance().getCnx();
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
        Connection cn = cnx.getInstance().getCnx();
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
    public Publication fetch_pub(int id)
     {
        try{
        Connection cn = cnx.getInstance().getCnx();
        PreparedStatement posted = cn.prepareStatement("SELECT * From publication where id_pub= ?;");
        posted.setInt(1, id);
        ResultSet result = posted.executeQuery();
        Publication pub = new Publication();
            if(result.first())
             {
                 
                 pub.setId(result.getInt("id_pub"));
                 pub.setUser_id(result.getInt("id_user"));
                 pub.setNb_react(result.getInt("nb_reaction"));
                 pub.setText(result.getString("texte"));
                 pub.setDate(result.getString("date_pub"));
                      return pub;   
             }
           }
           
        catch(SQLException e)
        {
            System.out.println(e);
        }
               return null;   
       }
                
                public String change_username(String username, String type)
                {
                    String new_username = new String();
                    if(type == "psy")
                    {
                        new_username = "Dr. " + username;
                    }
                    else if(type == "nutritionnist")
                    {
                        new_username = "Nutritionist. " + username;
                    }
                    else if(type == "admin")
                    {
                        new_username = "Admin. " + username; 
                    }
                    else if(type == "coach")
                    {
                        new_username = "Coach. " + username;
                    }
                    else if(type == "" || type == null)
                    {
                        new_username = username;
                    }
                    return new_username;
                }
                public String ext_type(String username)
                {
                     
                   String type = username.substring(0,3);
                    System.out.println(type);
                    
                    if(null != type)
                    switch (type) {
        case "Dr.":
            return "psy";
        case "Nut":
            return "nutritionnist";
        case "Coa":
            return "coach";
        case "Adm":
            return "admin";
        default:
            break;
    }
                    
                        return null;
                }

   public int getrequest(){
       Connection cn = cnx.getInstance().getCnx();
        try {
            String envoi ="";
            System.out.println(userclient.getType());
            switch(userclient.getType()) {
                case "simple":
                    System.out.println("i am mr simple");
                    envoi = "select id_user from simple WHERE username = ?";
                  break;
                case "psy":
                    System.out.println("i am mr psy");
                  envoi = "select id_user from psycho WHERE username = ?";
                  break;
                  case "nutri":
                      System.out.println("i am mr nutri");
                      envoi = "select id_user from nutri WHERE username = ?";
                  break;
                  case "coach":
                      System.out.println("i am mr coach");
                      envoi = "select id_user from coach WHERE username = ?";
                  break;
                  case "admin":
                      System.out.println("i am mr coach");
                      envoi = "select id_user from admin WHERE username = ?";
                  break;
            }
            PreparedStatement st = cn.prepareStatement(envoi);
            st.setString(1,userclient.getUsername());
            System.out.println("ching kong");
            ResultSet rs = st.executeQuery();
            
//            System.out.println(rs1.next());
            if(rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.err.println("Erreur lors du chargement " + ex.getMessage());
            return 999999;
        }
        return 999999;
    }
}
    

