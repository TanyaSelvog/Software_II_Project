package utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointments;
import model.Customer;
import model.Division;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ApptsDB {

    public static User currentUser;
    public static int userID;
    public static LocalDateTime loginTime;
    public static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("hh:mm a");

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
                int contactID = result.getInt("Contact_ID");
                int customerID = result.getInt("Customer_ID");
                int userID = result.getInt("User_ID");
                String apptTitle = result.getString("Title");
                String apptDescription = result.getString("Description");
                String apptLocation = result.getString("Location");
                String apptContact = result.getString("Contact_Name");
                String apptType = result.getString("Type");
                Timestamp startDate = result.getTimestamp("Start");
                LocalDateTime testDate = startDate.toLocalDateTime();
                Timestamp endDate = result.getTimestamp("End");
                LocalDateTime testEnd = endDate.toLocalDateTime();
                System.out.println(apptTitle + " " + apptContact + " " + apptType);
                Appointments appointments = new Appointments(apptID, apptTitle,apptDescription,apptLocation, apptContact, apptType, customerID,
                        userID, contactID, testDate, testEnd);
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

    public static Appointments getUserAppt(){
        Appointments userAppt = null;
        LocalDateTime loginTime = LocalDateTime.now();

            try {
                String sqlStatement = "SELECT appointment_ID, start, description, " +
                        "user_ID from appointments where User_ID = " +currentUser.getUserID();

                PreparedStatement ps = ConnectionJDBC.openConnection().prepareStatement(sqlStatement);

                ResultSet result = ps.executeQuery();
                while (result.next()) {
                    int apptID = result.getInt("Appointment_ID");
                    int userID = result.getInt("User_ID");
                    String apptDescription = result.getString("Description");
                    Timestamp startDate = result.getTimestamp("Start");
                    LocalDateTime startDateTime = startDate.toLocalDateTime();

                    userAppt = new Appointments(apptID, apptDescription, startDateTime, userID);
                    System.out.println("startDateTime: " + startDateTime + " userID: " + userID);
                    String apptTimeNotice=dtf.format(startDateTime);
                    System.out.println("Appt Time notice to compare actual log in time to: " + apptTimeNotice);

                    if (loginTime.isBefore(startDateTime)){
                        System.out.println("Login time is before startDateTime " + loginTime);
                    }

                    LocalDateTime timeAfterAppt = startDateTime.plusMinutes(15);
                    LocalDateTime timeBeforeAppt = startDateTime.minusMinutes(15);
                    System.out.println("timeAfterAppt: " + timeAfterAppt + " This is for 15 minutes after start of appt");
                    System.out.println("timeBeforeAppt: " + timeBeforeAppt + "This is for the 15 minutes before an appt starts");
                }

            } catch (SQLException exception) {
                exception.printStackTrace();

            }
            return userAppt;
        }
//SELECT appointment_ID, start, description, appointments.user_ID from appointments, users where users.user_ID = appointments.user_ID ;

    }