/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import PIClass.Message;
import PIClass.Suivi;
import PIClass.userclient;
import PIClass.user;
import PIUtils.MyConnection;

/**
 *
 * @author shidono
 */
public class SuiviService {
    Connection cnx = MyConnection.getInstance().getCnx();
    public boolean check(Suivi suiv){
        return suiv.getClient() != null && suiv.getDate_ds() != null && suiv.getDate_fs() != null && suiv.getTitre_s() != null && suiv.getTemps_ds() != null && suiv.getTemps_fs() != null;
    }
    public String ajouterSuivi(Suivi suiv){
        String result=" ";
        try {
            if(suiv.getDate_ds().compareTo(suiv.getDate_fs())>0) result+="date ";
            if(suiv.getTemps_ds().compareTo(suiv.getTemps_fs())>0) result+="temps ";
            String requete = "select username from simple where username=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, suiv.getClient());
            ResultSet rs = pst.executeQuery();
            if(!rs.next()) result+="foreign ";
            if(result == " " && check(suiv)){
                requete = "INSERT INTO suivi VALUES (null,?,?,?,?,?,?,?)";
                pst = cnx.prepareStatement(requete);
                pst.setString(1, userclient.getUsername());
                pst.setString(2, suiv.getClient());
                pst.setString(3,suiv.getTitre_s());
                pst.setDate(4, suiv.getDate_ds());
                pst.setDate(5, suiv.getDate_fs());
                pst.setTime(6, suiv.getTemps_ds());
                pst.setTime(7, suiv.getTemps_fs());
                pst.executeUpdate();
                System.out.println("Suivi crée !");
                requete = "SELECT nom_destinaire from discussion where nom_source=? AND nom_destinaire=?";
                 pst = cnx.prepareStatement(requete);
                pst.setString(1, userclient.getUsername());
                pst.setString(2, suiv.getClient());
                rs = pst.executeQuery();
                if(!rs.next()){
                    requete = "insert into discussion values (null,DEFAULT,?,?,DEFAULT)";
                    pst = cnx.prepareStatement(requete);
                    pst.setString(1, userclient.getUsername());
                    pst.setString(2, suiv.getClient());
                    pst.executeUpdate();
                    System.out.println("Dicussion créer");
                }
            }
        } catch (SQLException ex) {
            String message = ex.getMessage();
            System.out.println("erreur lors de la création du suivi \n " + ex.getMessage()); 
//            if(message.contains("foreign key")) result+="foreign ";
            return result.substring(0, result.length() - 1);
//            else if(message.contains(""))
        }
        return result.substring(0, result.length() - 1);
    }
    
    public void ModifierSuivi(int id,Object obj,String row){   
        try {
            String requete = "UPDATE Suivi set "+row+"=? WHERE id_s=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            if(obj instanceof String) pst.setString(1, (String) obj);
               else if(obj instanceof Time) pst.setTime(1, (Time) obj);
               else if(obj instanceof Date) pst.setDate(1,(Date) obj);
            pst.setInt(2,id);
           pst.executeUpdate();
           System.out.println("Row suivi modifier : "+id);
        } catch (SQLException ex) {
        System.out.println("erreur lors de la modification de la tache \n" + ex.getMessage());
        }
    }

    public void SupprimerSuivi(int id){
        try {
            String requete = "DELETE FROM suivi WHERE id_s=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Suivi effacer !");
        } catch (SQLException ex) {
            System.out.println("erreur lors de l'effacement du suivi \n " + ex.getMessage());  
        }
    }
   
//    public static void method(Object obj) {
//        if (obj instanceof String)
//            System.out.println("I am a String!");
//
//        if (obj instanceof Integer)
//            System.out.println("I am an Integer!");
//
//        // Similarly for other types of Object
//        // The .getClass() is for any Object
//        System.out.println(obj.getClass());
//    }
    
    public ArrayList<Suivi> AfficherSuivi(){
        ArrayList<Suivi> suivliste = new ArrayList<>();
        try {
            String requete = "select * from suivi WHERE username=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1,userclient.getUsername());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                suivliste.add(new Suivi(rs.getInt(1),rs.getString(3),rs.getString(4),rs.getDate(5),rs.getDate(6),rs.getTime(7),rs.getTime(8)));
            }
            return suivliste;
        } catch (SQLException ex) {
            System.out.println("Probleme affichage Suivi \n " +ex+"\n"+ex.getMessage());
            return null;
        }
    }
}
