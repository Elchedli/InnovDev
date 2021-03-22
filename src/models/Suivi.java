/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;
import java.sql.Time;
import javafx.scene.control.TextField;

/**
 *
 * @author shidono
 */
public class Suivi {
    private Integer id_s;
    private String client;
    private String titre_s;
    private Date date_ds;
    private Date date_fs;
    private Time temps_ds;
    private Time temps_fs;

    public void setClient(String client) {
        this.client = client;
    }

    public void setTitre_s(String titre_s) {
        this.titre_s = titre_s;
    }

    public void setDate_ds(Date date_ds) {
        this.date_ds = date_ds;
    }

    public void setDate_fs(Date date_fs) {
        this.date_fs = date_fs;
    }

    public void setTemps_ds(Time temps_ds) {
        this.temps_ds = temps_ds;
    }

    public void setTemps_fs(Time temps_fs) {
        this.temps_fs = temps_fs;
    }

    public Suivi(int id, String titre, Date debut, Date fin, Time timedeb, Time timefin) {
        this.id_s = id;
        this.titre_s = titre;
        this.date_ds = debut;
        this.date_fs = fin;
        this.temps_ds = timedeb;
        this.temps_fs = timefin;
    }

    public Suivi(String client,String titre, Date datedeb, Date datefin, Time timedeb, Time timefin) {
        this.client = client;
        this.titre_s = titre;
        this.date_ds = datedeb;
        this.date_fs = datefin;
        this.temps_ds = timedeb;
        this.temps_fs = timefin;
    }

    public Suivi(int id,String user, String titre, Date debut, Date fin, Time timedeb, Time timefin) {
        this.id_s = id;
        this.client = user;
        this.titre_s = titre;
        this.date_ds = debut;
        this.date_fs = fin;
        this.temps_ds = timedeb;
        this.temps_fs = timefin;
    }

//    public Suivi(String titre,Date ){
//        
//    }
    
    public Integer getId_s() {
        return id_s;
    }

    public String getClient() {
        return client;
    }
    
    public String getTitre_s() {
        return titre_s;
    }

    public Date getDate_ds() {
        return date_ds;
    }

    public Date getDate_fs() {
        return date_fs;
    }

    public Time getTemps_ds() {
        return temps_ds;
    }

    public Time getTemps_fs() {
        return temps_fs;
    }

    @Override
    public String toString() {
        return "Suivi{" + "id_s=" + id_s + ", user=" + client + ", titre_s=" + titre_s + ", date_ds=" + date_ds + ", date_fs=" + date_fs + ", temps_ds=" + temps_ds + ", temps_fs=" + temps_fs + '}';
    }
    
    
}
