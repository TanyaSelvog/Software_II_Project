package utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustDB {
//@TODO 12.23.21 Returns ResultList of customer names thus far
    public static ObservableList<Customer> getCustomerList() {
        ObservableList<Customer> customersList = FXCollections.observableArrayList();
        try {
        String sqlStatement = "SELECT Customer_Name FROM Customers";
        PreparedStatement pstmt = ConnectionJDBC.openConnection().prepareStatement(sqlStatement);

        ResultSet result = pstmt.executeQuery();
        while (result.next()) {
            System.out.println(result.getString("Customer_Name"));
        }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return customersList;
        // PreparedStatement pstmt = ConnectionJDBC.getConnection().prepareStatement;



    }

}



