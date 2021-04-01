/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIGui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class UserIn_AdminController implements Initializable {

    @FXML
    private TableView<?> tabid;
    @FXML
    private TableColumn<?, ?> colnom;
    @FXML
    private TableColumn<?, ?> coltype;
    @FXML
    private TableColumn<?, ?> colemplacement;
    @FXML
    private TableColumn<?, ?> colDate_deb;
    @FXML
    private TableColumn<?, ?> colDate_fin;
    @FXML
    private TableColumn<?, ?> colTemps_deb;
    @FXML
    private TableColumn<?, ?> colTemps_fin;
    @FXML
    private TableColumn<?, ?> colAge_min;
    @FXML
    private TableColumn<?, ?> colAge_max;
    @FXML
    private TableColumn<?, ?> colemplacement1;
    @FXML
    private TableColumn<?, ?> colidev;
    @FXML
    private TextField cherch;
    @FXML
    private ImageView imageview1;
    @FXML
    private Button back;
    @FXML
    private Button afficher;
    @FXML
    private TextField nom1;
    @FXML
    private Button afficherParActiv;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void cherchetab(KeyEvent event) {
    }

    @FXML
    private void cherchetab(ActionEvent event) {
    }

    @FXML
    private void back(ActionEvent event) {
    }

    @FXML
    private void AfficherActivités(ActionEvent event) {
    }

    @FXML
    private void afficherParActiv(ActionEvent event) {
    }

    
}
