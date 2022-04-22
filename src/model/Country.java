package model;

/**
 * This class provides general data about Countries
 */
public class Country {

    /**
     * Country Name
     */
    private String countryName;

    /**
     * Country ID
     */
    private int countryID;

    /**
     * Division ID
     */
    private int divisionID;
/**
    Constructor
    * @param  countryName
    * @param countryID
     */
    public Country (String countryName, int countryID){
        this.countryName = countryName;
        this.countryID = countryID;
    }

    /**
     * Getter for countryName
     * @return Name of country
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * Setter for countryName
     * @param countryName
     */

    public void setCountryName(String countryName){
        this.countryName = countryName;
    }

    /**
     * Getter for countryID
     * @return country ID
     */

    public int getCountryID(){
        return countryID;

    }

    /**
     * Getter for Division ID
     * @return Division ID
     */
    public int getDivisionID(){

        return divisionID;
    }

    /**
     * Setter for Division ID
     * @param divisionID Division ID
     */
    public void setDivisionID(int divisionID){
        this.divisionID = divisionID;
    }
    /**
     * Setter for countryID
     * @param countryID Country ID as int
     */

    // will need to look at this tomorrow 12/29
    public void setCountryID(int countryID){
        this.countryID = countryID;
    }

    /**
     * To String for Country Name
     * @return Country Name
     */
    @Override
    public String toString(){
        // return (getClass().getName() + '@' + Integer.toHexString(hashCode()));
        return (countryName);
    }
}
