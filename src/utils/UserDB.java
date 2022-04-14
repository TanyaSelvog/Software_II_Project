package utils;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.Contact;
import model.User;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import java.util.ResourceBundle;

public class UserDB {
    public static String userName;
    public static String password;
    public static ResourceBundle rb = ResourceBundle.getBundle("Resources/Login", Locale.getDefault());
    public static ObservableList<User> getUserList() {
        ObservableList<User> userList = FXCollections.observableArrayList();
    //    public ResourceBundle rb = ResourceBundle.getBundle("Resources/Login", Locale.getDefault());
        try {
            String sqlStatement = "SELECT User_ID, User_Name FROM Users";
            PreparedStatement ps = ConnectionJDBC.openConnection().prepareStatement(sqlStatement);

            ResultSet result = ps.executeQuery();
            while (result.next()) {

                int userID = result.getInt("User_ID");
                String userName = result.getString("User_Name");

                User user = new User(userID, userName);
                userList.add(user);
                System.out.println(userID);
                System.out.println(userName);

            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return userList;

    }
    public static User getUser(String username, String password) {
        try {
            String sqlStatement = "SELECT * FROM users WHERE User_Name = ?";

            PreparedStatement ps = ConnectionJDBC.openConnection().prepareStatement(sqlStatement);
            ps.setString(1, username);

            ResultSet result = ps.executeQuery();
            result.next();

            int id = result.getInt("User_ID");
            String passwordDB= result.getString("Password");

            User user = new User(id, username, password);
            System.out.println("username from DB: " + username + " password: " + passwordDB);
            System.out.println("passwordStatic and what the user enters: " + password);
            System.out.println("passwordDB - what is in DB: " + passwordDB);
            if (!passwordDB.equals(password)) {
                System.out.println("PasswordDB != password");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText(rb.getString("errorMsg"));
                alert.showAndWait();
                return null;
            }
            //if (password != )
            return user;
        }catch (SQLException exception) {
        System.out.println("Error in DB");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Invalid username.");
            alert.showAndWait();

        }
        System.out.println("after catch");
        return null;}}













