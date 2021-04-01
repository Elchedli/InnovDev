/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIGui;

import PIServices.ServicesPhoto;
import PIServices.ServicesPublication;
import PIServices.ServicesTags;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import PIClass.Publication;
import PIClass.Tag;

import org.controlsfx.control.*;

import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import PIClass.Photo;
import PIClass.userclient;
import PIGui.EditPublicationController;
import PIGui.PhotoDisplayController;


/**
 * FXML Controller class
 *
 * @author HP I5
 */
public class FrontEnd_PublicationController implements Initializable {

    public int getID_user() {
        return ID_user;
    }
    
    
    
    String url;
    
    @FXML
    private ListView<ListView> lv_postFE;
    @FXML
    private Button btn_postFE;
    @FXML
    private TextArea ta_postFE;

    
    public final int ID_user = 1;
    @FXML
    private TextField tf_search;
    @FXML
    private Button btn_search;
    @FXML
    private AnchorPane anchor_fe;
    @FXML
    private TextField tf_ph;
    
    
    public void changeTextCellEvent(TableColumn.CellEditEvent eddited_cell, ListView l)
{
    Publication pub_editable = (Publication) l.getSelectionModel().getSelectedItem();
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
    display();
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
                      listPub.add(p);
                      
                  }
               }
           }
        }
        for (int i = 0; i<=listPub.size()-1;i++)
        {
            int id = listPub.get(i).getId();
            for(int j =i+1;j<=listPub.size()-1;j++)
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
    
 
    
    
    
    
     public ObservableList<Publication> getPubs(ArrayList<Publication> pubs)
{
    ObservableList<Publication> listPub = FXCollections.observableArrayList();
    for(int i = 0;i<=pubs.size()-1;i++)
    {
        listPub.add(pubs.get(i));
    }
    return listPub;
}
    public void display()
    {
        ServicesPublication ps= new ServicesPublication();
        Publication p = new Publication();
        lv_postFE.setItems(SwitchPubs(ps.select_all(p)));
    }
        public ObservableList<ListView> SwitchPubs(ArrayList<Publication> pubs)
        {
         ObservableList<ListView> listPub = FXCollections.observableArrayList();
         ServicesPublication sp = new ServicesPublication();
         
         for(int i = 0;i<=pubs.size()-1;i++)
    {
        Publication pub = new Publication(pubs.get(i).getId(),pubs.get(i).getUser_id(),pubs.get(i).getNb_react(),pubs.get(i).getText(),pubs.get(i).getDate());
        ServicesPublication spub = new ServicesPublication();
        ServicesPhoto sph = new ServicesPhoto();
        
        ListView l = new ListView();
        Button btn = new Button();
        Button supp = new Button();
        Button btn_ph = new Button();
        Button btn_edit = new Button();
        Button btn_com = new Button();
        Text t = new Text();
        
        t.setWrappingWidth(200);
        t.setText("'' "+pub.getText()+" ''");
        

        

        btn.setText("Like / Dislike");
        btn_com.setText("Comments");
        supp.setText("Supprimer");
        btn_ph.setText("See pics !");
        btn_edit.setText("Edit");
        
                double h =(pub.getText().length())*10 + btn.getHeight() + btn_ph.getHeight() +15;
        
                btn_edit.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event)
            {
               try{
         FXMLLoader loader = new FXMLLoader();
         loader.setLocation(getClass().getResource("EditPublication.fxml"));
         Parent EditWindow = loader.load();
         
            Scene photo_view_scene = new Scene(EditWindow);
            
            EditPublicationController con = loader.getController();
            
            
            Stage stage = new Stage();
            stage.setScene(photo_view_scene);
            stage.setResizable(false);
            stage.setTitle("Photo");
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
            con.set_p(pub);
            //con.username = "Dr. Anas";
            con.Display();
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }
            }
        });
                
                
                
        btn_ph.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event)
            {
                ArrayList array= new ArrayList<String>();
                //String link = (String) sph.get_LinksByIdPub(pub.getId()).get(0);
                array = sph.get_LinksByIdPub(pub.getId());
                //System.out.println(link);
                display_ph(array);
            }
        });
        
        supp.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event)
            {
                spub.drop_track(pub);
                spub.delete(pub);
                    
                display();
                notif("Publication","Suppression avec succée !");
            }
        });
        
        btn.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event)
            {
                spub.like_post(pub,userclient.getId());
                display();
                notif("Publication","Like à jour !");

            }
        });
        
        btn_com.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event)
            {
                
            }
        });
       // System.out.println(pub.getText().length());
        l.setPrefSize(100, 160);
        l.getItems().add(spub.getUsername(pubs.get(i).getUser_id())+" a dit :");
        
        l.getItems().add(t);
        l.getItems().add("Like: "+pubs.get(i).getNb_react());
        l.getItems().add("Date: "+pubs.get(i).getDate());
        l.getItems().add(btn);
        l.getItems().add(btn_com);
        l.getItems().add(btn_ph);
        
        if(userclient.getType() == "psycho" || userclient.getType() == "nutri" || userclient.getType() == "coach" || userclient.getId() == pubs.get(i).getUser_id())
        {
        l.getItems().add(supp);
        l.getItems().add(btn_edit);
        }
        listPub.add(l);
        
    } 

            return listPub;
        }
    /**
     * Initializes the controller class.
     */
        @FXML
         private void Add() throws IOException
    {
        if(ta_postFE.getText().isEmpty() == false)
        {
        ServicesPublication ps = new ServicesPublication();
        Publication p = new Publication();
        Photo ph = new Photo();
        ServicesPhoto sph = new ServicesPhoto();
        p.setText(ta_postFE.getText());
        ps.insert(p);
        notif("Publication","Publication Ajoutée avec succée !");
        if(tf_ph.getText().isEmpty()==false)
        {
            
            ph.setId_pub(p.getId());
            ph.setUrl(tf_ph.getText());
            System.out.println(ph.toString());
            sph.insert(ph);
            //System.out.println("test");
        }
        else if(tf_ph.getText().isEmpty() == true)
        {
             if(notif_choix() == 0)
            {
            ph.setId_pub(p.getId());
            ph.setUrl(test());
            System.out.println(ph.toString());
            sph.insert(ph);
            }
        }
        ServicesTags STAG = new ServicesTags();
        STAG.add_relation(p);
        display();
        ta_postFE.clear();
        tf_ph.clear();
        }
           notif("Publication","Photo Ajoutée avec succée !");
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(userclient.getType());
        if(userclient.getType() == "simple" || userclient.getType() == "admin")
        {
            ta_postFE.setDisable(true);
            btn_postFE.setDisable(true);
            tf_ph.setDisable(true);
           
        }
        display();
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
           // stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }
            
    }
    @FXML
    public void Display_BySearch()
    {
        ServicesPublication ps= new ServicesPublication();
        Publication p = new Publication();
        ObservableList<ListView> OLV = SwitchPubs(Search_by_tag());
        lv_postFE.setItems(OLV);
    }
    
    @FXML
     public void refresh()
        {
            if(tf_search.getText().isEmpty() == true)
            {
                display();
            }
        }
     
     
     public String test() throws IOException
        {
                   FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Open Resource File");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.png");
        FileChooser.ExtensionFilter extFilter2 = new FileChooser.ExtensionFilter("*.png","Image Files");
        FileChooser.ExtensionFilter extFilter3 = new FileChooser.ExtensionFilter("*.jpeg","*.png");
        FileChooser.ExtensionFilter extFilter4 = new FileChooser.ExtensionFilter("*.jpg","*.png");
        FileChooser.ExtensionFilter extFilter5 = new FileChooser.ExtensionFilter("*.gif","*.png");
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
