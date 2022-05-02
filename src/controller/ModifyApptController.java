package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import static utils.ApptsDB.getCustomerAppts;

/**
 * Controller class for Modify Appointment
 */
public class ModifyApptController implements Initializable {
    /**
     * Button for saving modified appointment details
     */
    @FXML
    public Button saveBtn;

    /**
     * Button for canceling appointment modifications
     */
    @FXML
    public Button cancelBtn;

    /**
     * Combobox with list of contacts
     */
    @FXML
    public ComboBox <Contact>contactComboBox;

    /**
     * TextField for appointment title
     */
    @FXML
    public TextField titleTF;

    /**
     * Textfield for appointment description
     */
    @FXML
    public TextField descriptionTF;

    /**
     * Textfield for appointment location
     */
    @FXML
    public TextField locationTF;

    /**
     * Textfield for appointment type
     */
    @FXML
    public TextField typeTF;


    @FXML
    /**
     * Combobox with list of appointment types
     */
    public ComboBox typeComboBox;

    /**
     * Combobox with list of customers
     */
    @FXML
    public ComboBox customerComboBox;

    /**
     * Combobox for start times
     */
    @FXML
    public ComboBox <String> startTimeCB;

    /**
     * Combobox for end times
     */
    @FXML
    public ComboBox <String> endTimeCB;

    /**
     * Textfield for appointment ID
     */
    @FXML
    public TextField apptIDTF;
    /**
     * Formatter for time (hh:mm a)
     */
    public static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("hh:mm a");

    /**
     * Formatter for date (MM-dd-yyyy)
     */
    public static DateTimeFormatter dateOnlyTime = DateTimeFormatter.ofPattern("MM-dd-yyyy");
    /**
     * Date picker for appointment start date
     */
    public DatePicker startDateDP;

    /**
     * Date picker for appointment end date
     */
    public DatePicker endDateDP;

    /**
     * Canceling modified appointment
     * @param actionEvent On clicking Cancel button
     * @throws Exception
     */
    public void onCancelClick(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/AppointmentsView.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Scheduler Homepage");
        Scene scene = new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }


    /**
     * Event handler for on Save Button
     * @param actionEvent on Save button click
     * @throws Exception
     */
    public void onSaveClick(ActionEvent actionEvent) throws Exception {
        try {

            Customer customerSelected = (Customer) customerComboBox.getSelectionModel().getSelectedItem();
            String apptType = String.valueOf(typeComboBox.getSelectionModel().getSelectedItem());


            String startTime = startTimeCB.getValue();
            LocalTime startLT = LocalTime.parse(startTime, dtf);
            LocalDate startDate = startDateDP.getValue();
            LocalDateTime startDateTime = startDate.atTime(startLT);
            String endTime = endTimeCB.getValue();
            LocalTime endLT = LocalTime.parse(endTime, dtf);
            LocalDate endLD = endDateDP.getValue();
            LocalDateTime endDateTime = endLD.atTime(endLT);
            String lastUpdatedBy = User.getUserName();
            int userID = User.getUserID();

            String apptTitle = titleTF.getText();
            String apptDescription = descriptionTF.getText();
            String apptLocation = locationTF.getText();
            int apptID = Integer.parseInt(apptIDTF.getText());
            Contact contactSelected = contactComboBox.getSelectionModel().getSelectedItem();


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


                if (compareAppts(apptID, customerID, startDateTime, endDateTime)) {
                    ApptsDB.modifyAppt(apptID, apptTitle, apptDescription, apptLocation, apptType, startDateTime, endDateTime,
                            lastUpdatedBy, customerID, userID, contactID);

                    Parent root = FXMLLoader.load(getClass().getResource("/view/AppointmentsView.fxml"));
                    Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    stage.setTitle("All Appointments");
                    Scene scene = new Scene(root, 1000, 600);
                    stage.setScene(scene);
                    stage.show();

                }}}

     catch(Exception displayE) {
                Alert alert = new Alert(Alert.AlertType.ERROR, ("Data is missing or contains invalid values."));
                alert.showAndWait();
            }
        }



    /**
     * Displays list of times that are within the business hours (8am-10pm EST)
     * @return timelist
     */
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

    /**
     * Method that initializes the controller and sets contact, customer, type, start & end Combo Boxes
     * @param url Used to rseolve relative paths for the root object, or null lif the location is not known.
     * @param resourceBundle Used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contactComboBox.setItems(ContactDB.getContactList());
        startTimeCB.setItems(getTimeList());
        endTimeCB.setItems(getTimeList());
        customerComboBox.setItems(CustDB.getCustomersList());
        typeComboBox.getItems().addAll("Initial Meeting", "Follow-Up Consultation", "Lunch Meeting", "Closing Session");

    }

    /**
     * Method for setting up object from modifyApptController
     * @param appointment Appointment to be modified
     */

    public void modAppointment(Appointments appointment){

        contactComboBox.getSelectionModel().select(ContactDB.getCustomerContact(appointment.getContactID()));
        customerComboBox.getSelectionModel().select(CustDB.getCustomerName(appointment.getCustomerID()));


        titleTF.setText(appointment.getApptTitle());
        descriptionTF.setText(appointment.getApptDescription());

        String location = appointment.getApptLocation();
        locationTF.setText(location);

        int id = appointment.getApptID();
        apptIDTF.setText(String.valueOf(id));

        String typeTest = appointment.getApptType();
        typeComboBox.setValue(typeTest);

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


    }

    /**
     * Compares appointments to make sure no overlaps for customer appointments
     * @param apptID apptID
     * @param customerID customerID
     * @param startDate startDate
     * @param endDate endDate
     * @return false if appointment times overlap, true if not
     */
    private boolean compareAppts (int apptID, int customerID, LocalDateTime startDate, LocalDateTime endDate) {

        ObservableList<Appointments> custApptsList = getCustomerAppts(customerID);
        for (Appointments appointments : custApptsList) {
          if (apptID != appointments.getApptID()){
            if (startDate.isEqual(appointments.getStartDate())
                    || startDate.isAfter(appointments.getStartDate())
                    && startDate.isBefore(appointments.getEndDate())
                    || endDate.isAfter(appointments.getStartDate())
                    && endDate.isBefore(appointments.getEndDate())
                    || startDate.isBefore(appointments.getStartDate())
                    && endDate.isAfter(appointments.getEndDate())
                    || startDate.isBefore(appointments.getStartDate())
                    && endDate.isEqual(appointments.getEndDate())){
                //  || startDateTime.isEqual(appt.getStartDate())
                //&& endDateTime.isEqual(appt.getEndDate())){
                Alert alert = new Alert(Alert.AlertType.ERROR, ("Appointment can not be saved. This appointment conflicts with another appointment."));
                alert.showAndWait();
                return false;
            }

        }}
        return true;
    }}





