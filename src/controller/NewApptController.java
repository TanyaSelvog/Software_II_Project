package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import utils.*;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    public ComboBox <LocalTime>startTimeCB;
    public ComboBox <LocalTime> endTimeCB;
    public ComboBox <Customer> customerComboBox;
    public DatePicker newApptDate;
    public ComboBox <User> userComboBox;
    public DatePicker endDatePicker;
    public ObservableList<LocalTime> timeList = FXCollections.observableArrayList();
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


        startTimeCB.setItems(timeList);
        endTimeCB.setItems(getTimeList());
    }

        //2.9 Displays time but want to fix display 12:00+
        public ObservableList<LocalTime> getTimeList() {
           // ObservableList<LocalTime> timeList = FXCollections.observableArrayList();

            ZoneId easternStandardTime = ZoneId.of("America/New_York");
            ZonedDateTime startTime = ZonedDateTime.of(2022, 1, 1, 8, 0, 0, 0, easternStandardTime);
            ZonedDateTime endTime = ZonedDateTime.of(2022, 1, 1, 22, 0, 0, 0, easternStandardTime);

            LocalTime startOfBusiness = startTime.withZoneSameInstant(ZoneId.systemDefault()).toLocalTime();
            LocalTime endOfBusiness = endTime.withZoneSameInstant(ZoneId.systemDefault()).toLocalTime();

            LocalTime startAdjustedTime = startOfBusiness.minusMinutes(15);

            //LocalTime localTime = LocalTime.parse(STRING, dtf);

           while(startAdjustedTime.isBefore(endOfBusiness)) {
                 startAdjustedTime = startAdjustedTime.plusMinutes(15);


      DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm a");
      String textTime = startAdjustedTime.format(dtf);
      LocalTime timeAgain = LocalTime.parse(textTime, dtf);
                timeList.add(startAdjustedTime);
                System.out.println(timeAgain);
                System.out.println(startOfBusiness);
                System.out.println(endOfBusiness);

            }

            System.out.println(startAdjustedTime);
         //   System.out.println(timeAgain);

            timeList.add(startAdjustedTime);
            return timeList;
        }

    /**Example from Java Documentation - to look at
     * LocalDate date = LocalDate.now();
     *   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd");
     *   String text = date.format(formatter);
     *   LocalDate parsedDate = LocalDate.parse(text, formatter);
     * @param actionEvent
     * @throws Exception
     *
     *
     *
     * LocalTime time = time.plusMinutes(30);
     * DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm a");
     * String textTime = time.format(dtf);
     * LocalTime timeAgain = LocalTime.parse(textTime, dtf);
     */

    public void onSave(ActionEvent actionEvent) throws Exception {

        String apptDescription = descTF.getText();
        String apptLocation = locationTF.getText();
        String apptTitle = titleTF.getText();
        Contact contactSelected = contactComboBox.getSelectionModel().getSelectedItem();
        Customer customerSelected = customerComboBox.getSelectionModel().getSelectedItem();
        String apptType =  typeComboBox.getSelectionModel().getSelectedItem();
        User userSelected = userComboBox.getSelectionModel().getSelectedItem();
        LocalDateTime start = LocalDateTime.of(newApptDate.getValue(), startTimeCB.getValue());

        /**static ZonedDateTime 	of(int year, int month, int dayOfMonth, int hour, int minute, int second, int nanoOfSecond, ZoneId zone)
         Obtains an instance of ZonedDateTime from a year, month, day, hour, minute, second, nanosecond and time-zone.
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
