package de.icytv.santorini;

/**
 * Implementation of human player in Santorini Mainly a data store for card
 * abilities
 * 
 * @author Michael Finger
 * @version 1.0.0
 */
public class Human {
    /**
     * Standard amount of card draws throughout the game
     */
    public static final int AMOUNT_OF_CARDS = 3;

    private int id;

    private boolean moveIsBlocked;
    private boolean canPlaceTwice;
    private boolean canMoveAgain;
    private boolean canMoveAnyWhere;
    private boolean canSwitch;
    private boolean canPlaceDome;

    private int amountOfCards;

    /**
     * Constructor of
     * 
     * @param id Unique Player id
     */
    public Human(int id) {
        this.id = id;
        this.amountOfCards = AMOUNT_OF_CARDS;
    }

    /**
     * Reset all abilities of Human
     */
    public void reset() {
        this.canMoveAgain(false).canMoveAnyWhere(false).canPlaceDome(false).canPlaceTwice(false).canSwitch(false);
    }

    /**
     * Gets Amount of cards left to draw
     * 
     * @return amount of cards
     */
    public int getAmountOfCards() {
        return this.amountOfCards;
    }

    /**
     * Sets Amount of cards
     * 
     * @param amountOfCards new amount of cards to draw from
     */
    public void setAmountOfCards(int amountOfCards) {
        this.amountOfCards = amountOfCards;
    }

    /**
     * If Human can Place a dome anywhere (Atlas card)
     * 
     * @return If Human can Place a dome anywhere (Atlas card)
     */
    public boolean getCanPlaceDome() {
        return this.canPlaceDome;
    }

    /**
     * Sets If Human can Place a dome anywhere (Atlas card)
     * 
     * @param canPlaceDome If Human can Place a dome anywhere (Atlas card)
     */
    public void setCanPlaceDome(boolean canPlaceDome) {
        this.canPlaceDome = canPlaceDome;
    }

    /**
     * Gets if the next movement upwards is blocked (Athena)
     * 
     * @return if the next movement upwards is blocked (Athena)
     */
    public boolean getMoveIsBlocked() {
        return this.moveIsBlocked;
    }

    /**
     * Sets if the next movement upwards is blocked (Athena)
     * 
     * @param moveIsBlocked if the next movement upwards is blocked (Athena)
     */
    public void setMoveIsBlocked(boolean moveIsBlocked) {
        this.moveIsBlocked = moveIsBlocked;
    }

    /**
     * Gets if a player can place twice in a turn (Demeter)
     * 
     * @return if a player can place twice in a turn (Demeter)
     */
    public boolean getCanPlaceTwice() {
        return this.canPlaceTwice;
    }

    /**
     * Sets if a player can place twice in a turn (Demeter)
     * 
     * @param canPlaceTwice if a player can place twice in a turn (Demeter)
     */
    public void setCanPlaceTwice(boolean canPlaceTwice) {
        this.canPlaceTwice = canPlaceTwice;
    }

    /**
     * Gets if a player can move twice (Artemis)
     * 
     * @return if a player can move twice (Artemis)
     */
    public boolean getCanMoveAgain() {
        return this.canMoveAgain;
    }

    /**
     * Sets if a player can move twice (Artemis)
     * 
     * @param canMoveAgain if a player can move twice (Artemis)
     */
    public void setCanMoveAgain(boolean canMoveAgain) {
        this.canMoveAgain = canMoveAgain;
    }

    /**
     * Gets a players Id
     * 
     * @return id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Gets if a player can move anywhere (Hermes)
     * 
     * @return if a player can move anywhere (Hermes)
     */
    public boolean getCanMoveAnyWhere() {
        return this.canMoveAnyWhere;
    }

    /**
     * Sets if a player can move anywhere (Hermes)
     * 
     * @param canMoveAnyWhere if a player can move anywhere (Hermes)
     */
    public void setCanMoveAnyWhere(boolean canMoveAnyWhere) {
        this.canMoveAnyWhere = canMoveAnyWhere;
    }

    /**
     * Gets if a player can switch positions with another (Apollo)
     * 
     * @return if a player can switch positions with another (Apollo)
     */
    public boolean getCanSwitch() {
        return this.canSwitch;
    }

    /**
     * Sets if a player can switch positions with another (Apollo)
     * 
     * @param canSwitch if a player can switch positions with another (Apollo)
     */
    public void setCanSwitch(boolean canSwitch) {
        this.canSwitch = canSwitch;
    }

    /**
     * setCanPlaceDome for chaining
     * 
     * @param canPlaceDome if player can set dome
     * @return this
     */
    public Human canPlaceDome(boolean canPlaceDome) {
        setCanPlaceDome(canPlaceDome);
        return this;
    }

    /**
     * setMoveIsBlocked for chaining
     * 
     * @param moveIsBlocked if players move is blocked
     * @return this
     */
    public Human moveIsBlocked(boolean moveIsBlocked) {
        setMoveIsBlocked(moveIsBlocked);
        return this;
    }

    /**
     * setCanPlaceTwice for chaining
     * 
     * @param canPlaceTwice if player can place twice
     * @return this
     */
    public Human canPlaceTwice(boolean canPlaceTwice) {
        setCanPlaceTwice(canPlaceTwice);
        return this;
    }

    /**
     * setCanMoveAgain for chaining
     * 
     * @param canMoveAgain if player can move again
     * @return this
     */
    public Human canMoveAgain(boolean canMoveAgain) {
        setCanMoveAgain(canMoveAgain);
        return this;
    }

    /**
     * setCanMoveAnyWhere for chaining
     * 
     * @param canMoveAnyWhere if player can move anywhere
     * @return this
     */
    public Human canMoveAnyWhere(boolean canMoveAnyWhere) {
        setCanMoveAnyWhere(canMoveAnyWhere);
        return this;
    }

    /**
     * setCanSwitch for chaining
     * 
     * @param canSwitch if player can switch
     * @return this
     */
    public Human canSwitch(boolean canSwitch) {
        setCanSwitch(canSwitch);
        return this;
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", moveIsBlocked='" + getMoveIsBlocked() + "'" + ", canPlaceTwice='"
                + getCanPlaceTwice() + "'" + ", canMoveAgain='" + getCanMoveAgain() + "'" + ", canMoveAnyWhere='"
                + getCanMoveAnyWhere() + "'" + ", canSwitch='" + getCanSwitch() + "'" + "}";
    }

}
