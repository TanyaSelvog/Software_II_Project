package controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Customer;

import java.net.URL;
import java.util.ResourceBundle;

public class ModifyCustomerFormController implements Initializable {

    public TextField customerName;
    public TextField customerPhone;
    public TextField customerPostalCode;
    public TextField customerAddress;
    public ComboBox countryComboBox;
    public ComboBox divisionComboBox;
    public TextField customerID;
    private static int index;
    private static Customer modCustomer;
    public static Customer getModCustomer(){
        return modCustomer;
    }

    @Override
    public void initialize (URL url, ResourceBundle resourceBundle) {




    }
    //Similar to TightEnd example in Passing The Football proj
    public void modCustomer(Customer cust){
        customerName.setText(cust.getCustomerName());
    }

    public void onSaveBtn(ActionEvent actionEvent) {
    }

    public void onCancelBtn(ActionEvent actionEvent) {
    }

    public void onHome(ActionEvent actionEvent) {
    }
}
