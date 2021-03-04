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
import utils.DataSource;

/**
 *
 * @author shidono
 */
public class MessageService {
    Connection cnx = DataSource.getInstance().getCnx();
     public void AjouterMessage(Message mess){
         try {
            String requete = "INSERT INTO `discussion` VALUES (null,?,?,default)";
//            String requete = "INSERT INTO evenement VALUES (null,?,?,?,CONVERT(?, DATE),CONVERT(?, DATE),CONVERT(?, TIME),CONVERT(?, TIME),?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1,mess.getContenu_msg());
            pst.setInt(2,mess.getId_disc());
            pst.executeUpdate();
            System.out.println("Message ajoutée !");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la création de la discussion \n " + ex.getMessage());
        }
    }
    
    public void SupprimerMessage(int id){
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
            String requete = "Select * from message JOIN discussion disc where disc.";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                //messages.add();
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors d'extraction des données \n" + ex.getMessage());
        }
        return messages;
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
}
