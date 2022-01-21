package model;


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

    public Appointments(int apptID, String apptTitle, String apptDescription, String apptLocation, String apptContact,
                        String apptType, int customerID, int userID, int contactID){
        this.apptID = apptID;
        this.apptTitle = apptTitle;
        this.apptDescription = apptDescription;
        this.apptLocation = apptLocation;
        this.apptContact = apptContact;
        this.apptType = apptType;
        this.customerID = customerID;
        this.userID = userID;
        this.contactID = contactID;
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

}
