/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIServices;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author Firas
 * @param <r>
 */
public interface IRec <p> {
    
    //ajouter reclamation/categorie
    public void ajout(p p);
    
    //modifier categorie
    public void modifier(p p);
    
    //supprimer reclamation/categorie
    public void supprimer(p p);
    
    
    //afficher toutes les reclamations/categories
    public List<p> afficher();
    
    
}