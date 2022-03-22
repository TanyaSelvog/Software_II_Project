package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;
import utils.UserDB;


public class LoginController extends AuthorizedController implements Initializable {


    public Button loginBtn;
    public Label usernameLbl;
    public Label passwordLbl;
    public TextField usernameTF;
    public TextField passwordTF;
    public Label zoneIdLbl;
    private static User currentUser;
    public Label headerLbl;
   public static String password;
    // private ResourceBundle rb = ResourceBundle.getBundle("Resources/Login", Locale.getDefault());

    public void initialize(URL url, ResourceBundle resourceBundle) {

        Locale userLocale = Locale.getDefault();
        zoneIdLbl.setText(ZoneId.systemDefault().toString());
        ResourceBundle rb = ResourceBundle.getBundle("Resources/Login");
        usernameLbl.setText(rb.getString("userNameLabel"));
        passwordLbl.setText(rb.getString("passwordLabel"));
        loginBtn.setText(rb.getString("loginButton"));

    LocalDate today = LocalDate.now();
    System.out.println(today);
    //zoneIdLbl.setText(ZoneId.systemDefault().getId());




    }

    //user this to check to authenticate user
  /**  public void validateUser() {
        if (userLogin() == true){
            onLoginBtnClicked(actionEvent);
        }
        userLogin();

        }
*/
    public void onLoginBtnClicked(ActionEvent actionEvent) throws Exception {
            if (userLogin() != true) {
                returnLoginWindow(actionEvent);
            } else {
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
            System.out.println(userName);
//3.19 working on
            User result = UserDB.getUser(userName, userPassword);
            System.out.println("Result: " + result);

            if (result == null) {
                isLoginValid = false;
                System.out.println("isLoginValid = false " );
            }
        //    if (password.equals(userPassword)){
            //    System.out.println("okay");
          //  }
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








    //getCurrentUser()




