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

    private Customer modCustomer;

    @Override
    public void initialize (URL url, ResourceBundle resourceBundle) {
/**
    selectedCustomer(modCustomer);

    }

    public void selectedCustomer(Customer customer){



       customerName.setText(customer.getCustomerName());


    }
*/
    }
    public void onSaveBtn(ActionEvent actionEvent) {
    }

    public void onCancelBtn(ActionEvent actionEvent) {
    }

    public void onHome(ActionEvent actionEvent) {
    }
}
