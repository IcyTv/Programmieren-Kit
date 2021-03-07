package de.icytv.santorini;

/**
 * Implementation of a "Player" (Playing Figure)
 * 
 * @author Michael Finger
 * @version 1.0.0
 */
public class Player {
    private int playerOwned;
    private int id;
    private String name;

    /**
     * Constructor for Player
     * 
     * @param id          Unique ID for identifying players
     * @param name        String identifier of player
     * @param playerOwned Id of player who owns this Figure
     */
    public Player(int id, String name, int playerOwned) {
        this.playerOwned = playerOwned;
        this.id = id;
        this.name = name;
    }

    /**
     * Gets Id of Player
     * 
     * @return id of player
     */
    public int getId() {
        return this.id;
    }

    /**
     * Gets name of player
     * 
     * @return name of player
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets id of Player who owns this figure
     * 
     * @return Id of Figure-Owner
     */
    public int getPlayerOwned() {
        return playerOwned;
    }

    /**
     * Sets Owner of figure (May be useful for future expansion, i.e. Taking control
     * of someones figure)
     * 
     * @param playerOwned Id of owner
     */
    public void setPlayerOwned(int playerOwned) {
        this.playerOwned = playerOwned;
    }

    /**
     * Checks for equality betweeen figures
     * 
     * @param o object to check against
     * @return If this == o
     */
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Player)) {
            return false;
        }
        Player player = (Player) o;
        return id == player.id && name.equals(player.name);
    }

}
