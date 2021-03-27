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
public class MessageService implements CRUD{
    Connection cnx = DataSource.getInstance().getCnx();
     public void AjouterMessage(String mess,int id){
         try {
            String requete = "INSERT INTO message VALUES (null,?,?,?,default)";
//            String requete = "INSERT INTO evenement VALUES (null,?,?,?,CONVERT(?, DATE),CONVERT(?, DATE),CONVERT(?, TIME),CONVERT(?, TIME),?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1,mess);
            pst.setInt(2,id);
            pst.setString(3,user.getUsername());
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
