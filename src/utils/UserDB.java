package utils;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contact;
import model.User;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class UserDB {

    public static ObservableList<User> getUserList(){
        ObservableList<User> userList = FXCollections.observableArrayList();

        try {
            String sqlStatement = "SELECT User_ID, User_Name FROM Users";
            PreparedStatement ps = ConnectionJDBC.openConnection().prepareStatement(sqlStatement);

            ResultSet result = ps.executeQuery();
            while (result.next()) {

                int userID = result.getInt("User_ID");
                String userName = result.getString("User_Name");

                User user = new User(userID, userName);
                userList.add(user);

            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return userList;


    }












}
