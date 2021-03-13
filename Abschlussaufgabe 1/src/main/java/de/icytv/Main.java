package de.icytv;

import edu.kit.informatik.Terminal;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) {
        String command = Terminal.readLine();
        while (!command.equals("quit")) {
            CommandHandler.handle(command);
            command = Terminal.readLine();
        }
    }
}