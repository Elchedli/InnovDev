/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spirity.tests;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import models.Discussion;
import models.Message;
import services.DiscussionService;
import services.MessageService;

/**
 *
 * @author shidono
 */
public class mainProg {
    
     public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choix,id,id2;
        String message;
        DiscussionService dservice = new DiscussionService();
        MessageService messerv = new MessageService();
        System.out.println(Time.valueOf(LocalTime.parse("23:30")));
        while(true){
            System.out.println("[0]-Exit");
            System.out.println("----------------------------");
            System.out.println("[1]-Ajouter une discussion");
            System.out.println("[2]-Supprimer une discussion");
            System.out.println("[3]-change le status de la vue [true] | [false]");
            System.out.println("[4]-Afficher les messages dans une discussion");
            System.out.println("----------------------------");
            System.out.println("[5]-Ajouter un message");
             System.out.println("[6]-Supprimer message par id");
            System.out.println("donner votre choix : ");
            choix = sc.nextInt();
            switch(choix){
                case 1:
                    System.out.println("Donner l'id de la createur de la discussion : ");
                    id = sc.nextInt();
                    System.out.println("Donner l'id du destinaire : ");
                    id2 = sc.nextInt();
                  Discussion disc = new Discussion(id,id2);
                   dservice.creerDiscussion(disc);
                break;
                case 2:
                    System.out.println("Donner l'id de la discussion : ");
                    id = sc.nextInt();
                   dservice.SupprimerDiscussion(id);
                break;
                case 3:
                     System.out.println("Donner l'id de la discussion : ");
                    id = sc.nextInt();
                    dservice.VerifierVue(id);
                break;
                case 4:
                    System.out.println("Donner l'id de la discussion : ");
                    id = sc.nextInt();
                    ArrayList<Message> mess = dservice.AfficherMessageDiscussion(id);
                    mess.forEach(System.out::println);
                break;
                case 5:
                    System.out.println("Donner le contenu du message : ");
                    message = sc.next();
                    System.out.println("Donner l'id de la discussion : ");
                    id = sc.nextInt();
                    messerv.AjouterMessage(new Message(message,id));
                break;
                case 6:
                    System.out.println("Donner l'id du message : ");
                    id = sc.nextInt();
                    messerv.SupprimerMessage(id);
                break;
                default:
            }  
        }
    }
}
