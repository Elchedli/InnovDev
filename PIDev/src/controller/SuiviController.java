/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import PIClass.Suivi;
import services.SuiviService;
import com.jfoenix.controls.JFXToggleButton;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import PIClass.userclient;
import com.jfoenix.controls.JFXButton;
import java.sql.Timestamp;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.stage.WindowEvent;
import org.controlsfx.control.Notifications;
import tweaks.DateEditingCell;
import tweaks.TimeEditingCell;
/**
 *
 * @author shidono
 */



public class SuiviController{
    String verif;
    boolean language = userclient.getLanguage().compareTo("fr") == 0;
    SuiviService suivser = new SuiviService();
    ArrayList listesuivi = new ArrayList();
    ObservableList<String> trieList = language ? FXCollections.observableArrayList("identifiant","Titre","Date","Temps") : FXCollections.observableArrayList("Identifier","Title","Date","Time");
    ObservableList<Suivi> SuiviList = FXCollections.observableArrayList();
    private final FilteredList <Suivi> filter = new FilteredList <> (SuiviList, e -> true);
    private final SortedList <Suivi> sortsuivi = new SortedList<>(filter);
    @FXML
    private JFXDatePicker date_deb;

    @FXML
    private JFXDatePicker date_fin;

    @FXML
    private JFXTimePicker temp_deb;

    @FXML
    private JFXTimePicker temps_fin;

    @FXML
    private JFXTextField client;
    
    @FXML
    private JFXTextField titre;
    
    @FXML
    private JFXButton addbutt;
    

    
    @FXML
    private ComboBox<String> trie;
    
     @FXML
    private TableView<Suivi> suivitable;
     
      @FXML
    private TableColumn<Suivi, Integer> id_table;

      @FXML
    private TableColumn<Suivi, String> client_table;
      
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
    private JFXTextField tabrecherche;
     
      @FXML
    private Label labelclient;

    @FXML
    private Label labeldate;

    @FXML
    private Label labeltemps;

    
    @FXML
    void AjouterSuiv(ActionEvent event) {
//        Date date = Date.from(date_deb.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
//        Suivi nouvsuiv = new Suivi(client.getText(),titre.getText(),Date.from(date_deb.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),Date.from(date_fin.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()), Time.valueOf(temp_deb.getValue()),Time.valueOf(temps_fin.getValue()));
        if(client.getText().isEmpty() || titre.getText().isEmpty() || date_deb.getValue() == null || date_fin.getValue() == null || temp_deb.getValue() == null || temps_fin.getValue() == null){
            JOptionPane.showMessageDialog(null, "il y a un champ vide!");
        }else{
            Suivi nouvsuiv = new Suivi(client.getText(),titre.getText(),Date.valueOf(date_deb.getValue()), Date.valueOf(date_fin.getValue()), Time.valueOf(temp_deb.getValue()),Time.valueOf(temps_fin.getValue()));
            verif = suivser.ajouterSuivi(nouvsuiv);
            System.out.println("verif est : "+verif);
            labelclient.setVisible(false);
            labeldate.setVisible(false);
            labeltemps.setVisible(false);
            client.setStyle("-jfx-unfocus-color: black");
            date_deb.lookup(".jfx-text-field").setStyle("-jfx-unfocus-color: black");
            temp_deb.lookup(".jfx-text-field").setStyle("-jfx-unfocus-color: black");
            if(verif.isEmpty()){
                System.out.println("utilisateur ajouter");
                SuiviList.add(nouvsuiv);
                suivitable.setItems(SuiviList);
                JOptionPane.showMessageDialog(null, "Suivi ajouter");  
                Notifications notificationBuilder;
                notificationBuilder = Notifications.create()
                        .title("Suivi ajouter").text("Le nouveau suivi avec le titre "+nouvsuiv.getTitre_s()+" a était ajouter.").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
                        .position(Pos.BOTTOM_RIGHT)
                        .onAction((ActionEvent event1) -> {System.out.println("clicked ON ");});
                notificationBuilder.show();
            }else{
                if(verif.contains("foreign")){
                    labelclient.setVisible(true);
                    client.setStyle("-jfx-unfocus-color: red");
                }
                if(verif.contains("date")){
                    labeldate.setVisible(true);
                    date_deb.lookup(".jfx-text-field").setStyle("-jfx-unfocus-color: red");
                }
                if(verif.contains("temps")){
                    labeltemps.setVisible(true);
                    temp_deb.lookup(".jfx-text-field").setStyle("-jfx-unfocus-color: red");


                }
            }
        }
    }

    @FXML
    void SupprimerSuiv(ActionEvent event) {

    }

    @FXML
    void modifierSuiv(ActionEvent event) {

    }
    
    @FXML
    void testFunction(ActionEvent event) {
        SuiviList.clear();
        SuiviList.removeAll(SuiviList);
        suivser.AfficherSuivi().forEach(e->{
            SuiviList.add(e);
        });
        suivitable.setItems(SuiviList);
    }
    
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
    
    @FXML
    void TrieMethode(ActionEvent event) {
        System.out.println(trie.getValue());
        System.out.println(trie.getSelectionModel().getSelectedIndex());
        SuiviList.clear();
        SuiviList.removeAll(SuiviList);
        int id = trie.getSelectionModel().getSelectedIndex();
         switch(id){
                case 0:
                     suivser.AfficherSuivi().stream().sorted((a,b)->a.getId_s()-b.getId_s()).forEach(e->{
                        SuiviList.add(e);
                    });
                break;
                case 1:
                     suivser.AfficherSuivi().stream().sorted((a,b)->a.getTitre_s().compareTo(b.getTitre_s())).forEach(e->{
                        SuiviList.add(e);
                     });
                break;
                case 2:
                     suivser.AfficherSuivi().stream().sorted((a,b)->a.getDate_ds().compareTo(b.getDate_ds())).forEach(e->{
                        SuiviList.add(e);
                     });
                break;
                case 3:
                     suivser.AfficherSuivi().stream().sorted((a,b)->a.getTemps_ds().compareTo(b.getTemps_ds())).forEach(e->{
                        SuiviList.add(e);
                     });
                break;
                default:
                    System.out.println("ca n'a pas marcher");
                break;
        }
        if(ReverseSuivi.isSelected()) Collections.reverse(SuiviList);
        suivitable.setItems(SuiviList);
    }
    
    @FXML
    void reverseSuivi(ActionEvent event) {
        Collections.reverse(SuiviList);
    }
    
    public void changeCellClient(CellEditEvent editcell){
        Suivi suiviSelected = suivitable.getSelectionModel().getSelectedItem();
         String newvar = editcell.getNewValue().toString();
        suiviSelected.setClient(newvar);
        suivser.ModifierSuivi(suiviSelected.getId_s(),newvar,"client");
    }
    
    public void changeCellTitre(CellEditEvent editcell){
        Suivi suiviSelected = suivitable.getSelectionModel().getSelectedItem();
        String newvar = editcell.getNewValue().toString();
        suiviSelected.setTitre_s(newvar);
        suivser.ModifierSuivi(suiviSelected.getId_s(),newvar,"titre_s");
    }
    
    public void changeCellDateDeb(CellEditEvent editcell){
        Suivi suiviSelected = suivitable.getSelectionModel().getSelectedItem();
        Date newvar = Date.valueOf(editcell.getNewValue().toString());
        suiviSelected.setDate_ds(newvar);
        suivser.ModifierSuivi(suiviSelected.getId_s(),newvar,"date_ds");
    }
    
    public void changeCellDateFin(CellEditEvent editcell){
        Suivi suiviSelected = suivitable.getSelectionModel().getSelectedItem();
        Date newvar = Date.valueOf(editcell.getNewValue().toString());
        suiviSelected.setDate_fs(newvar);
        suivser.ModifierSuivi(suiviSelected.getId_s(),newvar,"date_fs");
    }
    
    public void changeCellTempsDeb(CellEditEvent editcell){
        Suivi suiviSelected = suivitable.getSelectionModel().getSelectedItem();
        Time newvar = Time.valueOf(editcell.getNewValue().toString());
        suiviSelected.setTemps_ds(newvar);
         suivser.ModifierSuivi(suiviSelected.getId_s(),newvar,"temps_ds");
    }
    public void changeCellTempsFin(CellEditEvent editcell){
        Suivi suiviSelected = suivitable.getSelectionModel().getSelectedItem();
        Time newvar = Time.valueOf(editcell.getNewValue().toString());
        suiviSelected.setTemps_fs(newvar);
        suivser.ModifierSuivi(suiviSelected.getId_s(),newvar,"temps_fs");
    }
    public void initialize() {
        trie.setItems(trieList);
        if(language) trie.setValue("Identifiant");
        else trie.setValue("Identifier");
        id_table.setCellValueFactory(new PropertyValueFactory<>("id_s"));
        client_table.setCellValueFactory(new PropertyValueFactory<>("client"));
        titre_table.setCellValueFactory(new PropertyValueFactory<>("titre_s"));
        datedeb_table.setCellValueFactory(new PropertyValueFactory<>("date_ds"));
        datefin_table.setCellValueFactory(new PropertyValueFactory<>("date_fs"));
        tempsdeb_table.setCellValueFactory(new PropertyValueFactory<>("temps_ds"));
        tempsfin_table.setCellValueFactory(new PropertyValueFactory<>("temps_fs"));
        suivser.AfficherSuivi().forEach(e->{
            SuiviList.add(e);
        });
        suivitable.setItems(SuiviList);
        suivitable.setEditable(true);
        Callback<TableColumn<Suivi, Date>, TableCell<Suivi, Date>> dateCellFactory;
        dateCellFactory = (TableColumn<Suivi, Date> param) -> new DateEditingCell();
         Callback<TableColumn<Suivi, Time>,  TableCell<Suivi, Time>> TimeCellFactory;
        TimeCellFactory = (TableColumn<Suivi, Time> param) -> new TimeEditingCell();
        client_table.setCellFactory(TextFieldTableCell.forTableColumn());
        titre_table.setCellFactory(TextFieldTableCell.forTableColumn());
        datedeb_table.setCellFactory(dateCellFactory);
        datefin_table.setCellFactory(dateCellFactory);
        tempsdeb_table.setCellFactory(TimeCellFactory);
        tempsfin_table.setCellFactory(TimeCellFactory);
        
        ContextMenu contextMenuSuivi = new ContextMenu();
        MenuItem DeleteItem = new MenuItem("Supprimer Suivi");
        
        DeleteItem.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                Object item = suivitable.getSelectionModel().getSelectedItem();
                Suivi nouvsuiv = (Suivi) item;
                System.out.println(nouvsuiv.toString());
                suivser.SupprimerSuivi(nouvsuiv.getId_s());
                SuiviList.clear();
                SuiviList.removeAll(SuiviList);
                suivser.AfficherSuivi().forEach(e->{
                    SuiviList.add(e);
                });
                suivitable.setItems(SuiviList);
            }
        }
        );
        
        
            EventHandler<WindowEvent> event = new EventHandler<WindowEvent>()
            {
                @Override
                public void handle(WindowEvent e)
                {
                    if(contextMenuSuivi.isShowing())
                    {
                       //System.out.println("Showing");
                    }
                    else
                    {
                       //System.out.println("Hidden");
                    }
                }
            };
             EventHandler<MouseEvent> handler = new EventHandler<MouseEvent>() {  
        @Override
        public void handle(MouseEvent event) {
        }  
          
    };
            contextMenuSuivi.getItems().add(DeleteItem);
            contextMenuSuivi.setOnShowing(event);
            contextMenuSuivi.setOnHiding(event);
            suivitable.setOnMouseClicked(handler);
            suivitable.setContextMenu(contextMenuSuivi);
            labelclient.setVisible(false);
            labeldate.setVisible(false);
            labeltemps.setVisible(false);
            if(language){
                titre.setPromptText("Titre");
                date_deb.setPromptText("Date Debut");
                date_fin.setPromptText("Date Fin");
                temp_deb.setPromptText("Temps Debut");
                temps_fin.setPromptText("Temps Fin");
                addbutt.setText("Ajouter");
                titre_table.setText("Titre");
                datedeb_table.setText("Date Debut");
                datefin_table.setText("Date Fin");
                tempsdeb_table.setText("Temps Debut");
                tempsfin_table.setText("Temps Fin");
                labelclient.setText("Client n'est pas existant");
                labeldate.setText("Date debut plus grande");
                labeltemps.setText("Temps début plus grand");
            }else{
                titre.setPromptText("Title");
                date_deb.setPromptText("Date Start");
                date_fin.setPromptText("Date End");
                temp_deb.setPromptText("Time Start");
                temps_fin.setPromptText("Time End");
                addbutt.setText("Add");
                titre_table.setText("Title");
                datedeb_table.setText("Date Start");
                datefin_table.setText("Date End");
                tempsdeb_table.setText("Time Start");
                tempsfin_table.setText("Time End");
                labelclient.setText("Client doesn't exist");
                labeldate.setText("Date Start is bigger");
                labeltemps.setText("Time Start is bigger");
            }
   }
   

}
