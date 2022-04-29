package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointments;
import model.Contact;
import model.Division;
import utils.ApptsDB;
import utils.ContactDB;

import java.net.URL;
import java.util.ResourceBundle;

import static utils.ApptsDB.getContactList;

/**
 * Class for Contact Scheduler Controller
 */
public class ContactScheduleController implements Initializable {
    public TableView contactTable;
    public TableColumn apptIDCol;
    public TableColumn titleCol;
    public TableColumn typeCol;
    public TableColumn descCol;
    public TableColumn startCol;
    public TableColumn endCol;
    public TableColumn custIDCol;
    public Button homeBtn;
    public Button reportsBtn;
    public ComboBox contactCB;
    public Button generateBtn;

    /**
     * Method that initializes the controller for Contact Scheduler
     * @param url Used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle Used to localize the root object, or null if the root object was not localized.
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        apptIDCol.setCellValueFactory(new PropertyValueFactory<>("apptID"));

        titleCol.setCellValueFactory(new PropertyValueFactory<>("apptTitle"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("apptType"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("apptDescription"));
        //contactAllAppts.setCellValueFactory(new PropertyValueFactory<>("apptContact"));

        startCol.setCellValueFactory(new PropertyValueFactory<>("startDateString"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("endDateString"));
        custIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));


        // countryComboBox.setItems(CountryDB.getCountryList());
        contactCB.setItems(ContactDB.getContactList());


    }

    /**
     * Event handler for Reports button that returns users to Reports page
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
     * Event handler for Generate button that shows a Contact's schedule based on contact selected
     * @param actionEvent Generate button click
     * @throws Exception
     */
    public void onGenerateBtn (ActionEvent actionEvent) throws Exception {

        Contact cb = (Contact)contactCB.getValue();
        int contactID = cb.getContactID();

        getContactList(contactID);
        contactTable.setItems(getContactList(contactID));
        if (getContactList(contactID).isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Schedule");
            alert.setContentText("No schedule available.");
            alert.showAndWait();

        }

    }

    /**
     * Event handler for on Home button click that returns users to Home page
     * @param actionEvent Home button clicked
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
}
