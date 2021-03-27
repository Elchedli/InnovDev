/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.Client;
import services.NetworkConnection;
import services.Server;

/**
 *
 * @author shidono
 */
public class ChatClient  extends Application{
    private final boolean isServer = false;
    private final TextArea messages = new TextArea();
    private final NetworkConnection connection = isServer ? createServer(): createCLient();
    
    private Parent createContent(){
        messages.setPrefHeight(550);
        TextField input = new TextField();
        input.setOnAction(event ->{
            String message = isServer ? "Server: " : "Client: ";
            message+=input.getText();
            input.clear();
            messages.appendText(message+"\n");
            try {
                connection.send(message);
            } catch (Exception ex) {
                messages.appendText("Failed to send\n");
            }
        });
        VBox root = new VBox(20,messages,input);
        root.setPrefSize(600,600);
        return root;
    }
    
    @Override
    public void init() throws Exception{
        try {
            connection.startConnection();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.setTitle("Client");
        primaryStage.show();
    }
    
    @Override
    public void stop() throws Exception{
        connection.closeConnection();
    }
    
    private Server createServer(){
        return new Server(1234,data->{
            Platform.runLater(()->{
                messages.appendText(data.toString()+"\n");
            });
        });
    }
    
    private Client createCLient(){
        return new Client("127.0.0.1",1234,data->{
            Platform.runLater(()->{
                messages.appendText(data.toString()+"\n");
            });
        });
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
