package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Appointments;
import utils.ContactDB;

import java.net.URL;
import java.util.ResourceBundle;

public class ModifyApptController implements Initializable {
    public Button saveBtn;
    public Button cancelBtn;

    public ComboBox contactComboBox;
    public TextField titleTF;
    public TextField descriptionTF;
    public TextField locationTF;

    public TextField typeTF;
    public ComboBox typeComboBox;
    public ComboBox customerComboBox;
    public ComboBox startTimeCB;
    public ComboBox endTimeCB;
    public DatePicker modApptDate;

    public void onCancelClick(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/AppointmentsView.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Scheduler Homepage");
        Scene scene = new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }

    public void onSaveClick(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/AppointmentsView.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Scheduler Homepage");
        Scene scene = new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    //    contactComboBox.setItems(ContactDB.getContactList());
       // descriptionTF.setText(Appointments.getApptDescription());
/**
 *  public void modCustomer(Customer customer){
 *         customerID.setText(String.valueOf(customer.getCustomerID()));
 *         customerName.setText(customer.getCustomerName());
 *         customerPhone.setText(customer.getCustomerPhone());
 *         customerAddress.setText(customer.getCustomerAddress());
 *         customerPostalCode.setText(customer.getCustomerPostal());
 *         countryComboBox.setValue(customer.getCustomerCountry());
 *         divisionComboBox.setValue(customer.getCustomerDivision());
 */
    }
    // 2.1.22 WORKING ON THIS
    public void modAppointment(Appointments appointment){
        descriptionTF.setText(appointment.getApptDescription());
        locationTF.setText(appointment.getApptLocation());
        typeTF.setText(appointment.getApptType());
        titleTF.setText(appointment.getApptTitle());
        contactComboBox.setItems(ContactDB.getContactList());

    }

}
