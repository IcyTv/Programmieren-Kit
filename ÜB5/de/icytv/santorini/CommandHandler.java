package de.icytv.santorini;

import java.util.ArrayList;

import edu.kit.informatik.Terminal;

/**
 * Handles Command strings
 * 
 * @author Michael Finger
 * @version 1.0.0
 */
public class CommandHandler {
    private Game game;

    /**
     * Constructor for CommandHandler
     * 
     * @param players Players to add to game
     * @param humans  Human Players to add to game
     * @param field   Current PlayingField
     */
    public CommandHandler(Player[] players, Human[] humans, PlayingField field) {
        this.game = new Game(players, humans, field);
    }

    /**
     * Handles a single command
     * 
     * @param command Command to process
     */
    public void handle(String command) {
        String[] split = command.split(" ");

        switch (split[0]) {
            case "draw-card":
                drawCard(split[1]);
                break;
            case "move":
                move(split[1]);
                break;
            case "build":
                build(split[1]);
                break;
            case "turn":
                turn();
                break;
            case "surrender":
                surrender();
                break;
            case "bag":
                bag();
                break;
            case "cellprint":
                cellprint(split[1]);
                break;
            case "print":
                print();
                break;
            case "quit":
                handleError("Arguments on quit not supported");
                break;
            default:
                handleError("That command is not recognized");
        }
    }

    /**
     * Prints the current playing field as per exercise
     */
    private void print() {
        for (Field[] x : game.getCells()) {
            String out = "";
            for (Field cell : x) {
                Piece piece = cell.getCurrentPiece();
                if (cell.getPlayer() != null) {
                    out += cell.getPlayer().getName() + ",";
                    continue;
                }
                if (piece == null) {
                    out += ".,";
                } else if (piece == Piece.CUBOID) {
                    out += "C,";
                } else {
                    out += "D,";
                }
            }
            Terminal.printLine(out.substring(0, out.length() - 1));
        }
    }

    /**
     * Prints a single cell as per exercise
     * 
     * @param args [x, y] position
     */
    private void cellprint(String args) {
        if(!args.matches("[0-9]+;[0-9]+")) {
            Terminal.printLine("Error, your arguments were wrong!");
            return;
        }

        String[] sPos = args.split(";");
        int[] iPos = new int[] {Integer.parseInt(sPos[0]), Integer.parseInt(sPos[1])};

        Field cell = game.getCell(iPos[0], iPos[1]);

        if (cell.getAmountOfBlocks() == 0) {
            Terminal.printLine("Empty");
        } else {
            ArrayList<String> pieces = new ArrayList<String>();
            for (Piece p : cell.getPieces()) {
                if (p == Piece.DOME) {
                    pieces.add("D");
                } else if (p == Piece.CUBOID) {
                    pieces.add("C");
                }
            }
            if (cell.getPlayer() != null) {
                pieces.add(cell.getPlayer().getName());
            }
            Terminal.printLine(String.join(";", pieces));
        }
    }

    /**
     * Prints amount of blocks as per exercise specification
     */
    private void bag() {
        Terminal.printLine("C;" + game.getAmountOfCuboids());
        Terminal.printLine("D;" + game.getAmountOfDomes());
    }

    /**
     * Surrenders one player
     */
    private void surrender() { // TODO end
        Terminal.printLine("P" + ((game.getCurrentHumanId() + 1) % 2 + 1) + " wins");
    }

    /**
     * Ends current turn if player finished it
     */
    private void turn() {
        if (!game.validateAndEndTurn()) {
            handleError("Please complete your turn before moving on");
        } else {
            Terminal.printLine("P" + (game.getCurrentHumanId() % 2 + 1));
        }
    }

    /**
     * Builds a Piece on x, y
     * 
     * @param args [piece,x,y]
     */
    private void build(String args) {
        String[] split = args.split(";");

        if (split.length != 3) {
            handleError("Unexpected argument length. Usage: build [Piece;x;y]");
            return;
        }

        try {
            Piece p;
            switch (split[0]) {
                case "C":
                    p = Piece.CUBOID;
                    break;
                case "D":
                    p = Piece.DOME;
                    break;
                default:
                    throw new IllegalMoveException("The piece " + split[0] + " is not recognized");
            }
            this.game.place(p, Integer.parseInt(split[1]), Integer.parseInt(split[2]));
            Terminal.printLine("OK");
        } catch (IllegalMoveException e) {
            handleError(e.getMessage());
        }
    }

    /**
     * Moves a player to x,y
     * 
     * @param args [playerStringId, x, y]
     */
    private void move(String args) {
        String[] split = args.split(";");

        if (split.length != 3) {
            handleError("Unexpected argument length. Usage: move [Identifier;x;y]");
            return;
        }

        try {
            Player p = this.game.getPlayerByString(split[0]);
            if (p == null) {
                handleError("No player with that identifier");
                return;
            }
            this.game.move(p, Integer.parseInt(split[1]), Integer.parseInt(split[2]));
            Terminal.printLine("OK");
        } catch (IllegalMoveException e) {
            handleError(e.getMessage());
        }

    }

    /**
     * Draws a specified card
     * 
     * @param args card to draw
     */
    public void drawCard(String args) {
        try {
            this.game.playCard(args.toLowerCase());
            Terminal.printLine("OK");
        } catch (IllegalMoveException e) {
            handleError(e.getMessage());
        }
    }

    /**
     * Handles all error messages as per exercise specification
     * 
     * @param message Message to print after error
     */
    public void handleError(String message) {
        Terminal.printLine("Error, " + message);
    }
}
