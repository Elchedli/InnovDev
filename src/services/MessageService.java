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
import PIClass.Discussion;
import PIClass.Message;
import PIClass.userclient;
import PIClass.user;
import PIUtils.MyConnection;

/**
 *
 * @author shidono
 */
public class MessageService{
    Connection cnx = MyConnection.getInstance().getCnx();
     public void AjouterMessage(String mess,int id){
         try {
            String requete = "INSERT INTO message VALUES (null,?,?,?,default)";
//            String requete = "INSERT INTO evenement VALUES (null,?,?,?,CONVERT(?, DATE),CONVERT(?, DATE),CONVERT(?, TIME),CONVERT(?, TIME),?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1,mess);
            pst.setInt(2,id);
            pst.setString(3,userclient.getUsername());
            pst.executeUpdate();
            System.out.println("Message ajoutée !");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la création de la discussion \n " + ex.getMessage());
        }
    }
    
    public void SupprimerMessage(int id){
        try {
            String requete = "DELETE FROM message WHERE id_msg=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1,id);
            pst.executeUpdate();
            System.out.println("Message supprimée !");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression de la discussion \n " + ex.getMessage());
        }
    }
    
    public void RafraichirMessage(int id){
        
    }
    
}
