/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIServices;
import PIClass.Categories;
import PIUtils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASuS
 */
public class ServiceCategories{
    Connection cnx = MyConnection.getInstance().getCnx();
    public void ajout(Categories p) {
        try {
            String req = "INSERT INTO Categories (id_cat, nom_cat) VALUES  (?,?)";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, p.getId_cat());
            pst.setString(2, p.getNom_cat());
            pst.executeUpdate();
            System.out.println("Catégorie ajoutée avec succès !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void supprimer(Categories p) {
     try {
            String req = "DELETE FROM Categories WHERE nom_cat = ?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, p.getNom_cat());
            pst.executeUpdate();
            System.out.println("Catégorie supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }    
    }

    public void suppid(int p) {
    try {
            String req = "DELETE FROM Categories WHERE id_cat = ?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, p);
            pst.executeUpdate();
            System.out.println("Catégorie " + p +":" + " est supprimée !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }    
    }
    
    public List<Categories> afficher() {
       List<Categories> l = new ArrayList<>();
        try {
            String req = "SELECT nom_cat FROM Categories";
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery(req);
            while (rs.next()) {
                l.add(new Categories(rs.getString(1)));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return l;
    }

    public void modifier(Categories p) {
        try {
            String req = "UPDATE Categories SET nom_cat = ? WHERE id_cat = ?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, p.getNom_cat());
            pst.setInt(2, p.getId_cat());
            pst.executeUpdate();
            System.out.println("Catégorie " + p.getId_cat()+" :" + " est modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } 
    }


    
}
