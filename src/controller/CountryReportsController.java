package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Contact;
import model.Country;
import model.Customer;
import utils.ContactDB;
import utils.CountryDB;
import utils.CustDB;

import java.net.URL;
import java.util.ResourceBundle;

import static utils.ApptsDB.getContactList;
import static utils.CountryDB.getCountryCustList;

public class CountryReportsController implements Initializable {
    public TableView countryTable;
    public TableColumn custIDCol;

    public ComboBox countryCB;
    public TableColumn customerCol;
    public Button home;
    public Button generateBtn;
    public Button reports;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        custIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        customerCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        //custIDCol.setCellValueFactory(new PropertyValueFactory<>("apptID"));


        countryCB.setItems(CountryDB.getCountryList());
    }

    public void onHomeBtn(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/HomepageWindow.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Scheduler Homepage");
        Scene scene = new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }

    public void onGenerateRpt(ActionEvent actionEvent) throws Exception{

        Country cb = (Country)countryCB.getValue();
        int country_ID = cb.getCountryID();

          countryTable.setItems(getCountryCustList(country_ID));

    }

    public void onReportsBtn(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/ReportsView.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Reports");
        Scene scene = new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }
}
