/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;

/**
 *
 * @author shidono
 */
public class Discussion {
    private int id_disc;
    private Date datetemps_disc;
    private String nom_source;
    private String nom_destinaire;
    private int vue_disc;

    public Discussion(int h,Date s,String a,String b){
        id_disc = h;
        datetemps_disc = s;
        nom_source = a;
        nom_destinaire = b;
        vue_disc = 0;
    }

    public Discussion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int getId_disc() {
        return id_disc;
    }

    public Date getDatetemps_disc() {
        return datetemps_disc;
    }

    public String getNom_source() {
        return nom_source;
    }

    public String getNom_destinaire() {
        return nom_destinaire;
    }

    public int getVue_disc() {
        return vue_disc;
    }

    
    
}
