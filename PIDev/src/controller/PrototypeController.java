/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
/**
 *
 * @author shidono
 */

import PIClass.userclient;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import tweaks.outils;

public class PrototypeController{
    boolean language = userclient.getLanguage().compareTo("fr") == 0;
    
    @FXML
    private AnchorPane MainPage;
    
        @FXML
    private JFXButton suivibutt;

    @FXML
    private JFXButton messbutt;

    @FXML
    private JFXButton taskbutt;
    
    @FXML
    private Label welcome;

    @FXML
    private JFXButton first;

    @FXML
    private JFXButton second;

    @FXML
    private JFXButton third;
    
    @FXML
    private JFXButton languagebutt;

    @FXML   
    private JFXButton backbutt;
    
    @FXML
    private Label labelangue;
    
    @FXML
    void PageSuivi(ActionEvent event) throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/Suivi.fxml"));
        MainPage.getStylesheets().add("design/design.css");
        MainPage.getChildren().setAll(pane);
        suivibutt.setStyle("-fx-background-color: linear-gradient(to right top, #00a1a1, #00a1a1, #00a1a1, #00a1a1, #00a1a1)");
        taskbutt.setStyle("-fx-background-color:transparent");
        messbutt.setStyle("-fx-background-color:transparent");
    }
    
    @FXML
    void PageMess(ActionEvent event) throws IOException  {
//       final Stage stage = (Stage) messbutt.getScene().getWindow();
       AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/message.fxml"));
//        MainPage.getStylesheets().add("design/design.css");
        MainPage.getChildren().setAll(pane);
        suivibutt.setStyle("-fx-background-color:transparent");
        taskbutt.setStyle("-fx-background-color:transparent");
        messbutt.setStyle("-fx-background-color: linear-gradient(to right top, #00a1a1, #00a1a1, #00a1a1, #00a1a1, #00a1a1)");
    }

    @FXML
    void PageTasks(ActionEvent event) throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/privtasks.fxml"));
        MainPage.getStylesheets().add("design/design.css");
//        MainPage.getStylesheets().remove("design/design.css");
        MainPage.getChildren().setAll(pane);
        suivibutt.setStyle("-fx-background-color:transparent");
        taskbutt.setStyle("-fx-background-color: linear-gradient(to right top, #00a1a1, #00a1a1, #00a1a1, #00a1a1, #00a1a1)");
        messbutt.setStyle("-fx-background-color:transparent");
    }
    
    @FXML
    void BackMenu(ActionEvent event) {

    }

    @FXML
    void ChangeLanguage(ActionEvent event) throws IOException {
        boolean verif = userclient.getLanguage().compareTo("fr") == 0;
        verif = !verif;
        if(verif){
            labelangue.setText("FR");
            userclient.setLanguage("fr");   
        }
        else{
            labelangue.setText("EN");
            userclient.setLanguage("en");
        }
        MainPage.getChildren().clear();
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/Suivi.fxml"));
        suivibutt.setStyle("-fx-background-color:transparent");
        taskbutt.setStyle("-fx-background-color:transparent");
        messbutt.setStyle("-fx-background-color:transparent");
        MainPage.getChildren().setAll(pane);
    }
    
    public void initialize() throws IOException  {
        String bonjour = userclient.getLanguage().compareTo("fr") == 0 ? "Bonjour " : "Hello ";
        welcome.setText(bonjour+userclient.getUsername());
   }
   
    
}