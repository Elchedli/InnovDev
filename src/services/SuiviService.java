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
import models.Message;
import models.Suivi;
import models.user;
import utils.DataSource;

/**
 *
 * @author shidono
 */
public class SuiviService implements CRUD{
    Connection cnx = DataSource.getInstance().getCnx();
    public void ajouterSuivi(Suivi suiv){
        try {
            String requete = "INSERT INTO suivi VALUES (null,?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, user.getUsername());
            pst.setString(1, suiv.getClient());
            pst.setString(2,suiv.getTitre_s());
            pst.setDate(3, suiv.getDate_ds());
            pst.setDate(4, suiv.getDate_fs());
            pst.setTime(5, suiv.getTemps_ds());
            pst.setTime(6, suiv.getTemps_fs());
            pst.executeUpdate();
            System.out.println("Suivi crée !");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la création du suivi \n " + ex.getMessage());  
        }
            
    }
    
    public void ModifierSuivi(String row,Object obj,int id){   
        try {
            String requete = "UPDATE Suivi set ?=? WHERE id_s=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1,row);
            if(obj instanceof String) pst.setString(2, (String) obj);
               else if(obj instanceof Time) pst.setTime(2, (Time) obj);
               else if(obj instanceof Date) pst.setDate(2,(Date) obj);
            pst.setInt(3,id);
           pst.executeUpdate();
           System.out.println("Row suivi modifier : "+id);
        } catch (SQLException ex) {
        System.out.println("erreur lors de la modification de la tache \n" + ex.getMessage());
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
            pst.setString(1,user.getUsername());
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

    @Override
    public void Ajouter() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Modifier() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Supprimer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
