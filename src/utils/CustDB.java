package utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Country;
import model.Customer;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustDB {
//@TODO 12.23.21 Returns ResultList of customer names thus far with System.println
    public static ObservableList<Customer> getCustomersList() {
        ObservableList<Customer> customersList = FXCollections.observableArrayList();
        try {
            //need to fix the SQL statement
            String sqlStatement = "SELECT Customers.Customer_ID, Customers.Customer_Name, Customers.Address, " +
                    "Customers.Postal_Code, Customers.Phone, Customers.DivisionID, "+
                    "First_Level_Divisions.Division_ID, First_Level_Divisions.Division, Countries.Country_ID" +
                    "FROM Customers INNER JOIN Customers ON First_Level_Divisions.Division_ID =Customers.Division_ID)" +
                     "INNER JOIN Countries ON First_Level_Divisions.Country_ID = =Countries.Country_ID)";
            /** "SELECT Customers.Customer_ID, Customers.Customer_Name,
             * Customers.Address, Customers.Postal_Code, Customers.Phone,
             * First_Level_Divisions.Division_ID, First_Level_Divisions.Division,
             * Country_ID.Countries, Country.Countries
             * FROM (First_Level_Divisions
             * INNER JOIN Customers ON First_Level_Divisions.Division_ID =Customers.Division_ID)
             * INNER JOIN Countries ON First_Level_Divisions.Country_ID = =Countries.Country_ID)
             *
             *
             *
             */
            PreparedStatement pstmt = ConnectionJDBC.openConnection().prepareStatement(sqlStatement);

            ResultSet result = pstmt.executeQuery();

            while (result.next()) {
                int customerID = result.getInt("Customer_ID");
                String customerName = result.getString("Customer_Name");
                String customerAddress = result.getString("Address");
                String customerPostal = result.getString("Postal_Code");
                String customerPhone = result.getString("Phone");
                String customerCountry = result.getString("Country");
                int divisionID = result.getInt("Division_ID");
                String customerDivision = result.getString("Division");

                Customer customer = new Customer(customerID, customerName, customerAddress,
                        customerPostal, customerPhone, customerCountry, divisionID, customerDivision);
            }

        } catch (SQLException exception) {
            exception.printStackTrace();

    } return customersList;
        // PreparedStatement pstmt = ConnectionJDBC.getConnection().prepareStatement;



    }

}



