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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Country;
import model.Customer;
import utils.CountryDB;
import utils.CustDB;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {
    public TableColumn customerID;
    public TableColumn name;
    public TableColumn address;
    public TableColumn postalCode;
    public TableColumn phone;
    public TableColumn country;
    public TableColumn division;
    public Button addNewBtn;
    public Button modifyBtn;
    public Button backBtn;
    public TableView customersTable;

    private ObservableList<Customer> customersList = FXCollections.observableArrayList();
   private  ObservableList<Country> countriesList = FXCollections.observableArrayList();
    private Customer modCustomer;

    private int index;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        customersTable.setItems(CustDB.getCustomersList());




        //fxid (for each column name) is 1st
        customerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        name.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        address.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        postalCode.setCellValueFactory(new PropertyValueFactory<>("customerPostal"));
        phone.setCellValueFactory(new PropertyValueFactory<>("customerPhone"));
        country.setCellValueFactory(new PropertyValueFactory<>("customerCountry"));
        division.setCellValueFactory(new PropertyValueFactory<>("customerDivision"));
        /** @TODO Created list example to test code; need to look into DB 12.8
         *


         */
        //customersList.add(new Customer(2, "Logan Roy", "100 Neverland" , "3","3", "s", "Test"));
    }

    public void onAddNewCustomer(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/CustomerForm.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Add New Customer");
        Scene scene = new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }

    public void onModifyCurrent(ActionEvent actionEvent) throws Exception {

        modCustomer = (Customer) customersTable.getSelectionModel().getSelectedItem();
        index = customersTable.getSelectionModel().getSelectedIndex();

        if (modCustomer == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, ("Select a Customer to modify."));
            alert.showAndWait();
        } else {
            Parent root = FXMLLoader.load(getClass().getResource("/view/CustomerForm.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("Modify Current Customer");
            Scene scene = new Scene(root, 1000, 600);
            stage.setScene(scene);
            stage.show();
        }

    }


    public void onBackToMain(ActionEvent actionEvent) throws Exception {
            Parent root = FXMLLoader.load(getClass().getResource("/view/HomepageWindow.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("Scheduler Homepage");
            Scene scene = new Scene(root, 1000, 600);
            stage.setScene(scene);
            stage.show();
        }
    }

