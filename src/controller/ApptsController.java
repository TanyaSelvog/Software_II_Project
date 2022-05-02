package controller;

import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Appointments;
import model.Customer;
import utils.ApptsDB;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Filter;

/**
 * This class is a controller for the Appointments view page.
 */
public class ApptsController implements Initializable {

    /**
     * Table for monthly appointments only
     */
    public TableView <Appointments> monthlyTable;

    /**
     * Title column for monthly table
     */
    public TableColumn titleMonthly;

    /**
     * Description column for monthly table
     */
    public TableColumn descMonthly;

    /**
     * Location column for monthly table
     */
    public TableColumn locationMonthly;
    /**
     * Contact column for monthly table
     */
    public TableColumn contactMonthly;

    /**
     * Type column for monthly table
     */
    public TableColumn typeMonthly;

    /**
     * Start time column for monthly table
     */
    public TableColumn startMonthly;

    /**
     * Customer column for monthly table
     */
    public TableColumn customerMonthly;

    /**
     * End time for monthly table
     */
    public TableColumn endMonthly;

    /**
     * User ID column for monthly table
     */
    public TableColumn userIDmonthly;

    /**
     * Appointment ID column for monthly table
     */
    public TableColumn apptIDmonthly;

    /**
     * Tab for weekly appointment table
     */
    public Tab weeklyApptTab;

    /**
     * Title column for weekly table
     */
    public TableColumn titleWeekly;
    /**
     * Table for displaying weekly appointments
     */
    public TableView <Appointments> weeklyTable;
    /**
     * Description column for weekly table
     */
    public TableColumn descWeekly;

    /**
     * Location column for weekly table
     */

    public TableColumn locationWeekly;

    /**
     * Type column for weekly table
     */

    public TableColumn typeWeekly;
    /**
     * Contact column for weekly table
     */

    public TableColumn contactWeekly;

    /**
     * Start time column for weekly table
     */
    public TableColumn startWeekly;

    /**
     * End time column for weekly table
     */
    public TableColumn endWeekly;

    /**
     * User ID column for weekly table
     */
    public TableColumn userIDweekly;
    /**
     * Customer column for weekly table
     */
    public TableColumn customerWeekly;

    /**
     * Appointment ID column for weekly table
     */
    public TableColumn apptIDweekly;

    /**
     * Button for creating new appointments
     */
    public Button newApptBtn;

    /**
     * Button for modifying existing appointments
     */
    public Button modifyApptBtn;
    /**
     * Button for deleting appointments
     */
    public Button deleteApptBtn;
    /**
     * Back button (for returning to Homepage)
     */
    public Button backBtn;
    /**
     * Tab for all appointments table
     */
    public Tab allApptsTab;
    /**
     * Table that displays all appointments in system
     */
    public TableView <Appointments> allApptsTable;
    /**
     * Title column for all appointments table
     */
    public TableColumn titleAllAppts;
    /**
     * Description column for all appointments table
     */
    public TableColumn descAllAppts;
    /**
     * Location column for all appointments table
     */

    public TableColumn locationAllAppts;

    /**
     * Contact column for all appointments table
     */
    public TableColumn contactAllAppts;

    /**
     * Type column for all appointments table
     */
    public TableColumn typeAllAppts;

    /**
     * Start time column for all appointments table
     */
    public TableColumn  startAllAppts;

    /**
     * End time column for all appointments table
     */

    public TableColumn endAllAppts;

    /**
     * Customer column for all appointments table
     */
    public TableColumn customerAllAppts;

    /**
     * User ID column for all appointments table
     */
    public TableColumn idUserAllAppts;

    /**
     * Appointment ID column for all appointments table
     */
    public TableColumn idApptAllAppt;

    /**
     * Tab pane for appointment tables
     */
    public TabPane apptsTabPane;

    /**
     * Tab for weekly appointments table
     */
    public Tab weeklyTab;

    /**
     * Tab for monthly appointments table
     */
    public Tab monthlyTab;
    private Appointments modAppointments;
    /**
     * Stage
     */
    private Stage stage;

    /**
     * Scene
     */
    private Parent scene;

    /**
     * List of all appointments (from the database)
     */
    private ObservableList<Appointments> appointmentList = ApptsDB.getApptsList();

    /**
     * Filtered list of appointments
     */
    private FilteredList<Appointments> apptFilteredList = new FilteredList<>(appointmentList, n -> true);

    /**
     * Login time from when the user logs in
     */
    public static LocalDateTime loginTime;

    /**
     * Formatter for time (hh:mm a)
     */
    public static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("hh:mm a");

    /**
     * Formatter for date and time (MM-dd-yyyy hh:mm a)
     */
    public static DateTimeFormatter dateTime = DateTimeFormatter.ofPattern("MM-dd-yyyy hh:mm a");

    /**
     * Formatter for date (MM-dd-yyyy)
     */
    public static DateTimeFormatter dateOnlyTime = DateTimeFormatter.ofPattern("MM-dd-yyyy");

    public static  ObservableList<Appointments> selectedItems;

    /**
     * Method that initializes the controller and displays the tables
     * @param url
     * @param resourceBundle
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allApptsTable.setItems(appointmentList);
        updateWeeklyTable();
        updateMonthlyTable();


        titleMonthly.setCellValueFactory(new PropertyValueFactory<>("apptTitle"));
        descMonthly.setCellValueFactory(new PropertyValueFactory<>("apptDescription"));
        locationMonthly.setCellValueFactory(new PropertyValueFactory<>("apptLocation"));
        contactMonthly.setCellValueFactory(new PropertyValueFactory<>("apptContact"));
        typeMonthly.setCellValueFactory(new PropertyValueFactory<>("apptType"));
        startMonthly.setCellValueFactory(new PropertyValueFactory<>("startDateString"));
        endMonthly.setCellValueFactory(new PropertyValueFactory<>("endDateString"));
        customerMonthly.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        userIDmonthly.setCellValueFactory(new PropertyValueFactory<>("userID"));
        apptIDmonthly.setCellValueFactory(new PropertyValueFactory<>("apptID"));


        titleWeekly.setCellValueFactory(new PropertyValueFactory<>("apptTitle"));
        descWeekly.setCellValueFactory(new PropertyValueFactory<>("apptDescription"));
        locationWeekly.setCellValueFactory(new PropertyValueFactory<>("apptLocation"));
        contactWeekly.setCellValueFactory(new PropertyValueFactory<>("apptContact"));
        typeWeekly.setCellValueFactory(new PropertyValueFactory<>("apptType"));
        startWeekly.setCellValueFactory(new PropertyValueFactory<>("startDateString"));
        endWeekly.setCellValueFactory(new PropertyValueFactory<>("endDateString"));
        customerWeekly.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        userIDweekly.setCellValueFactory(new PropertyValueFactory<>("userID"));
        apptIDweekly.setCellValueFactory(new PropertyValueFactory<>("apptID"));

        //fxid (for each column name) is 1s
        titleAllAppts.setCellValueFactory(new PropertyValueFactory<>("apptTitle"));
        descAllAppts.setCellValueFactory(new PropertyValueFactory<>("apptDescription"));
        locationAllAppts.setCellValueFactory(new PropertyValueFactory<>("apptLocation"));
        contactAllAppts.setCellValueFactory(new PropertyValueFactory<>("apptContact"));
        typeAllAppts.setCellValueFactory(new PropertyValueFactory<>("apptType"));
        startAllAppts.setCellValueFactory(new PropertyValueFactory<>("startDateString"));
        endAllAppts.setCellValueFactory(new PropertyValueFactory<>("endDateString"));
        customerAllAppts.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        idUserAllAppts.setCellValueFactory(new PropertyValueFactory<>("userID"));
        idApptAllAppt.setCellValueFactory(new PropertyValueFactory<>("apptID"));

    }

    /**
     * Method for on New Appointment button click that directs user to New Appointment page
     * @param actionEvent New Appointment button clicked
     * @throws Exception
     */
    public void onNewAppt(ActionEvent actionEvent) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/view/NewAppointmentForm.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("New Appointment");
        Scene scene = new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.show();

    }

    /**
     * Method for on Modify Appointment button that gets appointment object from table based on which one is selected
     * @param actionEvent Modify Appointment button click
     * @throws Exception
     */
    public void onModifyAppt(ActionEvent actionEvent) throws Exception {

        TableView<Appointments> currentTable = weeklyTab.isSelected() ? weeklyTable:
          monthlyTab.isSelected() ? monthlyTable: allApptsTable;
          Appointments appointment = currentTable.getSelectionModel().getSelectedItem();

          if (appointment == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, ("Select an appointment to modify."));
            alert.showAndWait();
        } else {

            try{
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/modifyAppointmentForm.fxml"));
             Parent root = loader.load();

             // must get access to the controller to make the screen
             //so using the getController method
             ModifyApptController controller = loader.getController();
             controller.modAppointment(appointment);

             //set the stage
             stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
             scene = loader.getRoot();
             stage.setScene(new Scene(scene));
             stage.show();

            } catch (Exception e) {
                e.printStackTrace();
        }}
        }

    /**
     * Displays appointments when tab is selected and updates/refreshes table contents
     */
    public void tabSelection(){
        Tab selectedTab = apptsTabPane.getSelectionModel().getSelectedItem();
        ObservableList<Appointments> appointmentList = ApptsDB.getApptsList();
        apptFilteredList =new FilteredList<>(appointmentList, n -> true);

            if (selectedTab == allApptsTab) {
               updateAllTable();
            }else if (selectedTab == weeklyTab) {
                updateWeeklyTable();
            } else if (selectedTab == monthlyTab) {
                updateMonthlyTable();
            }
        }

    /**
     * Deletes appointment based on selection and confirmation; refreshes table contents after successful deletion
     * @param actionEvent Delete button click
     * @throws Exception
     */
    public void onDeleteAppt(ActionEvent actionEvent)throws Exception{

        TableView<Appointments> currentTable = weeklyTab.isSelected() ? weeklyTable:
                monthlyTab.isSelected() ? monthlyTable: allApptsTable;
                Appointments deletedAppt = currentTable.getSelectionModel().getSelectedItem();


        if (deletedAppt != null) {
            Alert alertDelete = new Alert(Alert.AlertType.CONFIRMATION);
            alertDelete.setContentText("Do you want to delete the selected appointment?");
            Optional<ButtonType> userAnswer = alertDelete.showAndWait();

            if (userAnswer.isPresent() && userAnswer.get() == ButtonType.OK) {
                int apptID = deletedAppt.getApptID();
                String apptType = deletedAppt.getApptType();
                ApptsDB.deleteAppointment(deletedAppt.getApptID());

                Alert alert = new Alert(Alert.AlertType.INFORMATION,
                        (apptType +" with appointment ID: " + apptID + " has been deleted."));

                alert.setTitle("Appointment deleted.");
                alert.showAndWait();
                tabSelection();

            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, ("Select an appointment to delete."));
            alert.showAndWait();
        }

        }

    /**
     * Event handler for BackToMain button that sends user to Home page
     * @param actionEvent on button click
     * @throws Exception
     */
    public void onBackToMain(ActionEvent actionEvent) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/view/HomepageWindow.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Scheduler Homepage");
        Scene scene = new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }


    /**
     * Shows filtered list of all appointments within the upcoming week
     * Used lambda expression for reducing lines of code needed for filtering appointments
     */
    public void updateWeeklyTable(){
        ObservableList<Appointments> appointmentList = ApptsDB.getApptsList();
        FilteredList<Appointments> apptWeekFilter = new FilteredList<>(appointmentList, n -> true);

        LocalDateTime userLogin = LocalDateTime.now();
        LocalDateTime logTimePlusDays = userLogin.plusDays(7);

        apptWeekFilter.setPredicate(appt -> appt.getStartDate().isAfter(userLogin) && appt.getStartDate().isBefore(logTimePlusDays));
        weeklyTable.setItems(apptWeekFilter);

    }

    /**
     * Method that displays monthly table contents via filtered list
     * Used lambda expression for reducing lines of code needed for filtering appointments
     */
    public void updateMonthlyTable(){
        ObservableList<Appointments> appointmentList = ApptsDB.getApptsList();
        FilteredList<Appointments> apptMonthFilter = new FilteredList<>(appointmentList, n -> true);

        LocalDateTime userLogin = LocalDateTime.now();
        LocalDateTime logTimePlusMonth = userLogin.plusMonths(1);

        apptMonthFilter.setPredicate(appt -> appt.getStartDate().isAfter(userLogin) && appt.getStartDate().isBefore(logTimePlusMonth));
        monthlyTable.setItems(apptMonthFilter);
    }

    /**
     * Shows filtered list of all appointments in All Appointments table
      */
    public void updateAllTable(){
        ObservableList<Appointments> appointmentList = ApptsDB.getApptsList();
        FilteredList<Appointments> allApptFilter = new FilteredList<>(appointmentList, n -> true);
        allApptsTable.setItems(allApptFilter);

    }

}



