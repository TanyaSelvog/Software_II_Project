package model;

/**
 * This class holds general data about Contacts
 */

public class Contact {
    /**
     * Contact ID as int
     */
    private int contactID;

    /**
     * Contact Name
     */
    private String contactName;


    /**
     * Constructor
     *
     */
    public Contact(int contactID, String contactName){
        this.contactID = contactID;
        this.contactName = contactName;

    }

    /**
     * Getter for Contact ID
     * @return contactID
     */
    public int getContactID() {
        return contactID;
    }

    /**
     * Setter for Contact ID
     * @param contactID
     */
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    /**
     * Getter for Contact Name
     * @return contactName
     */

    public String getContactName() {
        return contactName;
    }

    /**
     * Setter for Contact Name
     * @param contactName
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * Using toString to return Contact Name
     * @return Contact Name
     */
    @Override
    public String toString(){
        return (contactName);
    }
}
