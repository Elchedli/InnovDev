package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;

public abstract class pasdemessage extends AnchorPane {

    protected final Pane leftpage;
    protected final TextField textField;
    protected final Pane user1;
    protected final Circle circle;
    protected final Label label;
    protected final Label label0;
    protected final Button buttcontact;
    protected final AnchorPane mainpage;
    protected final Label currentuser;
    protected final Label label1;
    protected final Line line;
    protected final Line line0;
    protected final JFXTextField discinput;
    protected final FontAwesomeIconView fontAwesomeIconView;
    protected final Line line1;
    protected final Label discidentity;
    protected final ScrollPane scrollchatroom;
    protected final Pane chatroom;
    protected final JFXTextField mail_titre;
    protected final JFXTextArea mail_contenu;
    protected final FontAwesomeIconView titre_icon;
    protected final JFXButton mailbutt;
    protected final JFXButton jFXButton;
    protected final FontAwesomeIconView fontAwesomeIconView0;

    public pasdemessage() {

        leftpage = new Pane();
        textField = new TextField();
        user1 = new Pane();
        circle = new Circle();
        label = new Label();
        label0 = new Label();
        buttcontact = new Button();
        mainpage = new AnchorPane();
        currentuser = new Label();
        label1 = new Label();
        line = new Line();
        line0 = new Line();
        discinput = new JFXTextField();
        fontAwesomeIconView = new FontAwesomeIconView();
        line1 = new Line();
        discidentity = new Label();
        scrollchatroom = new ScrollPane();
        chatroom = new Pane();
        mail_titre = new JFXTextField();
        mail_contenu = new JFXTextArea();
        titre_icon = new FontAwesomeIconView();
        mailbutt = new JFXButton();
        jFXButton = new JFXButton();
        fontAwesomeIconView0 = new FontAwesomeIconView();

        setId("AnchorPane");
        setPrefHeight(559.0);
        setPrefWidth(960.0);

        leftpage.setPrefHeight(566.0);
        leftpage.setPrefWidth(238.0);
        leftpage.setStyle("-fx-background-color: #272E36;");

        textField.setLayoutX(27.0);
        textField.setLayoutY(23.0);
        textField.setPrefHeight(34.0);
        textField.setPrefWidth(184.0);
        textField.setPromptText("Recherche");
        textField.setStyle("-fx-background-color: #363D45;");

        user1.setLayoutX(6.0);
        user1.setLayoutY(82.0);
        user1.setPrefHeight(72.0);
        user1.setPrefWidth(227.0);

        circle.setFill(javafx.scene.paint.Color.valueOf("#08fc25"));
        circle.setLayoutX(25.0);
        circle.setLayoutY(36.0);
        circle.setRadius(5.0);
        circle.setStroke(javafx.scene.paint.Color.valueOf("#09fc36"));
        circle.setStrokeType(javafx.scene.shape.StrokeType.INSIDE);
        circle.setStrokeWidth(0.0);

        label.setLayoutX(46.0);
        label.setLayoutY(14.0);
        label.setPrefHeight(27.0);
        label.setPrefWidth(133.0);
        label.setText("Ahmed");
        label.setTextFill(javafx.scene.paint.Color.WHITE);
        label.setFont(new Font("Arial Black", 18.0));

        label0.setLayoutX(46.0);
        label0.setLayoutY(41.0);
        label0.setPrefHeight(17.0);
        label0.setPrefWidth(126.0);
        label0.setText("yeah sure i am okay");
        label0.setTextFill(javafx.scene.paint.Color.valueOf("#5b5b5b"));

        buttcontact.setLayoutY(1.0);
        buttcontact.setMnemonicParsing(false);
        buttcontact.setPrefHeight(72.0);
        buttcontact.setPrefWidth(227.0);
        buttcontact.setStyle("-fx-background-color: transparent;");

        mainpage.setLayoutX(238.0);
        mainpage.setPrefHeight(562.0);
        mainpage.setPrefWidth(721.0);

        currentuser.setLayoutX(21.0);
        currentuser.setLayoutY(14.0);
        currentuser.setPrefHeight(46.0);
        currentuser.setPrefWidth(113.0);
        currentuser.setText("Ahmed ");
        currentuser.setTextFill(javafx.scene.paint.Color.valueOf("#6b6565"));
        currentuser.setFont(new Font("Arial Black", 24.0));

        label1.setLayoutX(144.0);
        label1.setLayoutY(23.0);
        label1.setPrefHeight(27.0);
        label1.setPrefWidth(106.0);
        label1.setText("is typing ...");
        label1.setTextFill(javafx.scene.paint.Color.valueOf("#d0c8c8"));
        label1.setFont(new Font("Arial Bold", 18.0));

        line.setEndX(620.0);
        line.setLayoutX(101.0);
        line.setLayoutY(73.0);
        line.setOpacity(0.22);
        line.setStartX(-100.0);
        line.setStroke(javafx.scene.paint.Color.valueOf("#8d7f7f"));

        line0.setEndX(118.0);
        line0.setEndY(-8.500015258789062);
        line0.setLayoutX(533.0);
        line0.setLayoutY(82.0);
        line0.setOpacity(0.22);
        line0.setStartX(118.0);
        line0.setStartY(-81.0);
        line0.setStroke(javafx.scene.paint.Color.valueOf("#8d7f7f"));

        discinput.setLayoutX(3.0);
        discinput.setLayoutY(526.0);

        fontAwesomeIconView.setLayoutX(694.0);
        fontAwesomeIconView.setLayoutY(553.0);

        line1.setEndX(620.0);
        line1.setLayoutX(101.0);
        line1.setLayoutY(526.0);
        line1.setOpacity(0.22);
        line1.setStartX(-100.0);
        line1.setStroke(javafx.scene.paint.Color.valueOf("#8d7f7f"));

        discidentity.setLayoutX(586.0);
        discidentity.setLayoutY(36.0);
        discidentity.setOpacity(0.0);
        discidentity.setText("Label");

        scrollchatroom.setLayoutY(74.0);
        scrollchatroom.setPrefHeight(449.0);
        scrollchatroom.setPrefWidth(725.0);

        chatroom.setMaxHeight(USE_PREF_SIZE);
        chatroom.setMinHeight(USE_PREF_SIZE);
        chatroom.setPrefHeight(447.0);
        chatroom.setPrefWidth(703.0);

        mail_titre.setLayoutX(204.0);
        mail_titre.setLayoutY(53.0);

        mail_contenu.setLayoutX(91.0);
        mail_contenu.setLayoutY(118.0);

        titre_icon.setLayoutX(162.0);
        titre_icon.setLayoutY(77.0);

        mailbutt.setLayoutX(255.0);
        mailbutt.setLayoutY(342.0);
        scrollchatroom.setContent(chatroom);

        jFXButton.setLayoutX(660.0);
        jFXButton.setLayoutY(9.0);

        jFXButton.setGraphic(fontAwesomeIconView0);

        leftpage.getChildren().add(textField);
        user1.getChildren().add(circle);
        user1.getChildren().add(label);
        user1.getChildren().add(label0);
        user1.getChildren().add(buttcontact);
        leftpage.getChildren().add(user1);
        getChildren().add(leftpage);
        mainpage.getChildren().add(currentuser);
        mainpage.getChildren().add(label1);
        mainpage.getChildren().add(line);
        mainpage.getChildren().add(line0);
        mainpage.getChildren().add(discinput);
        mainpage.getChildren().add(fontAwesomeIconView);
        mainpage.getChildren().add(line1);
        mainpage.getChildren().add(discidentity);
        chatroom.getChildren().add(mail_titre);
        chatroom.getChildren().add(mail_contenu);
        chatroom.getChildren().add(titre_icon);
        chatroom.getChildren().add(mailbutt);
        mainpage.getChildren().add(scrollchatroom);
        mainpage.getChildren().add(jFXButton);
        getChildren().add(mainpage);

    }
}
