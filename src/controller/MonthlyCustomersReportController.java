package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import utils.ContactDB;
import utils.CustDB;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class MonthlyCustomersReportController implements Initializable{
    public Button reports;
    public Button home;
    public ComboBox monthCB;
    public ComboBox typeCB;
    public Button generateBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        typeCB.getItems().addAll("Initial Meeting", "Follow-Up Consultation", "Lunch Meeting", "Closing Session");
        //temporarily hard coding month
        monthCB.getItems().addAll("January", "February", "March", "April", "May", "June", "July", "August","September",
                "October", "November", "December");



    }

    public void onReportsBtn(ActionEvent actionEvent) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/view/ReportsView.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Reports");
        Scene scene = new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }

    public void onHomeBtn(ActionEvent actionEvent) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/view/HomepageWindow.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Scheduler Homepage");
        Scene scene = new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }

    public void onGenerateRpt(ActionEvent actionEvent) {
        //() to click to generate report based on user month & type selection

    }
}
