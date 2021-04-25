/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIServices;
import PIClass.Reclamation;
import PIClass.userclient;
import PIUtils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
/**
 *
 * @author ASuS
 */
public class ServiceReclamation implements IRec <Reclamation> {
    Connection cnx = MyConnection.getInstance().getCnx();
    @Override
    public void ajout(Reclamation p) {
       try {
            String envoi = "INSERT INTO reclamation VALUES (null,(select id_cat from categories where nom_cat=?),?,?,?,'To do',current_timestamp)";
            PreparedStatement st1 = cnx.prepareStatement(envoi);
            st1.setString(1,p.getNom_cat());
            st1.setString(2,userclient.getUsername());
            st1.setString(3,p.getObj_rec());
            st1.setString(4,p.getSuj_rec());
            st1.executeUpdate();
            System.out.println("Reclamation envoyée avec succès !");
        } catch (SQLException ex) {
            System.err.println("Erreur lors de l'envoi " + ex.getMessage());
        }
    }

    @Override
    public void supprimer(Reclamation p) {
        try {
            String supp = "DELETE FROM Reclamation WHERE id_rec = ?";
            PreparedStatement pst = cnx.prepareStatement(supp);
            pst.setInt(1, p.getId_rec());
            pst.executeUpdate();
            System.out.println("Reclamation supprimée avec succès !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void suppetat(Reclamation p) {
        try {
            String supp = "DELETE FROM Reclamation WHERE etat_rec LIKE 'Done' ";
            PreparedStatement st2 = cnx.prepareStatement(supp);
            st2.executeUpdate();
            System.out.println("Reclamation supprimée avec succès !");
        } catch (SQLException ex) {
            System.err.println("Erreur lors de la suppression " + ex.getMessage());
        }
    }
    
    public void suppid(int p) {
        try {
            String supp = "DELETE FROM Reclamation WHERE id_rec = ? ";
            PreparedStatement st2 = cnx.prepareStatement(supp);
            st2.setInt(1, p);
            st2.executeUpdate();
            System.out.println("Reclamation supprimée avec succès !");
        } catch (SQLException ex) {
            System.err.println("Erreur lors de la suppression " + ex.getMessage());
        }
    }
    
    public void etatRec(Reclamation p) {
        try {
            String m = "Done";
            String req = "UPDATE reclamation SET etat_rec =? WHERE id_rec =?";
            System.out.println("est ce que c'est la");
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, m);
            st.setInt(2,p.getId_rec());
            st.executeUpdate();
            System.out.println("Etat changé avec succès !");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la modification " + ex.getMessage());
        }
    }

    @Override
    public List<Reclamation> afficher() {
        List<Reclamation> l1 = new ArrayList<>();
        try {
            String aff = "select r.id_rec, r.username, r.obj_rec, r.suj_rec, r.etat_rec, r.date_rec, cat.nom_cat from reclamation r, categories cat where (r.id_cat = cat.id_cat)";
            PreparedStatement st3 = cnx.prepareStatement(aff);
            ResultSet rs1 = st3.executeQuery();
            while (rs1.next()) {
                Reclamation rec = new Reclamation();
                rec.setId_rec(rs1.getInt(1));
                rec.setUsername(rs1.getString(2));
                rec.setObj_rec(rs1.getString(3));
                rec.setSuj_rec(rs1.getString(4));
                rec.setEtat_rec(rs1.getString(5));
                rec.setDate_rec(rs1.getTimestamp(6));
                rec.setNom_cat(rs1.getString(7));
                l1.add(rec);
            }
        } catch (SQLException ex) {
            System.err.println("Erreur lors du chargement " + ex.getMessage());
        }
        return l1;
    }
    
     public ArrayList<Reclamation> afficherCat(String x) {
        ArrayList<Reclamation> lCat = new ArrayList<>();
        try {
            String req ="SELECT r.id_rec, r.username, r.obj_rec, r.mail_rec, r.area_rec, r.suj_rec, r.etat_rec, r.date_rec, cat.nom_cat FROM Reclamation r, Categories cat WHERE(r.id_cat= cat.id_cat and cat.nom_cat='"+x+"')";
            PreparedStatement st = cnx.prepareStatement(req);
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
              //lCat.add(new Reclamation(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5),rs.getInt(6),rs.getString(7),rs.getString(8)));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        if (lCat.isEmpty()) {
            System.out.println("Il y a aucune réclamation dans cette catégorie");
        }
        return lCat;
    }
     
    public List<Reclamation> afficherRecUser() {
        List<Reclamation> l5 = new ArrayList<>();
        try {
            String aff = "select r.id_rec, r.username, r.obj_rec, r.suj_rec, r.etat_rec, r.date_rec, cat.nom_cat from reclamation r, categories cat where (r.id_cat= cat.id_cat) and (username = ?)";
            PreparedStatement st3 = cnx.prepareStatement(aff);
            st3.setString(1, userclient.getUsername());
            ResultSet rs1 = st3.executeQuery();
            while (rs1.next()) {
                Reclamation rec = new Reclamation();
                rec.setId_rec(rs1.getInt(1));
                rec.setUsername(rs1.getString(2));
                rec.setObj_rec(rs1.getString(3));
                rec.setSuj_rec(rs1.getString(4));
                rec.setEtat_rec(rs1.getString(5));
                rec.setDate_rec(rs1.getTimestamp(6));
                rec.setNom_cat(rs1.getString(7));
                l5.add(rec);
            }
        } catch (SQLException ex) {
            System.err.println("Erreur lors du chargement " + ex.getMessage());
        }
        return l5;
    }
    
    
    public List<Reclamation> searchCatRec(String prob) {
        ArrayList<Reclamation> l2 = new ArrayList<>();
        try {
            String rech = "select id_rec, username, obj_rec, suj_rec, etat_rec, date_rec,(select id_cat from categories where nom_cat = ?) as cool from reclamation where id_cat = (select id_cat from categories where nom_cat = ?)";
            PreparedStatement st = cnx.prepareStatement(rech);
             st.setString(1,prob);
             st.setString(2,prob);
            ResultSet rs = st.executeQuery();
            while (rs.next()) 
            {
                Reclamation Rec = new Reclamation();
                Rec.setId_rec(rs.getInt(1));
                Rec.setNom_cat(prob);
                Rec.setUsername(rs.getString(2));
                Rec.setObj_rec(rs.getString(3));
                Rec.setSuj_rec(rs.getString(4));
                Rec.setEtat_rec(rs.getString(5));
                Rec.setDate_rec(rs.getTimestamp(6));
                Rec.setId_cat(rs.getInt(7));
                l2.add(Rec);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if (l2.isEmpty()) {
            System.out.println("Il y a aucun résultat ");
        }
        return l2;
    }
    
    public List <Reclamation> triDate(){ 
        List <Reclamation> l4 = new ArrayList<>();
        try {
            String tri = "select r.id_rec, r.username, r.obj_rec, r.suj_rec, r.etat_rec, r.date_rec, cat.nom_cat from reclamation r, categories cat where (r.id_cat = cat.id_cat)";
            PreparedStatement st6 = cnx.prepareStatement(tri);
            ResultSet rs2=st6.executeQuery();
            while (rs2.next()) {
                l4.add(new Reclamation(rs2.getInt("id_rec"), rs2.getString("nom_cat"), rs2.getString("username"), rs2.getString("obj_rec"),rs2.getString("suj_rec"), rs2.getString("etat_rec"),rs2.getTimestamp("date_rec")));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return l4.stream().sorted((a,b)->b.getDate_rec().compareTo(a.getDate_rec())).collect(Collectors.toList());
    }

    public int nbrRec() {
        int nb = 0;
        try {
            String req = "SELECT * FROM Reclamation";
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet res = pst.executeQuery(req);
            while (res.next()) {
                nb = nb + 1;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return nb; }

    @Override
    public void modifier(Reclamation p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}