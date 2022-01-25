package utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointments;
import model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

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
                int customerID = result.getInt("Customer_ID");
                int userID = result.getInt("User_ID");
                String apptTitle = result.getString("Title");
                String apptDescription = result.getString("Description");
                String apptLocation = result.getString("Location");
                String apptContact = result.getString("Contact_Name");
                String apptType = result.getString("Type");
                Timestamp startDate = result.getTimestamp("Start");
                Timestamp endDate = result.getTimestamp("End");
                System.out.println(apptTitle + " " + apptContact + " " + apptType);
                Appointments appointments = new Appointments(apptID, apptTitle,apptDescription,apptLocation, apptContact, apptType, customerID,
                        userID, contactID, startDate, endDate);
                    apptsList.add(appointments);
                }


        } catch (SQLException exception) {
                exception.printStackTrace();
            }
            return apptsList;
            }


/** SELECT appointment_ID, title, description, location, type, start, end, create_date, created_By,
 * customer_ID, user_ID, contact_ID
 *
 */     // 1.24. working on this method
        public static String getContactName() {

            String contactName = null;
            try {
                String sqlStatement = "SELECT contact_name Contacts;";
                PreparedStatement ps = ConnectionJDBC.openConnection().prepareStatement(sqlStatement);

                ResultSet result = ps.executeQuery();
                while (result.next()) {
                    contactName = result.getString("Contact_name");
                    System.out.println(contactName);
                }
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
            return contactName;
        }
}
