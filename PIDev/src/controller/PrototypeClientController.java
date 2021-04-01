/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import PIClass.userclient;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import PIClass.user;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author shidono
 */
public class PrototypeClientController{

    @FXML
    private AnchorPane MainPage;

    @FXML
    private JFXButton messbutt;

    @FXML
    private JFXButton taskbutt;
    
    @FXML
    private Label labelangue;
    
    @FXML
    void PageMess(ActionEvent event) throws IOException  {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/message.fxml"));
        MainPage.getChildren().setAll(pane);
        taskbutt.setStyle("-fx-background-color: transparent");
        messbutt.setStyle("-fx-background-color: linear-gradient(to right top, #00a1a1, #00a1a1, #00a1a1, #00a1a1, #00a1a1)");
    }

    @FXML
    void PageTasks(ActionEvent event) throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/TaskClient.fxml"));
        MainPage.getStylesheets().add("design/design.css");
        MainPage.getChildren().setAll(pane);
        taskbutt.setStyle("-fx-background-color:linear-gradient(to right top, #00a1a1, #00a1a1, #00a1a1, #00a1a1, #00a1a1)");
        messbutt.setStyle("-fx-background-color: transparent");
    }
    public void initialize() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/TaskClient.fxml"));
        MainPage.getStylesheets().add("design/design.css");
        MainPage.getChildren().setAll(pane);
        taskbutt.setStyle("-fx-background-color:linear-gradient(to right top, #00a1a1, #00a1a1, #00a1a1, #00a1a1, #00a1a1)");
        messbutt.setStyle("-fx-background-color: transparent");
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
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/TaskClient.fxml"));
        taskbutt.setStyle("-fx-background-color:linear-gradient(to right top, #00a1a1, #00a1a1, #00a1a1, #00a1a1, #00a1a1)");
        messbutt.setStyle("-fx-background-color:transparent");
        MainPage.getChildren().setAll(pane);
    }
    
}
