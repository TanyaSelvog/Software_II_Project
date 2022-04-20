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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
 * Class for Customer Form Controller
 */
public class CustomerFormController implements Initializable {

    @FXML
    public ComboBox countryComboBox;
    @FXML
    public ComboBox divisionComboBox;
    @FXML
    public Button homeBtn;
    @FXML
    public Button cancelBtn;
    @FXML
    public Button saveBtn;
    @FXML
    public TextField customerName;
    @FXML
    public TextField customerPhone;
    @FXML
    public TextField customerPostalCode;
    @FXML
    public TextField customerAddress;
    @FXML
    public TextField customerID;
    private static Customer modCustomer;
    private ObservableList<Country> countriesList = FXCollections.observableArrayList();
    public static Customer getModCustomer(){
        return modCustomer;

    }

    private static int index;
    /**
     * This method initializes the controller.
     *
     * @param url            Used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle Used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        countryComboBox.setItems(CountryDB.getCountryList());
    }

    /**
     *
     * @param actionEvent
     * @throws Exception
     */
    public void onHome(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/HomepageWindow.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Modify Current Customer");
        Scene scene = new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.show();

    }

    /**
     *
     * @param actionEvent
     * @throws Exception
     */

    public void onSaveBtn(ActionEvent actionEvent) throws Exception {
        String custName = customerName.getText();
        String custAddress = customerAddress.getText();

        String customerPostal =customerPostalCode.getText();
        String custPhone = customerPhone.getText();
        Country countryName = (Country) countryComboBox.getValue();
        Division division = (Division)divisionComboBox.getSelectionModel().getSelectedItem();

        if (custName.isEmpty() || custAddress.isEmpty() || custPhone.isEmpty() || countryName == null  || divisionComboBox == null ||
                customerPostal.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Missing input.");
            alert.setContentText("Data is missing in one or more fields.");
            alert.showAndWait();

        }else{
            int divisionID = division.getDivisionID();
            CustDB.createCustomer(custName, custAddress, customerPostal, custPhone, divisionID);
            Parent root = FXMLLoader.load(getClass().getResource("/view/CustomersView.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("All Customers");
            Scene scene = new Scene(root, 1000, 600);
            stage.setScene(scene);
        }

     /**   Parent root = FXMLLoader.load(getClass().getResource("/view/CustomersView.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("All Customers");
        Scene scene = new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.show();

*/

    }

    /**
     *
     * @param actionEvent
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
     *
     * @param actionEvent
     */
    public void onCountrySelected(ActionEvent actionEvent) {
        Country selectedCountry = (Country) countryComboBox.getValue();
        divisionComboBox.setItems(DivisionsDB.getDivisionList(selectedCountry.getCountryID()));


    }

}
