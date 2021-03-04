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
    private int id_source;
    private int id_destinaire;
    private int vue_disc;

    public Discussion(int a,int b){
        id_source = a;
        id_destinaire = b;
        vue_disc = 0;
    }
    public int getId_disc() {
        return id_disc;
    }

    public Date getDatetemps_disc() {
        return datetemps_disc;
    }

    public int getidsou() {
        return id_source;
    }
    
     public int getiddes(){
        return id_destinaire;
    }

    public int getVue_disc() {
        return vue_disc;
    }
    
}
