/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIGui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Islem
 */
public class MenuEventController implements Initializable {
        @FXML
    private Button admin;

    @FXML
    private Button user;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    void admin(ActionEvent event) throws IOException {
          FXMLLoader loader =new FXMLLoader (getClass().getResource("AccEv.fxml"));
        Parent root= loader.load();
           admin.getScene().setRoot(root);

    }

    @FXML
    void user(ActionEvent event) throws IOException {
                FXMLLoader loader =new FXMLLoader (getClass().getResource("UserIn.fxml"));
        Parent root= loader.load();
           user.getScene().setRoot(root);

    }
    
}
