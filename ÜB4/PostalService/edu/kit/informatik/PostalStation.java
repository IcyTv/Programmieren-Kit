package edu.kit.informatik;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Object store for the postal System
 * @author Michael Finger
 * @version 1.0.0
 */
public class PostalStation {
    private HashMap<String, ArrayList<Mail>> toBeDelivered;
    private HashMap<String, HashMap<MailType, Double>> spent;

    /**
     * Constructor for PostalStation
     */
    public PostalStation() {
        this.toBeDelivered = new HashMap<String, ArrayList<Mail>>();
        this.spent = new HashMap<String, HashMap<MailType, Double>>();
    }

    /**
     * Adds a User to the Postal System, (Initializes Mail Array)
     * @param user
     */
    public void addUser(User user) {
        this.toBeDelivered.put(user.getPersonalnumber(), new ArrayList<Mail>());
        this.spent.put(user.getPersonalnumber(), new HashMap<MailType, Double>());
    }

    /**
     * Send a mail in the Postal System
     * @param from who sends the mail
     * @param to who recieves the mail
     * @param mail the mail to send
     */
    public void sendMail(User from, User to, Mail mail) {
        Double previousAmount = spent.get(from.getPersonalnumber()).get(mail.getType());
        previousAmount = previousAmount != null ? previousAmount : 0.0;
        spent.get(from.getPersonalnumber()).put(mail.getType(), previousAmount + mail.getPrice());

        this.toBeDelivered.get(to.getPersonalnumber()).add(mail);
    }

    /**
     * Gets all accumulated mail for a specific User
     * @throws IllegalArgumentException if the User has not been added to the system previously
     * @param user User to get the mail for
     * @return boolean There was mail to retrieve and it has been retrieved
     */
    public boolean getMail(User user) {
        ArrayList<Mail> mail = toBeDelivered.get(user.getPersonalnumber());
        if (mail == null) {
            throw new IllegalArgumentException("User has not been added!");
        }

        if (mail.size() == 0) {
            return false;
        } else {
            toBeDelivered.put(user.getPersonalnumber(), new ArrayList<Mail>());
            return true;
        }
    }

    /**
     * Gets an ArrayList of Mails for the specfied User
     * without delivering them
     * @param user User to get mail for
     * @return ArrayList<Mail> all mails for the specified User
     */
    public ArrayList<Mail> listMail(User user) {
        return toBeDelivered.get(user.getPersonalnumber());
    }

    /**
     * Gets money spent for specific MailType
     * @param user User to get amount for
     * @param type MailType to get spent amount for
     * @return spent amount
     */
    public double getSpent(User user, MailType type) {
        return spent.get(user.getPersonalnumber()).get(type);
    }

    /**
     * Gets spent money for ALL MailTypes
     * @param user User to get All Spent for
     * @return HashMap<MailType, Double> with amounts spent for specific MailTypes
     */
    public HashMap<MailType, Double> getAllSpent(User user) {
        return spent.get(user.getPersonalnumber());
    }
}
