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
     * Method that initializes the Country Reports Controller and sets the table columns and country combo box
     * @param url            Used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle Used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        custIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        customerCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));

        countryCB.setItems(CountryDB.getCountryList());
    }

    /**
     * Event handler for on Home button that sends user back to Home page
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
     * Gets Country combo box value and sets table display when Generate button is clicked
     * If no customers in selected Country, alert message is displayed
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
     * Event handler for Reports button that sends user to Reports View page
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
}
