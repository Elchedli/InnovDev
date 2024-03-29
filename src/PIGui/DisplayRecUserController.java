/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIGui;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import PIClass.Reclamation;
import PIServices.ServiceReclamation;
import PIUtils.MyConnection;
import java.awt.Desktop;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author ASuS
 */
public class DisplayRecUserController implements Initializable {

    @FXML
    private TextField search;
    @FXML
    private TableView<Reclamation> table;
    @FXML
    private TableColumn<Reclamation, Integer> rec;
    @FXML
    private TableColumn<Reclamation, String> obj;
    @FXML
    private TableColumn<Reclamation, String> area;
    @FXML
    private TableColumn<Reclamation, String> suj;
    @FXML
    private TableColumn<Reclamation, String> etat;
    @FXML
    private TableColumn<Reclamation, Timestamp> date;
    @FXML
    private Button xbtn;
    @FXML
    private Button home;
    @FXML
    private Button pdf;
    
    private ObservableList<Reclamation> dataList = FXCollections.observableArrayList();
    private FilteredList <Reclamation> filter = new FilteredList <> (dataList, e -> true);
    private SortedList <Reclamation> sort = new SortedList<>(filter);
    
    private Connection cnx = MyConnection.getInstance().getCnx();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Tableview Dynamic Display 
        rec.setCellValueFactory(new PropertyValueFactory<Reclamation, Integer>("id_rec"));
        obj.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("obj_rec"));        
        area.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("area_rec"));        
        suj.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("suj_rec"));
        etat.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("etat_rec"));        
        date.setCellValueFactory(new PropertyValueFactory<Reclamation, Timestamp>("date_rec"));
        ServiceReclamation srec = new ServiceReclamation();
        srec.afficherIdUser(4).forEach(e->{dataList.add(e);});
        //[take id_user connected (ServiceReclamation)
        table.setItems(dataList); 
        
        //Message Welcome
        JOptionPane.showMessageDialog(null,"Welcome !!");
    }    

    @FXML
    private void Menu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Profil.fxml"));
        Parent root = loader.load(); 
        home.getScene().setRoot(root);
        JOptionPane.showMessageDialog(null, "Welcome HOOOME !!");
    }
    
    @FXML
    private void DisplayRec() {
        rec.setCellValueFactory(new PropertyValueFactory<Reclamation, Integer>("id_rec"));
        obj.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("obj_rec"));        
        area.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("area_rec"));        
        suj.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("suj_rec"));
        etat.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("etat_rec"));        
        date.setCellValueFactory(new PropertyValueFactory<Reclamation, Timestamp>("date_rec"));
        ServiceReclamation srec = new ServiceReclamation();
        srec.afficherIdUser(4).forEach(e->{dataList.add(e);});
        table.setItems(dataList);  
    }
    
    @FXML
    private void Search(KeyEvent event) {
        //ServiceReclamation srec = new ServiceReclamation();
        search.setOnKeyReleased(e -> { 
               search.textProperty().addListener((observable, oldValue, newValue) -> {
            filter.setPredicate(Reclamation -> {
                if (newValue == null || newValue.isEmpty()) {
					return true;
				}
                String lowerCaseFilter = newValue.toLowerCase();
                if (Reclamation.getArea_rec().toLowerCase().contains(lowerCaseFilter) ) {
					return true;}
                else  
				    	 return false;
        });
        
        });
        sort.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sort);
    });
    }
    
    @FXML
    private void CreatePDF(ActionEvent event) throws SQLException {
        try {
       Document doc = new Document();
       PdfWriter.getInstance(doc,new FileOutputStream("C:\\Users\\HP\\Desktop\\Esprit\\3eme\\2. PIDev\\PIDev\\src\\com\\spirity\\pdf\\ReclamationsUser.pdf"));
       doc.open();
       
       Image img = Image.getInstance("C:\\Users\\HP\\Desktop\\Esprit\\3eme\\2. PIDev\\PIDev\\src\\com\\spirity\\spirity\\img\\spirity.png");
       img.scaleAbsoluteHeight(60);
       img.scaleAbsoluteWidth(100);
       img.setAlignment(Image.ALIGN_RIGHT);
       doc.add(img);
       
       doc.add(new Paragraph(" "));
       Font font = new Font(Font.FontFamily.TIMES_ROMAN, 28, Font.UNDERLINE, BaseColor.BLACK);
       Paragraph p = new Paragraph("Your list of Reports", font);
       p.setAlignment(Element.ALIGN_CENTER);
       doc.add(p);
       doc.add(new Paragraph(" "));
       doc.add(new Paragraph(" "));
       
       PdfPTable tabpdf = new PdfPTable(5);
       tabpdf.setWidthPercentage(100);
       
       PdfPCell cell;
       cell = new PdfPCell(new Phrase("Object", FontFactory.getFont("Times New Roman", 11)));
       cell.setHorizontalAlignment(Element.ALIGN_CENTER);
       cell.setBackgroundColor(BaseColor.WHITE);
       tabpdf.addCell(cell);
       
       cell = new PdfPCell(new Phrase("Area", FontFactory.getFont("Times New Roman", 11)));
       cell.setHorizontalAlignment(Element.ALIGN_CENTER);
       cell.setBackgroundColor(BaseColor.WHITE);
       tabpdf.addCell(cell);
       
       cell = new PdfPCell(new Phrase("Subject", FontFactory.getFont("Times New Roman", 11)));
       cell.setHorizontalAlignment(Element.ALIGN_CENTER);
       cell.setBackgroundColor(BaseColor.WHITE);
       tabpdf.addCell(cell);
       
       cell = new PdfPCell(new Phrase("Status", FontFactory.getFont("Times New Roman", 11)));
       cell.setHorizontalAlignment(Element.ALIGN_CENTER);
       cell.setBackgroundColor(BaseColor.WHITE);
       tabpdf.addCell(cell);
       
       cell = new PdfPCell(new Phrase("Date", FontFactory.getFont("Times New Roman", 11)));
       cell.setHorizontalAlignment(Element.ALIGN_CENTER);
       cell.setBackgroundColor(BaseColor.WHITE);
       tabpdf.addCell(cell);
       
       Statement st = cnx.createStatement();
       ResultSet rs=st.executeQuery("SELECT obj_rec, area_rec, suj_rec, etat_rec, date_rec FROM Reclamation");
       while(rs.next()){
           cell = new PdfPCell(new Phrase(rs.getString("obj_rec"), FontFactory.getFont("Times New Roman", 11)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.WHITE);
           tabpdf.addCell(cell);
           
           cell = new PdfPCell(new Phrase(rs.getString("area_rec"), FontFactory.getFont("Times New Roman", 11)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.WHITE);
           tabpdf.addCell(cell);
           
           cell = new PdfPCell(new Phrase(rs.getString("suj_rec"), FontFactory.getFont("Times New Roman", 11)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.WHITE);
           tabpdf.addCell(cell);
           
           cell = new PdfPCell(new Phrase(rs.getString("etat_rec"), FontFactory.getFont("Times New Roman", 11)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.WHITE);
           tabpdf.addCell(cell);
           
           cell = new PdfPCell(new Phrase(rs.getString("date_rec"), FontFactory.getFont("Times New Roman", 11)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.WHITE);
           tabpdf.addCell(cell);
       }
       
       doc.add(tabpdf);
       JOptionPane.showMessageDialog(null, "Success !!");
       doc.close();
       Desktop.getDesktop().open(new File("C:\\Users\\HP\\Desktop\\Esprit\\3eme\\2. PIDev\\PIDev\\src\\com\\spirity\\pdf\\ReclamationsUser.pdf"));
       
       Notifications notificationBuilder = Notifications.create()
            .title("Succes").text("Your document has been saved as PDF !!").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
               .position(Pos.CENTER_LEFT)
               .onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event)
                   {
                       System.out.println("clicked ON ");
               }});
       notificationBuilder.darkStyle();
       notificationBuilder.show();
        } catch (DocumentException | HeadlessException | IOException e) {
            System.out.println("ERROR PDF");
            System.out.println(Arrays.toString(e.getStackTrace()));
            System.out.println(e.getMessage());
        }
    }
    
    @FXML
    void LettersOnly(KeyEvent event) {
        if (search.getText().matches(".*[0-9].*")|| search.getText().matches(".*[%-@-_].*")) {
            Alert a2 = new Alert(Alert.AlertType.ERROR);
            a2.setHeaderText(null);
            a2.setContentText("Please enter letters only");
            a2.showAndWait();
        }
    }
    
    @FXML
    private void Exit(ActionEvent event) {
        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        stage.close();
        JOptionPane.showMessageDialog(null, "Are you sure ? :(");  
    }

}
