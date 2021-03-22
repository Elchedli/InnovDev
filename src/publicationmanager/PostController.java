/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package publicationmanager;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.sql.*;
import pi.*;
import ServicesPublication.*;
import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableColumnBase;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.WindowEvent;

import javafx.util.converter.IntegerStringConverter;

/**
 *
 * @author HP I5
 */
public class PostController implements Initializable {
    ConnectionDB cnx = new ConnectionDB();

    private Label label;
    @FXML
    private Button btn_post;
    @FXML
    private TableView<?> tableView_Publication;
    @FXML
    private TextArea text_area;
    @FXML
    private TableColumn<?, String> Col_username;
    @FXML
    private TableColumn<Publication, String> Col_Texte;
    @FXML
    private TableColumn<Publication, Integer> Col_likes;
    @FXML
    private TableColumn<?, ?> Col_date;
    @FXML
    private TextField tf_ph;
    @FXML
    private TableView<?> tableView_Links;
    @FXML
    private TableColumn<Photo, String> Col_Links;
    @FXML
    private TextField tf_id;
    
    private void handleButtonAction(ActionEvent event) {
        
    }
    public ObservableList<Publication> getPubs(ArrayList<Publication> pubs)
{
    ObservableList<Publication> listPub = FXCollections.observableArrayList();
    for(int i = 0;i<=pubs.size()-1;i++)
    {
        listPub.add(pubs.get(i));
    }
    return listPub;
}
    public ObservableList<Photo> getPhotos(ArrayList<Photo> photos)
{
    ObservableList<Photo> listPhotos = FXCollections.observableArrayList();
    for(int i = 0;i<=photos.size()-1;i++)
    {
        listPhotos.add(photos.get(i));
    }
    return listPhotos;
}
  
    @FXML
    private void Add()
    {
        if(text_area.getText().isEmpty() == false)
        {
        ServicesPublication ps = new ServicesPublication();
        Publication p = new Publication();
        Photo ph = new Photo();
        ServicesPhoto sph = new ServicesPhoto();
        p.setText(text_area.getText());
        ps.insert(p);
        if(tf_ph.getText().isEmpty()==false)
        {
            ph.setId_pub(p.getId());
            ph.setUrl(tf_ph.getText());
            System.out.println(ph.toString());
            sph.insert(ph);
            //System.out.println("test");
        }
        ServicesTags STAG = new ServicesTags();
        STAG.add_relation(p);
        Display();
        text_area.clear();
        tf_ph.clear();
        }
    }
    
    
    
    
    private void Display() {
        ServicesPublication ps= new ServicesPublication();
        Publication p = new Publication();
        //Col_username.setCellValueFactory(new PropertyValueFactory<>("text"));
        Col_Texte.setCellValueFactory(new PropertyValueFactory<>("text"));
        Col_likes.setCellValueFactory(new PropertyValueFactory<>("nb_react"));
        Col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        tableView_Publication.setItems(getPubs(ps.select_all(p)));
    }
    @FXML
public void changeTextCellEvent(CellEditEvent eddited_cell)
{
    Publication pub_editable = (Publication) tableView_Publication.getSelectionModel().getSelectedItem();
    pub_editable.setText(eddited_cell.getNewValue().toString());
    ServicesPublication spub = new ServicesPublication();
    spub.update(pub_editable);
    ServicesTags STAG = new ServicesTags();
    STAG.delete_relation(pub_editable);
    STAG.add_relation(pub_editable);
}
public void Display_link()
{
  
        Object item = tableView_Publication.getSelectionModel().getSelectedItem();
        Publication pub = (Publication) item;
        ServicesPhoto sph = new ServicesPhoto();
        
        Col_Links.setCellValueFactory(new PropertyValueFactory<>("url"));
        tableView_Links.setItems(getPhotos(sph.select_all(pub.getId())));
}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Display();
        //Display_link();
        tableView_Publication.setEditable(true);
        Col_Texte.setCellFactory(TextFieldTableCell.forTableColumn());
        
        ContextMenu contextMenuPub = new ContextMenu();
        MenuItem DeleteItem = new MenuItem("Supprimer Publication");
        MenuItem LikeItem = new MenuItem("Like / Dislike");
        MenuItem DisplayPhotos = new MenuItem("Display Photos");
        
        
                DisplayPhotos.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event)
            {
                
            }
        });
        
        DeleteItem.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event)
            {
                Object item = tableView_Publication.getSelectionModel().getSelectedItem();
                Publication pub = (Publication) item;
                ServicesPublication spub = new ServicesPublication();
                System.out.println(pub.toString());
                spub.drop_track(pub);
                spub.delete(pub);
                Display();
               
            }
        }
        );
        
        
            EventHandler<WindowEvent> event = new EventHandler<WindowEvent>()
            {
                public void handle(WindowEvent e)
                {
                    if(contextMenuPub.isShowing())
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
            if(event.getSource() == tableView_Publication && tableView_Publication.getSelectionModel().getSelectedItem() != null)
            { 
                Object item = tableView_Publication.getSelectionModel().getSelectedItem();
                Publication pub = (Publication) item;
                ServicesPhoto sph = new ServicesPhoto();
        
                Col_Links.setCellValueFactory(new PropertyValueFactory<>("url"));
                tableView_Links.setItems(getPhotos(sph.select_all(pub.getId())));   
            }
           //System.out.println(event.getS);
        }  
          
    };
            LikeItem.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event)
            {
                /*Publication pub_supp = (Publication) tableView_Publication.getSelectionModel().getSelectedItem();
                CellEditEvent eddited_cell;*/
                Object item = tableView_Publication.getSelectionModel().getSelectedItem();
                Publication pub = (Publication) item;
                ServicesPublication spub = new ServicesPublication();
                System.out.println(pub.toString());
                spub.like_post(item,Integer.parseInt(tf_id.getText()));
                Display();
               
            }
        });
            contextMenuPub.getItems().add(LikeItem);
            contextMenuPub.getItems().add(DeleteItem);
            
            contextMenuPub.setOnShowing(event);
            contextMenuPub.setOnHiding(event);
            tableView_Publication.setOnMouseClicked(handler);
            tableView_Publication.setContextMenu(contextMenuPub);
            
    }    

}
