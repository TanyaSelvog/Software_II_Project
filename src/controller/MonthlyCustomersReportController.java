package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Appointments;
import utils.ApptsDB;
import utils.ContactDB;
import utils.CustDB;
import javafx.fxml.Initializable;

import java.net.URL;
import java.time.Month;
import java.util.ResourceBundle;

public class MonthlyCustomersReportController implements Initializable{
    public Button reports;
    public Button home;
    public ComboBox monthCB;
    public ComboBox typeCB;
    public Button generateBtn;
    public TextArea reportsTA;
    public TableView monthCust;
    public TableColumn monthCol;
    public TableColumn typeCol;
    public TableColumn apptCol;
    private int month;

    private static ObservableList<String> months = FXCollections.observableArrayList("January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        typeCB.getItems().addAll("Initial Meeting", "Follow-Up Consultation", "Lunch Meeting", "Closing Session");
        //temporarily hard coding month
        monthCB.setItems(months);



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
        ObservableList<Appointments> monthTypeList = FXCollections.observableArrayList();
        //() to click to generate report based on user month & type selection
        month = monthCB.getSelectionModel().getSelectedIndex()+1;

        if (ApptsDB.getMonthType(month).isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Appointments");
            alert.setContentText("No appointments in this month.");
            alert.showAndWait();

        }
       // reportsTA.setText(ApptsDB.getApptByMonthAndType(month));
        System.out.println(ApptsDB.getMonthType(month));
    }
}
