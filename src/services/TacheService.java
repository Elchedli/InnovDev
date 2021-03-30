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
import PIClass.Tache;
import PIUtils.MyConnection;

/**
 *
 * @author shidono
 */
public class TacheService{
    Connection cnx = MyConnection.getInstance().getCnx();
    public void ajouterTache(Tache task){
      try {
            String requete = "INSERT INTO tache VALUES (null,?,DEFAULT,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1,task.getUsername());
            pst.setString(2,task.getDifficulte_tache());
            pst.setString(3,task.getDescription_tache()); 
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

    public void ModifierTache(int id,String nouvelle,String type){
        try {
            String requete;
            if(type.compareTo("desc") == 0) requete = "UPDATE tache set description_tache=? WHERE id_tache=?";
            else requete = "UPDATE tache set difficulte_tache=? WHERE id_tache=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1,nouvelle);
            pst.setInt(2,id);
            pst.executeUpdate();
            System.out.println("task updated for id : "+id);
        } catch (SQLException ex) {
            System.out.println("erreur lors de la modidification du nom de la tache \n" + ex.getMessage());
        }
    }
    
    public ArrayList<Tache> AfficherTaches(String client){
         ArrayList<Tache> TacheListe = new ArrayList<>();
        try {
            String requete = "select * from tache WHERE username=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1,client);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                TacheListe.add(new Tache(rs.getInt(1),rs.getString(2),rs.getBoolean(3),rs.getString(4),rs.getString(5)));
            }
            return TacheListe;
        } catch (SQLException ex) {
            System.out.println("Probleme affichage Suivi \n " +ex+"\n"+ex.getMessage());
            return null;
        }
    }
    
}
