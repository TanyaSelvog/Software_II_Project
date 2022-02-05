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
import model.Contact;
import model.Customer;
import model.User;
import utils.ApptsDB;
import utils.ContactDB;
import utils.CustDB;
import utils.UserDB;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class NewApptController implements Initializable {

    public TextField titleTF;
    public TextField descTF;
    public TextField locationTF;



    public Button saveBtn;
    public Button cancelBtn;

    public ComboBox<Contact> contactComboBox;
    public ComboBox <String> typeComboBox;
    public ComboBox <LocalTime>startTimeCB;
    public ComboBox <LocalTime> endTimeCB;
    public ComboBox <Customer> customerComboBox;
    public DatePicker newApptDate;
    public ComboBox <User> userComboBox;
    public DatePicker endDatePicker;

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
     // startTimeCB.getItems().addAll("8:00 AM", "9:00 AM", "10:00 AM", "11:00 AM", "12:00 PM");
      //2.4.22 Working on this - temporary
       // endTimeCB.getItems().addAll("8:00 AM", "9:00 AM", "10:00 AM", "11:00 AM", "12:00 PM");
      typeComboBox.getItems().addAll("Initial Meeting", "Follow-Up Consultation", "Lunch Meeting", "Closing Session");


        userComboBox.setItems(UserDB.getUserList());



    }




    public void onSave(ActionEvent actionEvent) throws Exception {
        String apptDescription = descTF.getText();
        String apptLocation = locationTF.getText();
        String apptTitle = titleTF.getText();
        Contact contactSelected = contactComboBox.getSelectionModel().getSelectedItem();
        Customer customerSelected = customerComboBox.getSelectionModel().getSelectedItem();
        String apptType =  typeComboBox.getSelectionModel().getSelectedItem();
        User userSelected = userComboBox.getSelectionModel().getSelectedItem();
        LocalDateTime start = LocalDateTime.of(newApptDate.getValue(), startTimeCB.getValue());

        /**
         *
         */
        Parent root = FXMLLoader.load(getClass().getResource("/view/AppointmentsView.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("All Appointments");
        Scene scene = new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.show();

       System.out.println(apptTitle + " " + contactSelected+ " " + apptType + " " + userSelected+ " " + customerSelected);

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
