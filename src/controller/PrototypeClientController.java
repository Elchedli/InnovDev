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
    void PageMess(ActionEvent event) throws IOException  {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/message.fxml"));
        MainPage.getChildren().setAll(pane);
        taskbutt.setStyle("-fx-background-color:#1D232A");
        messbutt.setStyle("-fx-background-color:#272E36");
    }

    @FXML
    void PageTasks(ActionEvent event) throws IOException{
        taskbutt.setStyle("-fx-background-color:#272E36");
        messbutt.setStyle("-fx-background-color:#1D232A");
    }
    public void initialize() {
        userclient.setUsername("shidono");
        userclient.setType("client");
   }
    
}
