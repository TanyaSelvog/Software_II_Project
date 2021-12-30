package model;

public class Division {
    private String divisionName;
    private int divisionID;
    private int countryID;


    public Division(String divisionName, int divisionID, int countryID){
        this.divisionName = divisionName;
        this.divisionID = divisionID;
        this.countryID = countryID;
    }

    public int getDivisionID(){
        return divisionID;
    }

    public void setDivisionID(int divisionID){
        this.divisionID = divisionID;
    }
    public int getCountryID(){
        return countryID;
    }

    public void setCountryID(int countryID){
        this.countryID = countryID;
    }
    public String getDivisionName(){
        return divisionName;
    }

    public void setDivisionName(String divisionName){
        this.divisionName = divisionName;
    }
    @Override
    public String toString(){
        // return (getClass().getName() + '@' + Integer.toHexString(hashCode()));
        return (divisionName);
    }

}
