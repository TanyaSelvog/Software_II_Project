package model;

public class Country {

    private String countryName;
    private int countryID;
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
     * Setter for countryID
     * @param countryID
     */

    // will need to look at this tomorrow 12/29
    public void setCountryID(int countryID){
        this.countryID = countryID;
    }
    @Override
    public String toString(){
        // return (getClass().getName() + '@' + Integer.toHexString(hashCode()));
        return (countryName);
    }
}
