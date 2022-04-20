package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Contact;
import model.Country;
import model.Customer;
import utils.ApptsDB;
import utils.ContactDB;
import utils.CountryDB;
import utils.CustDB;

import java.net.URL;
import java.util.ResourceBundle;

import static utils.ApptsDB.getContactList;
import static utils.CountryDB.getCountryCustList;

public class CountryReportsController implements Initializable {
    @FXML
    public TableView countryTable;
    @FXML
    public TableColumn custIDCol;
    @FXML
    public ComboBox countryCB;
    @FXML
    public TableColumn customerCol;
    @FXML
    public Button home;
    @FXML
    public Button generateBtn;
    @FXML
    public Button reports;
    @FXML
    public Label resultsLBL;

    /**
     * Method that initializes the Country Reports Controller
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        custIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        customerCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));

        countryCB.setItems(CountryDB.getCountryList());
    }

    /**
     * Method for on click Home Button
     * @param actionEvent
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
     * Method for on Generate Report button
     */
    public void onGenerateRpt(ActionEvent actionEvent) throws Exception{

        Country cb = (Country)countryCB.getValue();
        int country_ID = cb.getCountryID();

        countryTable.setItems(getCountryCustList(country_ID));
        if (CountryDB.getCountryCustList(country_ID).isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No customers.");
            alert.setContentText("No customers in this country.");
            alert.showAndWait();
        }

    }

    /**
     * Method for on Reports Button
     * @param actionEvent
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
}
