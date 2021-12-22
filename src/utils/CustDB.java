package utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustDB {
//@TODO 12.21.21 Working on this method
    public static void getCountriesDB(String countries) throws SQLException {
        String sqlStatement = "SELECT Country FROM Countries";
        PreparedStatement pstmt = ConnectionJDBC.getConnection().prepareStatement(sqlStatement);

        ResultSet result = pstmt.executeQuery(sqlStatement);
        while (result.next()) {
            System.out.println(result.getString("Country"));
        }
        // PreparedStatement pstmt = ConnectionJDBC.getConnection().prepareStatement;



    }

}



