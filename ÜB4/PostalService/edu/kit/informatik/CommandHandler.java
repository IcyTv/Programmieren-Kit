package edu.kit.informatik;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Michael Finger
 * @version 1.0.0
 */
public class CommandHandler {

    private static ArrayList<User> users = new ArrayList<User>();
    private static User loggedInUser = null;
    private static PostalStation postalStation = new PostalStation();

    /**
     * Handles a user input command
     * @param cmd
     */
    public static void handle(String cmd) {
        String[] split = cmd.split(" ");
        String command = split[0];
        String args;
        if (split.length > 1) {
            args = split[1];
        } else {
            args = null;
        }
        switch (command) {
            case "add-customer":
                addCustomer(args);
                break;
            case "add-mailman":
                addMailman(args);
                break;
            case "add-agent":
                addAgent(args);
                break;
            case "authenticate":
                authenticate(args);
                break;
            case "logout":
                logout();
                break;
            case "send-mail":
                sendMail(args);
                break;
            case "get-mail":
                getMail(args);
                break;
            case "list-mail":
                listMail(args);
                break;
            case "list-price":
                listPrice(args);
                break;
            case "reset-pin":
                resetPin(args);
                break;
            case "quit":
                handleError("incorrect input format, this command does not accept any parameters");
                break;
            default:
                handleError("Command not found");
                break;
        }
    }

    /**
     * Confirms if the recieved inputs are correct
     * @param args
     * @param pattern
     * @return
     */
    private static boolean confirmParameters(String args, String pattern) {
        boolean isCorrect = args != null && args.matches(pattern);
        if (isCorrect) {
            return false;
        } else {
            handleError("the command you entered does not accept these parameters");
            return true;
        }
    }

    /**
     * Same as confirmParameters but with RegExr
     * @param args
     * @param pattern
     * @return
     */
    private static boolean confirmParameters(String args, RegExr pattern) {
        return confirmParameters(args, pattern.toString());
    }

    /**
     * Adds a new customer
     * @param command
     */
    private static void addCustomer(String command) {
        if (ensureNoUser()) return;
        RegExr regexr = new RegExr(";").add(RegExrValues.NAME)
            .add(RegExrValues.NAME).add(RegExrValues.NAME).add(RegExrValues.STRING).add(RegExrValues.STRING);
        if (confirmParameters(command, regexr)) return;

        String[] args = command.split(";");

        String firstname = args[0];
        String lastname = args[1];
        String username = args[2];
        String password = args[3];
        String personalNumber = args[4];
        PostCustomer customer = new PostCustomer(firstname, lastname, personalNumber, username, password);


        if (users.contains(customer)) {
            handleError("That user already exists");
            return;
        }

        users.add(customer);
        postalStation.addUser(customer);
        Terminal.printLine("OK");
    }

    /**
     * Adds a Mailman
     * @param command
     */
    private static void addMailman(String command) {
        if (ensureNoUser()) return;
        RegExr regexr = new RegExr(";").add(RegExrValues.NAME).add(RegExrValues.NAME)
            .add(RegExrValues.NUMBER).add(RegExrValues.STRING);
        if (confirmParameters(command, regexr)) return;

        String[] args = command.split(";");

        String firstname = args[0];
        String lastname = args[1];
        String personalNumber = args[2];
        String password = args[3];

        Mailman mailman = new Mailman(firstname, lastname, personalNumber, password);

        if (users.contains(mailman)) {
            handleError("That personal number already exists");
            return;
        }

        users.add(mailman);
        Terminal.printLine("OK");
    }

    /**
     * Adds a service Agent
     * @param command
     */
    private static void addAgent(String command) {
        if (ensureNoUser()) {
            return;
        }
        RegExr regexr = new RegExr(";").add(RegExrValues.NAME).add(RegExrValues.NAME).add(RegExrValues.NUMBER)
                .add(RegExrValues.STRING);
        if (confirmParameters(command, regexr)) return;

        String[] args = command.split(";");

        String firstname = args[0];
        String lastname = args[1];
        String personalNumber = args[2];
        String password = args[3];

        Agent agent = new Agent(firstname, lastname, personalNumber, password);

        if (users.contains(agent)) {
            handleError("That personal number already exists");
            return;
        }

        users.add(agent);
        Terminal.printLine("OK");
    }

    /**
     * Authenticates a User and logs them in
     * @param command
     */
    public static void authenticate(String command) {
        if (ensureNoUser()) {
            return;
        }
        RegExr regexr = new RegExr(";").add(RegExrValues.NAME).add(RegExrValues.STRING);
        if (confirmParameters(command, regexr)) return;

        String[] args = command.split(";");

        String userid = args[0];
        String password = args[1];

        User u = getUser(userid);

        if (u != null && u.authenticate(password)) {
            loggedInUser = u;
            Terminal.printLine("OK");
        } else {
            handleError("Wrong password or user");
        }
    }
    
    /**
     * Logs out current user
     */
    private static void logout() {
        if (ensureUser()) return;

        loggedInUser = null;
        Terminal.printLine("OK");
    }

    /**
     * Sends a mail to a specific other user
     * @param command
     */
    private static void sendMail(String command) {
        if (ensureUser()) return;
        RegExr regexr = new RegExr(";").add("SERVICE").add("NAME");
        if (confirmParameters(command, regexr)) return;

        String[] args = command.split(";");

        if (loggedInUser.getClass() == Mailman.class) {
            String service = args[0];
            String recieverId = args[1];
            String senderId = args[2];

            User reciever = getUser(recieverId);
            User sender = getUser(senderId);

            if (reciever == null || sender == null) {
                handleError("No sender or reciever with that id found");
            } else {
                postalStation.sendMail(sender, reciever, Mail.fromString(service));
                Terminal.printLine("OK");
            }
        } else if (loggedInUser.getClass() == PostCustomer.class) {
            String service = args[0];
            String recieverId = args[1];

            User reciever = getUser(recieverId);

            if (reciever == null) {
                handleError("No reciever with that id found");
            } else {
                postalStation.sendMail(loggedInUser, reciever, Mail.fromString(service));
                Terminal.printLine("OK");
            }
        } else if (loggedInUser.getClass() == Agent.class) {
            handleError("You cannot do that as an Agent. Please log in as a customer or mailman");
        } else {
            handleError("User not implemented yet");
        }
    }

    /**
     * Gets all mail queued for the current user or specified user
     */
    private static void getMail(String args) {
        if (ensureUser()) return;

        if (loggedInUser.getClass() == PostCustomer.class) {
            RegExr regexr = new RegExr(";").add("NAME");
            if (confirmParameters(args, regexr)) return;
            if (postalStation.getMail(loggedInUser)) {
                Terminal.printLine("Okay");
            } else {
                handleError("No mail to get");
            }
        } else if (loggedInUser.getClass() == Mailman.class) {
            String recieverId = args;
            User reciever = getUser(recieverId);

            if (reciever == null) {
                handleError("No user with that id found!");
            } else {
                if (postalStation.getMail(reciever)) {
                    Terminal.printLine("Okay");
                } else {
                    handleError("No mail to get");
                }
            }
        } else {
            handleError("Cannot get mail with your user type");
        }
    }

    /**
     * Lists all mails Sent by a user
     * @param args
     */
    private static void listMail(String args) {
        if (ensureUser()) return;

        if (loggedInUser.getClass() == PostCustomer.class) {
            listMail(loggedInUser);
        } else if (loggedInUser.getClass() == Mailman.class) {
            RegExr regexr = new RegExr(";").add("NAME");
            if (confirmParameters(args, regexr))
                return;
            User u = getUser(args);
            if (u == null) {
                handleError("User not found");
            } else {
                listMail(u);
            }
        }
    }

    /**
     * List all mails sent by a user
     * @param u
     */
    private static void listMail(User u) {
        List<Mail> mail = postalStation.listMail(u);

        if (mail.size() == 0) {
            Terminal.printLine("OK");
            return;
        }

        HashMap<Mail, Integer> mailMap = new HashMap<Mail, Integer>();

        for (Mail m : mail) {
            Integer amount = mailMap.get(m);
            amount = amount != null ? amount : 0;
            mailMap.put(m, amount + 1);
        }

        for (Mail m : mailMap.keySet()) {
            Terminal.printLine(m.getName() + ";" + mailMap.get(m));
        }
    }

    /**
     * List the spent amount for each mail type for the specified or current user
     * @param args
     */
    private static void listPrice(String args) {
        if (ensureUser()) return;

        if (loggedInUser.getClass() == PostCustomer.class) {
            listPrice(loggedInUser);
        } else if (loggedInUser.getClass() == Mailman.class) {
            RegExr regexr = new RegExr(";").add("NAME");
            if (confirmParameters(args, regexr))
                return;
            User u = getUser(args);
            if (u == null) {
                handleError("No user found");
            } else {
                listPrice(u);
            }
        } //TODO else handleError
    }

    /**
     * Lists prices for specified User
     * @param u
     */
    private static void listPrice(User u) {
        Map<MailType, Double> spent = postalStation.getAllSpent(u);

        if (spent.keySet().size() == 0) {
            Terminal.printLine("OK");
            return;
        }

        Set<MailType> mails = spent.keySet();

        for (MailType m: mails) {
            double price = Mail.getPrice(m);
            double amount = postalStation.getSpent(u, m);
            Terminal.printLine(m.name() + ";" + ((int) (amount / price)) + ";" + String.format("%.2f", amount));
        }
    }

    /**
     * Resets the Password for specified User
     * @param command
     */
    private static void resetPin(String command) {
        if (ensureUser()) return;

        if (loggedInUser.getClass() == Agent.class) {
            RegExr regexr = new RegExr(";").add("NAME").add("STRING");
            if (confirmParameters(command, regexr))
                return;
            String[] args = command.split(";");
            String id = args[1];
            String newPassword = args[1];

            User u = getUser(id);

            if (u == null) {
                handleError("No user found");
            } else {
                u.setPassword(newPassword);
                Terminal.printLine("OK");
            }
        } else {
            handleError("Your user type does not support pin-resetting");
        }
    }

    /**
     * Utility Method: Gets a User by his/her PersonalNumber or by his/her Username
     * @param id
     * @return
     */
    private static User getUser(String id) {

        for (User u: users) {
            if (u.getPersonalnumber().equals(id)) {
                return u;
            } else if (u.getClass() == PostCustomer.class && ((PostCustomer) u).getUsername().equals(id)) {
                return u;
            }
        }

        return null;
    }

    /**
     * Utility Method: Ensures there is no current User and prints an Error message.
     * ATTENTION: returns false if there is no user for ease of use
     * <code>if (ensureNoUser()) return;</code>
     * @return false if no User true if user
     */
    private static boolean ensureNoUser() {
        if (loggedInUser == null) {
            return false;
        } else {
            handleError("You have to log out in order to use that command");
            return true;

        }
    }

    /**
     * Utility Method: Ensures there is a current User and prints an Error message.
     * ATTENTION: returns false if there is a user for ease of use
     * <code>if (ensureUser()) return;</code>
     * 
     * @return false if a current User exists true if no current User
     */
    private static boolean ensureUser() {
        if (loggedInUser == null) {
            handleError("You have to log in to use that command");
            return true;
        } else {
            return false;
        }
    }

    /**
     * Since Exceptions should not work for the programs controll flow, this method is used.
     * It Prints out "Error, *errorMessage*"
     * @param error the message you want to print
     */
    private static void handleError(String error) {
        Terminal.printLine("Error, " + error);
    }
}
