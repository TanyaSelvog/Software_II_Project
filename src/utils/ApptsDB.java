package utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointments;
import model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ApptsDB {
    // @TODO 12.24.21 STARTED THIS METHOD

    public static ObservableList<Appointments> getApptsList(){
        ObservableList<Appointments> apptsList = FXCollections.observableArrayList();

        try {
            String sqlStatement = "SELECT title, description, location, type, start, end, customer_ID, " +
                    " user_ID, appointment_ID, contacts.contact_ID, contacts.contact_name FROM Appointments, Contacts WHERE " +
                    "contacts.contact_ID = appointments.contact_ID";
            PreparedStatement ps = ConnectionJDBC.openConnection().prepareStatement(sqlStatement);

            ResultSet result = ps.executeQuery();
            while(result.next()){
                int apptID = result.getInt("Appointment_ID");
                int contactID = result.getInt("Contact_ID");
                String apptTitle = result.getString("Title");
                String apptDescription = result.getString("Description");
                String apptLocation = result.getString("Location");
                String apptContact = result.getString("Contact_Name");
                String apptType = result.getString("Type");
                System.out.println(apptTitle + " " + apptContact + " " + apptType);
                Appointments appointments = new Appointments(apptID, apptTitle,apptDescription,apptLocation, apptContact, apptType);
                    apptsList.add(appointments);
                }

            /**
             *   public Appointments(int apptID, String apptTitle, String apptDescription, String apptLocation, String apptContact,
             *                         String apptType){
             */
        } catch (SQLException exception) {
                exception.printStackTrace();
            }
            return apptsList;
            }


/** SELECT appointment_ID, title, description, location, type, start, end, create_date, created_By,
 * customer_ID, user_ID, contact_ID
 *
 */

}
