package edu.kit.informatik;

import edu.kit.informatik.utils.Sha256;

/**
 * @author Michael Finger
 * @version 1.0.0
 */
public abstract class User {
    private String passwordHash;
    private String firstname;
    private String lastname;
    private String personalnumber;
    private String salt;

    /**
     * Makes sure the empty constructor cannot be called
     */
    private User() {
        this("", "", "", "");
    }

    /**
     * Constructor for user with all Shared values
     * @param firstname First Name
     * @param lastname Last Name
     * @param personalnumber Presonal Number or ID Card Number
     * @param password Password
     */
    public User(String firstname, String lastname, String personalnumber, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.personalnumber = personalnumber;
        //A better Salt would use java.security.SecureRandom
        this.salt = Integer.toHexString((int) (Math.random() * 10e10)); 
        this.passwordHash = Sha256.getDigest(salt + ":" + password);
    }

    /**
     * Checks if passwords match
     * @param password Password for authentication
     * @return true if authentication succeeds, else false
     */
    public boolean authenticate(String password) {
        String hash = Sha256.getDigest(salt + ":" + password);
        return passwordHash.equals(hash);
    }

    /**
     * Resets a users password
     * @param password New Password
     */
    public void setPassword(String password) {
        this.passwordHash = Sha256.getDigest(salt + ":" + password);
    }

    /**
     * Gets Password Hash
     * @return Hex representation of Sha256 hashed password
     */
    public String getPasswordHash() {
        return this.passwordHash;
    }

    /**
     * Gets Users first name
     * @return first name
     */
    public String getFirstname() {
        return this.firstname;
    }

    /**
     * Gets users Last Name
     * @return lastName
     */
    public String getLastname() {
        return this.lastname;
    }

    /**
     * Gets Users Personal Number
     * @return PersonalNumber
     */
    public String getPersonalnumber() {
        return this.personalnumber;
    }

    /**
     * Implements user equal check by comparing personalnumbers,
     * since these are unique across all User groups and checks for matching classes
     * @return if User is Object
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User) {
            return ((User) obj).getPersonalnumber().equals(this.personalnumber) && obj.getClass() == this.getClass();
        } else {
            return false;
        }
    }
    
}
