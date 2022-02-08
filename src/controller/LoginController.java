package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.net.URL;
import java.time.LocalDate;
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


public class LoginController implements Initializable {


    public Button loginBtn;
    public Label usernameLbl;
    public Label passwordLbl;
    public TextField usernameTF;
    public TextField passwordTF;
    public Label zoneIdLbl;

   // private ResourceBundle rb = ResourceBundle.getBundle("Resources/Login", Locale.getDefault());

    public void initialize(URL url, ResourceBundle resourceBundle) {
    LocalDate today = LocalDate.now();
    System.out.println(today);
    zoneIdLbl.setText(ZoneId.systemDefault().getId());

           // =  zoneLbl.setText(ZoneId.systemDefault().getId());

    }

    public void onLogin(ActionEvent actionEvent) throws Exception {
            Parent root = FXMLLoader.load(getClass().getResource("/view/HomepageWindow.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("Scheduler Homepage");
            Scene scene = new Scene(root, 1000, 600);
            stage.setScene(scene);
            stage.show();
        }
    }

