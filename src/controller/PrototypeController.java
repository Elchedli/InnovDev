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

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class PrototypeController{

    @FXML
    private AnchorPane MainPage;
    
        @FXML
    private JFXButton suivibutt;

    @FXML
    private JFXButton messbutt;

    @FXML
    private JFXButton taskbutt;
    
    @FXML
    void PageSuivi(ActionEvent event) throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/Suivi.fxml"));
        MainPage.getStylesheets().add("design/design.css");
        MainPage.getChildren().setAll(pane);
        suivibutt.setStyle("-fx-background-color:#272E36");
        taskbutt.setStyle("-fx-background-color:#1D232A");
        messbutt.setStyle("-fx-background-color:#1D232A");
    }
    
    @FXML
    void PageMess(ActionEvent event) throws IOException  {
       AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/message.fxml"));
//        MainPage.getStylesheets().add("design/design.css");
        MainPage.getChildren().setAll(pane);
        suivibutt.setStyle("-fx-background-color:#1D232A");
        taskbutt.setStyle("-fx-background-color:#1D232A");
        messbutt.setStyle("-fx-background-color:#272E36");
    }

    @FXML
    void PageTasks(ActionEvent event) throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/privtasks.fxml"));
//        MainPage.getStylesheets().add("design/design.css");
        MainPage.getChildren().setAll(pane);
        suivibutt.setStyle("-fx-background-color:#1D232A");
        taskbutt.setStyle("-fx-background-color:#272E36");
        messbutt.setStyle("-fx-background-color:#1D232A");
    }
    public void initialize() {
        
   }
    
}
