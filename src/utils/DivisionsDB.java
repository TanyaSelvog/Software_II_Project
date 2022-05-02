package utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Country;
import model.Division;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class involves Divisions and queries to the database.
 */
public class DivisionsDB {
    /**
     * Method for getting a list of all divisions
     * @param countryID
     * @return divisionList - List of all Divisions
     */
    public static ObservableList<Division> getDivisionList(int countryID){
        ObservableList<Division> divisionList = FXCollections.observableArrayList();

        try {
            String sqlStatement = "SELECT * FROM First_Level_Divisions WHERE Country_ID =?";
            PreparedStatement ps = ConnectionJDBC.openConnection().prepareStatement(sqlStatement);

            ps.setInt(1, countryID);

            ResultSet result = ps.executeQuery();
                while (result.next()) {
                    int divisionID = result.getInt("Division_ID");
                    String divisionName = result.getString("Division");


                    Division division = new Division(divisionID, divisionName, countryID);
                    divisionList.add(division);
                    }
                }
        catch (SQLException exception){
            exception.printStackTrace();
            }
        return divisionList;
        }

    /**
     * Method for getting Customer's Division in the database
     * @param divisionID Division ID
     * @return division Division object
     */
    public static Division getCustomerDivision(int divisionID){
        Division div = null;

        try {
            String sqlStatement = "SELECT * FROM First_Level_Divisions WHERE Division_ID =?";
            PreparedStatement ps = ConnectionJDBC.openConnection().prepareStatement(sqlStatement);

            ps.setInt(1, divisionID);

            ResultSet result = ps.executeQuery();
            result.next();
                String divisionName = result.getString("Division");
                int countryID = result.getInt("Country_ID");

                 div = new Division(divisionID, divisionName, countryID);

            }

        catch (SQLException exception){
            exception.printStackTrace();
        }
        return div;
        }

}