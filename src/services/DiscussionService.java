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
public class DiscussionService {
    Connection cnx = DataSource.getInstance().getCnx();
    public void creerDiscussion(Discussion disc){
         try {
            String requete = "INSERT INTO `discussion` VALUES (null,default,?,?,default)";
//            String requete = "INSERT INTO evenement VALUES (null,?,?,?,CONVERT(?, DATE),CONVERT(?, DATE),CONVERT(?, TIME),CONVERT(?, TIME),?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1,disc.getidsou());
            pst.setInt(2,disc.getiddes());
            pst.executeUpdate();
            System.out.println("Discussion crée !");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la création de la discussion \n " + ex.getMessage());
        }
    }
    
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
    
    public ArrayList<Message> AfficherMessageDiscussion(){
        ArrayList<Message> messages = new ArrayList<>();
        try {
            String requete = "Select * from evenement";
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
