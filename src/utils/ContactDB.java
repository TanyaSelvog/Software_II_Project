package utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contact;
import model.Country;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactDB {

    public static ObservableList<Contact> getContactList() {
        ObservableList<Contact> contactsList = FXCollections.observableArrayList();

        try {
            String sqlStatement = "SELECT * FROM Contacts";
            PreparedStatement ps = ConnectionJDBC.openConnection().prepareStatement(sqlStatement);

            ResultSet result = ps.executeQuery();
            while (result.next()) {

                int contactID = result.getInt("Contact_ID");
                String contactName = result.getString("Contact_Name");
               // String contactEmail = result.getString("Email");

                Contact contact = new Contact(contactID, contactName);
                contactsList.add(contact);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return contactsList;
    }
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
            //    String contactEmail = result.getString("Email");

                 con = new Contact(contactID, contactName );

            } catch (SQLException e) {
            e.printStackTrace();

        }
        return con;
    }


}
