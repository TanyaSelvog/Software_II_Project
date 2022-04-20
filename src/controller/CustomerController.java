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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointments;
import model.Country;
import model.Customer;
import utils.ApptsDB;
import utils.CountryDB;
import utils.CustDB;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {
    @FXML
    public TableColumn customerID;
    @FXML
    public TableColumn name;
    @FXML
    public TableColumn address;
    @FXML
    public TableColumn postalCode;
    @FXML
    public TableColumn phone;
    @FXML
    public TableColumn country;
    @FXML
    public TableColumn division;
    @FXML
    public Button addNewBtn;
    @FXML
    public Button modifyBtn;
    @FXML
    public Button backBtn;
    @FXML
    public TableView <Customer>customersTable;
    @FXML
    public Button deleteCustomerBtn;
    private Stage stage;
    private Parent scene;

    private ObservableList<Customer> customersList = FXCollections.observableArrayList();
   private  ObservableList<Country> countriesList = FXCollections.observableArrayList();
    private static Customer modCustomer;

    public static Customer getModCustomer(){
        return modCustomer;
    }

    /**
     * Method that initializes the controller
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        customersTable.setItems(CustDB.getCustomersList());




        //fxid (for each column name) is 1st
        customerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        name.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        address.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        postalCode.setCellValueFactory(new PropertyValueFactory<>("customerPostal"));
        phone.setCellValueFactory(new PropertyValueFactory<>("customerPhone"));
        division.setCellValueFactory(new PropertyValueFactory<>("customerDivision"));
        country.setCellValueFactory(new PropertyValueFactory<>("customerCountry"));

    }

    /**
     *
     * @param actionEvent
     * @throws Exception
     */
    public void onAddNewCustomer(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/CustomerForm.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Add New Customer");
        Scene scene = new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }

    /**
     *
     * @param actionEvent
     * @throws Exception
     */
    public void onModifyCurrent(ActionEvent actionEvent) throws Exception {

        Customer modCustomer = customersTable.getSelectionModel().getSelectedItem();
        System.out.println("modCustomer from onModifyCurrent() in custController: " + modCustomer);
        if (modCustomer == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Modify a Customer");
            alert.setHeaderText("Error");
            alert.setContentText("Select a Customer to modify.");
            alert.showAndWait();
    }else {
            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/modifyCustomerForm.fxml"));
                Parent root = loader.load();

                // must get access to the controller to make the screen
                //so using the getController method
                ModifyCustomerFormController controller = loader.getController();
                controller.modCustomer(modCustomer);

                //set the stage
                stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                scene = loader.getRoot();
                stage.setScene(new Scene(scene));
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();

            }
        }}

    /**
     *
     * @param actionEvent
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
     *
     * @param actionEvent
     * @throws Exception
     */
    public void onDeleteClick(ActionEvent actionEvent) throws Exception {
        Customer deletedCustomer = (Customer) customersTable.getSelectionModel().getSelectedItem();
        if (deletedCustomer != null) {
            Alert alertDelete = new Alert(Alert.AlertType.CONFIRMATION);
            alertDelete.setContentText("Do you want to delete the selected customer?");
            Optional<ButtonType> userAnswer = alertDelete.showAndWait();

            if (userAnswer.isPresent() && userAnswer.get() == ButtonType.OK) {
                int custID = deletedCustomer.getCustomerID();
                System.out.println(custID);
                CustDB.deleteCustomer(custID);
                Alert alert = new Alert(Alert.AlertType.INFORMATION,
                        ("The records for " + deletedCustomer.getCustomerName()  + " have been deleted."));

                alert.setTitle("Customer deleted.");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, ("Select an appointment to delete."));
            alert.showAndWait();
        }
    }
}

