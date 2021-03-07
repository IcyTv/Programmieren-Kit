package de.icytv.santorini;

import edu.kit.informatik.Terminal;

/**
 * Main class
 * 
 * @author Michael Finger
 * @version 1.0.0
 */
public final class Main {
    /**
     * Amount of humans
     */
    public static final int HUMAN_AMOUNT = 2;
    /**
     * Amount of players
     */
    public static final int PLAYER_AMOUNT = 4;

    /**
     * Entry point of the program
     * 
     * @param args [identifier, x, y] * PLAYER_AMOUNT
     */
    public static void main(String[] args) {
        if (args.length != PLAYER_AMOUNT) {
            Terminal.printError("Error, Not the right amount of players specified. Expected: " + PLAYER_AMOUNT);
        }

        PlayingField field = new PlayingField();

        Player[] players = new Player[PLAYER_AMOUNT];
        for (int i = 0; i < args.length; i++) {
            String[] playerArgs = args[i].split(";");
            players[i] = new Player(i, playerArgs[0], (int) (i / HUMAN_AMOUNT));

            field.setPlayer(players[i], Integer.parseInt(playerArgs[1]), Integer.parseInt(playerArgs[2]));
        }

        Human[] humans = new Human[HUMAN_AMOUNT];

        for (int i = 0; i < HUMAN_AMOUNT; i++) {
            humans[i] = new Human(i);
        }

        String command = Terminal.readLine();
        CommandHandler handler = new CommandHandler(players, humans, field);
        while (!command.equals("quit")) {
            handler.handle(command);
            command = Terminal.readLine();
        }
    }

}