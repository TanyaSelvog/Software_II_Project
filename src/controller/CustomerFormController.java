package controller;

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
import utils.CustDB;

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

    /**
     * This method initializes the controller.
     *
     * @param url            Used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle Used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    private void getCustomer() {
        //@TODO 12.19 WORKING ON EDITING THIS; CHAPTER 11
       /** String custName = customerName.getText();
        String custAddress = customerAddress.getText();
        String custPhone = customerPhone.getText();
        String customerPostal =customerPostalCode.getText();
        */
        if ((customerName.getText() != null && !customerName.getText().isEmpty()) ||
            (customerAddress.getText() != null && !customerAddress.getText().isEmpty()) ||
            (customerPhone.getText() != null && !customerPhone.getText().isEmpty()) ||
            (customerPostalCode.getText() != null && !customerPostalCode.getText().isEmpty()))
        {
            String custName = customerName.getText();
            String custAddress = customerAddress.getText();
            String custPhone = customerPhone.getText();
            String customerPostal =customerPostalCode.getText();
            System.out.println(custName + " " + custAddress + " " +custPhone + " " + customerPostal);

        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR, ("Data is missing or contains invalid values."));
            alert.showAndWait();

        }
        }
                /**
                 *

                ||
        (customerAddress.getText() != null && !customerAddress.getText().isEmpty()) ||
                (customerPhone.getText() != null && !customerPhone.getText().isEmpty()) ||
                (customerPostalCode.getText() != null && !customerPostalCode.getText().isEmpty()))
        {
            System.out.println("Okay");}
        else { System.out.println("oops");
            }
        }
                 */
      //      .getText() != null && !comment.getText().isEmpty())
       // String custCountry = countryComboBox.getValue();
        //String custDivision = divisionComboBox.getValue();
      //  int customerID;



    public void onHome(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/HomepageWindow.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Modify Current Customer");
        Scene scene = new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.show();
        CustDB.testCustomer();
    }

    public void onSaveBtn(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/CustomersView.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("All Customers");
        Scene scene = new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.show();
        getCustomer();
    }

    public void onCancelBtn(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/CustomersView.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("All Customers");
        Scene scene = new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }
}
