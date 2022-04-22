package utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contact;
import model.Country;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class provides utlity relating to Contacts and the database.
 */
public class ContactDB {

    /**
     * Method for getting all Contacts in an Observable List
     * @return contactsList - List of all Contacts
     */
    public static ObservableList<Contact> getContactList() {
        ObservableList<Contact> contactsList = FXCollections.observableArrayList();

        try {
            String sqlStatement = "SELECT * FROM Contacts";
            PreparedStatement ps = ConnectionJDBC.openConnection().prepareStatement(sqlStatement);

            ResultSet result = ps.executeQuery();
            while (result.next()) {

                int contactID = result.getInt("Contact_ID");
                String contactName = result.getString("Contact_Name");

                Contact contact = new Contact(contactID, contactName);
                contactsList.add(contact);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return contactsList;
    }

    /**
     * Method for getting Contacts from database
     * @param contact_ID
     * @return
     */
    public static Contact getCustomerContact(int contact_ID) {
       Contact con = null;
        try {
            String sqlStatement = "SELECT * FROM Contacts WHERE Contact_ID =?";
            PreparedStatement ps = ConnectionJDBC.openConnection().prepareStatement(sqlStatement);

            ps.setInt(1, contact_ID);
            ResultSet result = ps.executeQuery();
            result.next();
                int contactID = result.getInt("Contact_ID");
                String contactName = result.getString("Contact_Name");

                 con = new Contact(contactID, contactName );

            } catch (SQLException e) {
            e.printStackTrace();

        }
        return con;
    }


}
