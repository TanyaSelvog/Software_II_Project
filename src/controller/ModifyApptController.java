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
import utils.ApptsDB;
import utils.ContactDB;
import utils.CustDB;
import utils.UserDB;

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
    public ComboBox userComboBox;
    public TextField apptIDTF;

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
        contactComboBox.setItems(ContactDB.getContactList());
        customerComboBox.setItems(CustDB.getCustomersList());
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
    // 3.28.2022 Setting up to get object from modifyApptController
    public void modAppointment(Appointments appointment){
        //need contact, apptid, title, description, location, type, customer, start date/time, end date/time, user
        contactComboBox.setItems(ContactDB.getContactList());
       //need to fix this apptIDTF.setText(ApptsDB.getUserAppt());
        titleTF.setText(appointment.getApptTitle());
        descriptionTF.setText(appointment.getApptDescription());
        locationTF.setText(appointment.getApptLocation());
        typeTF.setText(appointment.getApptType());
       customerComboBox.setItems(CustDB.getCustomersList());



       userComboBox.setItems(UserDB.getUserList());

    }

}
