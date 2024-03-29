/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIServices;

import PIClass.Article;
import PIUtils.MyConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ASUS
 */
public class ServiceArticle implements IserviceY<Article>{
    Connection cn = MyConnection.getTest().getCnx();

    @Override
    //CRUD AJOUTER ARTICLE
    public void ajouter(Article t) {
        try {
//            String req = "INSERT INTO article (titre_art,auteur_art,date_art,description_art,vues,id_cat) VALUES  (?,?,?,?,?,?)";
            System.out.println(t);
            String req = "INSERT INTO articles VALUES  (null,?,?,?,current_timestamp,DEFAULT,(select id_cat from categorie where titre_cat=?),?)";
            PreparedStatement pst = cn.prepareStatement(req);
            pst.setString(1, t.getTitre_art());
            pst.setString(2, t.getAuteur_art());
            pst.setString(3, t.getDescription_art());
//            pst.setDate(3, t.getDate_art());
//            pst.setInt(5, t.getLikes());
            pst.setString(4, t.getNomcat());
            pst.setString(5, t.getPhoto());
            pst.executeUpdate();
            System.out.println("Article ajouté !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
////CRUD SUPPRIMER ARTICLE
    @Override
    public void supprimer(Article t) {
        try {
            String req = "DELETE FROM articles WHERE titre_art=?";
            PreparedStatement pst = cn.prepareStatement(req);
            pst.setString(1, t.getTitre_art());
            pst.executeUpdate();
            System.out.println("Article supprimé !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    

//    //CRUD SUPPRIMER ARTICLE PAR ID
   
    public void supprimerArticleById(Article t,int id_art) {
        try {
            String req = "DELETE FROM articles WHERE id_art=?";
            PreparedStatement pst = cn.prepareStatement(req);
            pst.setInt(1, id_art);
            pst.executeUpdate();
             System.out.println("Article_id supprimé !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

////CRUD MODIFIER ARTICLE
    @Override
    public void modifier(Article t) {
        try {
            String req = "UPDATE articles SET titre_art=?,description_art=?  WHERE id_art=?";
            PreparedStatement pst = cn.prepareStatement(req);
            pst.setString(1, t.getTitre_art());
            pst.setString(2, t.getDescription_art());
            pst.setInt(3, t.getId_art());

            pst.executeUpdate();
            System.out.println("Article_id " + t.getId_art()+":" + " modifié !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
   
//    public void modifier(Article t) {
//        try {
//            String req = "UPDATE article SET titre_art=?,description_art=?  WHERE id_art=?";
//            PreparedStatement pst = cnx.prepareStatement(req);
//            pst.setString(1, t.getTitre_art());
//            pst.setString(2, t.getDescription_art());
//            pst.setInt(3, t.getId_art());
//
//            pst.executeUpdate();
//            System.out.println("Article_id modifié !"+ t.getId_art());
//
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//        }
//    }
//
//    //CRUD AFFICHER LA LISTE DE TOUTES LES ARTICLES
    @Override
    public ObservableList<Article> afficher() {
        ObservableList<Article> list = FXCollections.observableArrayList();
      

        try {
            //String requete = "SELECT * FROM article";
            String requete="select a.id_art, a.titre_art,a.auteur_art,a.description_art,a.date_art,a.likes,a.photo,cat.titre_cat from articles a, categorie cat where (a.id_cat= cat.id_cat)";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(requete);
           
            while (rs.next()) {
               // list.add(new Article(rs.getInt(1),rs.getString(2), rs.getString(2), rs.getDate(3), rs.getString(4),rs.getInt(5), rs.getString(6), rs.getString(7)));
               list.add(new Article(rs.getInt(1),rs.getString(2),rs.getString(3) ,  rs.getString(4), rs.getDate(5),rs.getInt(6),rs.getString(7),rs.getString(8)));
               
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

//    //CRUD AFFICHER LISTE DES ARTICLES SELON CATEGORIE
    
    public ArrayList<Article> afficherArticleByCategorie(String x) {
        ArrayList<Article> listCat = new ArrayList<>();
        try {
            
            //String req = "select * from article a, categorie cat where (a.id_cat= cat.id_cat and cat.titre_cat='"+x+"')";
             String req ="select a.id_art, a.titre_art,a.auteur_art,a.description_art,a.date_art,a.likes,a.photo,cat.titre_cat from articles a, categorie cat where (a.id_cat= cat.id_cat and cat.titre_cat='"+x+"')";
            PreparedStatement st = cn.prepareStatement(req);
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
              listCat.add(new Article(rs.getInt(1),rs.getString(2),rs.getString(3) ,  rs.getString(4), rs.getDate(5),rs.getInt(6),rs.getString(7),rs.getString(8)));
             
            }
 
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        if (listCat.isEmpty()) {
            System.out.println("Il y a aucun article de cette categorie");
        }

        return listCat;
    }

//    //METIER : FAIRE DES LIKES (REACTIONS)
    
    public void Likes(Article t) {
        try {
            String req = "UPDATE articles SET likes=likes+1 WHERE id_art=?";
            PreparedStatement pst1 = cn.prepareStatement(req);
            pst1.setInt(1, t.getId_art());
            pst1.executeUpdate();
            req = "SELECT likes FROM articles WHERE id_art=?";
            PreparedStatement pst = cn.prepareStatement(req);
            pst.setInt(1, t.getId_art());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println("l'article_id : " + t.getId_art() + " , Réaction_Likes = " + rs.getInt(1) );
                
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }
    
////    public void likes(Article p) throws SQLException{
////       
////         String modreac = "UPDATE article SET vues = vues +1 WHERE id_art = ?";
////            PreparedStatement st3 = cnx.prepareStatement(modreac);
////            st3.setInt(1, p.getId_art());
////            st3.executeUpdate();
////            System.out.println("Reaction ajoutée");
////        JOptionPane.showMessageDialog(null,"Yuppie !!");
////
////        
////    }
//    
// //METIER : chercher un article à une date saisie  
    
    public List<Article> chercherArticleByDate(Date d) {
        ArrayList<Article> listArtDate = new ArrayList<>();
        try {
            String req = "Select * from articles where date_art=?";
            PreparedStatement st = cn.prepareStatement(req);
            st.setDate(1, d);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Article a = new Article();
                a.setId_art(rs.getInt(1));
                a.setTitre_art(rs.getString(2));
                a.setAuteur_art(rs.getString(3));
                a.setDate_art(rs.getDate(4));
                a.setDescription_art(rs.getString(5));
                a.setLikes(rs.getInt(6));
                if (a.getDate_art().compareTo(d)<=0){
                    listArtDate.add(a);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if (listArtDate.isEmpty()) {
            System.out.println("Il y a aucun article dans cette date");
        }
        return listArtDate;
    }

    //comparator Date
    public static Comparator<Article> ArticleComparatorDate = (Article s1, Article s2) -> {
        Date d1 = s1.getDate_art();
        Date d2 = s2.getDate_art();
        return d1.compareTo(d2);
    };

//    //METIER : trier les articles selon date 
  
    public List<Article> trierArticleByDate() {
        ArrayList<Article> listArt = new ArrayList<>();
        try {
            String req="select a.id_art, a.titre_art,a.auteur_art,a.description_art,a.date_art,a.likes,a.photo,cat.titre_cat from articles a, categorie cat where (a.id_cat= cat.id_cat)";

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(req);
            Article a =null;
            while (rs.next()) {
                //listArt.add(new Article(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5), rs.getInt(6),rs.getInt(7)));
            a = (new Article(rs.getInt(1),rs.getString(2),rs.getString(3) ,  rs.getString(4), rs.getDate(5),rs.getInt(6),rs.getString(7),rs.getString(8)));
            listArt.add(a);
            }
           
            Collections.sort(listArt, ArticleComparatorDate);
            Collections.reverse(listArt);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listArt;
    }
   
//    //METIER : Calculer le nombre de photos dans un article
//  
//    public int nombrePHoto(int t) {
//        
//        ArrayList<Photo> listphoto = new ArrayList<>();
//        try {
//            
//            
//            String req = "select * from photo_articles where id_art="+t;
//            PreparedStatement st = cnx.prepareStatement(req);
//
//            ResultSet rs = st.executeQuery(req);
//            while (rs.next()) {
//               listphoto.add(new Photo(rs.getInt(1), rs.getInt(2), rs.getString(3)));
//               
//            }
//
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//            
//        }
//        if (listphoto.isEmpty()) {
//            System.out.println("Il y a aucun photo dans cet article");
//        }
//        
//        return listphoto.size();
//    }

    
    

  
    

}
