/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author HP I5
 */

public class PI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String link = "https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/d8bdecef-13ee-40da-aeac-dc56ccb3df3f/de4ww3r-66cd9341-9c2b-4e8a-8377-7a41f4d36641.png/v1/fill/w_1063,h_752,q_70,strp/curious_hunger_by_fantassticks_de4ww3r-pre.jpg?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOiIsImlzcyI6InVybjphcHA6Iiwib2JqIjpbW3siaGVpZ2h0IjoiPD05MDUiLCJwYXRoIjoiXC9mXC9kOGJkZWNlZi0xM2VlLTQwZGEtYWVhYy1kYzU2Y2NiM2RmM2ZcL2RlNHd3M3ItNjZjZDkzNDEtOWMyYi00ZThhLTgzNzctN2E0MWY0ZDM2NjQxLnBuZyIsIndpZHRoIjoiPD0xMjgwIn1dXSwiYXVkIjpbInVybjpzZXJ2aWNlOmltYWdlLm9wZXJhdGlvbnMiXX0.wWZfczFl4qDDockOp6AGWdiGEZ0-yPlmNVnAF8OUzzo";
        ConnectionDB cnx = new ConnectionDB();
        Publication empty_pub = new Publication();
        if (cnx.establish_conn())
        {
            boolean exit = false;
            while(exit == false)
            {
                System.out.println("1- Afficher Tout les Publications ");
                System.out.println("2- Ajouter une Publication");
                System.out.println("3- Modifer une Publication ");
                System.out.println("4- Supprimer une modification");
                System.out.println("5- Afficher les Mentions");
                System.out.println("6- Afficher les HashTags dans les publications");
                System.out.println("0- Exit\n");
                System.out.print("> ");
                Scanner sc=new Scanner(System.in);
                int choice = sc.nextInt();
                ArrayList<String> ids = new ArrayList<String>();
                switch(choice)
                {
                    case 1:
                       /* Publication p = new Publication(1,0,"");
                        p.select_all(cnx);
                        */
                        
                        break;
                    case 2:
                       /* Publication pub1 = new Publication(1,"Bonjour @Anas #Morning");
                        //pub1.Check_hashtags(pub1.getText());
                        pub1.insert(cnx);
                        int id_pub = Integer.parseInt(pub1.select_last(cnx).get(0));
                        Photo ph = new Photo(id_pub,link);
                        ph.insert_photo(cnx);*/
                        
                      /* Publication pub4 = new Publication();
                       pub4 = Publication.saisir(1);
                      //System.out.println( pub4.toString());
                       pub4.insert(cnx);
                       //System.out.println(pub4.getId());
                       Tag.add_relation(pub4, cnx);*/
                    
                       
                        
                        
                        break;
                    case 3:
                       /* Publication pub3 = new Publication(0,1,0,"","");
                        ids = pub3.select_all(cnx);
                        System.out.println("Entre numero de la publication a changer :");
                        Scanner sc1= new Scanner(System.in);
                        int id = sc1.nextInt();
                        int real_id = Integer.parseInt(ids.get(id));
                        pub3.setId(real_id);
                        System.out.println("Entre Le nouveau texte de la publication:");
                        String text = sc1.nextLine();
                        text = sc1.nextLine();
                        pub3.setText(text);
                        pub3.update(cnx);*/
                        break;
                    case 4:
                       /* ids = empty_pub.select_all(cnx);
                        System.out.println("Entre numero de la publication a supprimer :");
                        Scanner sc2= new Scanner(System.in);
                        int id1 = sc2.nextInt();
                        int real_id2 = Integer.parseInt(ids.get(id1)); 
                        Publication pub2 = new Publication(real_id2);
                        pub2.delete(cnx);
                        */
                        break;
                    case 5:
                       /* Publication.Select_mentions(cnx);*/
                        break;
                    case 6:
                       /* Publication.Select_hashtags(cnx);
                       // Publication pub2 = new Publication(1,"Bonjour ! @Salama #Mala");
                       // pub2.Check_hashtags("Bonjour ! @Salama #Wow!");*/
                        break;
                    case 7:
                       //Tag t = new Tag(3,"Morning");
                       //t.insert(cnx);
                       // t.delete(cnx);
                       // t.setName("Hello");
                       // t.update(cnx);
                       //t.select_all(cnx);

                        break;
                    case 0:
                        exit = true;
                        break;       
                }
            }
        }
    }
    
}
