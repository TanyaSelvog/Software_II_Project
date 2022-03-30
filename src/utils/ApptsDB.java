package utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.Appointments;
import model.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ApptsDB {

    public static User currentUser;
    public static int userID;
    public static LocalDateTime loginTime;
    public static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("hh:mm a");
    public static DateTimeFormatter dateTime = DateTimeFormatter.ofPattern("MM-dd-yyyy hh:mm a");

    public static ObservableList<Appointments> getApptsList(){
        ObservableList<Appointments> apptsList = FXCollections.observableArrayList();

        try {
            String sqlStatement = "SELECT title, description, location, type, start, end, customer_ID, " +
                    " user_ID, appointment_ID, contacts.contact_ID, contacts.contact_name FROM Appointments, Contacts WHERE " +
                    "contacts.contact_ID = appointments.contact_ID";

            PreparedStatement ps = ConnectionJDBC.openConnection().prepareStatement(sqlStatement);

            ResultSet result = ps.executeQuery();
            while(result.next()){
                int apptID = result.getInt("Appointment_ID");
                System.out.println("apptID from apptsDB: " + apptID);
                int contactID = result.getInt("Contact_ID");
                int customerID = result.getInt("Customer_ID");
              //  String customerName = customerID.getCustomerName;
                int userID = result.getInt("User_ID");
                String apptTitle = result.getString("Title");
                String apptDescription = result.getString("Description");
                String apptLocation = result.getString("Location");
                String apptContact = result.getString("Contact_Name");
                System.out.println("apptContact Name from apptDB :" + apptContact);
                String apptType = result.getString("Type");
                LocalDateTime startDate = result.getTimestamp("Start").toLocalDateTime();
                String startDateString = dateTime.format(startDate);
                LocalDateTime endDate = result.getTimestamp("End").toLocalDateTime();
                String endDateString = dateTime.format(endDate);
                System.out.println(startDateString);
                Appointments appointments = new Appointments(apptID, apptTitle,apptDescription,apptLocation, apptContact, apptType, customerID,
                        userID, contactID, startDate, endDate, startDateString, endDateString);
                    apptsList.add(appointments);
                }


        } catch (SQLException exception) {
                exception.printStackTrace();
            }
            return apptsList;
            }
    //3.16
    public static void createAppointment(String apptTitle, String apptDesc, String apptLocation,
                                         String apptType, LocalDateTime startAppt, LocalDateTime endAppt,
                                         String createdBy, int customerID, int userID, int contactID) {
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
            ps.setString(7, createdBy);
            ps.setInt(8, customerID);
            ps.setInt(9, userID);
            ps.setInt(10, contactID);





            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

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
                String apptTimeNotice = dtf.format(startDateTime);
                System.out.println("Appt Time notice to compare actual log in time to: " + apptTimeNotice);

                //  if (loginTime.isBefore(startDateTime)){
                //    System.out.println("Login time is before startDateTime " + loginTime);
                //}

                //time before appt
                LocalDateTime timeBeforeAppt = startDateTime.minusMinutes(15);

                // System.out.println("timeBeforeAppt: " + timeBeforeAppt + "This is for the 15 minutes before an appt starts");
                String beforeTime = dtf.format(timeBeforeAppt);
                System.out.println("In String format, beforeTime: " + beforeTime);

                //time after appt starts

                LocalDateTime timeAfterAppt = startDateTime.plusMinutes(15);
                //  System.out.println("timeAfterAppt: " + timeAfterAppt + " This is for 15 minutes after start of appt");
                String afterTime = dtf.format(timeAfterAppt);
                System.out.println("In String format, afterTime: " + afterTime);

                //checking to see if a time is between two times

                if (loginTime.isAfter(timeBeforeAppt) && loginTime.isBefore(timeAfterAppt)) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, ("You have an appointment (Appointment ID: " + apptID + ") "
                            + "at " + apptTimeNotice + "."));
                    alert.setTitle("Upcoming appointment");
                    alert.showAndWait();
                    System.out.println("Appointment time of: " + loginTime + " is between " + timeBeforeAppt + " and " + timeAfterAppt);
                }

            }


                //need to fix time



        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;

//SELECT appointment_ID, start, description, appointments.user_ID from appointments, users where users.user_ID = appointments.user_ID ;

    }}