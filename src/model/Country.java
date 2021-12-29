package model;

public class Country {

    private String countryName;
    private int countryID;
    /*
    Constructor

     */
    public Country (String countryName, int countryID){
        this.countryName = countryName;
        this.countryID = countryID;
    }

    public String getCountryName() {
        return countryName;
    }


    public void setCountryName(String countryName){
        this.countryName = countryName;
    }

    public int getCountryID(){
        return countryID;

    }

    // will need to look at this tomorrow 12/29
    public void setCountryID(int countryID){
        this.countryID = countryID;
    }
}
