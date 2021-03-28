/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;
import controller.MessageController;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author shidono
 */
public class PrototypePiChedli extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Prototype.fxml"));
        Parent root = loader.load();
//        Parent root = FXMLLoader.load(getClass().getResource("../gui/Prototype.fxml"));
//        MessageController controller = loader.getController();
        Scene scene = new Scene(root);
//        stage.setOnHidden(e -> {
//            try {
//                controller.stop();
//            } catch (Exception ex) {
//                Logger.getLogger(PrototypePiChedli.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        });
//        scene.getStylesheets().add("design/design.css");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void stop(){
        System.out.println("Stage is closing");
        // Save file
    }
    
}
