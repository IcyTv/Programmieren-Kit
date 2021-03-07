package edu.kit.informatik;

/**
 * Main Class
 * @author Michael Finger
 * @version 1.0.0
 */
public final class Main {
    /**
     * Main Method
     * @param args never used
     */
    public static void main(String[] args) {
        String command = Terminal.readLine();
        while (!command.equals("quit")) {
            CommandHandler.handle(command);
            command = Terminal.readLine();
        }
    }
}
