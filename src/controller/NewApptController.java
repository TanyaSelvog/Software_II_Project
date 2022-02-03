package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Appointments;
import utils.ApptsDB;
import utils.ContactDB;
import utils.CustDB;

import java.net.URL;
import java.util.ResourceBundle;

public class NewApptController implements Initializable {
    public TextField apptID;
    public TextField titleTF;
    public TextField descTF;
    public TextField locationTF;
    public TextField userID;
    public TextField custID;



    public Button saveBtn;
    public Button cancelBtn;

    public ComboBox contactComboBox;
    public ComboBox typeComboBox;
    public ComboBox startTimeCB;
    public ComboBox endTimeCB;
    public ComboBox customerComboBox;
    public DatePicker newApptDate;
    public ComboBox userComboBox;

    /**
     * This method initializes the controller.
     *
     * @param url            Used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle Used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
      contactComboBox.setItems(ContactDB.getContactList());
      customerComboBox.setItems(CustDB.getCustomersList());
      //2.2.21 working on this - going to modify in future
      startTimeCB.getItems().add("8:00 AM");
      typeComboBox.getItems().add("Initial Meeting");
        typeComboBox.getItems().add("Follow-up Consultation");
        typeComboBox.getItems().add("Lunch Meeting");
        typeComboBox.getItems().add("Closing Session");



    }


    /** TODO started on method to get data from users; need to make System.out.println/String methods for testing
     */

    public Appointments newAppointment() {
        try {
            String apptDescription = descTF.getText();


        } catch (Exception displayE) {
            Alert alert = new Alert(Alert.AlertType.ERROR, ("Data is missing or contains invalid values."));
            alert.showAndWait();

        }
            return null;
        }

    public void onSave(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/AppointmentsView.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("All Appointments");
        Scene scene = new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.show();

        Appointments appt = newAppointment();

    }


    public void onCancel(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/AppointmentsView.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("All Appointments");
        Scene scene = new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }

}
