/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.Discussion;
import models.Message;
import models.user;
import utils.DataSource;

/**
 *
 * @author shidono
 */
public class DiscussionService implements CRUD{
    Connection cnx = DataSource.getInstance().getCnx();
    public void SupprimerDiscussion(int id){
        try {
            String requete = "DELETE FROM discussion WHERE id_disc=?";
//            String requete = "INSERT INTO evenement VALUES (null,?,?,?,CONVERT(?, DATE),CONVERT(?, DATE),CONVERT(?, TIME),CONVERT(?, TIME),?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1,id);
            pst.executeUpdate();
            System.out.println("Discussion supprimée !");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression de la discussion \n " + ex.getMessage());
        }
    }
    
     public ArrayList<Message> AfficherMessageDiscussion(int id){
        ArrayList<Message> messages = new ArrayList<>();
        try {
            String requete = "Select disc.id_disc,contenu_msg,sender,datetemps_msg from message JOIN discussion disc where disc.id_disc = message.id_disc AND disc.id_disc=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1,id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                messages.add(new Message(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(4)));
            }
        } catch (SQLException ex) {
            System.out.println("Erreur affichage des message \n" + ex.getMessage());
        }
        return messages;
    }
     
     
     public ArrayList<Discussion> AfficherListe(){
          ArrayList<Discussion> discussion = new ArrayList<>();
        try {
            String requete;
            if(user.getType().compareTo("psy") == 0) requete = "Select * from discussion where nom_source=?";
            else requete = "Select * from discussion where nom_destinaire=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1,user.getUsername());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                discussion.add(new Discussion(rs.getInt(1),rs.getDate(2),rs.getString(3),rs.getString(4)));
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors d'extraction des données \n" + ex.getMessage());
        }
        return discussion;
    }
     
      public Discussion AfficherDiscussion(int id){
        try {
            String requete;
            requete = "Select * from discussion where id_disc=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1,id);
            ResultSet rs = pst.executeQuery();
            if(rs.next()) {
                return new Discussion(rs.getInt(1),rs.getDate(2),rs.getString(3),rs.getString(4));
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors d'extraction des données \n" + ex.getMessage());
            return null;
        }
        return null;
    }
     
     public String AfficherDernierMess(int id){
         try {
         String requete = "Select contenu_msg from message where id_disc=? ORDER BY id_msg DESC limit 1";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1,id);
            ResultSet rs = pst.executeQuery();
            if(rs.next()) return rs.getString(1);
            return "pas de message";
         } catch (SQLException ex) {
            System.out.println("Erreur lors d'extraction des données \n" + ex.getMessage());
            return "pas de message";
        }
     }
     
    public void VerifierVue(int id){
        try {
            String requete = "UPDATE discussion set vue_disc= NOT vue_disc WHERE id_disc=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1,id);
            pst.executeUpdate();
            System.out.println("vue altérnée");
//            Execution d'un code spécifique pour le changement en cours de la modification
        } catch (SQLException ex) {
            System.out.println("Erreur lors d'extraction des données \n" + ex.getMessage());
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
