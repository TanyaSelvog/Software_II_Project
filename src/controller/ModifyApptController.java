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
import model.Contact;
import utils.ApptsDB;
import utils.ContactDB;
import utils.CustDB;
import utils.UserDB;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
    public ComboBox userComboBox;
    public TextField apptIDTF;
    public static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("hh:mm a");
    public static DateTimeFormatter dateOnlyTime = DateTimeFormatter.ofPattern("MM-dd-yyyy");
    public DatePicker startDateDP;
    public DatePicker endDateDP;

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
     //   Contact contact = ContactDB.getContactList(appointment.getContactID());
//3.28 need to fix
      //  String contactTest = String.valueOf(appointment.getContactID());
        //contactComboBox.setValue(contactTest);
        //System.out.println(contactTest);

      // Contact contact = ContactDB.getCustomerContact(appointment.getContactID());
        //contactComboBox.getSelectionModel().select(contact);
        //System.out.println("Contact contact from modifyApptController is: " + contact);
       // contactComboBox.getSelectionModel().select(contact);
  //  ContactDB.getContactList());
       //need to fix this apptIDTF.setText(ApptsDB.getUserAppt());
        String contactTest = appointment.getApptContact();
        System.out.println(contactTest);
        contactComboBox.setValue(contactTest);

        String titleTest = appointment.getApptTitle();
        titleTF.setText(titleTest);
        System.out.println("Title from appointment is: " + titleTest);
       // titleTF.setText(appointment.getApptTitle());

        String descriptionTest = appointment.getApptDescription();
        descriptionTF.setText(descriptionTest);

        //descriptionTF.setText(appointment.getApptDescription());

        String location = appointment.getApptLocation();
        locationTF.setText(location);
       // locationTF.setText(appointment.getApptLocation());
       // typeTF.setText(appointment.getApptType());
     //  customerComboBox.setItems(CustDB.getCustomersList());
        int id = appointment.getApptID();
        apptIDTF.setText(String.valueOf(id));
        System.out.println("int apptID from modApptController is: " + id);

        String typeTest = appointment.getApptType();
        typeComboBox.setValue(typeTest);
        System.out.println("typeTest from MAC: " + typeTest);

        String customerTest = appointment.getCustomerName();
        customerComboBox.setValue(customerTest);
        System.out.println("customerTest from MAC " + customerTest);

        //modify startTime
        LocalDateTime ldtStart = appointment.getStartDate();
        String startTime = dtf.format(ldtStart);
        startTimeCB.setValue(startTime);

        //modify startDate
        LocalDateTime stTest = appointment.getStartDate();
        LocalDate sDate = stTest.toLocalDate();
        startDateDP.setValue(sDate);


        //modify end time
        LocalDateTime ldtTest = appointment.getEndDate();
        String endLDT = dtf.format(ldtTest);
        endTimeCB.setValue(endLDT);


        //modify endDate
        LocalDateTime ld = appointment.getEndDate();
        LocalDate ldtTestDate = ld.toLocalDate();
       endDateDP.setValue(ldtTestDate);
     //   endTimeCB.setValue(endLDT);
        System.out.println(ldtTest);
        System.out.println(endLDT);





    //    customerComboBox.setValue(customerTest);



       //userComboBox.setItems(UserDB.getUserList());

    }

}
