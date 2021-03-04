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
public class Message {
    private int id_msg;
    private String contenu_msg;
    private int id_disc;
    
    public Message(String a,int b){
        contenu_msg = a;
        id_disc = b;
    }
    
    public String getContenu_msg() {
        return contenu_msg;s
    }

    public int getId_disc() {
        return id_disc;
    }
}
