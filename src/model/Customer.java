package model;

public class Customer {

    private int customerID;
    private String customerName;
    private String customerAddress;

    /**
     * Constructor
     * @param customerID
     * @param customerName
     * @param customerAddress
     */
    public Customer (int customerID, String customerName, String customerAddress){
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerAddress= customerAddress;
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
}
