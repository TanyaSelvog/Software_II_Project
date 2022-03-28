package controller;

import javafx.collections.ObservableList;
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

import java.net.URL;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;

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
    private Appointments modAppointments;
    private Stage stage;
    private Parent scene;
    private ObservableList<Appointments> appointmentList = ApptsDB.getApptsList();


    private int index;




    public void onNewAppt(ActionEvent actionEvent) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/view/NewAppointmentForm.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("New Appointment");
        Scene scene = new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.show();

    }
    //testing for checking tab 1.23
    public void testTab(){
        Tab selectedTab = apptsTabPane.getSelectionModel().getSelectedItem();
        if (selectedTab == weeklyApptTab) {
            System.out.println("good");
        } else{
            System.out.println("nope");
        }
    }


    public void onModifyAppt(ActionEvent actionEvent) throws Exception {

        //Tab selectedTab = apptsTabPane.getSelectionModel().getSelectedItem();
        Appointments modAppt = allApptsTable.getSelectionModel().getSelectedItem();
        System.out.println("modAppointments from onModifyAppt() in apptsController: " + modAppointments);
        index = allApptsTable.getSelectionModel().getSelectedIndex();
      //  modAppointments = (Appointments) weeklyTable.getSelectionModel().getSelectedItem();
        //index = weeklyTable.getSelectionModel().getSelectedIndex();


        if (modAppt == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, ("Select an appointment to modify."));
            alert.showAndWait();
        } else {
            try{
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/modifyAppointmentForm.fxml"));
             Parent root = loader.load();

             // must get access to the controller to make the screen
             //so using the getController method
             ModifyApptController controller = loader.getController();
             controller.modAppointment(modAppt);

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
        testTab();
        Parent root = FXMLLoader.load(getClass().getResource("/view/HomepageWindow.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Scheduler Homepage");
        Scene scene = new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //need to show monthly & weekly appts
        allApptsTable.setItems(appointmentList);
//       allApptsTable.setItems(ApptsDB.getApptsList());





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
        startAllAppts.setCellValueFactory(new PropertyValueFactory<>("startDateString"));
        endAllAppts.setCellValueFactory(new PropertyValueFactory<>("endDateString"));
        customerAllAppts.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        idUserAllAppts.setCellValueFactory(new PropertyValueFactory<>("userID"));
        idApptAllAppt.setCellValueFactory(new PropertyValueFactory<>("apptID"));

    }
        }



