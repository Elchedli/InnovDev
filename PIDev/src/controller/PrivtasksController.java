/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javax.swing.JOptionPane;
import PIClass.Suivi;
import PIClass.Tache;
import PIClass.userclient;
import com.jfoenix.controls.JFXButton;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import org.controlsfx.control.Notifications;
import services.SuiviService;
import services.TacheService;

/**
 * FXML Controller class
 *
 * @author shidono
 */
public class PrivtasksController implements Initializable {
     boolean language = userclient.getLanguage().compareTo("fr") == 0;
    ObservableList<String> difficulteliste = language ? FXCollections.observableArrayList("facile","moyenne","difficile")
            : FXCollections.observableArrayList("easy","medium","hard");
    ObservableList<Suivi> SuiviList = FXCollections.observableArrayList();
    ObservableList<Tache> TacheList = FXCollections.observableArrayList();
    SuiviService suivser = new SuiviService();
    TacheService tacheser = new TacheService();
    private final FilteredList <Suivi> filter = new FilteredList <> (SuiviList, e -> true);
    private final SortedList <Suivi> sortsuivi = new SortedList<>(filter);

    @FXML
    private JFXTextField description;
        
    @FXML
    private TableColumn<Suivi, String> table_client;
      
    @FXML
    private TableColumn<Suivi, String> table_titre;

    @FXML
    private TableColumn<Suivi, Date> table_datedeb;

    @FXML
    private TableColumn<Suivi, Date> table_datefin;
    
        @FXML
    private TableColumn<Tache, String> tabletache_client;

    @FXML
    private TableColumn<Tache, String> tabletache_description;

    @FXML
    private TableColumn<Tache, String> tabletache_difficulte;

    @FXML
    private TableColumn<Tache, Boolean> tabletache_etat;
    
    @FXML
    private TableColumn<Tache, Integer> tabletache_id;
    
    @FXML
    private JFXComboBox<String> combo_difficulte;
    
    @FXML
    private TableView<Suivi> suivitable;
    
    
    @FXML
    private TableView<Tache> tachetable;
    /**
     * Initializes the controller class.
     */
    
    @FXML
    private JFXTextField tabrecherche;
    
    
    @FXML
    private JFXButton AjouterButt;
    
    @FXML
    private void rechercheSuivi(KeyEvent event) {
        tabrecherche.setOnKeyReleased((KeyEvent e) -> { 
               tabrecherche.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                    filter.setPredicate((Suivi Suivitable) -> {
                        if (newValue == null || newValue.isEmpty()) return true;
                        String lowerCaseFilter = newValue.toLowerCase();
                        return Suivitable.getClient().toLowerCase().contains(lowerCaseFilter);
                    });
                });
            sortsuivi.comparatorProperty().bind(suivitable.comparatorProperty());
            suivitable.setItems(sortsuivi);
        });
    }
    
    public void changeCellDifficulte(TableColumn.CellEditEvent editcell){
        Tache TacheSelected = tachetable.getSelectionModel().getSelectedItem();
        String newvar = editcell.getNewValue().toString();
        TacheSelected.setDifficulte_tache(newvar);
        tacheser.ModifierTache(TacheSelected.getId_tache(),newvar,"diff");
    }
    
    public void changeCellDescription(TableColumn.CellEditEvent editcell){
        Tache TacheSelected = tachetable.getSelectionModel().getSelectedItem();
        String newvar = editcell.getNewValue().toString();
        TacheSelected.setDifficulte_tache(newvar);
        tacheser.ModifierTache(TacheSelected.getId_tache(),newvar,"desc");
    }
    
        @FXML
    void AjouterTache(ActionEvent event) {
        Suivi suiviSelected = suivitable.getSelectionModel().getSelectedItem();
        if(description.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "description est vide"); 
        }else if(suiviSelected == null){
            JOptionPane.showMessageDialog(null, "Utilisateur non selectionnée"); 
        }else{
            Tache nouvTache = new Tache(suiviSelected.getClient(),description.getText(),combo_difficulte.getValue());
            tacheser.ajouterTache(nouvTache);
            Notifications notificationBuilder;
            notificationBuilder = Notifications.create()
                    .title("Tache ajouter.").text("Client : "+nouvTache.getUsername()+" a une nouvelle tache.").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
                    .position(Pos.BOTTOM_RIGHT)
                    .onAction((ActionEvent event1) -> {System.out.println("clicked ON ");});
            notificationBuilder.show();
            TacheList.clear();
            TacheList.removeAll(TacheList);
            tacheser.AfficherTaches(suiviSelected.getClient()).forEach(e->{
                TacheList.add(e);
            });
            tachetable.setItems(TacheList);
        }
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        combo_difficulte.setItems(difficulteliste);
        if(language) combo_difficulte.setValue("facile");
        else combo_difficulte.setValue("easy");
        table_client.setCellValueFactory(new PropertyValueFactory<>("client"));
        table_titre.setCellValueFactory(new PropertyValueFactory<>("titre_s"));
        table_datedeb.setCellValueFactory(new PropertyValueFactory<>("date_ds"));
        table_datefin.setCellValueFactory(new PropertyValueFactory<>("date_fs"));
        tabletache_client.setCellValueFactory(new PropertyValueFactory<>("username"));
        tabletache_description.setCellValueFactory(new PropertyValueFactory<>("description_tache"));
        tabletache_difficulte.setCellValueFactory(new PropertyValueFactory<>("difficulte_tache"));
        tabletache_etat.setCellValueFactory(new PropertyValueFactory<>("etat_tache"));
        ContextMenu contextMenuTache = new ContextMenu();
        MenuItem DeleteItem = new MenuItem("Supprimer Tache");
        DeleteItem.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                Tache itemtache = tachetable.getSelectionModel().getSelectedItem();
                System.out.println("id : "+itemtache.getId_tache());
                tacheser.SupprimerTache(itemtache.getId_tache());
                TacheList.clear();
                TacheList.removeAll(TacheList);
                tacheser.AfficherTaches(itemtache.getUsername()).forEach(e->{
                    TacheList.add(e);
                });
                tachetable.setItems(TacheList);
            }
        });
        
        contextMenuTache.getItems().add(DeleteItem);
        tachetable.setContextMenu(contextMenuTache);
        
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
        
        tachetable.setEditable(true);
        tabletache_description.setCellFactory(TextFieldTableCell.forTableColumn());
        tabletache_difficulte.setCellFactory(TextFieldTableCell.forTableColumn());
        
        if(language){
            table_titre.setText("Titre");
            table_datedeb.setText("Date Debut");
            table_datefin.setText("Date Fin");
            tabletache_difficulte.setText("Difficulté");
            tabletache_etat.setText("Etat");
            AjouterButt.setText("Ajouter Tache");
            tabrecherche.setPromptText("Recherche");
        }else{
            table_titre.setText("Title");
            table_datedeb.setText("Date Start");
            table_datefin.setText("Date Finish");
            tabletache_difficulte.setText("Difficulty");
            tabletache_etat.setText("Stat");
            AjouterButt.setText("Add Task");
            tabrecherche.setPromptText("Search");
        }
    }    
    
}
