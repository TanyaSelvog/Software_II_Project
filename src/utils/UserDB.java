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

/**
 * This class relates to Users and the database
 */
public class UserDB {
    public static String userName;
    public static String password;
    public static ResourceBundle rb = ResourceBundle.getBundle("Resources/Login", Locale.getDefault());

    /**
     * Method for getting username and password and checking credentials
     * @param username
     * @param password
     * @return Null if no user is found or user
     */

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

            if (!passwordDB.equals(password)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(rb.getString("errorMsg"));
                alert.showAndWait();
                return null;
            }
            //if (password != )
            return user;
        }catch (SQLException exception) {
        System.out.println("Error in DB");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(rb.getString("errorMsg"));
            alert.showAndWait();

        }
        return null;}}













