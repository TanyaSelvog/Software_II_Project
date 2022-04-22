package model;

import utils.UserDB;

import javax.sql.RowSet;
import java.time.LocalDateTime;

/**
 * This class provides general information on the User
 */
public class User {
    /**
     * User ID
     */
    private static int userID;

    /**
     * Username
     */
    private static String userName;
    /**
     * Password
     */
    private static String password;
    /**
     * Current User as User
     */

    private static User currentUser;
    /**
     * Last Updated By
     */
    private String lastUpdatedBy;

    /**
     * Constructor for User
     * @param userID
     * @param userName
     * @param password
     * @param lastUpdatedBy
     */
    public User(int userID, String userName, String password,  String lastUpdatedBy) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * Constructor for User
     * @param userID
     * @param userName
     */

    public User(int userID, String userName) {
        this.userID = userID;
        this.userName = userName;}

    /**
     * Constructor for User
     * @param userID
     * @param userName
     * @param password
     */
    public User(int userID, String userName, String password) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;}

    /**
     * Getter for Current User
     * @return Current User
     */
    public static User getCurrentUser(){
       return currentUser;
    }

    /**
     * Setter for Current User
     * @param user User
     */

    public void setCurrentUser(User user){
       currentUser = user;
    }

    /**
     * Getter for User ID
     * @return User ID
     */
    public static int getUserID() {
        return userID;
    }

    /**
     * Setter for User ID
     * @param userID User ID
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * Getter for Username
     * @return Username
     */
    public static String getUserName() {
        return userName;
    }

    /**
     * Setter for Username
     * @param userName Username
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Getter for Password
     * @return Password
     */
    public static String getPassword(){
        return password;
    }

    /**
     * Setter for Password
     * @param password Password
     */
    public void setPassword(String password){
        this.password = password;
    }

    /**
     * Getter for LastUpdatedBy
     * @return LastUpdatedBy
     */

    public String getLastUpdatedBy(){
        return lastUpdatedBy;
    }

    /**
     * Setter for LastUpdatedBy
     * @param lastUpdatedBy
     */
    public void setLastUpdatedBy(String lastUpdatedBy){
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * To String for User
     * @return Username
     */
    @Override
    public String toString() {
        // return (getClass().getName() + '@' + Integer.toHexString(hashCode()));
        return (userName);


    }
}