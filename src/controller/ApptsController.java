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

public class ApptsController implements Initializable {
    public TableView <Appointments> monthlyTable;
    public TableColumn titleMonthly;
    public TableColumn descMonthly;
    public TableColumn locationMonthly;
    public TableColumn contactMonthly;
    public TableColumn typeMonthly;
    public TableColumn startMonthly;
    public TableColumn customerMonthly;
    public TableColumn endMonthly;
    public TableColumn userIDmonthly;
    public TableColumn apptIDmonthly;
    public Tab weeklyApptTab;
    public TableColumn titleWeekly;
    public TableView <Appointments> weeklyTable;
    public TableColumn descWeekly;
    public TableColumn locationWeekly;
    public TableColumn typeWeekly;
    public TableColumn contactWeekly;
    public TableColumn startWeekly;
    public TableColumn endWeekly;
    public TableColumn userIDweekly;
    public TableColumn customerWeekly;
    public TableColumn apptIDweekly;
    public Button newApptBtn;
    public Button modifyApptBtn;
    public Button deleteApptBtn;
    public Button backBtn;

    public Appointments deletedAppt;
    public Tab allApptsTab;
    public TableView <Appointments> allApptsTable;
    public TableColumn titleAllAppts;
    public TableColumn descAllAppts;
    public TableColumn locationAllAppts;
    public TableColumn contactAllAppts;
    public TableColumn typeAllAppts;
    public TableColumn  startAllAppts;
    public TableColumn endAllAppts;
    public TableColumn customerAllAppts;
    public TableColumn idUserAllAppts;
    public TableColumn idApptAllAppt;
    public TabPane apptsTabPane;

    public Tab weeklyTab;
    public Tab monthlyTab;
    private Appointments modAppointments;
    private Stage stage;
    private Parent scene;
    private ObservableList<Appointments> appointmentList = ApptsDB.getApptsList();
    private FilteredList<Appointments> apptFilteredList = new FilteredList<>(appointmentList, n -> true);
    public static LocalDateTime loginTime;
    public static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("hh:mm a");
    public static DateTimeFormatter dateTime = DateTimeFormatter.ofPattern("MM-dd-yyyy hh:mm a");
    public static DateTimeFormatter dateOnlyTime = DateTimeFormatter.ofPattern("MM-dd-yyyy");
    public static String ap;
    public static  ObservableList<Appointments> selectedItems;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //need to show monthly & weekly appts
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

    public void onNewAppt(ActionEvent actionEvent) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/view/NewAppointmentForm.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("New Appointment");
        Scene scene = new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.show();

    }

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
          /**  Parent root = FXMLLoader.load(getClass().getResource("/view/ModifyAppointmentForm.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("Modify Appointment");
            Scene scene = new Scene(root, 1000, 600);
            stage.setScene(scene);
            stage.show();
             */
            } catch (Exception e) {
                e.printStackTrace();
        }}
        }
    public void onDeleteAppt(ActionEvent actionEvent){

        TableView<Appointments> currentTable = weeklyTab.isSelected() ? weeklyTable:
                monthlyTab.isSelected() ? monthlyTable: allApptsTable;
                Appointments deletedAppt = currentTable.getSelectionModel().getSelectedItem();

        if (deletedAppt != null) {
            Alert alertDelete = new Alert(Alert.AlertType.CONFIRMATION);
            alertDelete.setContentText("Do you want to delete the selected appointment?");
            Optional<ButtonType> userAnswer = alertDelete.showAndWait();

            if (userAnswer.isPresent() && userAnswer.get() == ButtonType.OK) {
                int apptID = deletedAppt.getApptID();
                System.out.println(apptID);
                String apptType = deletedAppt.getApptType();
                System.out.println(apptType);
                ApptsDB.deleteAppointment(deletedAppt.getApptID());
                Alert alert = new Alert(Alert.AlertType.INFORMATION,
                        (apptType +" with appointment ID: " + apptID + " has been deleted."));

                alert.setTitle("Appointment deleted.");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, ("Select an appointment to delete."));
            alert.showAndWait();
        }
        /**
        Parent root = FXMLLoader.load(getClass().getResource("/view/CustomerForm.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Modify Current Customer");
        Scene scene = new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }
           */
        }

    public void onBackToMain(ActionEvent actionEvent) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/view/HomepageWindow.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Scheduler Homepage");
        Scene scene = new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }




    public void updateWeeklyTable(){
        ObservableList<Appointments> appointmentList = ApptsDB.getApptsList();
        FilteredList<Appointments> apptWeekFilter = new FilteredList<>(appointmentList, n -> true);

        LocalDateTime userLogin = LocalDateTime.now();
        LocalDateTime logTimePlusDays = userLogin.plusDays(7);

        apptWeekFilter.setPredicate(appt -> appt.getStartDate().isAfter(userLogin) && appt.getStartDate().isBefore(logTimePlusDays));
        weeklyTable.setItems(apptWeekFilter);

    }
    public void updateMonthlyTable(){
        ObservableList<Appointments> appointmentList = ApptsDB.getApptsList();
        FilteredList<Appointments> apptMonthFilter = new FilteredList<>(appointmentList, n -> true);

        LocalDateTime userLogin = LocalDateTime.now();
        LocalDateTime logTimePlusMonth = userLogin.plusMonths(1);

        apptMonthFilter.setPredicate(appt -> appt.getStartDate().isAfter(userLogin) && appt.getStartDate().isBefore(logTimePlusMonth));
        monthlyTable.setItems(apptMonthFilter);
    }


    public void onAllApptsTab(Event event) {
        Appointments appt = allApptsTable.getSelectionModel().getSelectedItem();
    }

    public void event(Event event) {
    }
}



