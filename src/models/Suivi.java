/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author shidono
 */
public class Suivi {
    private int id_s;
    private String titre_s;
    private Date date_ds;
    private Date date_fs;
    private Time temps_ds;
    private Time temps_fs;

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
    
    
}
