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
                String contactEmail = result.getString("Email");

                Contact contact = new Contact(contactID, contactName, contactEmail);
                contactsList.add(contact);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return contactsList;
    }
}
