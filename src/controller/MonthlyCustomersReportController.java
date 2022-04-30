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

    public Button generateBtn;
    public TextArea reportsTA;
    public TableView monthCust;
    public TableColumn monthCol;
    public TableColumn typeCol;
    public TableColumn apptCol;
    private int month;


    private static ObservableList<Month> months = FXCollections.observableArrayList(Month.JANUARY, Month.FEBRUARY, Month.MARCH,
            Month.APRIL, Month.MAY, Month.JUNE, Month.JULY, Month.AUGUST, Month.SEPTEMBER, Month.OCTOBER, Month.NOVEMBER, Month.DECEMBER);


    /** Method initializes the controller
     * @param url            Used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle Used to localize the root object, or null if the root object was not localized.
    */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        monthCol.setCellValueFactory(new PropertyValueFactory<>("monthA"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("apptType"));
        apptCol.setCellValueFactory(new PropertyValueFactory<>("apptID"));

        monthCB.setItems(months);
    }

    /**
     * Event handler for Reports that returns user to Reports View
     * @param actionEvent Reports button click
     * @throws Exception
     */
    public void onReportsBtn(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/ReportsView.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Reports");
        Scene scene = new Scene(root, 1000, 600);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Event handler for Home that returns user to Home page
     * @param actionEvent Home button click
     * @throws Exception
     */
    public void onHomeBtn(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/HomepageWindow.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Scheduler Homepage");
        Scene scene = new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Gets appointments based on month selected and displays them in table
     * @param actionEvent Generate Report button click
     * @throws Exception
     */
    public void onGenerateRpt(ActionEvent actionEvent) throws Exception{
             ObservableList<Appointments> monthTypeList = FXCollections.observableArrayList();


        month = monthCB.getSelectionModel().getSelectedItem().getValue();
        monthCust.setItems(ApptsDB.getMonthType(month));
        if (ApptsDB.getMonthType(month).isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Appointments");
            alert.setContentText("No appointments in this month.");
            alert.showAndWait();

        }





    }
}