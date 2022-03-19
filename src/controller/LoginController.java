package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

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
   // private ResourceBundle rb = ResourceBundle.getBundle("Resources/Login", Locale.getDefault());

    public void initialize(URL url, ResourceBundle resourceBundle) {
    LocalDate today = LocalDate.now();
    System.out.println(today);
    zoneIdLbl.setText(ZoneId.systemDefault().getId());


    }


    public void onLoginBtnClicked(ActionEvent actionEvent) throws Exception {

        userLogin();
        Parent root = FXMLLoader.load(getClass().getResource("/view/HomepageWindow.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("Scheduler Homepage");
            Scene scene = new Scene(root, 1000, 600);
            stage.setScene(scene);
            stage.show();
        }

        public boolean userLogin() {

            String userName = usernameTF.getText();
            String userPassword = passwordTF.getText();

            User result = UserDB.getUser(userName);
            if (result != null) {
                return true;
            }else{
                return false;

    }

        }
        //checkDB()
        //babyStep()
    //isUserLogin valid?
    // yes then proceed (right now just println) if no then present erro mess

    // validate login
    // is result valid or null?
    // result == true else no result == false
    // does result exist or not? if result doesn't exist, then false
   /**
    * public Boolean isValidLogin(User userResult){
    * boolean resultStatus
    *     if (result != null)
    *     resultStatus = true;
    *     else
    *     resultStatus=false;
    *     }
    *
    *
    * }


    */
        }

    //getCurrentUser()




