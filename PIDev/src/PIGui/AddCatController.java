/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIGui;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import PIClass.Categories;
import PIServices.ServiceCategories;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author ASuS
 */
public class AddCatController implements Initializable {

    @FXML
    private JFXTextField nomcat;
    @FXML
    private TableView<Categories> tabcat;
    @FXML
    private TableColumn<Categories, String> cat;
    @FXML
    private JFXButton tbtn;
    @FXML
    private JFXButton tbtn1;
    @FXML
    private FontAwesomeIconView btnactualiser;
    @FXML
    private JFXButton listerec;
    @FXML
    private FontAwesomeIconView btnback;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        aff();
        ContextMenu concat = new ContextMenu();
        MenuItem delete = new MenuItem("Supprimer");
        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                /*Publication pub_supp = (Publication) tableView_Publication.getSelectionModel().getSelectedItem();
                CellEditEvent eddited_cell;*/
                Object item = tabcat.getSelectionModel().getSelectedItem();
                Categories sc = (Categories) item;
                ServiceCategories scat = new ServiceCategories();
                System.out.println(sc.toString());
                scat.supprimer(sc);
                aff();

            }
        }
        );

        EventHandler<WindowEvent> event = new EventHandler<WindowEvent>() {
            public void handle(WindowEvent e) {
                if (concat.isShowing()) {
                    // System.out.println("Showing");
                } else {
                    //System.out.println("Hidden");
                }
            }
        };

        concat.getItems().add(delete);
        concat.setOnShowing(event);
        concat.setOnHiding(event);
        tabcat.setContextMenu(concat);
       
    }

    private void aff() {
        ObservableList<Categories> list = FXCollections.observableArrayList();
        cat.setCellValueFactory(new PropertyValueFactory<>("nom_cat"));
        ServiceCategories scat = new ServiceCategories();
        scat.afficher().forEach(e -> {
            list.add(e);
        });
        tabcat.setItems(list);
    }    

    @FXML
    private void AjoutCat(ActionEvent event) {
        if (nomcat.getText().isEmpty()) {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setHeaderText(null);
            al.setContentText("Veuillez remplir les champs vides !");
            al.showAndWait();

        } else if ( nomcat.getText().matches(".*[0-9].*")||nomcat.getText().matches(".*[%-@-.-/-!-;-,-_].*")) {
            Alert a2 = new Alert(Alert.AlertType.ERROR);
            a2.setHeaderText(null);
            a2.setContentText("Veuillez saisir uniquement des lettres !");
            a2.showAndWait();

        } else {

            ServiceCategories scat = new ServiceCategories();
            Categories test = new Categories(nomcat.getText());
            scat.ajout(test);
            aff();
    }
    }
    
    @FXML
    private void EditCat(TableColumn.CellEditEvent edittedCell) {
        if(edittedCell.getNewValue().toString().isEmpty()){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText(null);
            a.setContentText("Veuillez remplir les champs vides !!");
            a.showAndWait();
        }
        else {
        Categories cc = tabcat.getSelectionModel().getSelectedItem();
        cc.setNom_cat(edittedCell.getNewValue().toString());
        ServiceCategories scat = new ServiceCategories();
        scat.modifier(cc);
        JOptionPane.showMessageDialog(null, "Succès !!");}  
        Notifications notificationBuilder = Notifications.create()
            .title("Succès").text("Modifiée avec succès !!").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
               .position(Pos.CENTER_LEFT)
               .onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event)
                   {
                       System.out.println("clicked ON ");
               }});
       notificationBuilder.darkStyle();
       notificationBuilder.show();
    } 
    
    @FXML
    private void Annuler(ActionEvent event) {
        nomcat.setText("");
    }

    @FXML
    private void Menu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionU.fxml"));
        Parent root = loader.load(); 
        listerec.getScene().setRoot(root);
        JOptionPane.showMessageDialog(null, "Bienvenue !!"); 
    }
    
}
