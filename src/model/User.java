package model;

import javax.sql.RowSet;
import java.time.LocalDateTime;

public class User {

    private int userID;
    private String userName;
    private static String password;

    private static User currentUser;

    private String lastUpdatedBy;


    public User(int userID, String userName, String password,  String lastUpdatedBy) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public User(int userID, String userName) {
        this.userID = userID;
        this.userName = userName;}


    public User(int userID, String userName, String password) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;}

    public static User getCurrentUser(){
       return currentUser;
    }



    public void setCurrentUser(User user){
       currentUser = user;
    }
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public  static String getPassword(String userPassword){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }



    public String getLastUpdatedBy(){
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy){
        this.lastUpdatedBy = lastUpdatedBy;
    }

    @Override
    public String toString() {
        // return (getClass().getName() + '@' + Integer.toHexString(hashCode()));
        return (userName);


    }
}