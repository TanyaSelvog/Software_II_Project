package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointments;
import utils.ApptsDB;
import utils.CustDB;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ApptsController implements Initializable {
    public TableView monthlyTable;
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
    public TableView weeklyTable;
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
    public Tab monthlyApptTab;
    public Appointments deletedAppt;
    public Tab allApptsTab;
    public TableView allApptsTable;
    public TableColumn titleAllAppts;
    public TableColumn descAllAppts;
    public TableColumn locationAllAppts;
    public TableColumn contactAllAppts;
    public TableColumn typeAllAppts;
    public TableColumn startAllAppts;
    public TableColumn endAllAppts;
    public TableColumn customerAllAppts;
    public TableColumn idUserAllAppts;
    public TableColumn idApptAllAppt;
    private Appointments modAppointments;

    private int index;
    public void onNewAppt(ActionEvent actionEvent) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/view/NewAppointmentForm.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("New Appointment");
        Scene scene = new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.show();

    }

    public void onModifyAppt(ActionEvent actionEvent) throws Exception {

        modAppointments = (Appointments) weeklyTable.getSelectionModel().getSelectedItem();
        index = weeklyTable.getSelectionModel().getSelectedIndex();


        if (modAppointments == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, ("Select an appointment to modify."));
            alert.showAndWait();
        } else {
            Parent root = FXMLLoader.load(getClass().getResource("/view/ModifyAppointmentForm.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("Modify Appointment");
            Scene scene = new Scene(root, 1000, 600);
            stage.setScene(scene);
            stage.show();
        }
    }
    public void onDeleteAppt(ActionEvent actionEvent){
//@TODO 12.14 Started
        deletedAppt = (Appointments) monthlyTable.getSelectionModel().getSelectedItem();
        if (deletedAppt != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Do you want to delete the selected appointment?");
            Optional<ButtonType> userAnswer = alert.showAndWait();

          /**  if (userAnswer.isPresent() && userAnswer.get() == ButtonType.OK) {
                Inventory.deletePart(deletedPart);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, ("Select a Part to delete."));
            alert.showAndWait();
        }
        Parent root = FXMLLoader.load(getClass().getResource("/view/CustomerForm.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Modify Current Customer");
        Scene scene = new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }
           */
        }
    }
    public void onBackToMain(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/HomepageWindow.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Scheduler Homepage");
        Scene scene = new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        allApptsTable.setItems(ApptsDB.getApptsList());


       /** allApptsTable;
        public TableColumn titleAllAppts = null;
        public TableColumn descAllAppts;
        public TableColumn locationAllAppts;
        public TableColumn contactAllAppts;
        public TableColumn typeAllAppts;
        public TableColumn startAllAppts;
        public TableColumn endAllAppts;
        public TableColumn customerAllAppts;
        public TableColumn idUserAllAppts;
        public TableColumn idApptAllAppt;
        */


        //fxid (for each column name) is 1s
        titleAllAppts.setCellValueFactory(new PropertyValueFactory<>("apptTitle"));
        descAllAppts.setCellValueFactory(new PropertyValueFactory<>("apptDescription"));
        locationAllAppts.setCellValueFactory(new PropertyValueFactory<>("apptLocation"));
        contactAllAppts.setCellValueFactory(new PropertyValueFactory<>("apptContact"));
        typeAllAppts.setCellValueFactory(new PropertyValueFactory<>("apptType"));
        startAllAppts.setCellValueFactory(new PropertyValueFactory<>("Start_Date"));
        endAllAppts.setCellValueFactory(new PropertyValueFactory<>("End_Date"));
        customerAllAppts.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        idUserAllAppts.setCellValueFactory(new PropertyValueFactory<>("userID"));
        idApptAllAppt.setCellValueFactory(new PropertyValueFactory<>("apptID"));

    }
        }



