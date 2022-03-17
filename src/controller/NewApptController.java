package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
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
import utils.*;

import java.net.URL;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class NewApptController implements Initializable {

    public TextField titleTF;
    public TextField descTF;
    public TextField locationTF;



    public Button saveBtn;
    public Button cancelBtn;

    public ComboBox<Contact> contactComboBox;
    public ComboBox <String> typeComboBox;
    public ComboBox<String> startTimeCB;
    public ComboBox <String>endTimeCB;
    public ComboBox <Customer> customerComboBox;
    public DatePicker newApptDate;
    public ComboBox <User> userComboBox;
    public DatePicker endDatePicker;
    public ObservableList<String> timeList = FXCollections.observableArrayList();
   public DateTimeFormatter dtf = DateTimeFormatter.ofPattern("hh:mm a");

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
        typeComboBox.getItems().addAll("Initial Meeting", "Follow-Up Consultation", "Lunch Meeting", "Closing Session");
        userComboBox.setItems(UserDB.getUserList());


        startTimeCB.setItems(getTimeList());
        endTimeCB.setItems(getTimeList());

    }
        public ObservableList<String> getTimeList() {
            ObservableList<String> timeList = FXCollections.observableArrayList();


            ZoneId easternStandardTime = ZoneId.of("America/New_York");
            ZonedDateTime startTime = ZonedDateTime.of(2022, 1, 1, 8, 0, 0, 0, easternStandardTime);
            ZonedDateTime endTime = ZonedDateTime.of(2022, 1, 1, 22, 0, 0, 0, easternStandardTime);

            LocalTime startOfBusiness = startTime.withZoneSameInstant(ZoneId.systemDefault()).toLocalTime();
            LocalTime endOfBusiness = endTime.withZoneSameInstant(ZoneId.systemDefault()).toLocalTime();

            for(LocalTime startAdjustedTime = startOfBusiness; startAdjustedTime.isBefore(endOfBusiness); startAdjustedTime =startAdjustedTime.plusMinutes(15)){
              // timeList.add(LocalTime.parse(startAdjustedTime.toString()));

                String textTime = startAdjustedTime.format(dtf);
                timeList.add(textTime);

            }


            return timeList;





        }

    /**Example from Java Documentation - to look at
     * LocalDate date = LocalDate.now();
     *   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd");
     *   String text = date.format(formatter);
     *   LocalDate parsedDate = LocalDate.parse(text, formatter);
     *
     *
     *
     *
     * LocalTime time = time.plusMinutes(30);
     * DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm a");
     * String textTime = time.format(dtf);
     * LocalTime timeAgain = LocalTime.parse(textTime, dtf);
     */

    //Working on this; need to adjust time 3.16

    private LocalDateTime getStartDateTime() {

        LocalDate startDate = newApptDate.getValue();
        LocalTime startTime = LocalTime.parse(startTimeCB.getValue(), dtf);
        LocalDateTime startDateTime = LocalDateTime.of(startDate, startTime);
        System.out.println(startDateTime);
        return startDateTime;

    }
    private LocalDateTime getEndDateTime() {

        LocalDate endDate = endDatePicker.getValue();
        LocalTime endTime = LocalTime.parse(endTimeCB.getValue(), dtf);
        LocalDateTime endDateTime = LocalDateTime.of(endDate, endTime);
        System.out.println(endDateTime);
        return endDateTime;

    }
    public void onSave(ActionEvent actionEvent) throws Exception {

        String apptDescription = descTF.getText();
        String apptLocation = locationTF.getText();
        String apptTitle = titleTF.getText();
        Contact contactSelected = contactComboBox.getSelectionModel().getSelectedItem();
        int contactID = contactSelected.getContactID();

        Customer customerSelected = customerComboBox.getSelectionModel().getSelectedItem();
        int customerID = customerSelected.getCustomerID();
        String apptType =  typeComboBox.getSelectionModel().getSelectedItem();
        User userSelected = userComboBox.getSelectionModel().getSelectedItem();
        LocalDateTime startDateTime = getStartDateTime();
        //LocalDate startDate = newApptDate.getValue();
        LocalDateTime endDateTime = getEndDateTime();

        //fails at this point
        ApptsDB.createAppointment(apptTitle, apptDescription, apptLocation, apptType, startDateTime, endDateTime, customerID, contactID);
        Parent root = FXMLLoader.load(getClass().getResource("/view/AppointmentsView.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("All Appointments");
        Scene scene = new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.show();

      System.out.println(apptTitle + " " + apptDescription + " " + contactSelected+ " " + apptType + " " + userSelected+ " " + customerSelected + "" );

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
