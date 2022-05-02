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

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Controller for Customer view
 */
public class CustomerController implements Initializable {
    /**
     * Customer ID column
     */
    @FXML
    public TableColumn customerID;

    /**
     * Customer name column
     */
    @FXML
    public TableColumn name;

    /**
     * Customer address column
     */
    @FXML
    public TableColumn address;

    /**
     * Customer postal code column
     */
    @FXML
    public TableColumn postalCode;

    /**
     * Customer phone column
     */
    @FXML
    public TableColumn phone;

    /**
     * Customer country column
     */
    @FXML
    public TableColumn country;

    /**
     * Customer division column
     */
    @FXML
    public TableColumn division;

    /**
     * Button for add a new customer
     */
    @FXML
    public Button addNewBtn;

    /**
     * Button for modify a customer
     */
    @FXML
    public Button modifyBtn;

    /**
     * Back button (for returning to Homepage)
     */
    @FXML
    public Button backBtn;

    /**
     * Customer table
     */
    @FXML
    public TableView <Customer>customersTable;

    /**
     * Delete customer button
     */
    @FXML
    public Button deleteCustomerBtn;

    /**
     * Stage
     */
    private Stage stage;

    /**
     * Scene
     */
    private Parent scene;

    /**
     * List of customers
     */
    private static ObservableList<Customer> customersList = FXCollections.observableArrayList();

    /**
     * List of countries
     */
    private  ObservableList<Country> countriesList = FXCollections.observableArrayList();

    /**
     * Customer object
     */
    private static Customer modCustomer;

    /**
     * Getter for customer object
     * @return modCustomer
     */
    public static Customer getModCustomer(){
        return modCustomer;
    }

    /**
     * Method that initializes the controller and sets the table
     * @param url Used to rseolve relative paths for the root object, or null lif the location is not known.
     * @param resourceBundle Used to localize the root object, or null if the root object was not localized.
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
     * Event that takes user to Add New Customer page on click
     * @param actionEvent Add New Customer button click
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
     * Gets customer from customers table or displays error if no customer is selected
     * @param actionEvent Modify button
     * @throws Exception
     */
    public void onModifyCurrent(ActionEvent actionEvent) throws Exception {

        Customer modCustomer = customersTable.getSelectionModel().getSelectedItem();
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
     * Event handler for returning user back to home page
     * @param actionEvent on click
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
     * Deletes customer based on user selection and confirmation
     * @param actionEvent delete button click
     * @throws IOException
     */
    public void onDeleteClick(ActionEvent actionEvent) throws IOException {
        Customer deletedCustomer = (Customer) customersTable.getSelectionModel().getSelectedItem();

        if (deletedCustomer != null) {
            Alert alertDelete = new Alert(Alert.AlertType.CONFIRMATION);
            alertDelete.setContentText("Do you want to delete the selected customer?");
            Optional<ButtonType> userAnswer = alertDelete.showAndWait();

            if (userAnswer.isPresent() && userAnswer.get() == ButtonType.OK) {

                int custID = deletedCustomer.getCustomerID();
                System.out.println(custID);
                CustDB.deleteCustomer(custID);
                customersTable.getItems().remove(deletedCustomer);
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

