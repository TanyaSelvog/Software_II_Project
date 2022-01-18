package model;

public class Customer {

    private int customerID;
    private String customerName;
    private String customerAddress;

    private String customerPhone;
    private String customerPostal;

    private String customerCountry;

    private int divisionID;

    private String customerDivision;



    /**
     * Constructor
     * @param customerID
     * @param customerName
     * @param customerAddress
     * @param customerPhone
     * @param customerPostal
     * @param divisionID
     * @param customerDivision
     * @param customerCountry

     */
    public Customer (int customerID, String customerName, String customerAddress, String customerPostal, String customerPhone, int divisionID, String customerDivision, String customerCountry){
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerAddress= customerAddress;
        this.customerPostal = customerPostal;
        this.customerPhone = customerPhone;
        this.divisionID = divisionID;
        this.customerDivision = customerDivision;
        this.customerCountry = customerCountry;
    }

    /**
     * Getter for customerID
     * @return customerID Unique ID for each customer
     */
    public int getCustomerID(){
        return customerID;
    }

    /**
     * Setter for customerID
     * @param customerID Unique ID for customer
     */
    public void setCustomerID(int customerID){
        this.customerID = customerID;
    }

    /**
     * Getter for customerName
     * @return customerName Name of customer
     */
    public String getCustomerName(){
        return customerName;
    }

    /**
     * Setter for customerName
     * @param customerName Customer name
     */
    public void setCustomerName(String customerName){
        this.customerName = customerName;
    }

    /**
     * Getter for customerAddress
     * @return customerAddress Address of customer
     */
    public String getCustomerAddress(){
        return customerAddress;
    }

    /**
     * Setter for customerAddress
     * @param customerAddress Address of customer
     */
    public void setCustomerAddress(String customerAddress){
        this.customerAddress = customerAddress;
    }
    /**
     * Getter for customerCountry
     * @return customerCountry Country of customer
     */
    public String getCustomerCountry(){
        return customerCountry;
    }

    /**
     * Setter for customerCountry
     * @param customerCountry Country of customer
     */
    public void setCustomerCountry(String customerCountry){
        this.customerCountry = customerCountry;
    }

    /**
     * Getter for divisionID
     * @return divisionID Customer locale
     */
    public int getDivisionID(){
        return divisionID;
    }

    /**
     * Setter for divisionID
     * @param divisionID Customer locale
     */
    public void setDivisionID(int divisionID){
        this.divisionID = divisionID;
    }

    /**
     * Getter for customerPhone
     * @return customerPhone Customer phone number
     */
    public String getCustomerPhone(){
        return customerPhone;
    }

    /**
     * Setter for customerPhone
     * @param customerPhone Customer phone number
     */

    public void setCustomerPhone(String customerPhone){
        this.customerPhone = customerPhone;
    }

    /**
     * Getter for customerPostal
     * @return customerPostal Postal code of customer
     */
    public String getCustomerPostal(){
        return customerPostal;
    }

    /**
     * Setter for customerPostal
     * @param customerPostal Postal code of customer
     */

    public void setCustomerPostal(String customerPostal){
        this.customerPostal = customerPostal;
    }

    public String getCustomerDivision(){
        return customerDivision;
    }

    public void setCustomerDivision(String customerDivision){
        this.customerDivision = customerDivision;
    }
}
