/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Suivi;
import services.SuiviService;
import com.jfoenix.controls.JFXToggleButton;

/**
 *
 * @author shidono
 */
public class PrototypeController{
    SuiviService suivser = new SuiviService();
    ArrayList listesuivi = new ArrayList();
    ObservableList<String> trieList = FXCollections.observableArrayList("identifiant","Titre","Date","Temps");
    ObservableList<Suivi> SuiviList = FXCollections.observableArrayList();
     Comparator c = Collections.reverseOrder();
    String user = "ahmed"; // n'oublier pas de changer au nom d'utilisateur;
    @FXML
    private DatePicker date_deb;

    @FXML
    private DatePicker date_fin;

    @FXML
    private TextField titre;

    @FXML
    private TextField temp_deb;

    @FXML
    private TextField temps_fin;

    @FXML
    private ComboBox<String> trie;
    
     @FXML
    private TableView<Suivi> suivitable;
     
      @FXML
    private TableColumn<Suivi, Integer> id_table;

    @FXML
    private TableColumn<Suivi, String> titre_table;

    @FXML
    private TableColumn<Suivi, Date> datedeb_table;

    @FXML
    private TableColumn<Suivi, Date> datefin_table;

    @FXML
    private TableColumn<Suivi, Time> tempsdeb_table;

    @FXML
    private TableColumn<Suivi, Time> tempsfin_table;

    @FXML
    private JFXToggleButton ReverseSuivi;
    
     @FXML
    private TextField tabrecherche;
    
    @FXML
    void AjouterSuiv(ActionEvent event) {
        Suivi nouvsuiv = new Suivi("ahmed",titre.getText(),Date.valueOf(date_deb.getValue()), Date.valueOf(date_fin.getValue()), Time.valueOf(LocalTime.parse(temp_deb.getText())),Time.valueOf(LocalTime.parse(temps_fin.getText())));
        suivser.ajouterSuivi(nouvsuiv);
        System.out.println("utilisateur ajouter");
        SuiviList.add(nouvsuiv);
        suivitable.setItems(SuiviList);
//        Suivi
    }

    @FXML
    void SupprimerSuiv(ActionEvent event) {

    }

    @FXML
    void modifierSuiv(ActionEvent event) {

    }
    
    @FXML
    void testFunction(ActionEvent event) {
        suivser.AfficherSuivi(user).forEach(System.out::println);
    }
    
    @FXML
    void TrieMethode(ActionEvent event) {
        System.out.println(trie.getValue());
        System.out.println(trie.getSelectionModel().getSelectedIndex());
        SuiviList.clear();
        SuiviList.removeAll(SuiviList);
        int id = trie.getSelectionModel().getSelectedIndex();
         switch(id){
                case 0:
                     suivser.AfficherSuivi(user).stream().sorted((a,b)->a.getId()-b.getId()).forEach(e->{
                        SuiviList.add(e);
                    });
                break;
                case 1:
                     suivser.AfficherSuivi(user).stream().sorted((a,b)->a.getTitre_s().compareTo(b.getTitre_s())).forEach(e->{
                        SuiviList.add(e);
                     });
                break;
                case 2:
                     suivser.AfficherSuivi(user).stream().sorted((a,b)->a.getDate_ds().compareTo(b.getDate_ds())).forEach(e->{
                        SuiviList.add(e);
                     });
                break;
                case 3:
                     suivser.AfficherSuivi(user).stream().sorted((a,b)->a.getTemps_ds().compareTo(b.getTemps_ds())).forEach(e->{
                        SuiviList.add(e);
                     });
                break;
                default:
                    System.out.println("ca n'a pas marcher");
                break;
        }
//        if(drachn) Collections.sort(SuiviList, Collections.reverseOrder());
        suivitable.setItems(SuiviList);
//        suivitable.setItems(SuiviList);
//            suivser.AfficherSuivi(user).stream();
//        suivser.AfficherSuivi(user).stream().sorted((a,b)->a.getId()-b.getId()).forEach(e->System.out.println(e));
        
    }
    
    @FXML
    void reverseSuivi(ActionEvent event) {
//        ReverseSuivi.get
    }
    
       @FXML
    void rechercheSuivi(ActionEvent event) {

    }
    
    public void initialize() {
        trie.setItems(trieList);
        trie.setValue("identifiant");
        id_table.setCellValueFactory(new PropertyValueFactory<Suivi, Integer>("id_s"));
        titre_table.setCellValueFactory(new PropertyValueFactory<Suivi, String>("titre_s"));
        datedeb_table.setCellValueFactory(new PropertyValueFactory<Suivi, Date>("date_ds"));
        datefin_table.setCellValueFactory(new PropertyValueFactory<Suivi, Date>("date_fs"));
        tempsdeb_table.setCellValueFactory(new PropertyValueFactory<Suivi, Time>("temps_ds"));
        tempsfin_table.setCellValueFactory(new PropertyValueFactory<Suivi, Time>("temps_fs"));
        suivser.AfficherSuivi(user).forEach(e->{
            SuiviList.add(e);
        });
        suivitable.setItems(SuiviList);
    }
    
}
