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
public class Message {
    private int id_msg;
    private String contenu_msg;
    private Date date_msg;
    private int id_disc;
    
    public Message(String a,int b){
        contenu_msg = a;
        id_disc = b;
    }
    
    public Message(String a,Date date){
        contenu_msg = a;
        date_msg = date;
    }

    public Message() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public String getContenu_msg() {
        return contenu_msg;
    }

    public int getId_disc() {
        return id_disc;
    }

    @Override
    public String toString() {
        return "Message{" + "contenu_msg=" + contenu_msg + ", date_msg=" + date_msg + '}';
    }
    
    
}
