/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PIGui;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.sql.*;
import PIClass.*;
import PIServices.ServicesPhoto;
import PIServices.ServicesPublication;
import PIServices.ServicesTags;
import PIUtils.MyConnection;
//import com.sun.xml.internal.bind.v2.runtime.property.PropertyFactory;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableColumnBase;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.geometry.Pos;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import javafx.util.converter.IntegerStringConverter;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import org.controlsfx.control.*;
import org.controlsfx.control.PropertySheet.Item;

/**
 *
 * @author HP I5
 */
public class EditPublicationController implements Initializable {
    MyConnection cnx = new MyConnection();
    Publication p;

    
    public void set_p(Publication pub)
    {
        p = pub;
        System.out.println(p.toString());
    }
    @FXML
    private ListView<String> lv_username;
    @FXML
    private Button btn_post;
    @FXML
    private TableView<Publication> tableView_Publication;
    @FXML
    private TableColumn<Publication, String> Col_Texte;
    @FXML
    private TableColumn<Publication, Integer> Col_likes;
    @FXML
    private TableColumn<Publication, String> Col_date;
    @FXML
    private TextArea text_area;
    @FXML
    private TableView<?> tableView_Links;
    @FXML
    private TableColumn<?, ?> Col_Links;
    @FXML
    private TextField tf_ph;
    @FXML
    private Button btn_back;
    @FXML
    private Button btn_refresh;
    @FXML
    private Button btn_addpic;
    @FXML
    private TextField tf_url;
    @FXML
    private TextField tf_search;
    @FXML
    private Button btn_search;

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
    
    public ObservableList<String> getUsername(String usernmae)
{
    ObservableList<String> listPub = FXCollections.observableArrayList();
   listPub.add(usernmae);
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
    private void Add() throws IOException
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
        else
        {
            
            ph.setId_pub(p.getId());
            String url = test();
            if(url != null)
            {
            ph.setUrl(url);
            System.out.println(ph.toString());
            sph.insert(ph);
            }
        }
        ServicesTags STAG = new ServicesTags();
        STAG.add_relation(p);
        Display();
        text_area.clear();
        tf_ph.clear();
        }
    }
    
    
        
    @FXML
    public void Display() {

        ServicesPublication ps= new ServicesPublication();
        //Col_username.setCellValueFactory(new PropertyValueFactory<>(String.valueOf(username)));
        Col_Texte.setCellValueFactory(new PropertyValueFactory<>("text"));
        Col_likes.setCellValueFactory(new PropertyValueFactory<>("nb_react"));
        Col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        

        tableView_Publication.setItems(getPubs(ps.return_Pub(p.getId())));
        System.out.println(getPubs(ps.return_Pub(p.getId())));
    }

    @FXML
    public void changeTextCellEvent(CellEditEvent eddited_cell)
{
    Publication pub_editable = (Publication) tableView_Publication.getSelectionModel().getSelectedItem();
    if(eddited_cell.getNewValue().toString().isEmpty() == false)
    {
    pub_editable.setText(eddited_cell.getNewValue().toString());
    ServicesPublication spub = new ServicesPublication();
    spub.update(pub_editable);
    ServicesTags STAG = new ServicesTags();
    STAG.delete_relation(pub_editable);
    STAG.add_relation(pub_editable);
    }
    notif("Text","Text changé avec succée !");
    Display();
}
public void Display_link()
{
  
        Object item = tableView_Publication.getSelectionModel().getSelectedItem();
        Publication pub = (Publication) item;
        ServicesPhoto sph = new ServicesPhoto();
        
        Col_Links.setCellValueFactory(new PropertyValueFactory<>("url"));
        tableView_Links.setItems(getPhotos(sph.select_all(pub.getId())));
        
}
@FXML
    public void add_pic() throws IOException
    {
        Object item = tableView_Publication.getSelectionModel().getSelectedItem();
        Publication p = (Publication) item;
        ServicesPhoto sph = new ServicesPhoto();
        Photo ph = new Photo();
        if(tf_url.getText().isEmpty() == false)
        {
            System.out.println(p.getId());
            ph.setUrl(tf_url.getText());
            ph.setId_pub(p.getId());
            sph.insert(ph);
            tableView_Links.getItems().clear();
            Col_Links.setCellValueFactory(new PropertyValueFactory<>("url"));
            tableView_Links.setItems(getPhotos(sph.select_all(p.getId())));
            
    notif("Photo", "Photo Ajouté avec succée !");
        }
        else if(tf_url.getText().isEmpty() == true)
        {
            if(notif_choix() == 0)
            {
                String url = test();
                     if(url != null)
                     {
                         ph.setId_pub(p.getId());
                         ph.setUrl(url);
                         System.out.println(ph.toString());
                         sph.insert(ph);

                         tableView_Links.getItems().clear();
                         Col_Links.setCellValueFactory(new PropertyValueFactory<>("url"));
                         tableView_Links.setItems(getPhotos(sph.select_all(p.getId())));
                         notif("Photo", "Photo Ajouté avec succée !");
                    }
            }
        }
        
        //ph.setUrl(url);
        
    }
    public int notif_choix()
{
        int dialogButton = 0;
        dialogButton = JOptionPane.showConfirmDialog (null, "You didn't add a link, do you wanna upload an Image ?","Image", dialogButton);
        System.out.println(dialogButton);
        if (dialogButton == 0) {
    return 0;
} else {
    return 1;
}
}
public void notif(String title, String text)
{
    Notifications notificationBuilder = Notifications.create()
    .title(title)
    .text(text)
    .hideAfter(javafx.util.Duration.seconds(5))
    .position(Pos.TOP_CENTER);
    notificationBuilder.show();
}
public ArrayList<Publication> Search_by_tag()
    {
        ServicesTags STAG = new ServicesTags();
        ArrayList<Publication> listPub = new ArrayList<>();
        if(tf_search.getText().isEmpty() == false)
        {
           ArrayList<String> tags = new ArrayList<>();
           tags = STAG.Check_hashtags(tf_search.getText());
           for(int i = 0; i<=tags.size()-1;i++)
           {
               Tag t = new Tag();
               t.setName(tags.get(i));
               int id_tag = STAG.fetchId(t);
              if(id_tag != -1)
               {
                  ArrayList<Integer> id_pubs = new ArrayList<>();
                  id_pubs = STAG.get_pubs_from_tag(id_tag);
                  ServicesPublication spub = new ServicesPublication();
                  for(int j = 0 ; j<=id_pubs.size()-1;j++)
                  {
                      Publication p = new Publication();
                      p = spub.fetch_pub(id_pubs.get(j));
                      //System.out.println(p.toString());
                      listPub.add(p);
                  }
               }
           }
        }
        for (int i = 0; i<=listPub.size()-1;i++)
        {
            int id = listPub.get(i).getId();
            for(int j=i+1;j<=listPub.size()-1;j++)
            {
                if(id==listPub.get(j).getId())
                {
                    listPub.remove(j);
                    break;
                }
            }
        }
        return listPub;
    }
        
        @FXML
        public void search()
        {
        ObservableList<Publication> listPub = getPubs(Search_by_tag());
        tableView_Publication.setItems(listPub);
        }



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //tf_id.setText("1");
       // Display();
        tableView_Publication.setEditable(true);
        Col_Texte.setCellFactory(TextFieldTableCell.forTableColumn());
        tableView_Publication.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        ContextMenu contextMenuPub = new ContextMenu();
        ContextMenu contextMenuPhoto = new ContextMenu();
        MenuItem DeleteItem = new MenuItem("Supprimer");
        MenuItem LikeItem = new MenuItem("Like / Dislike");
        MenuItem DisplayPhotos = new MenuItem("Afficher");
        MenuItem DeletePhoto = new MenuItem("Supprimer");
        
          DeletePhoto.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event)
            {
                Object item = tableView_Links.getSelectionModel().getSelectedItem();
                Object item2 = tableView_Publication.getSelectionModel().getSelectedItem();
                Publication pub = (Publication) item2;
                Photo ph = (Photo) item;
                System.out.println(ph.toString());
                ServicesPhoto sph = new ServicesPhoto();
                sph.delete(ph);
                tableView_Links.getItems().clear();
                Col_Links.setCellValueFactory(new PropertyValueFactory<>("url"));
                tableView_Links.setItems(getPhotos(sph.select_all(pub.getId())));
                notif("Photo","Suppression avec succée !");

            }
        });
        
                DisplayPhotos.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event)
            {
                Object item = tableView_Links.getSelectionModel().getSelectedItem();
                Object item2 = tableView_Publication.getSelectionModel().getSelectedItem();
                Photo ph = (Photo) item;
                Publication pub = (Publication) item2;
                ServicesPhoto sph = new ServicesPhoto();
                ArrayList array= new ArrayList<String>();
                array = sph.get_LinksByIdPub(pub.getId());
                display_ph(array);
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
                notif("Publication","Suppression avec succée !");
               tableView_Links.getItems().clear();
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
                btn_addpic.setDisable(false);
                tf_url.setDisable(false);
            }
            else
                     {
                         btn_addpic.setDisable(true);
                         tf_url.setDisable(true);
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
                spub.like_post(item,1);
                Display();
                notif("Like","Like updated !");
               
            }
        });
            contextMenuPub.getItems().add(LikeItem);
            contextMenuPub.getItems().add(DeleteItem);
            contextMenuPhoto.getItems().add(DisplayPhotos);
            contextMenuPhoto.getItems().add(DeletePhoto);
            
            contextMenuPub.setOnShowing(event);
            contextMenuPub.setOnHiding(event);
            tableView_Publication.setOnMouseClicked(handler);
            tableView_Publication.setContextMenu(contextMenuPub);
            
            tableView_Links.setContextMenu(contextMenuPhoto);
            
    }    
    void load_FrontEnd()
    {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FrontEnd_Publication.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setScene(new Scene(root1));
            stage.show();
        }
        catch(Exception e)
        {
            System.out.println("Can't Load new window");
        }
    }
    public void display_ph(ArrayList<String> links)
    {
     try{
         FXMLLoader loader = new FXMLLoader();
         loader.setLocation(getClass().getResource("PhotoDisplay.fxml"));
         Parent photo_view = loader.load();
         
            Scene photo_view_scene = new Scene(photo_view);
            
            PhotoDisplayController con = loader.getController();
            con.list_pics= links;
            con.load_pic_index(links,0);
            Stage stage = new Stage();
            stage.setScene(photo_view_scene);
            stage.setResizable(false);
            stage.setTitle("Photo");
            //stage.initStyle(StageStyle.UNDECORATED);
            
            
            
            stage.show();
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }
            
    }
    @FXML
        public void refresh()
        {
            if(tf_search.getText().isEmpty() == true)
            {
                Display();  
            }
        }
        public String test() throws IOException
        {
                   FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Open Resource File");
        ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.png");
        ExtensionFilter extFilter2 = new FileChooser.ExtensionFilter("*.png","Image Files");
        ExtensionFilter extFilter3 = new FileChooser.ExtensionFilter("*.jpeg","*.png");
        ExtensionFilter extFilter4 = new FileChooser.ExtensionFilter("*.jpg","*.png");
        ExtensionFilter extFilter5 = new FileChooser.ExtensionFilter("*.gif","*.png");
        fileChooser.getExtensionFilters().addAll(extFilter);
        fileChooser.getExtensionFilters().addAll(extFilter2);
        fileChooser.getExtensionFilters().addAll(extFilter3);
        fileChooser.getExtensionFilters().addAll(extFilter4);
        fileChooser.getExtensionFilters().addAll(extFilter5);
        File selectedFile = fileChooser.showOpenDialog(null);
        
        if (selectedFile != null) {
            BufferedImage bufferedImage = ImageIO.read(selectedFile);
            WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
            System.out.println(selectedFile.getPath());
            return selectedFile.toURI().toURL().toString();
        } 
        return null;
        }
        
       
    
}