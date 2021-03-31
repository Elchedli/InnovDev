/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import static java.time.Clock.system;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import PIClass.Discussion;
import PIClass.Message;
import PIClass.userclient;
import PIClass.user;
import org.controlsfx.control.Notifications;
import services.Client;
import services.DiscussionService;
import services.MessageService;
import services.NetworkConnection;
import services.Server;
import tweaks.outils;

/**
 * FXML Controller class
 *
 * @author shidono
 */


public class MessageController implements Initializable{
    
    @FXML
    private Pane user1;

    @FXML
    private AnchorPane mainpage;
    
    @FXML
    private JFXTextField discinput;
    
    @FXML
    private Pane chatroom;
    
    @FXML
    private ScrollPane scrollchatroom;
    
    @FXML
    private Label currentuser;
    
    @FXML
    private Pane leftpage;
     
    @FXML
    private Label discidentity;
    
    @FXML
    private Button closebutt;
    
     @FXML
    private AnchorPane pagemain;
     
    @FXML
    private JFXTextField mail_titre;

    @FXML
    private JFXTextArea mail_contenu;
    
    @FXML
    private FontAwesomeIconView icon_envoyer;
       
    DiscussionService discserv = new DiscussionService();
    MessageService messerv = new MessageService();
    private boolean exit = true;
    private final boolean isServer = userclient.getType().compareTo("psy") == 0;
    private final NetworkConnection connection = isServer ? createServer(): createCLient();
//    Stage stage = (Stage) chatroom.getScene().getWindow();
//    chatroom.sceneProperty().addListener((observableScene, oldScene, newScene) -> {
//        if (oldScene == null && newScene != null){
//            bgimage.fitWidthProperty().bind(newScene.widthProperty());     	    	
//        }
//    });
//     
    
    /**
     * Initializes the controller class.
     */
     public void ChangerPaneMessage(Discussion disc)  {
         discidentity.setText(""+disc.getId_disc());
//         System.out.println("id de la discussion : "+disc.getId_disc());
         double y = 31;
         chatroom.getChildren().clear();
         chatroom.setPrefHeight(447);
        chatroom.setStyle("-fx-background-color: #E9E9E9;");
        ArrayList<Message> messagess = discserv.AfficherMessageDiscussion(disc.getId_disc());
        if(messagess.isEmpty()){
            Label labelpasdemessage = new Label();
            labelpasdemessage.setLayoutX(150.0);
            labelpasdemessage.setLayoutY(178.0);
            labelpasdemessage.setPrefHeight(58.0);
            labelpasdemessage.setPrefWidth(457.0);
            labelpasdemessage.setText("Pas de message dans cette conversation");
            labelpasdemessage.setTextFill(javafx.scene.paint.Color.valueOf("#a19f9f"));
            labelpasdemessage.setFont(new Font(24.0));
            chatroom.getChildren().add(labelpasdemessage);
        }else{
//            System.out.println("this is the work");
//            System.out.println(messagess);
            for (Message mess : messagess) {
                if(userclient.getUsername().compareTo(mess.getSender()) == 0){
                    HBox incoming = new HBox();
                    Label messagecontent = new Label();
                    incoming.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
                    incoming.setLayoutX(20.0);
                    incoming.setLayoutY(y);
                    incoming.setPrefHeight(46.0);
                    incoming.setPrefWidth(300.0);
                    incoming.getStyleClass().add("incoming-bubble");
                    incoming.getStylesheets().add("/gui/../design/design.css");

                    messagecontent.setPrefHeight(45.0);
                    messagecontent.setPrefWidth(300.0);
                    messagecontent.setText(mess.getContenu_msg());
                    messagecontent.setTextFill(javafx.scene.paint.Color.WHITE);
                    messagecontent.setFont(new Font(20.0));
                    HBox.setMargin(messagecontent, new Insets(0.0, 0.0, 0.0, 10.0));

                    incoming.getChildren().add(messagecontent);
                    chatroom.getChildren().add(incoming);
                }else{
                    HBox outgoing = new HBox();
                    Label messagecontent = new Label();

                    outgoing.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
                    outgoing.setLayoutX(400.0);
                    outgoing.setLayoutY(y);
                    outgoing.setPrefHeight(46.0);
                    outgoing.setPrefWidth(300.0);
                    outgoing.getStyleClass().add("outgoing-bubble");
                    outgoing.getStylesheets().add("/gui/../design/design.css");
                    messagecontent.setPrefHeight(45.0);
                    messagecontent.setPrefWidth(300.0);
                    messagecontent.setText(mess.getContenu_msg());
                    messagecontent.setTextFill(javafx.scene.paint.Color.WHITE);
                    messagecontent.setFont(new Font(20.0));
                    HBox.setMargin(messagecontent, new Insets(0.0, 0.0, 0.0, 10.0));
                    outgoing.getChildren().add(messagecontent);
                    chatroom.getChildren().add(outgoing);
                }
                y+=50;
                if(y>440){
                    chatroom.setPrefHeight(chatroom.getPrefHeight()+50);
                }
            }
        } 
    }
    public Pane ajouterPaneContact(Discussion disc,double y){
         Circle circle = new Circle();
         Pane user1 = new Pane();
         Label labelname = new Label();
         Label labelastmess = new Label();
         Button buttcontact = new Button();
         labelname.setLayoutX(46.0);
        labelname.setLayoutY(14.0);
        labelname.setPrefHeight(27.0);
        labelname.setPrefWidth(133.0);
        if(userclient.getType().compareTo("psy") == 0) labelname.setText(disc.getNom_destinaire());
        else labelname.setText(disc.getNom_source());
        labelname.setTextFill(javafx.scene.paint.Color.WHITE);
        labelname.setFont(new Font("Arial Black", 18.0));
        labelastmess.setLayoutX(46.0);
        labelastmess.setLayoutY(41.0);
        labelastmess.setPrefHeight(17.0);
        labelastmess.setPrefWidth(126.0);
//        System.out.println("id disc : "+disc.getId_disc());
        labelastmess.setText(discserv.AfficherDernierMess(disc.getId_disc()));
        labelastmess.setTextFill(javafx.scene.paint.Color.valueOf("#5b5b5b"));
        circle.setFill(javafx.scene.paint.Color.valueOf("#08fc25"));
        circle.setLayoutX(25.0);
        circle.setLayoutY(36.0);
        circle.setRadius(5.0);
        circle.setStroke(javafx.scene.paint.Color.valueOf("#09fc36"));
        circle.setStrokeType(javafx.scene.shape.StrokeType.INSIDE);
        circle.setStrokeWidth(0.0);
        circle.setVisible(false);
        EventHandler<ActionEvent> buttonHandler = (ActionEvent event) -> {
            if(userclient.getType().compareTo("psy") == 0) currentuser.setText(disc.getNom_destinaire());
            else currentuser.setText(disc.getNom_source());
            ChangerPaneMessage(disc);
            mainpage.setVisible(true);
            discinput.setVisible(true);
            icon_envoyer.setVisible(true);
            try {
                connection.startConnection();
                System.out.println("started connexion");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
//                if(user.connected)
//                ((Button)event.getTarget()).setStyle("-fx-background-color: black;");
event.consume();
         };
        buttcontact.setLayoutY(1.0);
        buttcontact.setMnemonicParsing(false);
        buttcontact.setPrefHeight(72.0);
        buttcontact.setPrefWidth(227.0);
        buttcontact.setStyle("-fx-background-color: transparent;");
        buttcontact.setOnAction(buttonHandler);
        
        user1.setLayoutX(6.0);
        user1.setLayoutY(y);
        user1.setPrefHeight(72.0);
        user1.setPrefWidth(227.0);
        user1.getChildren().add(circle);
        user1.getChildren().add(labelname);
        user1.getChildren().add(labelastmess);
        user1.getChildren().add(buttcontact);
        return user1;
     }
    
    
    @FXML
    void AddMessage(ActionEvent event) {
//        System.out.println("Layout final is : "+chatroom.getChildren().get(chatroom.getChildren().size()-1).getLayoutY());
//        System.out.println("Value is : "+Integer.parseInt(discidentity.getText()));
//        System.out.println("Input value is : "+discinput.getText());
        messerv.AjouterMessage(discinput.getText(),Integer.parseInt(discidentity.getText()));
        HBox incoming = new HBox();
        Label messagecontent = new Label();
        incoming.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
        incoming.setLayoutX(20.0);
        if(chatroom.getChildren().get(0) instanceof Label){
            chatroom.getChildren().clear();
            incoming.setLayoutY(31.0);
        }else{
            incoming.setLayoutY(chatroom.getChildren().get(chatroom.getChildren().size()-1).getLayoutY()+50);
        }
        incoming.setPrefHeight(46.0);
        incoming.setPrefWidth(300.0);
        incoming.getStyleClass().add("incoming-bubble");
        incoming.getStylesheets().add("/gui/../design/design.css");
        messagecontent.setPrefHeight(45.0);
        messagecontent.setPrefWidth(300.0);
        messagecontent.setText(discinput.getText());
        try {
            System.out.println("sended message ");
            connection.send(discinput.getText());
        } catch (Exception ex) {
            System.out.println("Failed to connect");
        }
        discinput.clear();
        messagecontent.setTextFill(javafx.scene.paint.Color.WHITE);
        messagecontent.setFont(new Font(20.0));
        HBox.setMargin(messagecontent, new Insets(0.0, 0.0, 0.0, 10.0));
        incoming.getChildren().add(messagecontent);
        chatroom.getChildren().add(incoming);
        scrollchatroom.setVvalue(1.0);
        if(chatroom.getChildren().get(chatroom.getChildren().size()-1).getLayoutY()>400){
            chatroom.setPrefHeight(chatroom.getPrefHeight()+50);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        Timer timer = outils.setInterval(() -> System.out.println("bonjour"),2000);
        outils.setTimeout(() -> {
            chatroom.getScene().getWindow().setOnCloseRequest((WindowEvent ev) -> {
                try {
                    connection.closeConnection();
                } catch (Exception ex) {
                    System.out.println("nothing to close");
                }
                System.out.println("cool");
//                timer.cancel();
//                timer.purge();
                Platform.exit();
                ev.consume();
            });
        }, 1000);
        Pane contact = (Pane) leftpage.getChildren().get(1);
        leftpage.getChildren().get(1).setVisible(false);
        double y = contact.getLayoutY();
        ArrayList<Discussion> discussion = discserv.AfficherListe();
        for (Discussion element : discussion) {
            leftpage.getChildren().add(ajouterPaneContact(element,y));
            y+=70;
        }
        mainpage.setVisible(false);
//        stage.setOnCloseRequest( ev -> {
//            System.out.println("hello my friend");
//        });
        
//        System.out.println(thisStage);
    }    
    
    
    public Server createServer(){
        return new Server(1234,data->{
            Platform.runLater(()->{
                System.out.println("Server identity : "+Integer.parseInt(discidentity.getText()));
                Discussion disc = discserv.AfficherDiscussion(Integer.parseInt(discidentity.getText()));
                ChangerPaneMessage(disc);
            });
        });
    }
    
    public Client createCLient(){
        return new Client("127.0.0.1",1234,data->{
            Platform.runLater(()->{
                System.out.println("client identity : "+Integer.parseInt(discidentity.getText()));
                Discussion disc = discserv.AfficherDiscussion(Integer.parseInt(discidentity.getText()));
                ChangerPaneMessage(disc);
            });
        });
    }
    
    
    EventHandler<ActionEvent> EmailHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(userclient.getType().compareTo("psy")==0){
                outils.sendMail(discserv.getmail(Integer.parseInt(discidentity.getText())),mail_titre.getText(),mail_contenu.getText());
                Notifications notificationBuilder;
                notificationBuilder = Notifications.create()
                    .title("Mail envoyer").text("l'utilisateur a recu le mail.").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
                    .position(Pos.BOTTOM_RIGHT)
                    .onAction((ActionEvent event1) -> {System.out.println("clicked ON ");});
                notificationBuilder.show();
                chatroom.getChildren().clear();
                discinput.setVisible(true);
                icon_envoyer.setVisible(true);
                event.consume();
            }
        };
    };
    
    @FXML
    void MailChanger(ActionEvent event) {
        chatroom.getChildren().clear();
        mail_titre = new JFXTextField();
        mail_contenu = new JFXTextArea();
        Label text = new Label();
        JFXButton mailbutt = new JFXButton();
        
        mail_titre.setLayoutX(204.0);
        mail_titre.setLayoutY(53.0);
        mail_titre.setPrefWidth(200);
        text.setLayoutX(162.0);
        text.setLayoutY(65.0);
        text.setText("Titre : ");
        mail_contenu.setLayoutX(91.0);
        mail_contenu.setLayoutY(118.0);
        mailbutt.setLayoutX(255.0);
        mailbutt.setLayoutY(342.0);
        mailbutt.setText("Envoyer");
        chatroom.getChildren().add(mail_titre);
        chatroom.getChildren().add(text);
        chatroom.getChildren().add(mail_contenu);
        chatroom.getChildren().add(mailbutt);
        
        mailbutt.setOnAction(EmailHandler);
        
        discinput.setVisible(false);
        icon_envoyer.setVisible(false);
    }
}
