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

            String sqlStatement = "Select customer_ID, customer_Name, address, postal_code, phone, " +
          "  customers.division_ID, first_level_divisions.division, countries.country_ID, " +
           "  countries.country from customers, first_level_divisions, countries WHERE " +
            "customers.division_ID = first_level_divisions.division_ID AND " +
            "countries.country_ID = first_level_divisions.country_ID";

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
                System.out.println(customerName + " " + customerAddress + " " + customerPostal);
                Customer customer = new Customer(customerID, customerName, customerAddress,
                       customerPostal, customerPhone, customerCountry, divisionID, customerDivision);
                customersList.add(customer);


            }

        } catch (SQLException exception) {
            exception.printStackTrace();

    } return customersList;
        // PreparedStatement pstmt = ConnectionJDBC.getConnection().prepareStatement;



    }

}



