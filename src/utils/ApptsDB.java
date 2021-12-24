package utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointments;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ApptsDB {
    // @TODO 12.24.21 STARTED THIS METHOD

    public static ObservableList<Appointments> getApptsList(){
        ObservableList<Appointments> apptsList = FXCollections.observableArrayList();

        try {
            String sqlStatement = "SELECT Title FROM Appointments";
            PreparedStatement ps = ConnectionJDBC.openConnection().prepareStatement(sqlStatement);

            ResultSet result = ps.executeQuery();
            while(result.next()){
                    System.out.println(result.getString("Title"));
                }
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
            return apptsList;
            }




}
