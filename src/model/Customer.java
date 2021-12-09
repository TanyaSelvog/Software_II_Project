package model;

public class Customer {

    private int customerID;
    private String customerName;
    private String customerAddress;

    private int customerPhone;
    private int customerPostal;

    private String customerCountry;

    private String customerDivision;


    /**
     * Constructor
     * @param customerID
     * @param customerName
     * @param customerAddress
     */
    public Customer (int customerID, String customerName, String customerAddress, int customerPostal, int customerPhone,
                     String customerCountry, String customerDivision){
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerAddress= customerAddress;
        this.customerPostal = customerPostal;
        this.customerPhone = customerPhone;
        this.customerCountry = customerCountry;
        this.customerDivision = customerDivision;
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
    public String getCustomerAddress(){
        return customerAddress;
    }
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
    public String getCustomerDivision(){
        return customerDivision;
    }

    public void setCustomerDivision(String customerDivision){
        this.customerDivision = customerDivision;
    }
    public int getCustomerPhone(){
        return customerPhone;
    }


    public void setCustomerPhone(int customerPhone){
        this.customerPhone = customerPhone;
    }
    public int getCustomerPostal(){
        return customerPostal;
    }


    public void setCustomerPostal(int customerPostal){
        this.customerPostal = customerPostal;
    }
}
