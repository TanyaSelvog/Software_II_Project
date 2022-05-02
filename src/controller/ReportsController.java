package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Class for Reports Controller
 */
public class ReportsController implements Initializable {

    /**
     * Button to link to Monthly Customers Report page
     */
    @FXML
    private Button monthlyCust;

    /**
     * Button to link to Contact Scheduler Report page
     */
    @FXML
    private Button contact;

    /**
     * Button to link to Country Report page
     */
    @FXML
    private Button country;

    /**
     * Event handler for Home button that returns user to Home page
     * @param actionEvent Home button click
     * @throws Exception
     */
    public void onHomeClick(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/HomepageWindow.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("View Appointments");
        Scene scene = new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }


    /**
     * Event handler for on click Contact Button
     * @param actionEvent Contact click button
     * @throws Exception
     */
    public void onContactBtn(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/ContactSchedule.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Contact Schedule Reports");
        Scene scene = new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Event handler Monthly button that sends users to Monthly Customers Report page
     * @param actionEvent Monthly button click
     * @throws Exception
     */
    public void onMonthlyBtn(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/MonthlyCustomersReport.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Monthly Customers Report");
        Scene scene = new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Event handler for Country button that sends users to Country Reports page
     * @param actionEvent Country button click
     * @throws Exception
     */
    public void onCountryBtn(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/CountryReports.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Country Report");
        Scene scene = new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Method that intializes the Reports controller.
     * @param url            Used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle Used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
