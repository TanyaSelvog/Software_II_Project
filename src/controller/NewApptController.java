package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.*;
import utils.*;

import java.net.URL;
import java.time.*;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import static utils.ApptsDB.getCustomerAppts;

/**
 * Controller class for New Appointment
 */
public class NewApptController implements Initializable {
    /**
     * Textfield for appointment title
     */
    @FXML
    public TextField titleTF;

    /**
     * Textfield appointment description
     */
    @FXML
    public TextField descTF;

    /**
     * Textfield for location
     */
    @FXML
    public TextField locationTF;

    /**
     * Save button
     */
    @FXML
    public Button saveBtn;

    /**
     * Cancel button
     */
    @FXML
    public Button cancelBtn;

    /**
     * Combobox for contacts
     */
    @FXML
    public ComboBox<Contact> contactComboBox;

    /**
     * Combobox for appointment types
     */
    @FXML
    public ComboBox<String> typeComboBox;

    /**
     * Combobox for appointment start times
     */
    @FXML
    public ComboBox<String> startTimeCB;

    /**
     * Combobox for appointment end times
     */
    @FXML
    public ComboBox<String> endTimeCB;

    /**
     * Combobox for customers
     */
    @FXML
    public ComboBox<Customer> customerComboBox;

    /**
     * Date picker for start date for new appointment
     */
    @FXML
    public DatePicker newApptDate;

    /**
     * Date picker for end date for new appointment
     */
    @FXML
    public DatePicker endDatePicker;

    public ObservableList<String> timeList = FXCollections.observableArrayList();

    /**
     * Time formatter (in the form of hh:mm a)
     */
    public DateTimeFormatter dtf = DateTimeFormatter.ofPattern("hh:mm a");

    /**
     * Textfield (uneditable) for appointment ID
     */
    public TextField apptIDTF;


    /**
     * This method initializes the New Appointment controller.
     *
     * @param url            Used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle Used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contactComboBox.setItems(ContactDB.getContactList());
        customerComboBox.setItems(CustDB.getCustomersList());
        typeComboBox.getItems().addAll("Initial Meeting", "Follow-Up Consultation", "Lunch Meeting", "Closing Session");


        startTimeCB.setItems(getTimeList());
        endTimeCB.setItems(getTimeList());

    }

    /**
     * Sets and displays the business hours of 8am-10pm EST
     * @return timeList
     */
    public ObservableList<String> getTimeList() {
        ObservableList<String> timeList = FXCollections.observableArrayList();


        ZoneId easternStandardTime = ZoneId.of("America/New_York");
        ZonedDateTime startTime = ZonedDateTime.of(2022, 1, 1, 8, 0, 0, 0, easternStandardTime);
        ZonedDateTime endTime = ZonedDateTime.of(2022, 1, 1, 22, 0, 0, 0, easternStandardTime);

        LocalTime startOfBusiness = startTime.withZoneSameInstant(ZoneId.systemDefault()).toLocalTime();
        LocalTime endOfBusiness = endTime.withZoneSameInstant(ZoneId.systemDefault()).toLocalTime();

        for (LocalTime startAdjustedTime = startOfBusiness; startAdjustedTime.isBefore(endOfBusiness); startAdjustedTime = startAdjustedTime.plusMinutes(15)) {
            // timeList.add(LocalTime.parse(startAdjustedTime.toString()));

            String textTime = startAdjustedTime.format(dtf);
            timeList.add(textTime);

        }


        return timeList;

    }


    /**
     * Gets start time and date and changes it to a LocalDateTime object
     * @return startDateTime in LocalDateTime
     */
    private LocalDateTime getStartDateTime() {

        LocalDate startDate = newApptDate.getValue();
        LocalTime startTime = LocalTime.parse(startTimeCB.getValue(), dtf);
        LocalDateTime startDateTime = LocalDateTime.of(startDate, startTime);
        return startDateTime;

    }

    /**
     * Gets an end time and changes it to LocalDateTime object
     * @return endDateTime in LocalDateTime
     */
    private LocalDateTime getEndDateTime() {

            LocalDate endDate = endDatePicker.getValue();
            LocalTime endTime = LocalTime.parse(endTimeCB.getValue(), dtf);
            LocalDateTime endDateTime = LocalDateTime.of(endDate, endTime);
            return endDateTime;
    }


    /**
     * Event handler for the Save button that validates user input and saves the appointment
     *
     * @param actionEvent On Save button click
     * @throws Exception
     */
    //working on 4.27
        public void onSave(ActionEvent actionEvent) throws Exception {
            try {
            Customer customerSelected = customerComboBox.getSelectionModel().getSelectedItem();
            LocalDateTime startDateTime = getStartDateTime();
            LocalDateTime endDateTime = getEndDateTime();


            String apptType = typeComboBox.getSelectionModel().getSelectedItem();
            String apptDescription = descTF.getText();
            String apptLocation = locationTF.getText();
            String apptTitle = titleTF.getText();
            Contact contactSelected = contactComboBox.getSelectionModel().getSelectedItem();
            //     int contactID = contactSelected.getContactID();
                if (startDateTime.isAfter(endDateTime) || startDateTime.isEqual(endDateTime)) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("End time is before start time.");
                    alert.setContentText("Start time needs to be before end time.");
                    alert.showAndWait();
                } else if (contactSelected == null || apptType == null || customerSelected == null || apptDescription.isEmpty()
                    || apptTitle.isEmpty() || apptLocation.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Missing input.");
                alert.setContentText("Data is missing in one or more fields.");
                alert.showAndWait();
            } else {
                int contactID = contactSelected.getContactID();
                int customerID = customerSelected.getCustomerID();


                if (getCustApptsCompare(customerID, startDateTime, endDateTime) ){
                        ApptsDB.createAppointment(apptTitle, apptDescription, apptLocation, apptType, startDateTime, endDateTime, customerID,
                                contactID);
                    Parent root = FXMLLoader.load(getClass().getResource("/view/AppointmentsView.fxml"));
                    Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    stage.setTitle("All Appointments");
                    Scene scene = new Scene(root, 1000, 600);
                    stage.setScene(scene);
                    stage.show();
                    } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, ("Appointment can not be saved. This appointment conflicts with another appointment."));
                    alert.showAndWait();

                }


            }
        } catch (Exception displayE) {
            Alert alert = new Alert(Alert.AlertType.ERROR, ("Data is missing or contains invalid values."));
            alert.showAndWait();

        }

    }


    /**
     * Event handler for Cancel button that returns user to Appointments View page
     * @param actionEvent Clicked Cancel button
     * @throws Exception
     */

    public void onCancel(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/AppointmentsView.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("All Appointments");
        Scene scene = new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Compares customer appointments to make sure no overlapping appointments can be scheduled
     * @param customerID
     * @param startDate startDate for appointment to be scheduled
     * @param endDate endDate for appointment to be scheduled
     * @return false, if any conflict
     */
    private boolean getCustApptsCompare(int customerID, LocalDateTime startDate, LocalDateTime endDate) {

// || start.isBefore(appointment.getStart()) && end.isAfter(appointment.getEnd())) {
        ObservableList<Appointments> custApptsList = getCustomerAppts(customerID);
        for (Appointments appointments : custApptsList) {
            if (startDate.isEqual(appointments.getStartDate())

                    || startDate.isAfter(appointments.getStartDate())
                    && startDate.isBefore(appointments.getEndDate())
                    || endDate.isAfter(appointments.getStartDate())
                    && endDate.isBefore(appointments.getEndDate())
                    || startDate.isBefore(appointments.getStartDate())
                    && endDate.isAfter(appointments.getEndDate())
                    || startDate.isEqual(appointments.getStartDate())
                    && endDate.isEqual(appointments.getEndDate())) {


                return false;
            }
        }return true;}
}

