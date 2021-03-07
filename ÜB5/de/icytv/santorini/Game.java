package de.icytv.santorini;

/**
 * Game implementation of Santorini
 * 
 * @author Michael Finger
 * @version 1.0.0
 */
public class Game {
    private PlayingField field;
    private int currentHumanId;
    private Player[] players;
    private Human[] humans;

    private boolean moved;
    private boolean placed;
    private boolean drawnCard;

    private int amountOfCuboids;
    private int amountOfDomes;

    /**
     * Constructor for Satorini Game
     * 
     * @param players Playing figures
     * @param humans  Human players
     * @param field   Playing Field
     */
    public Game(Player[] players, Human[] humans, PlayingField field) {
        this.field = field;
        this.players = players;
        this.humans = humans;
        this.currentHumanId = 0;
        this.moved = false;
        this.placed = false;

        this.amountOfCuboids = 54;
        this.amountOfDomes = 18;
    }

    /**
     * validates a turn and initializes for next turn
     * 
     * @return if turn is finished
     */
    public boolean validateAndEndTurn() {
        if (this.moved && this.placed) {
            this.moved = false;
            this.placed = false;
            this.drawnCard = false;
            this.currentHumanId = (this.currentHumanId + 1) % Main.HUMAN_AMOUNT;
            getCurrentHuman().reset();
            // this.currentPlayerId = (this.currentPlayerId + 1) % PLAYER_AMOUNT;
            // this.currentPlayer = this.players[this.currentPlayerId];
            return true;
        } else {
            return false;
        }
    }

    /**
     * Allows Player to place piece
     * 
     * @param piece Piece to place [DOME|CUBOID]
     * @param x     x position for piece
     * @param y     y position for piece
     * @return if player can place again
     * @throws IllegalMoveException if the move is (for whatever reaseon) invalid
     */
    public boolean place(Piece piece, int x, int y) throws IllegalMoveException {
        Human h = getCurrentHuman();

        boolean canPlaceAgain = h.getCanPlaceTwice();

        if (this.placed && !canPlaceAgain) {
            throw new IllegalMoveException("You have already built a block");
        }

        if (piece == Piece.CUBOID && this.amountOfCuboids == 0) {
            throw new IllegalMoveException("There are no cuboids left to place");
        } else if (piece == Piece.DOME && this.amountOfDomes == 0) {
            throw new IllegalMoveException("There are no domes left to place");
        }

        boolean isValid = h.getCanPlaceDome();
        for (Player p : this.players) {
            if (p.getPlayerOwned() == h.getId() && field.isNeighbour(p, x, y)) {
                isValid = true;
                break;
            }
        }

        if (!isValid && !(h.getCanPlaceDome() && piece == Piece.DOME)) {
            throw new IllegalMoveException("You have to place on adjacent fields");
        }

        if (!field.getCell(x, y).put(piece)) {
            throw new IllegalMoveException("You cannot place that piece there");
        }

        h.setCanPlaceTwice(false);

        this.placed = true;

        return canPlaceAgain;
    }

    /**
     * Moves a player figure to (x, y)
     * 
     * @param p Player figure to move
     * @param x x position
     * @param y y position
     * @return if player can move again
     * @throws IllegalMoveException if the move was (for whatever reaseon) invalid
     */
    public boolean move(Player p, int x, int y) throws IllegalMoveException {
        Human h = getCurrentHuman();
        if (p.getPlayerOwned() != this.currentHumanId) {
            throw new IllegalMoveException("You do not own this figure");
        }

        boolean canMoveAgain = h.getCanMoveAgain();

        if (this.moved && !canMoveAgain) {
            throw new IllegalMoveException("You have already moved");
        }

        if (!(h.getCanMoveAnyWhere() || h.getCanSwitch()) && !field.isNeighbour(p, x, y)) {
            throw new IllegalMoveException("You have to move onto a neighbouring field");
        }

        int[] pos = field.getPlayerPosition(this.currentHumanId);

        if (pos[0] == x && pos[1] == y) {
            throw new IllegalMoveException("You cannot move onto the square you are standing on");
        }

        Field cell = field.getCell(x, y);
        Field playerCell = field.getCell(pos[0], pos[1]);
        if (h.getCanSwitch() && cell.getPlayer() != null) {
            field.move(p, x, y);

            this.moved = true;

            h.canMoveAgain(false);
            return canMoveAgain;
        } else if (cell.getPlayer() != null) {
            throw new IllegalMoveException("The cell you are trying to move to has a player already on it");
        }
        if (cell.hasDome()) {
            throw new IllegalMoveException("The cell you are trying to move to has a dome on it");
        }

        if (cell.getAmountOfBlocks() - playerCell.getAmountOfBlocks() > 1) {
            throw new IllegalMoveException("You cannot move up more than 1 block");
        }

        if (h.getMoveIsBlocked() && cell.getAmountOfBlocks() - playerCell.getAmountOfBlocks() > 0) {
            throw new IllegalMoveException("Your opponent blocked your move upwards");
        }

        field.move(p, x, y);

        this.moved = true;

        h.canMoveAgain(false);
        return canMoveAgain;
    }

    /**
     * Gets player by string identifier
     * 
     * @param playerName String identifier (i.e. yellow)
     * @return Player | null
     */
    public Player getPlayerByString(String playerName) {
        for (Player p : this.players) {
            if (p.getName().equals(playerName)) {
                return p;
            }
        }
        return null;
    }

    /**
     * Gets currently active Human player
     * 
     * @return currently active human
     */
    private Human getCurrentHuman() {
        return this.humans[this.currentHumanId];
    }

    /**
     * Gets total amount of domes left
     * 
     * @return amount of domes
     */
    public int getAmountOfDomes() {
        return this.amountOfDomes;
    }

    /**
     * Gets total amount of cuboids left
     * 
     * @return amount of cuboids
     */
    public int getAmountOfCuboids() {
        return this.amountOfCuboids;
    }

    /**
     * Gets cell at x, y
     * 
     * @param x x position
     * @param y y position
     * @return Cell at x, y
     */
    public Field getCell(int x, int y) {
        return field.getCell(x, y);
    }

    /**
     * Gets all cells
     * 
     * @return all cells
     */
    public Field[][] getCells() {
        return field.getCells();
    }

    /**
     * Gets current human player id
     * 
     * @return current human id
     */
    public int getCurrentHumanId() {
        return this.currentHumanId;
    }

    /**
     * Plays specified card
     * 
     * @param card apollo | artemis | athena | atlas | demeter | hermes
     * @throws IllegalMoveException if the move is invalid
     */
    public void playCard(String card) throws IllegalMoveException {
        if (this.drawnCard) {
            throw new IllegalMoveException("You cannot draw two cards in one turn");
        }
        Human h = this.humans[this.currentHumanId];
        if (h.getAmountOfCards() <= 0) {
            throw new IllegalMoveException("You cannot draw any more cards");
        }
        switch (card) {
            case "apollo":
                h.setCanSwitch(true);
                break;
            case "artemis":
                h.setCanMoveAgain(true);
                break;
            case "athena":
                this.humans[(this.currentHumanId + 1) % Main.HUMAN_AMOUNT].setMoveIsBlocked(true);
                break;
            case "atlas":
                h.setCanPlaceDome(true);
                break;
            case "demeter":
                h.canPlaceTwice(true);
                break;
            case "hermes":
                h.canMoveAnyWhere(true);
                break;
            default:
                throw new IllegalMoveException("There is no " + card + " card");
        }
        this.drawnCard = true;
        h.setAmountOfCards(h.getAmountOfCards() - 1);
    }

    /**
     * Gets debug info of current playing field
     * 
     * @return Current Playingfield debug information
     */
    @Override
    public String toString() {
        return this.field.toString();
    }
}
