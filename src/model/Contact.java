package model;

public class Contact {

    private int contactID;
    private String contactName;
    private String contactEmail;

    /**
     * Constructor
     *
     */
    public Contact(int contactID, String contactName, String contactEmail){
        this.contactID = contactID;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
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
     * Getter for Contact Email
     * @return contactEmail
     */
    public String getContactEmail() {
        return contactEmail;
    }

    /**
     * Setter for Contact Email
     * @param contactEmail
     */
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    @Override
    public String toString(){
        // return (getClass().getName() + '@' + Integer.toHexString(hashCode()));
        return (contactName);
    }
}
