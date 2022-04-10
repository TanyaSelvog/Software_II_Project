package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointments;
import utils.ApptsDB;
import utils.ContactDB;
import utils.CustDB;
import javafx.fxml.Initializable;

import java.net.URL;
import java.time.Month;
import java.util.ResourceBundle;

public class MonthlyCustomersReportController implements Initializable {
    public Button reports;
    public Button home;


    public ComboBox<Month> monthCB;
    public ComboBox typeCB;
    public Button generateBtn;
    public TextArea reportsTA;
    public TableView monthCust;
    public TableColumn monthCol;
    public TableColumn typeCol;
    public TableColumn apptCol;
    private int month;
    private static ObservableList<String> m = FXCollections.observableArrayList("January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December");

    private static ObservableList<Month> months = FXCollections.observableArrayList(Month.JANUARY, Month.FEBRUARY, Month.MARCH,
            Month.APRIL, Month.MAY, Month.JUNE, Month.JULY, Month.AUGUST, Month.SEPTEMBER, Month.OCTOBER, Month.NOVEMBER, Month.DECEMBER);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        typeCB.getItems().addAll("Initial Meeting", "Follow-Up Consultation", "Lunch Meeting", "Closing Session");
        //NEED TO FIX THIS; 4.9.2022
        monthCol.setCellValueFactory(new PropertyValueFactory<>("monthA"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("apptType"));
        apptCol.setCellValueFactory(new PropertyValueFactory<>("monthB"));

        monthCB.setItems(months);
    }

    public void onReportsBtn(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/ReportsView.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Reports");
        Scene scene = new Scene(root, 1000, 600);

        stage.setScene(scene);
        stage.show();
    }

    public void onHomeBtn(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/HomepageWindow.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Scheduler Homepage");
        Scene scene = new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }

    public void onGenerateRpt(ActionEvent actionEvent) throws Exception{
             ObservableList<Appointments> monthTypeList = FXCollections.observableArrayList();
        //() to click to generate report based on user month & type selection

        month = monthCB.getSelectionModel().getSelectedItem().getValue();
        System.out.println(month + "month from MonthlyController onGenerateReportBtn");
        /**if (ApptsDB.getMonthType(month).isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Appointments");
            alert.setContentText("No appointments in this month.");
            alert.showAndWait();

            //  }

         */
           monthCust.setItems(ApptsDB.getMonthType(month));
           //got text in 2 cols; 3rd col text is wrong and need to figure out month
        //   System.out.println(monthTypeList + "monthtypelist");
          //  System.out.println(ApptsDB.getMonthType(month));



    }
}