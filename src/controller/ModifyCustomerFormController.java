package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Country;
import model.Customer;
import model.Division;
import utils.CountryDB;
import utils.CustDB;
import utils.DivisionsDB;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for the Modify Customer Form
 */

public class ModifyCustomerFormController implements Initializable {

    @FXML
    private TextField customerName;
    @FXML
    private TextField customerPhone;
    @FXML
    private TextField customerPostalCode;
    @FXML
    private TextField customerAddress;
    @FXML
    private ComboBox  countryComboBox;
    @FXML
    private ComboBox<Division> divisionComboBox;
    @FXML
    private TextField customerID;


    public Customer customerModify = null;


    /**
     * Method for initializing the Modify Customer Form Controller
     * @param url            Used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle Used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize (URL url, ResourceBundle resourceBundle) {

        countryComboBox.setItems(CountryDB.getCountryList());

    }

    /**
     * Event handler for when a country is selected
     * @param actionEvent Country selected from Combo Box
     * @throws Exception
     */
    public void countrySelected(ActionEvent actionEvent) throws Exception {
        Country countrySelected = (Country) countryComboBox.getSelectionModel().getSelectedItem();
        System.out.println("countrySelected from countrySelected method: " + countrySelected);
        divisionComboBox.setItems(DivisionsDB.getDivisionList(countrySelected.getCountryID()));

    }
    /**
     * Method for setting the customer to be modified
     * @param customer
     */
    public void modCustomer(Customer customer){

        Division division = DivisionsDB.getCustomerDivision(customer.getDivisionID());
        divisionComboBox.getSelectionModel().select(division);
        int id = customer.getCustomerID();
        customerID.setText(String.valueOf(id));
        customerName.setText(customer.getCustomerName());
        customerPhone.setText(customer.getCustomerPhone());
        customerAddress.setText(customer.getCustomerAddress());
        customerPostalCode.setText(customer.getCustomerPostal());
        String countryTest = customer.getCustomerCountry();
        countryComboBox.setValue(countryTest);
        System.out.println(countryTest);

    }

    /**
     * Method for validating input fields and saving modified customer data to database
     * @return null
     */

    public Customer getCustomerModification() {
        try{
            int id = Integer.parseInt(customerID.getText());
            System.out.println("int id from onSaveBtn() " + id);
            String custName = customerName.getText();
            System.out.println(custName);
            String custAddress = customerAddress.getText();
            String custPhone = customerPhone.getText();
            String customerPostal = customerPostalCode.getText();
            int divisionID = divisionComboBox.getSelectionModel().getSelectedItem().getDivisionID();
            System.out.println("onSaveBtn()'s divisionID " + divisionID);

            if(custName.isEmpty() || custAddress.isEmpty() || custPhone.isEmpty() || customerPostal.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Missing input.");
            alert.setContentText("Data is missing in one or more fields.");
            alert.showAndWait();
            }
            else{
                Customer newCustomer = new Customer(id, custName, custAddress, custPhone, customerPostal, divisionID);
                CustDB.modifyCustomer(id, custName, custAddress, customerPostal, custPhone, divisionID);
                return newCustomer;
            }
        } catch(Exception displayE){
            Alert alert = new Alert(Alert.AlertType.ERROR, ("Data is missing or contains invalid values."));
            alert.showAndWait();}

        return null;
        }

    /**
     * Event handler for Save button
     * @param actionEvent On click
     * @throws Exception
     */
    public void onSaveBtn(ActionEvent actionEvent) throws Exception{
        Customer customer =  getCustomerModification();
            if (customer !=null){
                Parent root = FXMLLoader.load(getClass().getResource("/view/CustomersView.fxml"));
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setTitle("All Customers");
                Scene scene = new Scene(root, 1000, 600);
                stage.setScene(scene);
                stage.show();
        }
    }

    /**
     * Event handler for Cancel button
     * @param actionEvent On click
     * @throws Exception
     */
    public void onCancelBtn(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/CustomersView.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("All Customers");
        Scene scene = new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.show();

    }

    /**
     * Event handler for Home button
     * @param actionEvent On click
     * @throws Exception
     */
    public void onHome(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/HomePageWindow.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Scheduler Homepage");
        Scene scene = new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }

}
