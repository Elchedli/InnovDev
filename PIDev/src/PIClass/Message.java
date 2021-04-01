/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIClass;

import java.sql.Date;

/**
 *
 * @author shidono
 */
public class Message {
    private int id_disc;
    private String contenu_msg;
    private String sender;
    private Date date_msg;

    public String getSender() {
        return sender;
    }

    public Date getDate_msg() {
        return date_msg;
    }
    
    public Message(int id,String contenu,String sender,Date date){
        id_disc = id;
        contenu_msg = contenu;
        this.sender = sender;
        date_msg = date;
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
