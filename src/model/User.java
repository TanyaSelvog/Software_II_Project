package model;

import javax.sql.RowSet;
import java.time.LocalDateTime;

public class User {

    private int userID;
    private String userName;
    private String password;
    private LocalDateTime lastUpdate;

    private String lastUpdatedBy;


    public User(int userID, String userName, String password, LocalDateTime lastUpdate, String lastUpdatedBy) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public User(int userID, String userName) {
        this.userID = userID;
        this.userName = userName;}

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

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public LocalDateTime getLastUpdate(){
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate){
        this.lastUpdate = lastUpdate;
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