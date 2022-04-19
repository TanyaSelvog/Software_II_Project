package model;

import jdk.jfr.Description;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Month;

/**
 * This class holds data for appointments.
 */
public class Appointments {
    /**
     * Appointment title
     */
    private String apptTitle;
    /**
     * Appointment description
     */
    private String apptDescription;
    /**
     * Appointment location
     */
    private String apptLocation;
    /**
     * Appointment contact
     */
    private String apptContact;
    /**
     * Appointment type
     */
    private String apptType;
    /**
     * Appointment ID
     */
    private int apptID;
    /**
     * Customer ID
     */
    private int customerID;
    /**
     * Customer name
     */
    private String customerName;
    /**
     * User ID
     */
    private int userID;
    /**
     * Username
     */
    private String userName;
    /**
     * Contact ID
     */
    private int contactID;
    /**
     * Appointment start time & date
     */
    private LocalDateTime startDate;
    /**
     * Appointment start as a String (for readability)
     */
    private String startDateString;
    /**
     * Appointment end time & date
     */
    private LocalDateTime endDate;
    /**
     * Appointment end as a String (for readability)
     */
    private String endDateString;

    /**
     * Contact name
     */
    private Contact contactName;

    /**
     * Month object used for comparing month & times
     */
    private Month monthA;

    /**
     * Constructor for Appointments object
     * @param apptID AppointmentID
     * @param apptTitle Appointment Title
     * @param apptDescription Appointment Description
     * @param apptLocation Appointment Location
     * @param apptContact Appointment Contact
     * @param apptType Appointment Type
     * @param customerID Appointment Customer ID
     * @param userID Appointment User ID
     * @param contactID Appointment Contact ID
     * @param startDate Appointment Start
     * @param endDate Appointment End
     */
    public Appointments(int apptID, String apptTitle, String apptDescription, String apptLocation, String apptContact,
                        String apptType, int customerID, int userID, int contactID, LocalDateTime startDate, LocalDateTime endDate) {
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


    /**
     * Constructor for NewApptController's onSave()
     * @param apptDescription
     * @param apptLocation
     * @param apptTitle
     * @param apptType
     * @param apptContact
     * @param startDate
     * @param endDate
     */

    public Appointments(String apptDescription, String apptLocation, String apptTitle, String apptType, String apptContact,
                        LocalDateTime startDate, LocalDateTime endDate) {
        this.apptDescription = apptDescription;
        this.apptLocation = apptLocation;
        this.apptTitle = apptTitle;
        this.apptType = apptType;
        this.apptContact = apptContact;
        this.startDate = startDate;
        this.endDate = endDate;
    }


    //for getAllAppts() in ApptsDB

    /**
     * Constructor for getAllAppts() in ApptsDB
     * @param apptID
     * @param apptTitle
     * @param apptDescription
     * @param apptLocation
     * @param apptContact
     * @param apptType
     * @param customerID
     * @param userID
     * @param contactID
     * @param contactName
     * @param startDate
     * @param endDate
     * @param startDateString
     * @param endDateString
     */
    public Appointments(int apptID, String apptTitle, String apptDescription, String apptLocation, String apptContact,
                        String apptType, int customerID, int userID, int contactID, Contact contactName, LocalDateTime startDate, LocalDateTime endDate, String startDateString, String endDateString) {
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
        this.contactName = contactName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startDateString = startDateString;
        this.endDateString = endDateString;
    }

    //for getAllAppts() -rev 2; adding customerName & userName
    public Appointments(int apptID, String apptTitle, String apptDescription, String apptLocation, String apptContact,
                        String apptType, int customerID, String customerName, int userID, int contactID, LocalDateTime startDate, LocalDateTime endDate, String startDateString, String endDateString) {
        this.apptID = apptID;
        this.apptTitle = apptTitle;
        this.apptDescription = apptDescription;
        this.apptLocation = apptLocation;
        this.apptContact = apptContact;
        this.apptType = apptType;
        this.customerID = customerID;
        this.customerName = customerName;
        this.userID = userID;
        // this.userName = userName;
        this.contactID = contactID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startDateString = startDateString;
        this.endDateString = endDateString;
    }

    public Appointments(int apptID, String apptDescription, LocalDateTime startDate, int userID) {
        this.apptID = apptID;
        this.apptDescription = apptDescription;
        this.startDate = startDate;
        this.userID = userID;
    }


    //for comparing customer appts getCustList ApptsDB & NewApptCont
    public Appointments(int apptID,
                        int customerID,
                        int contactID, String apptTitle, String apptDescription,
                        String apptLocation, String apptType, LocalDateTime startDate, LocalDateTime endDate, int userID) {
        this.apptID = apptID;
        this.customerID = customerID;
        this.contactID = contactID;
        this.apptTitle = apptTitle;
        this.apptDescription =apptDescription;
        this.apptLocation = apptLocation;
        this.apptType = apptType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.userID = userID;


    }

    public Appointments(int apptID, String apptType) {
        this.apptID=apptID;
        this.apptType=apptType;
    }



    public Appointments(int apptID,
                        String apptTitle,
                        String apptType,
                        String apptDescription,
                        LocalDateTime startDate, LocalDateTime endDate, int contactID, int customerID) {

        this.apptID = apptID;
        this.apptTitle = apptTitle;
        this.apptType = apptType;
        this.apptDescription =apptDescription;
        this.startDate = startDate;
        this.endDate = endDate;
        this.contactID = contactID;
        this.customerID = customerID;


    }

    /**
     * Getter for Appointment Title
     * @return Appointment Title
     */
    public String getApptTitle() {
        return apptTitle;
    }

    /**
     * Setter for Appointment Title
     * @param apptTitle Title of Appointment
     */

    public void setApptTitle(String apptTitle) {
        this.apptTitle = apptTitle;
    }

    /**
     * Getter for Appointment Description
     * @return Appointment Description
     */

    public String getApptDescription() {
        return apptDescription;
    }

    /**
     * Setter for Appointment Description
     * @param apptDescription Description of Appointment
     */
    public void setApptDescription(String apptDescription) {
        this.apptDescription = apptDescription;
    }

    /**
     * Getter for Appointment Location
     * @return Appointment Location
     */

    public String getApptLocation() {
        return apptLocation;
    }

    /**
     * Setter for Appointment Location
     * @param apptLocation Location of Appointment
     */
    public void setApptLocation(String apptLocation) {
        this.apptLocation = apptLocation;
    }

    /**
     * Getter for Appointment Contact
     * @return Appointment Contact
     */
    public String getApptContact() {
        return apptContact;
    }
    /**
     * Setter for Appointment Contact
     * @param apptContact Contact
     */
    public void setApptContact(String apptContact) {
        this.apptContact = apptContact;
    }

    /**
     * Getter for Appointment Type
     * @return Appointment Type
     */

    public String getApptType() {
        return apptType;
    }

    /**
     * Setter for Appointment Type
     * @param apptType Type of Appointment
     */
    public void setApptType(String apptType) {
        this.apptType = apptType;
    }

    /**
     * Getter for Customer ID
     * @return Customer ID as int
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * Setter for Customer ID
     * @param customerID ID of Customer
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /**
     * Getter for User ID
     * @return User ID as int
     */
    public int getUserID() {
        return userID;
    }

    /**
     * Setter for User ID
     * @param userID int User ID
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * Getter for Username
     * @return username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Setter for Username
     * @param userName username
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Getter for Contact ID
     * @return Contact ID
     */
    public int getContactID() {
        return contactID;
    }

    /**
     * Getter for Customer Name
     * @return Customer Name
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Setter for Customer Name
     * @param customerName Customer Name
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Setter for Contact ID
     * @param contactID Contact ID
     */

    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    /**
     * Getter for Appointment ID
     * @return Appointment ID as int
     */
    public int getApptID() {
        return apptID;
    }

    /**
     * Setter for Appointment ID
     * @param apptID Appointment ID
     */
    public void setApptID(int apptID) {
        this.apptID = apptID;
    }

    /**
     * Getter for Start as a String
     * @return Start Date as a String
     */
    public String getStartDateString() {
        return startDateString;
    }


    /**
     * Getter for Start Date
     * @return Start Date as LocalDateTime
     */
    public LocalDateTime getStartDate() {
        return startDate;
    }

    /**
     * Setter for Start Date
     * @param startDate Start Date
     */
    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    /**
     * Getter for End Date
     * @return End Date
     */
    public LocalDateTime getEndDate() {
        return endDate;
    }

    /**
     * Setter for End Date
     * @param endDate End Date
     */
    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    /**
     * Getter for Contact Name
     * @return Contact Name as Contact
     */
    public Contact getContactName() {
        return contactName;
    }

    /**
     * Setter for monthA
     * @param monthA
     */
    public void setMonthA(Month monthA){
        this.monthA = monthA;
    }

    /**
     * Getter for monthA
     * @return monthA
     */

    public Month getMonthA(){
        return monthA;
    }

    /**
     * Constructor
     * @param monthA
     * @param apptType
     * @param apptID
     */
    public Appointments(Month monthA, String apptType, int apptID){
        this.monthA = monthA;
        this.apptType = apptType;
        this.apptID = apptID;
    }

    }