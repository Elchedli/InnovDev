package gui;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public abstract class PrototypeBase extends AnchorPane {

    protected final Pane pane;
    protected final JFXButton suivibutt;
    protected final FontAwesomeIconView fontAwesomeIconView;
    protected final JFXButton messbutt;
    protected final FontAwesomeIconView fontAwesomeIconView0;
    protected final JFXButton taskbutt;
    protected final FontAwesomeIconView fontAwesomeIconView1;
    protected final AnchorPane MainPage;

    public PrototypeBase() {

        pane = new Pane();
        suivibutt = new JFXButton();
        fontAwesomeIconView = new FontAwesomeIconView();
        messbutt = new JFXButton();
        fontAwesomeIconView0 = new FontAwesomeIconView();
        taskbutt = new JFXButton();
        fontAwesomeIconView1 = new FontAwesomeIconView();
        MainPage = new AnchorPane();

        setId("AnchorPane");
        setPrefHeight(556.0);
        setPrefWidth(1010.0);

        pane.setLayoutX(-1.0);
        pane.setLayoutY(-1.0);
        pane.setPrefHeight(560.0);
        pane.setPrefWidth(74.0);
        pane.setStyle("-fx-background-color: #1D232A;");
        suivibutt.setLayoutX(3.0);
        suivibutt.setLayoutY(133.0);

        suivibutt.setGraphic(fontAwesomeIconView);

        messbutt.setLayoutX(4.0);
        messbutt.setLayoutY(208.0);

        messbutt.setGraphic(fontAwesomeIconView0);

        taskbutt.setLayoutX(4.0);
        taskbutt.setLayoutY(284.0);

        taskbutt.setGraphic(fontAwesomeIconView1);

        MainPage.setLayoutX(73.0);
        MainPage.setPrefHeight(556.0);
        MainPage.setPrefWidth(959.0);

        pane.getChildren().add(suivibutt);
        pane.getChildren().add(messbutt);
        pane.getChildren().add(taskbutt);
        getChildren().add(pane);
        getChildren().add(MainPage);

    }
}
