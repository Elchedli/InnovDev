package gui;

import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;

public abstract class messageBase extends AnchorPane {

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
    protected final FontAwesomeIconView fontAwesomeIconView;
    protected final JFXTextField discinput;
    protected final FontAwesomeIconView fontAwesomeIconView0;
    protected final Pane chatroom;
    protected final HBox incoming;
    protected final Label messagecontent;
    protected final HBox outgoing;
    protected final Label messagecontent2;
    protected final Line line1;

    public messageBase() {

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
        fontAwesomeIconView = new FontAwesomeIconView();
        discinput = new JFXTextField();
        fontAwesomeIconView0 = new FontAwesomeIconView();
        chatroom = new Pane();
        incoming = new HBox();
        messagecontent = new Label();
        outgoing = new HBox();
        messagecontent2 = new Label();
        line1 = new Line();

        setId("AnchorPane");
        setPrefHeight(556.0);
        setPrefWidth(959.0);

        leftpage.setPrefHeight(562.0);
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

        fontAwesomeIconView.setLayoutX(671.0);
        fontAwesomeIconView.setLayoutY(47.0);

        discinput.setLayoutX(3.0);
        discinput.setLayoutY(526.0);

        fontAwesomeIconView0.setLayoutX(694.0);
        fontAwesomeIconView0.setLayoutY(553.0);

        chatroom.setLayoutX(-1.0);
        chatroom.setLayoutY(74.0);
        chatroom.setPrefHeight(450.0);
        chatroom.setPrefWidth(721.0);
        chatroom.setStyle("-fx-background-color: #E9E9E9;");

        incoming.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
        incoming.setLayoutX(20.0);
        incoming.setLayoutY(31.0);
        incoming.setPrefHeight(46.0);
        incoming.setPrefWidth(178.0);
        incoming.getStyleClass().add("incoming-bubble");
        incoming.getStylesheets().add("/gui/../design/design.css");

        messagecontent.setPrefHeight(45.0);
        messagecontent.setPrefWidth(146.0);
        messagecontent.setText("ddd");
        messagecontent.setTextFill(javafx.scene.paint.Color.WHITE);
        messagecontent.setFont(new Font(20.0));
        HBox.setMargin(messagecontent, new Insets(0.0, 0.0, 0.0, 10.0));

        outgoing.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
        outgoing.setLayoutX(537.0);
        outgoing.setLayoutY(99.0);
        outgoing.setPrefHeight(46.0);
        outgoing.setPrefWidth(178.0);
        outgoing.getStyleClass().add("outgoing-bubble");
        outgoing.getStylesheets().add("/gui/../design/design.css");

        messagecontent2.setPrefHeight(45.0);
        messagecontent2.setPrefWidth(205.0);
        messagecontent2.setText("ddd");
        messagecontent2.setTextFill(javafx.scene.paint.Color.WHITE);
        messagecontent2.setFont(new Font(20.0));
        HBox.setMargin(messagecontent2, new Insets(0.0, 0.0, 0.0, 10.0));

        line1.setEndX(620.0);
        line1.setLayoutX(101.0);
        line1.setLayoutY(526.0);
        line1.setOpacity(0.22);
        line1.setStartX(-100.0);
        line1.setStroke(javafx.scene.paint.Color.valueOf("#8d7f7f"));

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
        mainpage.getChildren().add(fontAwesomeIconView);
        mainpage.getChildren().add(discinput);
        mainpage.getChildren().add(fontAwesomeIconView0);
        incoming.getChildren().add(messagecontent);
        chatroom.getChildren().add(incoming);
        outgoing.getChildren().add(messagecontent2);
        chatroom.getChildren().add(outgoing);
        mainpage.getChildren().add(chatroom);
        mainpage.getChildren().add(line1);
        getChildren().add(mainpage);

    }
}
