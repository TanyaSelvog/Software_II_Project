package model;

/**
 * This class provides general data about Division
 */
public class Division {
    /**
     * Name of Division
     */
    private String divisionName;
    /**
     * Division ID
     */
    private int divisionID;
    /**
     * Country ID
     * Also Foreign Key in First_Level_Divisions Table
     */
    private int countryID;

    /**
     * Constructor for Division
     * @param divisionName
     * @param divisionID
     * @param countryID
     */
    public Division(int divisionID, String divisionName, int countryID){
        this.divisionID = divisionID;
        this.divisionName = divisionName;
        this.countryID = countryID;
    }

    /**
     * Getter for divisionID
     * @return divisionID
     */
    public int getDivisionID(){
        return divisionID;
    }

    /**
     * Setter for divisionID
     * @param divisionID
     */
    public void setDivisionID(int divisionID){
        this.divisionID = divisionID;
    }

    /**
     * Getter for countryID
     * @return countryID
     */
    public int getCountryID(){
        return countryID;
    }

    /**
     * Setter for countryID
     * @param countryID
     */
    public void setCountryID(int countryID){
        this.countryID = countryID;
    }

    /**
     * Getter for divisionName
     * @return divisionName
     */
    public String getDivisionName(){
        return divisionName;
    }

    /**
     * Setter for divisionName
     * @param divisionName
     */
    public void setDivisionName(String divisionName){
        this.divisionName = divisionName;
    }

    /**
     * To String for Division Name
     * @return Division Name
     */
    @Override
    public String toString(){
        // return (getClass().getName() + '@' + Integer.toHexString(hashCode()));
        return (divisionName);
    }

}
