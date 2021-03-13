/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import models.Tache;
import utils.DataSource;

/**
 *
 * @author shidono
 */
public class TacheService implements CRUD{
    Connection cnx = DataSource.getInstance().getCnx();
    
    public void ajouterSuivi(Tache task) {
      try {
            String requete = "INSERT INTO suivi VALUES (null,?,DEFAULT,?,?)";
//            String requete = "INSERT INTO evenement VALUES (null,?,?,?,CONVERT(?, DATE),CONVERT(?, DATE),CONVERT(?, TIME),CONVERT(?, TIME),?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1,task.getUsername());
            pst.setString(2,task.getDifficulte_tache());
            pst.setString(2,task.getDescription_tache()); 
            pst.executeUpdate();
            System.out.println("Tache crée pour l'utilisateur : "+task.getUsername());
        } catch (SQLException ex) {
            System.out.println("erreur lors de la création la tache \n " + ex.getMessage());
        }
    }
    
    public void modifierUneTache(int id){
        try {
            String requete = "UPDATE tache set etat_tache= NOT etat_tache WHERE id_tache=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1,id);
            pst.executeUpdate();
            System.out.println("task updated for id : "+id);
//            Execution d'un code spécifique pour le changement en cours de la modification
        } catch (SQLException ex) {
            System.out.println("erreur lors de la modification de la tache \n" + ex.getMessage());
        }
    }

    public void modifierNomTache(int id,String nom){
        try {
            String requete = "UPDATE tache set description_tache=? WHERE id_tache=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1,nom);
            pst.setInt(2,id);
            pst.executeUpdate();
            System.out.println("task name updated for id : "+id);
//            Execution d'un code spécifique pour le changement en cours de la modification
        } catch (SQLException ex) {
            System.out.println("erreur lors de la modidification du nom de la tache \n" + ex.getMessage());
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
