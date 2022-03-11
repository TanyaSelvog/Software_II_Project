package controller;

import javafx.event.ActionEvent;
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

public class ModifyCustomerFormController implements Initializable {

    public TextField customerName;
    public TextField customerPhone;
    public TextField customerPostalCode;
    public TextField customerAddress;
    public ComboBox  countryComboBox;
    public ComboBox divisionComboBox;
    public TextField customerID;

    public Customer customerModify = null;



    @Override
    public void initialize (URL url, ResourceBundle resourceBundle) {

        String custName = customerName.getText();
        System.out.println(custName);


        countryComboBox.setItems(CountryDB.getCountryList());
    //    divisionComboBox.setItems(DivisionsDB.getDivisionList());

    }

    public void countrySelected(ActionEvent actionEvent) throws Exception{

        Country countrySelected = (Country) countryComboBox.getValue();
        divisionComboBox.setItems(DivisionsDB.getDivisionList(countrySelected.getCountryID()));
    }

    public void modCustomer(Customer customer){
        int id = customer.getCustomerID();
        customerID.setText(String.valueOf(id));
        System.out.println(id);

      //  customerID.setText(String.valueOf(customer.getCustomerID()));

        String nameTest = customer.getCustomerName();
        customerName.setText(nameTest);
        System.out.println(nameTest);

        String phone = customer.getCustomerPhone();
        customerPhone.setText(phone);
        System.out.println(phone);
       // customerPhone.setText(customer.getCustomerPhone());

        String address = customer.getCustomerAddress();
        customerAddress.setText(address);
        System.out.println(address);
        //customerAddress.setText(customer.getCustomerAddress());

        String postalCode = customer.getCustomerPostal();
        customerPostalCode.setText(postalCode);
        System.out.println(postalCode);
       // customerPostalCode.setText(customer.getCustomerPostal());

        String countryTest = customer.getCustomerCountry();
        countryComboBox.setValue(countryTest);
        System.out.println(countryTest);

        String divisionTest = customer.getCustomerDivision();
        divisionComboBox.setValue(divisionTest);
        System.out.println(divisionTest);

        int customerID = customer.getDivisionID();
        System.out.println(customerID);
        //Country country = (Country) countryComboBox.getValue();
        //Division division = (Division)divisionComboBox.getValue();
        //countryComboBox.setValue(customer.getCustomerCountry());
       // divisionComboBox.setValue(customer.getCustomerDivision());


    }
    // 3.10 Works - Need to filter to check for user input
    //need to fix division
    public void onSaveBtn(ActionEvent actionEvent) throws Exception{
        int id = Integer.parseInt(customerID.getText());
        String custName = customerName.getText();
        String custAddress = customerAddress.getText();
        String custPhone = customerPhone.getText();
        String customerPostal =customerPostalCode.getText();
        Country countryName = (Country) countryComboBox.getValue();
        Division division = (Division)divisionComboBox.getValue();

        int divisionID = division.getDivisionID();

        /**
        if (custName.isEmpty() || custAddress.isEmpty() || custPhone.isEmpty() || customerPostal.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Missing input.");
            alert.setContentText("Data is missing in one or more fields.");
            alert.showAndWait();

        } else {
            CustDB.modifyCustomer(id, custName, custAddress, customerPostal, custPhone, divisionID);
        }
         */
        CustDB.modifyCustomer(id, custName, custAddress, customerPostal, custPhone, divisionID);
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

    public void onHome(ActionEvent actionEvent) {
    }
}
