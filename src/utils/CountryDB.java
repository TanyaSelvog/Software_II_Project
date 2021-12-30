package utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointments;
import model.Country;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryDB {


    public static ObservableList<Country> getCountryList() {
        ObservableList<Country> countriesList = FXCollections.observableArrayList();
//Updated 12.29 Need to watch ComboBox video
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
}

