package utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointments;
import model.Country;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryDB {


    public static ObservableList<Country> getApptsList(){
        ObservableList<Country> countriesList = FXCollections.observableArrayList();

        try {
            String sqlStatement = "SELECT Country FROM Countries";
            PreparedStatement ps = ConnectionJDBC.openConnection().prepareStatement(sqlStatement);

            ResultSet result = ps.executeQuery();
            while(result.next()){
                System.out.println(result.getString("Country"));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return countriesList;
    }
}
