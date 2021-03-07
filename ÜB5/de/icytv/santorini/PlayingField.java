package de.icytv.santorini;

import java.util.HashMap;

/**
 * Implementation of a Santorini Playing field
 * 
 * @author Michael Finger
 * @version 1.0.0
 */
public class PlayingField {
    /**
     * Fixed board size
     */
    private static final int SIZE = 5;

    private Field[][] cells;
    private HashMap<Integer, int[]> playerPositions;

    /**
     * Constructor for PlayingField
     */
    public PlayingField() {
        this.playerPositions = new HashMap<Integer, int[]>();
        this.cells = new Field[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                this.cells[i][j] = new Field();
            }
        }
    }

    /**
     * Get height distance of 2 cells
     * 
     * @param x1 x of cell1
     * @param y1 y of cell1
     * @param x2 x of cell2
     * @param y2 y of cell2
     * @return height difference
     */
    public int getBlockDistance(int x1, int y1, int x2, int y2) {
        return getCell(x1, y1).getAmountOfBlocks() - getCell(x2, y2).getAmountOfBlocks();
    }

    /**
     * Gets cell at x, y
     * 
     * @param x x
     * @param y y
     * @return Cell at x,y
     */
    public Field getCell(int x, int y) {
        if (checkBounds(x, y)) {
            return this.cells[x][y];
        }
        return null;
    }

    /**
     * Checks if x, y is in bounds of playingfield
     * 
     * @param x x to test
     * @param y y to test
     * @return if (x, y) is in PlayingField bounds
     */
    public boolean checkBounds(int x, int y) {
        return x >= 0 && x < SIZE && y >= 0 && y < SIZE;
    }

    /**
     * Sets player at a given x,y position
     * 
     * @param player Player to add
     * @param x      x position of player
     * @param y      y position of player
     */
    public void setPlayer(Player player, int x, int y) {
        getCell(x, y).setPlayer(player);
        this.playerPositions.put(player.getId(), new int[] {x, y});
    }

    /**
     * Get all cells
     * 
     * @return all cells
     */
    public Field[][] getCells() {
        return this.cells;
    }

    /**
     * Move player to x, y
     * 
     * @param player player to move
     * @param x      x to move player to
     * @param y      y to move player to
     */
    public void move(Player player, int x, int y) {
        int[] pos = getPlayerPosition(player.getId());
        getCell(pos[0], pos[1]).setPlayer(null);
        getCell(x, y).setPlayer(player);
        this.playerPositions.put(player.getId(), new int[] {x, y});
    }

    /**
     * Checks if (x,y) is adjacent to player
     * 
     * @param p player
     * @param x x
     * @param y y
     * @return if player is adjacent to (x, y)
     */
    public boolean isNeighbour(Player p, int x, int y) {
        int[] pos = getPlayerPosition(p.getId());
        int dx = Math.abs(pos[0] - x);
        int dy = Math.abs(pos[1] - y);
        return dx <= 1 && dy <= 1;
    }

    /**
     * Gets the current Position of the specified player
     * 
     * @param player Id of player
     * @return int[]{xPosition, yPosition}
     */
    public int[] getPlayerPosition(Integer player) {
        return this.playerPositions.get(player);
    }

    /**
     * Prints debug information for the playing field
     * 
     * @return Debug information
     */
    public String toString() {
        String out = "";
        for (Field[] f : this.cells) {
            for (Field d : f) {
                out += d.toString();
            }
            out += "\n";
        }
        return out;
    }
}
