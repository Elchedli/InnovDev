/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIGui;
import PIClass.Reclamation;
import PIUtils.MyConnection;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ASuS
 */
public class StatRecController implements Initializable {
 Connection cnx = MyConnection.getInstance().getCnx();
    @FXML
    private PieChart pieChart;
    @FXML
    private Button home;
     private ObservableList data;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
  @Override
    public void initialize(URL url, ResourceBundle rb){
        data = FXCollections.observableArrayList();
        try{
           String SQL = "SELECT count(*)/(select count(*) from reclamation)*100 FROM reclamation  where etat_rec = 'Done';";
            PreparedStatement ste = (PreparedStatement) cnx.prepareStatement(SQL);
            ResultSet rs = ste.executeQuery();
            if(rs.next()){
               double total = rs.getDouble(1);
               data.add(new PieChart.Data("Done",total));
               data.add(new PieChart.Data("To do",100 - total));
            }
            pieChart.setData(data);
          }catch(SQLException e){
              System.out.println("Erreur");
          } 
    }
     /*@Override
    public void initialize(URL url, ResourceBundle rb) {
          data = FXCollections.observableArrayList();
          try{
           String SQL = "SELECT count()/(select count() from voyage)*100 as pays from voyage where promotion_id != '';";
            PreparedStatement ste = (PreparedStatement) conn.prepareStatement(SQL);
            ResultSet rs = ste.executeQuery();
            if(rs.next()){
                data.add(new PieChart.Data("promotion",rs.getDouble(1)));
            }
            SQL = "SELECT count()/(select count() from voyage)*100 as pays from voyage where promotion_id is null;";
            ste = (PreparedStatement) conn.prepareStatement(SQL);
            rs = ste.executeQuery();
            if(rs.next()){
                data.add(new PieChart.Data("sans promotion",rs.getDouble(1)));
            }
            pieChart.setData(data);
          }catch(SQLException e){
              System.out.println("Error on DB connection");
          }
    }*/
//Bouton
    
    @FXML
    private void Menu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DisplayEditRec.fxml"));
        Parent root = loader.load(); 
        home.getScene().setRoot(root);
        JOptionPane.showMessageDialog(null, "Bienvenue !!"); 
    }
    
}
