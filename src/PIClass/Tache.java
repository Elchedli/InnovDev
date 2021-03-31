/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIClass;

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

    public Tache(int id_tache, String username, boolean etat_tache, String difficulte_tache, String description_tache) {
        this.id_tache = id_tache;
        this.username = username;
        this.etat_tache = etat_tache;
        this.difficulte_tache = difficulte_tache;
        this.description_tache = description_tache;
    }

    public Tache(String username,String description_tache,String difficulte_tache) {
        this.username = username;
        this.description_tache = description_tache;
        this.difficulte_tache = difficulte_tache;
    }

    
    public int getId_tache() {
        return id_tache;
    }

    public void setId_tache(int id_tache) {
        this.id_tache = id_tache;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isEtat_tache() {
        return etat_tache;
    }

    public void setEtat_tache(boolean etat_tache) {
        this.etat_tache = etat_tache;
    }

    public String getDifficulte_tache() {
        return difficulte_tache;
    }

    public void setDifficulte_tache(String difficulte_tache) {
        this.difficulte_tache = difficulte_tache;
    }

    public String getDescription_tache() {
        return description_tache;
    }

    public void setDescription_tache(String description_tache) {
        this.description_tache = description_tache;
    }
    
    
}
