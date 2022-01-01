package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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

public class CustomerFormController implements Initializable {

    public ComboBox countryComboBox;
    public ComboBox divisionComboBox;
    public Button homeBtn;
    public Button cancelBtn;
    public Button saveBtn;
    public TextField customerName;
    public TextField customerPhone;
    public TextField customerPostalCode;
    public TextField customerAddress;
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

    public void onHome(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/HomepageWindow.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Modify Current Customer");
        Scene scene = new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.show();

    }

    public void onSaveBtn(ActionEvent actionEvent) throws Exception {
        String custName = customerName.getText();
        String custAddress = customerAddress.getText();
        String custPhone = customerPhone.getText();
        String customerPostal =customerPostalCode.getText();
        Country country = (Country) countryComboBox.getValue();
        Division division = (Division) divisionComboBox.getValue();

        if (custName.isEmpty() || custAddress.isEmpty() || custPhone.isEmpty() || customerPostal.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Missing input.");
            alert.setContentText("Data is missing in one or more fields.");
            alert.showAndWait();
        }else{
            System.out.println(custName + " " + custAddress + " " +custPhone + " " + customerPostal + division + country);
        }

        Parent root = FXMLLoader.load(getClass().getResource("/view/CustomersView.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("All Customers");
        Scene scene = new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.show();


    }

    public void onCancelBtn(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/CustomersView.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("All Customers");
        Scene scene = new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }

    public void onCountrySelected(ActionEvent actionEvent) {
        Country selectedCountry = (Country) countryComboBox.getValue();
        divisionComboBox.setItems(DivisionsDB.getDivisionList(selectedCountry.getCountryID()));


    }

}
