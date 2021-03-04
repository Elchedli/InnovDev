/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.util.Scanner;
import models.Discussion;
import services.DiscussionService;

/**
 *
 * @author shidono
 */
public class mainProg {
    
     public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choix,id,id2;
        DiscussionService dservice = new DiscussionService();
        while(true){
            System.out.println("----------------------------");
            System.out.println("[1]-Ajouter une discussion");
            System.out.println("[2]-Supprimer une discussion");
            System.out.println("[3]-change le status de la vue [true] | [false]");
            System.out.println("[4]-Afficher les messages dans une discussion");
            System.out.println("----------------------------");
            
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
                    dservice.VerifierVue(id);
                break;
                default:
            }  
        }
    }
}
