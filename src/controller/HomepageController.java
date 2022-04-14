package controller;


import javafx.event.ActionEvent;
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
 * This class is a controller class the provides logic for the Homepage screen
 */
public class HomepageController implements Initializable {

    /**
     * Button for link to Customer View screen
     */
    public Button customersBtn;
    /**
     * Button for link to Appointment View screen
     */
    public Button apptsBtn;

    /**
     * Button for link to Reports View screen
     */
    public Button reportsBtn;


    /**
     * This method links to Customer View window when clicked.
     * @param actionEvent on click
     * @throws Exception from FXMLLoader
     */
    public void onCustomersClick(ActionEvent actionEvent) throws Exception {
            Parent root = FXMLLoader.load(getClass().getResource("/view/CustomersView.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("Customers");
            Scene scene = new Scene(root, 1000, 600);
            stage.setScene(scene);
            stage.show();

    }

    /**
     * This method links to Appointment View window when clicked.
     * @param actionEvent on click
     * @throws Exception from FXMLLoader
     */
    public void onAppointmentsClick(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/AppointmentsView.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Appointments");
        Scene scene = new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method links to Reports View on click.
     * @param actionEvent on click
     * @throws Exception From FXMLLoader
     */

    public void onReportsClick(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/ReportsView.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Reports");
        Scene scene = new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method initializes the controller.
     *
     * @param url            Used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle Used to localize the root object, or null if the root object was not localized.
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
