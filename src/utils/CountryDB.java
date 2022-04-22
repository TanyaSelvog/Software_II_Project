package utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointments;
import model.Country;
import model.Customer;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



/**
 * This class involves utilities that are Country-related and are for the mySQL DB.
 */
public class CountryDB {

    /**
     * Method for getting list of all countries
     * @return countriesList - List of all countries
     */

    public static ObservableList<Country> getCountryList() {
        ObservableList<Country> countriesList = FXCollections.observableArrayList();
        try {
            String sqlStatement = "SELECT * FROM Countries";
            PreparedStatement ps = ConnectionJDBC.openConnection().prepareStatement(sqlStatement);

            ResultSet result = ps.executeQuery();
            while (result.next()) {
                String countryName = result.getString("Country");
                int countryID = result.getInt("Country_ID");

                Country country = new Country(countryName, countryID);
                countriesList.add(country);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return countriesList;
    }

    /**
     * Get the list of customers and countries
     * @param countryID
     * @return countryCustList - List of customers per selected country
     */
    public static ObservableList<Customer> getCountryCustList(int countryID) {
        ObservableList<Customer> countryCustList = FXCollections.observableArrayList();
        try {

            String sqlStatement = "Select first_level_divisions.division_ID, customer_Name, customers.customer_ID from first_level_divisions join customers on" +
                    " customers.Division_ID = first_level_divisions.Division_ID WHERE Country_ID = ?";

            PreparedStatement ps = ConnectionJDBC.openConnection().prepareStatement(sqlStatement);

            ps.setInt(1, countryID);
            ResultSet result = ps.executeQuery();

            while (result.next()) {
                int customerID = result.getInt("Customer_ID");
                String customerName = result.getString("Customer_Name");
                int divisionID = result.getInt("Division_ID");

                Customer customer = new Customer(customerID, customerName, divisionID);
                countryCustList.add(customer);

            }

        } catch (SQLException exception) {
            exception.printStackTrace();

        }
        return countryCustList;
}}

