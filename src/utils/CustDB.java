package utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Country;
import model.Customer;
import model.User;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustDB {

    public static ObservableList<Customer> getCustomersList() {
        ObservableList<Customer> customersList = FXCollections.observableArrayList();
        try {

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
                int divisionID = result.getInt("Division_ID");
                String customerDivision = result.getString("Division");
                String customerCountry = result.getString("Country");
               // System.out.println(customerName + " " + customerAddress + " " + customerPostal);
                Customer customer = new Customer(customerID, customerName, customerAddress,
                        customerPostal, customerPhone, divisionID, customerDivision, customerCountry);
                customersList.add(customer);


            }

        } catch (SQLException exception) {
            exception.printStackTrace();

        }
        return customersList;



    }


    public static void createCustomer(String name, String address, String postalCode, String phone, int divisionID) {
        try {
            String sql = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Division_ID) VALUES (?,?,?,?,?)";
            PreparedStatement ps = ConnectionJDBC.openConnection().prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, address);
            ps.setString(3, postalCode);
            ps.setString(4, phone);
            ps.setInt(5, divisionID);


            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //3.3
    public static void modifyCustomer(int customer_id, String name, String address, String postalCode, String phone, int divisionID){
        try {
            String sql = "UPDATE Customers set Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Division_ID = ? WHERE Customer_ID =?";
            PreparedStatement ps = ConnectionJDBC.openConnection().prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, address);
            ps.setString(3, postalCode);
            ps.setString(4, phone);
            ps.setInt(5, divisionID);
            ps.setInt(6, customer_id);


            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
    }
    //3.3.
    public static void deleteCustomer(int id){

    }
}




