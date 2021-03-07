package edu.kit.informatik;

/**
 * Data store for postal Customer
 * @author Michael Finger
 * @version 1.0.0
 */
public class PostCustomer extends User {
    private String username;

    public PostCustomer(String firstname, String lastname, String personalnumber, String username, String password) {
        super(firstname, lastname, personalnumber, password);
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

}
