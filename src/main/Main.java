package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.ConnectionJDBC;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Application main class
 */
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/LoginWindow.fxml"));
        primaryStage.setTitle("Welcome to Scheduler!");
        primaryStage.setScene(new Scene(root, 1000, 600));
        primaryStage.show();

    }

    /**
     * Application main method and starts application and starts DB connection
     * @param args
     */
    public static void main(String [] args){
        //For testing
       // Locale.setDefault(new Locale("fr"));


        launch(args);
        ConnectionJDBC.openConnection();
    }



}