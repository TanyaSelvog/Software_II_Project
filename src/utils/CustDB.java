package utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustDB {
//@TODO 12.21.21 Working on this method
    public static ObservableList<Customer> getCustomerList() {
        ObservableList<Customer> customersList = FXCollections.observableArrayList();
        try {
        String sqlStatement = "SELECT Customer_Name FROM CUSTOMERS";
        PreparedStatement pstmt = ConnectionJDBC.getConnection().prepareStatement(sqlStatement);

        ResultSet result = pstmt.executeQuery(sqlStatement);
        while (result.next()) {
            System.out.println(result.getString("Customer"));
        }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return customersList;
        // PreparedStatement pstmt = ConnectionJDBC.getConnection().prepareStatement;



    }

}



