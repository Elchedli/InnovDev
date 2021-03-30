/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIServices;

import PIClass.Ev;
import PIServices.IservicesSalma;
import PIClass.Participation;
import PIClass.userclient;
import PIUtils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASuS
 */

public class ServiceParticipation implements IservicesSalma <Participation>{
    
    Connection cnx = MyConnection.getInstance().getCnx();
    
    @Override
    public void envoyer(Participation p){
        try{
            String ajout = "INSERT INTO Participation (id_user, id_event, username) VALUES (?,?,?)";
            PreparedStatement st1 = cnx.prepareStatement(ajout);
            st1.setInt(1,p.getId_user());
            st1.setInt(2,p.getId_event());
            st1.setString(3,p.getUsername());
            st1.executeUpdate();
            calculNbrPar(p.getId_event());
            System.out.println("Participant ajouté");
        } catch (SQLException ex){
            System.err.println(ex.getMessage());
            try {
                String requete = "DELETE FROM Participation where username=?";
                PreparedStatement st;
                st = cnx.prepareStatement(requete);
                st.setString(1,p.getUsername());
                st.executeUpdate();
                System.out.println("Participation annulée");
            } catch (SQLException ex1) {
                System.out.println("c'est pas logique");
                System.err.println(ex.getMessage());
            }
            
        }
    }  
    
    @Override
    public void supprimer(Participation p) {
        try {
            String supp = "DELETE FROM Participation WHERE id_par = ?";
            PreparedStatement st2 = cnx.prepareStatement(supp);
            st2.setInt(1, p.getId_par());
            st2.executeUpdate();
            System.out.println("Participant supprimé");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    @Override
    public List <Participation> afficher() {
        List <Participation> l2 = new ArrayList<>();
        try {
            String aff = "SELECT * FROM Participation";
            PreparedStatement st3 = cnx.prepareStatement(aff);
            ResultSet rs2 = st3.executeQuery();
            while (rs2.next()) {
                l2.add(new Participation(rs2.getInt(1),rs2.getInt(2),rs2.getInt(3),rs2.getInt(4), rs2.getString(5),rs2.getString(6)));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return l2;
    }
    
    public void calculNbrPar(int p) {
        try {
            String cal = "UPDATE Participation set nbr_par=nbr_par+1 where id_event=?";
            PreparedStatement st = cnx.prepareStatement(cal);
            st.setInt(1, p);
            st.executeUpdate();
            System.out.println("incrementer avec succés");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("erreur");
        }
    }

    @Override
    public void modifier(Participation p) {
        
    }
    
    public int getUserBase(){
        try {
            String cal = "SELECT id_user FROM simple WHERE username = ?";
            PreparedStatement st = cnx.prepareStatement(cal);
            st.setString(1, userclient.getUsername());
            ResultSet rs = st.executeQuery();
            if(rs.next()) return rs.getInt(1);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return -1;
        }
        return -1;
    }
    
    public void AjouterParticipation(Ev event){
         Participation newpart = new Participation(getUserBase(),event.getId_ev(),userclient.getUsername());
         envoyer(newpart);
    }
    
    public int CountPar(int event) throws SQLException{
        String cal = "select count(*) from participation where id_event=?";
        PreparedStatement st;
        st = cnx.prepareStatement(cal);
        st.setInt(1, event);
        ResultSet rs = st.executeQuery();
        if(rs.next()) return rs.getInt(1);
        return -1;
    }
}

    
   
       
