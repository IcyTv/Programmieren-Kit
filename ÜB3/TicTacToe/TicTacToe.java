/**
 * Implementation as per Excercies of TicTacToe
 * 
 * @author Michael Finger
 * @version 1.0.1
 */
public class TicTacToe {
    /**
     * The size of the tic tac toe board. This sould not usually need to be changed,
     * but prevents future refactors IF the field ever needed to expand
     */
    public static final int BOARD_SIZE = 3;

    /**
     * The field is a 1D Array for memory scaling reasons: Let's say we had a
     * BOARD_SIZE of 10,000. On a 2D array we would have to store 10,000 extra
     * memory addresses (10,000 * 64 bits). plus the overhead for each one of the
     * 10,000 arrays. So it's a tradeoff between memory size and minimally more
     * clock cycles
     */
    private int[] field;
    private int[] moves;

    /**
     * Constructor for the {@link TicTacToe} class
     * 
     * @param moves Moves to test for the TicTacToe game. As per specification this
     *              needs to be 9 elements long. Maximum number needs to be &lt;
     *              BOARD_SIZE * BOARD_SIZE
     */
    public TicTacToe(int[] moves) {
        this.moves = moves;
        this.field = new int[BOARD_SIZE * BOARD_SIZE];
    }

    /**
     * Plays the game according to the passed moves.
     * 
     * @return Winner of the game
     */
    public String getWinner() {
        for (int round = 0; round < moves.length; round++) { // Uses moves.length to facilitate easy expansion
            play(round);
            if (round >= 2 && isDone()) { // round count starts from 0: round &gt;= 3 and we only need to be checking
                                          // after round 3 (maybe later, but 3 is a good starting point)
                return "P" + (round % 2 + 1) + " wins " + (round + 1);
            }
        }
        return "draw";
    }

    /**
     * Plays the round
     * 
     * @param round the round number to be played. Cannot
     */
    public void play(int round) {
        field[moves[round]] = round % 2 + 1;
    }

    /**
     * Checks if game is done
     * 
     * @return true if game is done, false if not
     */
    public boolean isDone() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            int playerRow = -1;
            int playerColumn = -1;

            for (int j = 0; j < BOARD_SIZE; j++) {
                if (playerRow == -1) {
                    playerRow = field[i * BOARD_SIZE + j];
                } else if (playerRow != field[i * BOARD_SIZE + j]) {
                    playerRow = -2; // Prevents last win check
                } else if (j == BOARD_SIZE - 1 && playerRow == field[i * BOARD_SIZE + j] && playerRow != 0) {
                    return true;
                }

                if (playerColumn == -1) {
                    playerColumn = field[j * BOARD_SIZE + i];
                } else if (playerColumn != field[j * BOARD_SIZE + i]) {
                    playerColumn = -2;
                } else if (j == BOARD_SIZE - 1 && playerColumn == field[j * BOARD_SIZE + i] && playerColumn != 0) {
                    return true;
                }
            }

            int playerDiagonal1 = -1; // Top left to bottom right diagonal player
            int playerDiagonal2 = -1; // Top right to bottom left diagonal player

            if (playerDiagonal1 == -1) {
                playerDiagonal1 = field[i * BOARD_SIZE + i];
            } else if (playerDiagonal1 != field[i * BOARD_SIZE + i]) {
                playerDiagonal1 = -2;
            } else if (i == BOARD_SIZE - 1 && playerDiagonal1 == field[i * BOARD_SIZE + i] && playerDiagonal1 != 0) {
                return true;
            }

            if (playerDiagonal2 == -1) {
                playerDiagonal2 = field[(BOARD_SIZE - i - 1) * BOARD_SIZE + (BOARD_SIZE - i - 1)];
            } else if (playerDiagonal2 != field[(BOARD_SIZE - i - 1) * BOARD_SIZE + (BOARD_SIZE - i - 1)]) {
                playerDiagonal2 = -2;
            } else if (i == BOARD_SIZE - 1
                    && playerDiagonal2 == field[(BOARD_SIZE - i - 1) * BOARD_SIZE + (BOARD_SIZE - i - 1)]
                    && playerDiagonal2 != 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a String representation of the current board state
     * 
     * @return String representation of the current board state
     */
    @Override
    public String toString() {
        String ret = "";

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                ret += field[i * BOARD_SIZE + j];
            }
            ret += "\n";
        }
        return ret;
    }

    // Static methods

    /**
     * Factory Method for creating a TicTacToeInstance
     * 
     * @param movesString A string array representing the moves to be played. Has to
     *                    be integers with
     *                    <code>max &lt; BOARD_LENGHT * BOARD_LENGTH</code>
     * @return an instance of TicTacToe
     */
    public static TicTacToe fromString(String[] movesString) {
        int[] moves = new int[movesString.length];
        for (int i = 0; i < movesString.length; i++) {
            moves[i] = Integer.parseInt(movesString[i]);
        }

        return new TicTacToe(moves);
    }

}
