package model;

public class Division {
    private String divisionName;
    private int divisionID;


    public Division(String divisionName, int divisionID){
        this.divisionName = divisionName;
        this.divisionID = divisionID;
    }

    public int getDivisionID(){
        return divisionID;
    }

    public void setDivisionID(int divisionID){
        this.divisionID = divisionID;
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
        return (divisionName + "" + divisionID);
    }

}
