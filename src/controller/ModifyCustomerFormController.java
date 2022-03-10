package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    public ComboBox <Country> countryComboBox;
    public ComboBox divisionComboBox;
    public TextField customerID;

    public Customer customerModify = null;



    @Override
    public void initialize (URL url, ResourceBundle resourceBundle) {




        countryComboBox.setItems(CountryDB.getCountryList());
    //    divisionComboBox.setItems(DivisionsDB.getDivisionList());

    }

    public void countrySelected(ActionEvent actionEvent) throws Exception{

        Country countrySelected = (Country) countryComboBox.getValue();
        divisionComboBox.setItems(DivisionsDB.getDivisionList(countrySelected.getCountryID()));
    }
    //Similar to TightEnd example in Passing The Football proj
    //@ TODO 1.20.2022
    //@TODO 3.6 Need to fix comboboxes
    public void modCustomer(Customer customer){
        customerID.setText(String.valueOf(customer.getCustomerID()));
        customerName.setText(customer.getCustomerName());
        customerPhone.setText(customer.getCustomerPhone());
        customerAddress.setText(customer.getCustomerAddress());
        customerPostalCode.setText(customer.getCustomerPostal());
        String test = customer.getCustomerCountry();
        System.out.println(test);
        //countryComboBox.setValue(String.valueOf(customer.getCustomerCountry()));
        divisionComboBox.setValue(customer.getCustomerDivision());
        System.out.println(customer.getCustomerName());
      //  System.out.println(customerID + " " + customerName + " " + customerPhone + " " + customerAddress + " " +
         //       customerPhone + " " + customerPostalCode);
    }

    public void onSaveBtn(ActionEvent actionEvent) throws Exception{
        int id = Integer.parseInt(customerID.getText());
        String custName = customerName.getText();
        String custAddress = customerAddress.getText();
        String custPhone = customerPhone.getText();
        String customerPostal =customerPostalCode.getText();
       Country country =  countryComboBox.getValue();
      //  Country country = (Country) countryComboBox.getValue();
        Division division = (Division)divisionComboBox.getValue();
        int divisionID = division.getDivisionID();
   //   int divisionID = divisionComboBox.getSelectionModel().getSelectedItem().getDivisionID();
      //  int divisionID = division.getDivisionID();

        CustDB.modifyCustomer(id, custName, custAddress, customerPostal, custPhone, divisionID);
        System.out.println(custName);
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
