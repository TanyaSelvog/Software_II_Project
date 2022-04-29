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
    @FXML
    public Button saveBtn;
    @FXML
    public Button cancelBtn;
    @FXML
    public ComboBox <Contact>contactComboBox;
    @FXML
    public TextField titleTF;
    @FXML
    public TextField descriptionTF;
    @FXML
    public TextField locationTF;
    @FXML
    public TextField typeTF;
    @FXML
    public ComboBox typeComboBox;
    @FXML
    public ComboBox customerComboBox;
    @FXML
    public ComboBox <String> startTimeCB;
    @FXML
    public ComboBox <String> endTimeCB;
    @FXML
    public TextField apptIDTF;
    public static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("hh:mm a");
    public static DateTimeFormatter dateOnlyTime = DateTimeFormatter.ofPattern("MM-dd-yyyy");
    public DatePicker startDateDP;
    public DatePicker endDateDP;

    /**
     * Method for on Click
     * @param actionEvent
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

   /** public LocalDateTime getModStartDateTime(String startTime, String sDate){
        LocalTime lt = LocalTime.parse(startTime, dtf);
        LocalDate ld = LocalDate.parse(sDate, dateOnlyTime);
        System.out.println("localTime: " + lt);
        LocalDateTime ltd = ld.atTime(lt);
        return ltd;
    }
    /**
    private LocalDateTime getStartDateTime() {

        LocalDate startDate = startDateDP.getValue();
        LocalTime startTime = (LocalTime) startTimeCB.getValue();
        LocalDateTime startDateTime = LocalDateTime.of(startDate, startTime);
        System.out.println(startDateTime);
        return startDateTime;

    }
    private LocalDateTime getEndDateTime() {

        LocalDate endDate = endDateDP.getValue();
        LocalTime endTime = (LocalTime) endTimeCB.getValue();
        LocalDateTime endDateTime = LocalDateTime.of(endDate, endTime);
        System.out.println(endDateTime);
        return endDateTime;
    }
*/


    /**
     * Method for on Save Button
     * @param actionEvent
     * @throws Exception
     */
    public void onSaveClick(ActionEvent actionEvent) throws Exception {
        try {
            String apptTitle = titleTF.getText();
            System.out.println(apptTitle);
            String apptDescription = descriptionTF.getText();
            System.out.println("apptDescription from MAC: " + apptDescription);
            String apptLocation = locationTF.getText();
            int apptID = Integer.parseInt(apptIDTF.getText());
            Contact contactSelected = (Contact) contactComboBox.getSelectionModel().getSelectedItem();
            int contactID = contactSelected.getContactID();
            System.out.println("Contact contactSelected from getApptModification(): " + contactSelected);
            System.out.println(contactID);
            //int contactID = contactSelected.getContactID();

            Customer customerSelected = (Customer) customerComboBox.getSelectionModel().getSelectedItem();
            int customerID = customerSelected.getCustomerID();
            String apptType = String.valueOf(typeComboBox.getSelectionModel().getSelectedItem());
            System.out.println("apptType: " + apptType);
            String startTime = startTimeCB.getValue();
            LocalTime startLT = LocalTime.parse(startTime, dtf);
            LocalDate startDate = startDateDP.getValue();
            LocalDateTime startDateTime = startDate.atTime(startLT);
            String endTime = endTimeCB.getValue();
            LocalTime endLT = LocalTime.parse(endTime, dtf);
            LocalDate endLD = endDateDP.getValue();
            LocalDateTime endDateTime = endLD.atTime(endLT);
            System.out.println("EndDateTime: " + endDateTime);
            //LocalDateTime lastUpdate = LocalDateTime.now();
            String lastUpdatedBy = User.getUserName();
            System.out.println(lastUpdatedBy);
            int userID = User.getUserID();
            System.out.println(userID);

            if (compareAppts(apptID, customerID, startDateTime, endDateTime))
                ApptsDB.modifyAppt(apptID, apptTitle, apptDescription, apptLocation, apptType, startDateTime, endDateTime,
                        lastUpdatedBy, customerID, userID, contactID);

            Parent root = FXMLLoader.load(getClass().getResource("/view/AppointmentsView.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("All Appointments");
            Scene scene = new Scene(root, 1000, 600);
            stage.setScene(scene);
            stage.show();
        }
     catch (Exception displayE)

    {
        Alert alert = new Alert(Alert.AlertType.ERROR, ("Data is missing or contains invalid values."));
        alert.showAndWait();
    }
    }
        /** private Appointments getApptModification() {

         String apptTitle = titleTF.getText();
         System.out.println(apptTitle);
         String apptDescription = descriptionTF.getText();
         System.out.println("apptDescription from MAC: " + apptDescription);
         String apptLocation = locationTF.getText();
         int id = Integer.parseInt(apptIDTF.getText());
         Contact contactSelected = (Contact) contactComboBox.getSelectionModel().getSelectedItem();
         int contactID = contactSelected.getContactID();
         System.out.println("Contact contactSelected from getApptModification(): " + contactSelected);
         System.out.println(contactID);
         //int contactID = contactSelected.getContactID();

         Customer customerSelected = (Customer) customerComboBox.getSelectionModel().getSelectedItem();
         int customerID = customerSelected.getCustomerID();
         String apptType = String.value
         String apptType = String.valueOf(typeComboBox.getSelectionModel().getSelectedItem());
         System.out.println("apptType: " + apptType);
         String startTime = startTimeCB.getValue();
         LocalTime startLT = LocalTime.parse(startTime,dtf);
         LocalDate startDate = startDateDP.getValue();
         LocalDateTime startDateTime = startDate.atTime(startLT);
         String endTime = endTimeCB.getValue();
         LocalTime endLT = LocalTime.parse(endTime,dtf);
         LocalDate endLD = endDateDP.getValue();
         LocalDateTime endDateTime = endLD.atTime(endLT);
         System.out.println("EndDateTime: " + endDateTime);
         //LocalDateTime lastUpdate = LocalDateTime.now();
         String lastUpdatedBy = User.getUserName();
         System.out.println(lastUpdatedBy);
         int userID = User.getUserID();
         System.out.println(userID);

         ApptsDB.modifyAppt(id, apptTitle, apptDescription, apptLocation, apptType, startDateTime, endDateTime,
         lastUpdatedBy, customerID, userID, contactID);
         ???

        /**
 ApptsDB.createAppointment(apptTitle, apptDescription, apptLocation, apptType, startDateTime, endDateTime, currentUser.getUserName(), customerID, currentUser.getUserID(),
 contactID);
 Parent root = FXMLLoader.load(getClass().getResource("/view/AppointmentsView.fxml"));
 Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
 stage.setTitle("All Appointments");
 Scene scene = new Scene(root, 1000, 600);
 stage.setScene(scene);
 stage.show();
*/

    /**
     * Method for getting timelist
     * @return
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
     * Method that initializes the controller
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contactComboBox.setItems(ContactDB.getContactList());
        startTimeCB.setItems(getTimeList());
        endTimeCB.setItems(getTimeList());
        customerComboBox.setItems(CustDB.getCustomersList());
        typeComboBox.getItems().addAll("Initial Meeting", "Follow-Up Consultation", "Lunch Meeting", "Closing Session");

        // descriptionTF.setText(Appointments.getApptDescription());

    }

    /**
     * Method for setting up object from modifyApptController
     * @param appointment
     */

    public void modAppointment(Appointments appointment){
        //need contact, apptid, title, description, location, type, customer, start date/time, end date/time

        contactComboBox.getSelectionModel().select(ContactDB.getCustomerContact(appointment.getContactID()));
        customerComboBox.getSelectionModel().select(CustDB.getCustomerName(appointment.getCustomerID()));

        String titleTest = appointment.getApptTitle();
        titleTF.setText(titleTest);
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

        String typeTest = appointment.getApptType();
        typeComboBox.setValue(typeTest);
        System.out.println("typeTest from MAC: " + typeTest);

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

    }

    /**
     * Method to compare appointments to make sure no overlaps for customer appointments
     * @param apptID
     * @param customerID
     * @param startDate
     * @param endDate
     * @return
     */
    private boolean compareAppts (int apptID, int customerID, LocalDateTime startDate, LocalDateTime endDate) {

// || start.isBefore(appointment.getStart()) && end.isAfter(appointment.getEnd())) {
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





