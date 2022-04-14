package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Appointments;
import model.User;
import utils.UserDB;

import static utils.ApptsDB.getUserAppt;


public class LoginController implements Initializable {


    public Button loginBtn;
    public Label usernameLbl;
    public Label passwordLbl;
    public TextField usernameTF;
    public TextField passwordTF;
    public Label zoneIdLbl;


    public Label headerLbl;
   public static String password;
    public static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("hh:mm a");
    public static LocalDateTime loginTime;


    // private ResourceBundle rb = ResourceBundle.getBundle("Resources/Login", Locale.getDefault());

    public void initialize(URL url, ResourceBundle resourceBundle) {


        Locale userLocale = Locale.getDefault();
        zoneIdLbl.setText(ZoneId.systemDefault().toString());
        ResourceBundle rb = ResourceBundle.getBundle("Resources/Login");
        usernameLbl.setText(rb.getString("userNameLabel"));
        passwordLbl.setText(rb.getString("passwordLabel"));
        loginBtn.setText(rb.getString("loginButton"));
/**
    LocalDate today = LocalDate.now();
        LocalDateTime loginTime = LocalDateTime.now();
        String login = dtf.format(loginTime);
        System.out.println("String login: " + login + " this currently is activated at init; will need to move to work with login button");
*/
    //zoneIdLbl.setText(ZoneId.systemDefault().getId());




    }
    public void trackUserLogin() throws IOException{
        String userName = usernameTF.getText();
        String userPassword = passwordTF.getText();
        User currentUser = UserDB.getUser(userName, userPassword);
        LocalDate loginAttempt = LocalDateTime.now().toLocalDate();
        Timestamp timeAttempt = Timestamp.valueOf(LocalDateTime.now());
        FileWriter fw = new FileWriter("login_activity.txt", true);
        PrintWriter output = new PrintWriter(fw);
        output.println("User " + currentUser  + " successfully logged in on " + timeAttempt + " UTC. ");
        output.close();
    }
    public void invalidUser() throws IOException{
        String userName = usernameTF.getText();
        LocalDate loginAttempt = LocalDateTime.now().toLocalDate();
        Timestamp timeAttempt = Timestamp.valueOf(LocalDateTime.now());
        FileWriter fw = new FileWriter("login_activity.txt", true);
        PrintWriter output = new PrintWriter(fw);
        output.println("User " + userName  + " was unable to log in on " + timeAttempt + " UTC. ");
        output.close();
    }
    public void onLoginBtnClicked(ActionEvent actionEvent) throws Exception {
        if (userLogin() != true) {
            returnLoginWindow(actionEvent);
            invalidUser();
        } else {
            getUserAppt();
            trackUserLogin();
            Alert msg = new Alert(Alert.AlertType.INFORMATION, ("You have no appointments in the next 15 minutes."));
            msg.setTitle("No appointments");
            msg.showAndWait();
            System.out.println("User has no appointments in the next 15 minutes.");
            Parent root = FXMLLoader.load(getClass().getResource("/view/HomepageWindow.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("Scheduler Homepage");
            Scene scene = new Scene(root, 1000, 600);
            stage.setScene(scene);
            stage.show();}}

        public boolean userLogin() {
            boolean isLoginValid = true;
            String userName = usernameTF.getText();
            String userPassword = passwordTF.getText();
//3.19 working on

            User currentUser = UserDB.getUser(userName, userPassword);
            System.out.println("Result: " + currentUser);

            if (currentUser == null) {
                isLoginValid = false;
                System.out.println("isLoginValid = false " );
            }
            if (currentUser!=null){
               // currentUser = result;
                System.out.println("Current user from LoginController: " + currentUser);
            }
            return isLoginValid;

    }


        public void returnLoginWindow(ActionEvent actionEvent) throws IOException{
            Parent root = FXMLLoader.load(getClass().getResource("/view/loginWindow.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("Scheduler Homepage");
            Scene scene = new Scene(root, 1000, 600);
            stage.setScene(scene);
            stage.show();
        }}







