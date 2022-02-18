package model;


import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Appointments {
    private String apptTitle;
    private String apptDescription;
    private String apptLocation;
    private String apptContact;
    private String apptType;
    private int apptID;
    private int customerID;
    private int userID;
    private int contactID;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Appointments(int apptID, String apptTitle, String apptDescription, String apptLocation, String apptContact,
                        String apptType, int customerID, int userID, int contactID, LocalDateTime startDate, LocalDateTime endDate){
        this.apptID = apptID;
        this.apptTitle = apptTitle;
        this.apptDescription = apptDescription;
        this.apptLocation = apptLocation;
        this.apptContact = apptContact;
        this.apptType = apptType;
        this.customerID = customerID;
        this.userID = userID;
        this.contactID = contactID;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    // Constructor for NewApptController 'onSave' - Working on
    //2.18 Constructor Overloading notes - Java class can have multiple constructors as long as parameters are not the same
    public Appointments(){
        this.apptDescription = apptDescription;
        this.apptLocation = apptLocation;
        this.apptTitle = apptTitle;
        this.apptType = apptType;
        this.apptContact = apptContact;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getApptTitle(){
        return apptTitle;
    }
    public void setApptTitle(String apptTitle){
        this.apptTitle = apptTitle;
    }

    public String getApptDescription(){
        return apptDescription;
    }

    public void setApptDescription(String apptDescription){
        this.apptDescription = apptDescription;
    }

    public String getApptLocation(){
        return apptLocation;
    }

    public void setApptLocation(String apptLocation){
        this.apptLocation = apptLocation;
    }

    public String getApptContact(String apptContact){
        return apptContact;
    }

    public void setApptContact(){
        this.apptContact = apptContact;
    }

    public String getApptType(){
        return apptType;
    }

    public void setApptType(String apptType){
        this.apptType = apptType;
    }

    public int getCustomerID(){
        return customerID;
    }

    public void setCustomerID(int customerID){
        this.customerID = customerID;
    }
    public int getUserID(){
        return userID;
    }

    public void setUserID(int userID){
        this.userID = userID;
    }
    public int getContactID(){
        return contactID;
    }

    public void setContactID(int contactID){
        this.contactID = contactID;
    }
    public int getApptID(){
        return apptID;
    }

    public void setApptID(int apptID){
        this.apptID = apptID;
    }

    public Timestamp getStartDate(){
        return startDate;
    }
    public void setStartDate(Timestamp startDate){
        this.startDate = startDate;
    }
    public Timestamp getEndDate(){
        return endDate;
    }

    public void setEndDate(Timestamp endDate){
        this.endDate = endDate;
    }
}
