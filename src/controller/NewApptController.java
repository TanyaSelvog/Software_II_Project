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
import java.util.ResourceBundle;

public class NewApptController implements Initializable {

    public TextField titleTF;
    public TextField descTF;
    public TextField locationTF;



    public Button saveBtn;
    public Button cancelBtn;

    public ComboBox<Contact> contactComboBox;
    public ComboBox typeComboBox;
    public ComboBox startTimeCB;
    public ComboBox endTimeCB;
    public ComboBox <Customer> customerComboBox;
    public DatePicker newApptDate;
    public ComboBox <User> userComboBox;

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
      typeComboBox.getItems().addAll("Initial Meeting", "Follow-Up Consultation", "Lunch Meeting", "Closing Session");


        userComboBox.setItems(UserDB.getUserList());



    }




    public void onSave(ActionEvent actionEvent) throws Exception {
        String apptDescription = descTF.getText();
        String apptLocation = locationTF.getText();
        String apptTitle = titleTF.getText();
        Contact contactSelected = contactComboBox.getSelectionModel().getSelectedItem();
        Customer customerSelected = customerComboBox.getSelectionModel().getSelectedItem();
        /**
         *
         */
        Parent root = FXMLLoader.load(getClass().getResource("/view/AppointmentsView.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("All Appointments");
        Scene scene = new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.show();

       System.out.println(apptTitle + " " + contactSelected);

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
