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


    @Override
    public void initialize (URL url, ResourceBundle resourceBundle) {




    }
    //Similar to TightEnd example in Passing The Football proj
    //@ TODO 1.1.22 working on this method
    public void modCustomer(Customer customer){
        customerName.setText(customer.getCustomerName());
        customerPhone.setText(customer.getCustomerPhone());
        customerPostalCode.setText(customer.getCustomerPostal());
    }

    public void onSaveBtn(ActionEvent actionEvent) {
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
