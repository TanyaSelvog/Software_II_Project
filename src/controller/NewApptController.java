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

    @FXML
    public TextField titleTF;
    @FXML
    public TextField descTF;
    @FXML
    public TextField locationTF;
    @FXML
    public Button saveBtn;
    @FXML
    public Button cancelBtn;

    @FXML
    public ComboBox<Contact> contactComboBox;
    @FXML
    public ComboBox<String> typeComboBox;
    @FXML
    public ComboBox<String> startTimeCB;
    @FXML
    public ComboBox<String> endTimeCB;
    @FXML
    public ComboBox<Customer> customerComboBox;
    @FXML
    public DatePicker newApptDate;
    @FXML
    public DatePicker endDatePicker;

    public ObservableList<String> timeList = FXCollections.observableArrayList();
    public static ObservableList<Appointments> getCustomerAppts = FXCollections.observableArrayList();
    public static ObservableList<Appointments> custApptsList = FXCollections.observableArrayList();
    public DateTimeFormatter dtf = DateTimeFormatter.ofPattern("hh:mm a");
    public TextField apptIDTF;

    //  public static User currentUser;

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

        for (LocalTime startAdjustedTime = startOfBusiness; startAdjustedTime.isBefore(endOfBusiness); startAdjustedTime = startAdjustedTime.plusMinutes(15)) {
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

    /**
     * @return
     */
    private LocalDateTime getStartDateTime() {

        LocalDate startDate = newApptDate.getValue();
        LocalTime startTime = LocalTime.parse(startTimeCB.getValue(), dtf);
        LocalDateTime startDateTime = LocalDateTime.of(startDate, startTime);
        System.out.println(startDateTime);
        return startDateTime;

    }

    /**
     * @return
     */
    private LocalDateTime getEndDateTime() {

            LocalDate endDate = endDatePicker.getValue();
            LocalTime endTime = LocalTime.parse(endTimeCB.getValue(), dtf);
            LocalDateTime endDateTime = LocalDateTime.of(endDate, endTime);
            System.out.println(endDateTime);
            return endDateTime;
    }
    private void compareTimeValidation(){
    LocalDateTime startDateTime = getStartDateTime();
    LocalDateTime endDateTime = getEndDateTime();
            if (startDateTime.isAfter(endDateTime) || startDateTime.isEqual(endDateTime)){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("End time is before start time.");
        alert.setContentText("Start time needs to be before end time.");
        alert.showAndWait();
    }}

    /**
     *
     * @param actionEvent
     * @throws Exception
     */
    //fields in here so far gets user input

        /**
        Customer customerSelected = customerComboBox.getSelectionModel().getSelectedItem();
    //    int customerID = customerSelected.getCustomerID();
        compareTimeValidation();
        String apptType = typeComboBox.getSelectionModel().getSelectedItem();

      LocalDateTime startDateTime = getStartDateTime();
            LocalDateTime endDateTime = getEndDateTime();
            if (startDateTime.isAfter(endDateTime) || startDateTime.isEqual(endDateTime)){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("End time is before start time.");
                alert.setContentText("Start time needs to be before end time.");
                alert.showAndWait();
            }



        String apptDescription = descTF.getText();
        String apptLocation = locationTF.getText();
        String apptTitle = titleTF.getText();
        Contact contactSelected = contactComboBox.getSelectionModel().getSelectedItem();
   //     int contactID = contactSelected.getContactID();
        if (contactSelected == null || apptType == null ||customerSelected == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Missing input.");
            alert.setContentText("Data is missing in one or more fields.");
            alert.showAndWait();
        }

        /**if (startDateTime.isAfter(endDateTime) || startDateTime.isEqual(endDateTime)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("End time is before start time.");
            alert.setContentText("Start time needs to be before end time.");
            alert.showAndWait();
        }


        if (apptDescription.isEmpty() || apptTitle.isEmpty() || apptLocation.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Missing input.");
            alert.setContentText("Data is missing in one or more fields.");
            alert.showAndWait();
        } else {
            int contactID = contactSelected.getContactID();
            int customerID = customerSelected.getCustomerID();
            getCustApptsCompare(customerID, startDateTime, endDateTime);
            ApptsDB.createAppointment(apptTitle, apptDescription, apptLocation, apptType, startDateTime, endDateTime, customerID,
                    contactID);


            System.out.println(apptTitle + " " + apptDescription + " " + contactSelected + " " + apptType + " " + endDateTime + " " + customerID + "");
            Parent root = FXMLLoader.load(getClass().getResource("/view/AppointmentsView.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("All Appointments");
            Scene scene = new Scene(root, 1000, 600);
            stage.setScene(scene);
            stage.show();
        }

        }
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
               // getCustApptsCompare(customerID, startDateTime, endDateTime);

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
            //    Parent root = FXMLLoader.load(getClass().getResource("/view/AppointmentsView.fxml"));
              //  Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                //stage.setTitle("All Appointments");
                //Scene scene = new Scene(root, 1000, 600);
                //stage.setScene(scene);
                //stage.show();

            }
        } catch (Exception displayE) {
            Alert alert = new Alert(Alert.AlertType.ERROR, ("Data is missing or contains invalid values."));
            alert.showAndWait();

        }

    }


    /**
     *
     * @param actionEvent
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
     *
     * @param customerID
     * @param startDate
     * @param endDate
     * @return
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

