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
    private String customerName;
    private int userID;
    private int contactID;
    private LocalDateTime startDate;
    private String startDateString;
    private LocalDateTime endDate;
    private String endDateString;
    // PROBABLY WILL DELETE CONTACTNAME 3.28
    private String contactName;

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
    public Appointments(String apptDescription, String apptLocation, String apptTitle, String apptType, String apptContact,
                        LocalDateTime startDate, LocalDateTime endDate){
        this.apptDescription = apptDescription;
        this.apptLocation = apptLocation;
        this.apptTitle = apptTitle;
        this.apptType = apptType;
        this.apptContact = apptContact;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Appointments(int apptID, String apptTitle, String apptDescription, String apptLocation, String apptContact,
                        String apptType, int customerID, int userID, int contactID, String contactName, LocalDateTime startDate, LocalDateTime endDate, String startDateString, String endDateString){
        this.apptID = apptID;
        this.apptTitle = apptTitle;
        this.apptDescription = apptDescription;
        this.apptLocation = apptLocation;
        this.apptContact = apptContact;
        this.apptType = apptType;
        this.customerID = customerID;
        this.userID = userID;
        this.contactID = contactID;
        this.contactName = contactName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startDateString = startDateString;
        this.endDateString = endDateString;
    }

    public Appointments(int apptID, String apptTitle, String apptDescription, String apptLocation, String apptContact,
                        String apptType, int customerID, int userID, int contactID, LocalDateTime startDate, LocalDateTime endDate, String startDateString, String endDateString){
        this.apptID = apptID;
        this.apptTitle = apptTitle;
        this.apptDescription = apptDescription;
        this.apptLocation = apptLocation;
        this.apptContact = apptContact;
        this.apptType = apptType;
        this.customerID = customerID;
    //    this.customerName = customerName;
        this.userID = userID;
        this.contactID = contactID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startDateString = startDateString;
        this.endDateString = endDateString;
    }
    public Appointments(int apptID, String apptDescription, LocalDateTime startDate, int userID){
        this.apptID = apptID;
        this.apptDescription = apptDescription;
        this.startDate = startDate;
        this.userID = userID;
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

    public String getApptContact(){
        return apptContact;
    }

    public void setApptContact(String apptContact){
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

    public String getCustomerName(){
        return customerName;}

    public void setCustomerName(String customerName){
        this.customerName = customerName;
    }
/** 3.28 PROBABLY WILL DELETE
    public String getContactName(){
        return contactName;
    }

    public void setContactName(String contactName){
        this.contactName = contactName;
    }
 */

    public void setContactID(int contactID){
        this.contactID = contactID;
    }
    public int getApptID(){
        return apptID;
    }

    public void setApptID(int apptID){
        this.apptID = apptID;
    }

    public String getStartDateString(){
        return startDateString;
    }
    public void setStartDateString(String startDate){
        this.startDateString = startDateString;
    }
    public String getEndDateString(){
        return endDateString;
    }

    public void setEndDate(String endDateString){
        this.endDateString = endDateString;
    }
    public LocalDateTime getStartDate(){
        return startDate;
    }
    public void setStartDate(LocalDateTime startDate){
        this.startDate = startDate;
    }
    public LocalDateTime getEndDate(){
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate){
        this.endDate = endDate;
    }
    @Override
    public String toString(){
        // return (getClass().getName() + '@' + Integer.toHexString(hashCode()));
        return (contactName);
    }
}
