package utils;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contact;
import model.User;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class UserDB {
    public static String userName;
    public static ObservableList<User> getUserList() {
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
                System.out.println(userID);
                System.out.println(userName);

            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return userList;

    }
    public static User getUser(String userName){
            try {
                String sqlStatement = "SELECT * FROM Users WHERE User_Name = ?";
                PreparedStatement ps = ConnectionJDBC.openConnection().prepareStatement(sqlStatement);
                ps.setString(1, userName);

                ResultSet rs = ps.executeQuery();
                rs.next();

                int id = rs.getInt("User_ID");
                String password = rs.getString("Password");

                User user = new User(id, userName, password);
                System.out.println(user);
                return user;

                }catch (SQLException exception) {
                    exception.printStackTrace();
                }
                return null;
        }


    }













