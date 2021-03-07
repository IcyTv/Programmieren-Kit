package de.icytv.santorini;

/**
 * Implemetation of single Playing cell
 * 
 * @author Michael Finger
 * @version 1.0.0
 */
public class Field {
    private Player player;
    private Piece[] blocks;
    private int currentHeight;
    private boolean hasDome;

    /**
     * Empty constructor for Field
     */
    public Field() {
        this(null);
    }

    /**
     * Cosntructor with playing figure for Field
     * 
     * @param figure Player at this field
     */
    public Field(Player figure) {
        this.blocks = new Piece[4];
        this.currentHeight = 0;
        this.hasDome = false;
    }

    /**
     * Gets Player at this field
     * 
     * @return Player at this field | null
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Sets player on this field
     * 
     * @param player Player to add
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Puts a piece on the current Field
     * 
     * @param piece Piece to put on current field
     * @return if the move is valid
     */
    public boolean put(Piece piece) {

        if (piece == Piece.CUBOID && currentHeight <= 3 && this.currentHeight > 0
                && this.blocks[currentHeight - 1] == Piece.DOME) {
            return false;
        } else if (this.currentHeight > 0 && this.blocks[currentHeight - 1] == Piece.DOME) {
            return false;
        } else if (this.currentHeight >= 4) {
            return false;
        }
        if (piece == Piece.DOME) {
            this.hasDome = true;
        }
        this.blocks[currentHeight] = piece;
        this.currentHeight++;
        return true;
    }

    /**
     * Gets top piece
     * 
     * @return top piece | nll
     */
    public Piece getCurrentPiece() {
        if (currentHeight > 0) {
            return this.blocks[this.currentHeight - 1];
        } else {
            return null;
        }
    }

    /**
     * Get height of current Tower
     * 
     * @return height of tower
     */
    public int getAmountOfBlocks() {
        return this.currentHeight;
    }

    /**
     * Gets all pieces
     * 
     * @return all pieces on this field
     */
    public Piece[] getPieces() {
        return this.blocks;
    }

    /**
     * if top block is a dome
     * 
     * @return if top block is a dome
     */
    public boolean hasDome() {
        return this.hasDome;
    }

    /**
     * Used for debugging
     * 
     * @return String representation of this field
     */
    @Override
    public String toString() {
        return "{" + " figure='" + getPlayer() + "'" + "}";
    }

}
