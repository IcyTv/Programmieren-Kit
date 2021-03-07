package de.icytv.santorini;

/**
 * Thrown if played move was illegal
 * 
 * @author Michael Finger
 * @version 1.0.0
 */
public class IllegalMoveException extends Exception {

    /**
     * Generated Serial
     */
    private static final long serialVersionUID = -4092059483059673340L;

    /**
     * Constructor for Illegal Move Exception
     * 
     * @param message Why was the move invalid
     */
    public IllegalMoveException(String message) {
        super(message);
    }

}
