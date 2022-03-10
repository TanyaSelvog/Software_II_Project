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
    //Similar to TightEnd example in Passing The Football proj
    //@ TODO 1.20.2022
    //@TODO 3.6 Need to fix comboboxes
    public void modCustomer(Customer customer){

        customerID.setText(String.valueOf(customer.getCustomerID()));

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


        //Country country = (Country) countryComboBox.getValue();
        //Division division = (Division)divisionComboBox.getValue();
        countryComboBox.setValue(customer.getCustomerCountry());
        divisionComboBox.setValue(customer.getCustomerDivision());
        System.out.println(customer.getCustomerName());

    }

    public void onSaveBtn(ActionEvent actionEvent) throws Exception{
        int id = Integer.parseInt(customerID.getText());
        String custName = customerName.getText();
        System.out.println(custName);
        String custAddress = customerAddress.getText();
        String custPhone = customerPhone.getText();
        String customerPostal =customerPostalCode.getText();
       Country country = (Country) countryComboBox.getValue();

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
