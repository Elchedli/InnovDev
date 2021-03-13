/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author shidono
 */
public class Tache {
    private int id_tache;
    private String username;
    private boolean etat_tache;
    private String difficulte_tache; // don't forget to change type
    private String description_tache;
    
    public boolean isEtat_tache() {
        return etat_tache;
    }

    public String getDifficulte_tache() {
        return difficulte_tache;
    }

    public String getDescription_tache() {
        return description_tache;
    }
    
     public String getUsername() {
        return username;
    }
}
