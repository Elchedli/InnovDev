/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
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
import models.Suivi;
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
import models.user;
import java.sql.Timestamp;
import org.controlsfx.control.Notifications;
import tweaks.DateEditingCell;
import tweaks.TimeEditingCell;
/**
 *
 * @author shidono
 */

//class DateEditingCell extends TableCell<Suivi, Date> {
//
//        private DatePicker datePicker;
//
//        DateEditingCell() {
//        }
//
//        @Override
//        public void startEdit() {
//            if (!isEmpty()) {
//                super.startEdit();
//                createDatePicker();
//                setText(null);
//                setGraphic(datePicker);
//            }
//        }
//
//        @Override
//        public void cancelEdit() {
//            super.cancelEdit();
//
//            setText(getDate().toString());
//            setGraphic(null);
//        }
//
//        @Override
//        public void updateItem(Date item, boolean empty) {
//            super.updateItem(item, empty);
//            if (empty) {
//                setText(null);
//                setGraphic(null);
//            } else {
//                if (isEditing()) {
//                    if (datePicker != null) {
//                        datePicker.setValue(getDate());
//                    }
//                    setText(null);
//                    setGraphic(datePicker);
//                } else {
//                    setText(getDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
//                    setGraphic(null);
//                }
//            }
//        }
//
//        private void createDatePicker() {
//            datePicker = new DatePicker(getDate());
//            datePicker.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
//            datePicker.setOnAction((e) -> {
//                System.out.println("Committed: " + datePicker.getValue().toString());
//                commitEdit(Date.valueOf(datePicker.getValue()));
//            });
////            datePicker.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
////                if (!newValue) {
////                    commitEdit(Date.from(datePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
////                }
////            });
//        }
//
//        private LocalDate getDate() {
//            return getItem() == null ? LocalDate.now() : getItem().toLocalDate();
//        }
//    }


public class PrototypeController{
    SuiviService suivser = new SuiviService();
    ArrayList listesuivi = new ArrayList();
    ObservableList<String> trieList = FXCollections.observableArrayList("identifiant","Titre","Date","Temps");
    ObservableList<Suivi> SuiviList = FXCollections.observableArrayList();
    private final FilteredList <Suivi> filter = new FilteredList <> (SuiviList, e -> true);
    private final SortedList <Suivi> sortsuivi = new SortedList<>(filter);
    @FXML
    private DatePicker date_deb;

    @FXML
    private DatePicker date_fin;

    @FXML
    private JFXTimePicker temp_deb;

    @FXML
    private JFXTimePicker temps_fin;

    @FXML
    private TextField client;
    
    @FXML
    private TextField titre;
    
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
    private TextField tabrecherche;
    
    
    @FXML
    void AjouterSuiv(ActionEvent event) {
//        Date date = Date.from(date_deb.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
//        Suivi nouvsuiv = new Suivi(client.getText(),titre.getText(),Date.from(date_deb.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),Date.from(date_fin.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()), Time.valueOf(temp_deb.getValue()),Time.valueOf(temps_fin.getValue()));
        Suivi nouvsuiv = new Suivi(client.getText(),titre.getText(),Date.valueOf(date_deb.getValue()), Date.valueOf(date_fin.getValue()), Time.valueOf(temp_deb.getValue()),Time.valueOf(temps_fin.getValue()));
        suivser.ajouterSuivi(nouvsuiv);
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
    }
    
    public void changeCellTitre(CellEditEvent editcell){
        Suivi suiviSelected = suivitable.getSelectionModel().getSelectedItem();
        String newvar = editcell.getNewValue().toString();
        suiviSelected.setTitre_s(newvar);
    }
    
    public void changeCellDateDeb(CellEditEvent editcell){
        Suivi suiviSelected = suivitable.getSelectionModel().getSelectedItem();
        Date newvar = Date.valueOf(editcell.getNewValue().toString());
        suiviSelected.setDate_ds(newvar);
    }
    
    public void changeCellDateFin(CellEditEvent editcell){
        Suivi suiviSelected = suivitable.getSelectionModel().getSelectedItem();
        Date newvar = Date.valueOf(editcell.getNewValue().toString());
        suiviSelected.setDate_fs(newvar);
    }
    
    public void changeCellTempsDeb(CellEditEvent editcell){
        Suivi suiviSelected = suivitable.getSelectionModel().getSelectedItem();
        Time newvar = Time.valueOf(editcell.getNewValue().toString());
        suiviSelected.setTemps_ds(newvar);
    }
    public void changeCellTempsFin(CellEditEvent editcell){
        Suivi suiviSelected = suivitable.getSelectionModel().getSelectedItem();
        Time newvar = Time.valueOf(editcell.getNewValue().toString());
        suiviSelected.setTemps_fs(newvar);
    }
    public void initialize() {
        trie.setItems(trieList);
        trie.setValue("identifiant");
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
        
        client_table.setCellFactory(new Callback<TableColumn<Suivi, String>, TableCell<Person, String>>() {
    @Override
    public TableCell<Person, String> call(TableColumn<Person, String> col) {
        final TableCell<Person, String> cell = new TableCell<>();
        cell.textProperty().bind(cell.itemProperty()); // in general might need to subclass TableCell and override updateItem(...) here
        cell.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton == MouseButton.SECONDARY) {
                    // handle right click on cell...
                    // access cell data with cell.getItem();
                    // access row data with (Person)cell.getTableRow().getItem();
                }
            }
        });
        return cell ;
    }
});
    }
    
}
