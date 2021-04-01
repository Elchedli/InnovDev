/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import PIClass.Suivi;
import PIClass.Tache;
import PIClass.userclient;
import com.jfoenix.controls.JFXTreeTableView;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import services.SuiviService;
import services.TacheService;

/**
 * FXML Controller class
 *
 * @author shidono
 */
public class TaskClientController implements Initializable {
    boolean language = userclient.getLanguage().compareTo("fr") == 0;
    ObservableList<String> difficulteliste = language ? FXCollections.observableArrayList("facile","moyenne","difficile")
            : FXCollections.observableArrayList("easy","medium","hard");
    ObservableList<Suivi> SuiviList = FXCollections.observableArrayList();
    ObservableList<Tache> TacheList = FXCollections.observableArrayList();
    SuiviService suivser = new SuiviService();
    TacheService tacheser = new TacheService();
    
    @FXML
    private TableView<Suivi> suivitable;

    @FXML
    private TableColumn<Suivi, String> table_titre;

    @FXML
    private TableColumn<Suivi, Date> table_datedeb;

    @FXML
    private TableColumn<Suivi, Date> table_datefin;

    @FXML
    private TableColumn<Tache, String> tabletache_desc;

    @FXML
    private TableColumn<Tache, String> tabletache_difficultee;

    @FXML
    private TableColumn<Tache, String> tabletache_accompli;
    
    @FXML
    private TableView<Tache> tachetable;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        table_titre.setCellValueFactory(new PropertyValueFactory<>("titre_s"));
        table_datedeb.setCellValueFactory(new PropertyValueFactory<>("date_ds"));
        table_datefin.setCellValueFactory(new PropertyValueFactory<>("date_fs"));
        tabletache_desc.setCellValueFactory(new PropertyValueFactory<>("description_tache"));
        tabletache_difficultee.setCellValueFactory(new PropertyValueFactory<>("difficulte_tache"));
        tabletache_accompli.setCellValueFactory(new PropertyValueFactory<>("etat_tache"));
        
         suivser.AfficherSuivi().forEach(e->{
            SuiviList.add(e);
        });
        suivitable.setItems(SuiviList);
        suivitable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                Suivi suiviSelected = suivitable.getSelectionModel().getSelectedItem();
                TacheList.clear();
                TacheList.removeAll(TacheList);
                tacheser.AfficherTaches(suiviSelected.getClient()).forEach(e->{
                    TacheList.add(e);
                });
                tachetable.setItems(TacheList);
            }
        });
        tachetable.setOnMouseClicked((MouseEvent event) -> {
            if (suivitable.getSelectionModel().selectedItemProperty() != null && event.getClickCount() == 2){
                System.out.println("double clicked");
                Tache itemtache = tachetable.getSelectionModel().getSelectedItem();
                tacheser.modifierUneTache(itemtache.getId_tache());
                TacheList.clear();
                TacheList.removeAll(TacheList);
                tacheser.AfficherTaches(itemtache.getUsername()).forEach(e->{
                    TacheList.add(e);
                });
                tachetable.setItems(TacheList);
            }
        });
        
        if(language){
            table_titre.setText("Titre");
            table_datedeb.setText("Date Debut");
            table_datefin.setText("Date Fin");
            tabletache_difficultee.setText("Difficultée");
            tabletache_accompli.setText("Accompli");
        }else{
            table_titre.setText("Title");
            table_datedeb.setText("Date Start");
            table_datefin.setText("Date End");
            tabletache_difficultee.setText("Difficulty");
            tabletache_accompli.setText("Accomplished");
        }
    } 
}
