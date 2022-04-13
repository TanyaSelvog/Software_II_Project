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
    public void onReportsBtn(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/ReportsView.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Reports");
        Scene scene = new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }

    public void onGenerateBtn (ActionEvent actionEvent) throws Exception {

        Contact cb = (Contact)contactCB.getValue();
        int contactID = cb.getContactID();

        getContactList(contactID);

        System.out.println("ok");


        contactTable.setItems(getContactList(contactID));
        if (getContactList(contactID).isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Appointments");
            alert.setContentText("No appointments in this month.");
            alert.showAndWait();

        }







    }
    public void onHomeBtn(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/HomepageWindow.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Scheduler Homepage");
        Scene scene = new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }
}
