package utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Country;
import model.Division;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DivisionsDB {

    public static ObservableList<Division> getDivisionList(int countryID){
        ObservableList<Division> divisionList = FXCollections.observableArrayList();

        try {
            String sqlStatement = "SELECT * FROM First_Level_Divisions WHERE Country_ID =?";
            PreparedStatement ps = ConnectionJDBC.openConnection().prepareStatement(sqlStatement);

            ps.setInt(0, countryID);

            ResultSet result = ps.executeQuery();
                while (result.next()) {
                    String divisionName = result.getString("Division");
                    int divisionID = result.getInt("Division_ID");

                    Division division = new Division(divisionName, divisionID, countryID);
                    divisionList.add(division);
                    }
                }
        catch (SQLException exception){
            exception.printStackTrace();
            }
        return divisionList;
        }


}