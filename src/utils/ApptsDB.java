package utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.Appointments;
import model.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * This class involves utilities that are appointment-related and are for the mySQL DB.
 */
public class ApptsDB {

    /**
     * Current User as User
     */

    public static User currentUser;
    /**
     * User ID
     */
    public static int userID;

    /**
     * Login time when user logs in
     */
    public static LocalDateTime loginTime;
    /**
     * Time formatter (in format of hh:mm a)
     */
    public static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("hh:mm a");

    /**
     * Date and time formatter (in format of MM-dd-yyyy hh:mm a)
     */
    public static DateTimeFormatter dateTime = DateTimeFormatter.ofPattern("MM-dd-yyyy hh:mm a");

    /**
     * Date formatter (in format of MM-dd-yyyy)
     */
    public static DateTimeFormatter dateOnlyTime = DateTimeFormatter.ofPattern("MM-dd-yyyy");
    public static ObservableList<Appointments> custApptsList;
    public static boolean apptTest = true;
    public static boolean getApptTest(){
        return apptTest;
    }
    /**
     * Method for getting all appointments in Observable List
     * @return apptsList List of all appointments
     */
    public static ObservableList<Appointments> getApptsList(){
        ObservableList<Appointments> apptsList = FXCollections.observableArrayList();

        try {
            String sqlStatement = "SELECT Appointments.*, Customers.Customer_Name, Contacts.Contact_Name " +
                "FROM appointments JOIN customers ON appointments.customer_ID = " +
                "customers.customer_ID JOIN contacts ON appointments.Contact_ID = Contacts.Contact_ID";

            PreparedStatement ps = ConnectionJDBC.openConnection().prepareStatement(sqlStatement);

            ResultSet result = ps.executeQuery();
            while(result.next()){
                int apptID = result.getInt("Appointment_ID");
                int contactID = result.getInt("Contact_ID");
                int customerID = result.getInt("Customer_ID");
                String custName = result.getString("Customer_Name");
                int userID = result.getInt("User_ID");
                String apptTitle = result.getString("Title");
                String apptDescription = result.getString("Description");
                String apptLocation = result.getString("Location");
                String apptContact = result.getString("Contact_Name");
                String apptType = result.getString("Type");
                LocalDateTime startDate = result.getTimestamp("Start").toLocalDateTime();
                String startDateString = dateTime.format(startDate);
                LocalDateTime endDate = result.getTimestamp("End").toLocalDateTime();
                String endTimeString = dateOnlyTime.format(endDate);
                String endDateString = dateTime.format(endDate);
                Appointments appointments = new Appointments(apptID, apptTitle,apptDescription,apptLocation, apptContact, apptType, customerID, custName,
                        userID, contactID, startDate, endDate, startDateString, endDateString);
                    apptsList.add(appointments);
                }


        } catch (SQLException exception) {
                exception.printStackTrace();
            }
            return apptsList;
            }

    /**
     * Method for modifying Customer's appointments in the database
     * @param apptID appointment ID
     * @param apptTitle appointment title
     * @param apptDesc appointment description
     * @param apptLocation appointment location
     * @param apptType appointment type
     * @param startAppt start time
     * @param endAppt end time
     * @param lastUpdatedBy last updated by
     * @param customerID customer ID
     * @param userID user ID
     * @param contactID contact ID
     */

    public static void modifyAppt(int apptID, String apptTitle, String apptDesc, String apptLocation,
                String apptType, LocalDateTime startAppt, LocalDateTime endAppt,
                 String lastUpdatedBy, int customerID, int userID, int contactID) {
        try {

            String sql = "UPDATE Appointments set Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Last_Updated_By = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID =?";
            PreparedStatement ps = ConnectionJDBC.openConnection().prepareStatement(sql);

                ps.setString(1, apptTitle);
                ps.setString(2, apptDesc);
                ps.setString(3, apptLocation);
                ps.setString(4, apptType);
                ps.setTimestamp(5, Timestamp.valueOf(startAppt));
                ps.setTimestamp(6, Timestamp.valueOf(endAppt));
              //  ps.setTimestamp(7, Timestamp.toLocalDateTime());
                ps.setString(7, lastUpdatedBy);
                ps.setInt(8, customerID);
                ps.setInt(9, userID);
                ps.setInt(10, contactID);
                ps.setInt(11, apptID);

            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
    }

    /**
     * Method for creating an appointment in the database
     * @param apptTitle appointment title
     * @param apptDesc appointment description
     * @param apptLocation appointment location
     * @param apptType appointment type
     * @param startAppt start time
     * @param endAppt end time
     * @param customerID customer ID
     * @param contactID contact ID
     */
    public static void createAppointment(String apptTitle, String apptDesc, String apptLocation,
                                         String apptType, LocalDateTime startAppt, LocalDateTime endAppt,
                                         int customerID, int contactID) {
        String sqlStatement = "INSERT INTO appointments (Title, Description, Location, " +
                "Type, Start, End, Created_By," +
                " Customer_ID, User_ID, Contact_ID) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = ConnectionJDBC.openConnection().prepareStatement(sqlStatement);
            ps.setString(1, apptTitle);
            ps.setString(2, apptDesc);
            ps.setString(3, apptLocation);
            ps.setString(4, apptType);
            ps.setTimestamp(5, Timestamp.valueOf(startAppt));
            ps.setTimestamp(6, Timestamp.valueOf(endAppt));
            ps.setString(7, User.getCurrentUser().getUserName());
            ps.setInt(8, customerID);
            ps.setInt(9, User.getCurrentUser().getUserID());
            ps.setInt(10, contactID);


            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Method for getting all appointments for a user and then if appointment is within 15 minutes, message is displayed
     * @return null
     */
    public static Appointments getUserAppt() {
        Appointments userAppt = null;
        LocalDateTime loginTime = LocalDateTime.now();


        try {
            String sqlStatement = "SELECT appointment_ID, start, description, " +
                    "user_ID from appointments where User_ID= " + User.getUserID();

            PreparedStatement ps = ConnectionJDBC.openConnection().prepareStatement(sqlStatement);

            ResultSet result = ps.executeQuery();
            while (result.next()) {
                int apptID = result.getInt("Appointment_ID");
                int userID = result.getInt("User_ID");
                String apptDescription = result.getString("Description");
                LocalDateTime startDateTime = result.getTimestamp("Start").toLocalDateTime();

                userAppt = new Appointments(apptID, apptDescription, startDateTime, userID);
                String apptTimeNotice = dateTime.format(startDateTime);


                //time before appt
                LocalDateTime timeBeforeAppt = startDateTime.minusMinutes(15);

                String beforeTime = dtf.format(timeBeforeAppt);

                //time after appt starts

                LocalDateTime timeAfterAppt = startDateTime.plusMinutes(15);
                String afterTime = dtf.format(timeAfterAppt);


                //checking to see if a time is between two times

                if (loginTime.isAfter(timeBeforeAppt) && loginTime.isBefore(timeAfterAppt)) {
                    apptTest = false;
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, ("You have an appointment (Appointment ID: " + apptID + ") "
                            + "on " + apptTimeNotice + "."));
                    alert.setTitle("Upcoming appointment");
                    alert.showAndWait();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * Method for deleting an appointment in the database
     * @param apptID appointment ID
     */
    public static void deleteAppointment(int apptID){
            try {
        String sqlStatement = "DELETE From Appointments WHERE Appointment_ID = ?";
        PreparedStatement ps = ConnectionJDBC.openConnection().prepareStatement(sqlStatement);

        ps.setInt(1, apptID);
        ps.execute();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    /**
     * Method for getting appointments per month
     * @param month
     * @return
     */
    public static ObservableList<Appointments> getMonthType(int month) {
        ObservableList<Appointments> monthTypeList = FXCollections.observableArrayList();
        Locale locale = Locale.getDefault();
        try{

            String sqlStatement = "SELECT COUNT(Appointment_ID), Type FROM appointments WHERE MONTH(Start) = ? GROUP BY Type";

            PreparedStatement ps = ConnectionJDBC.openConnection().prepareStatement(sqlStatement);
            ps.setInt(1, month);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                Month monthA = Month.of(month);

                int apptID = resultSet.getInt("COUNT(Appointment_ID)");
                String apptType = resultSet.getString("Type");

                Appointments appt = new Appointments(monthA, apptType, apptID);
                monthTypeList.add(appt);

            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return monthTypeList;
    }

    /**
     * Method for getting list of Contacts
     * @param contactID
     * @return
     */
    public static ObservableList<Appointments> getContactList(int contactID){

        ObservableList<Appointments> contactList = FXCollections.observableArrayList();

        try {
            String sqlStatement = "SELECT Appointment_ID, Title, Description, Type, Start, End, Contact_ID, Customer_ID From Appointments WHERE CONTACT_ID = ?";
            PreparedStatement ps = ConnectionJDBC.openConnection().prepareStatement(sqlStatement);

            ps.setInt(1, contactID);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int apptID = rs.getInt("Appointment_ID");
                System.out.println("apptID" + apptID);
                String apptTitle = rs.getString("Title");
                System.out.println("Title: " + apptTitle);
                String apptDescription = rs.getString("Description");
                System.out.println("description: " + apptDescription);
                String apptType = rs.getString("Type");
                System.out.println("Type: " + apptType);
                LocalDateTime startDate = rs.getTimestamp("Start").toLocalDateTime();
                String startDateString = dateTime.format(startDate);
                System.out.println("StartTime: " + startDate);
                LocalDateTime endDate = rs.getTimestamp("End").toLocalDateTime();
                String endDateString = dateTime.format(endDate);
                int customerID = rs.getInt("Customer_ID");

                Appointments appointments = new Appointments(apptID, apptTitle, apptDescription, apptType, startDateString, endDateString, contactID,customerID);

                contactList.add(appointments);            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contactList;
    }

    /**
     * Method for getting Customer appointments by Customer ID
     * @param customerID
     * @return
     */
    public static ObservableList<Appointments> getCustomerAppts(int customerID){

        ObservableList<Appointments> custApptsList = FXCollections.observableArrayList();
       // String sqlStatement = "SELECT * From Appointments WHERE CUSTOMER_ID = ?";
        try {
            String sqlStatement = "SELECT * From Appointments WHERE CUSTOMER_ID = ?";
            PreparedStatement ps = ConnectionJDBC.openConnection().prepareStatement(sqlStatement);

            ps.setInt(1, customerID);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                    int id = rs.getInt("Appointment_ID");
                    String title = rs.getString("Title");
                    String description = rs.getString("Description");
                    String location = rs.getString("Location");
                    String type = rs.getString("Type");
                    LocalDateTime startDate = rs.getTimestamp("Start").toLocalDateTime();
                    LocalDateTime endDate = rs.getTimestamp("End").toLocalDateTime();
                   // LocalDateTime created = rs.getTimestamp("Create_Date").toLocalDateTime();
                    int userID = rs.getInt("User_ID");
                    int contactID = rs.getInt("Contact_ID");

                    Appointments appointments = new Appointments(id, customerID, contactID, title, description, location, type, startDate, endDate, userID);

                    custApptsList.add(appointments);            }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return custApptsList;
        }
    }


