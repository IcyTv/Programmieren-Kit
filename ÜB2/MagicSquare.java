/**
 * Implementation of the Magic Square.
 * 
 * @author Michael Finger
 * @version 1.0.0
 */
public class MagicSquare {
    private final int[][] square;
    private int totalSum = -1;

    /**
     * Constructor for Magic Square.
     * 
     * @param square int[n][n] array
     */
    public MagicSquare(int[][] square) {
        this.square = square;
        int i = square.length;
        this.totalSum = ((i * i * i + i) / 2);
    }

    /**
     * Test if this Magic Square is a semimagic square <b>ATTENTION: </b> This
     * doesn't test if the square is exclusively semimagic.
     * 
     * @return if <code>this</code> is a semimagic square
     */
    public boolean isSemiMagicSquare() {
        for (int[] row : this.square) {
            int sum = 0;
            for (int cell : row) {
                sum += cell;
            }
            if (totalSum != sum) {
                return false;
            }
        }

        for (int i = 0; i < this.square.length; i++) {
            int sum = 0;
            for (int j = 0; j < this.square.length; j++) {
                sum += this.square[j][i];
            }
            if (totalSum != sum) {
                return false;
            }
        }

        return true;
    }

    /**
     * Tests if a Magic square is actually a magic square.
     * 
     * @return if <code>this</code> is a Magic square
     */
    public boolean isMagicSquare() {
        return isMagicSquare(true);
    }

    /**
     * Tests if a Magic square is actually a magic square.
     * 
     * @param testSemimagic used for performance reasons, <code>true</code> disables
     *                      the semimagic test
     * @return if <code>this</code> is a Magic square
     */
    public boolean isMagicSquare(boolean testSemimagic) {
        if (testSemimagic && !isSemiMagicSquare()) {
            return false;
        }

        int diagSum1 = 0;
        int diagSum2 = 0;
        int l = this.square.length - 1;

        for (int i = 0; i < this.square.length; i++) {
            diagSum1 += this.square[i][i];
            diagSum2 += this.square[l - i][l - i];
        }

        if (diagSum1 != totalSum || diagSum2 != totalSum) {
            return false;
        }

        return true;
    }

    /**
     * Generates the complementary Magic Square. Does not edit the existing square!
     * 
     * @return the complementary Magic Square
     */
    public MagicSquare complement() {
        int[][] square = new int[this.square.length][this.square.length];
        int n = this.square.length;

        for (int i = 0; i < square.length; i++) {
            for (int j = 0; j < square.length; j++) {
                square[i][j] = -this.square[i][j] + n * n + 1;
            }
        }

        return new MagicSquare(square);
    }

    /**
     * Returns an nxn-Formatting of the Square as a String.
     * 
     * @return String representation of the magic square
     */
    @Override
    public String toString() {
        String ret = "";
        for (int[] row : this.square) {
            for (int cell : row) {
                ret += cell + " ";
            }
            ret = ret.substring(0, ret.length() - 1) + "\n";
        }
        return ret.substring(0, ret.length() - 1);
    }

    // Static methods

    /**
     * Factory Method: Input format: cell,cell;cell,cell creates a 2x2 square with
     * your numbers.
     * 
     * 
     * @param s String to create square from
     * @return Magic Square
     */
    public static MagicSquare fromString(String s) {
        String[] rows = s.split(";");

        int[][] columns = new int[rows.length][rows.length];

        int i = 0;
        for (String column : rows) {
            String[] cells = column.split(",");
            int j = 0;
            for (String c : cells) {
                columns[i][j] = Integer.parseInt(c);
                j++;
            }
            i++;
        }

        return new MagicSquare(columns);
    }

    /**
     * Creates Magic numbers from n=1 to n=k.
     * 
     * @param k Top boundary
     * @return Comma seperated Magic Numbers
     */
    public static String showMagicNumbers(int k) {
        String magicNumbers = "";
        for (int i = 1; i <= k; i++) {
            magicNumbers += ((i * i * i + i) / 2) + ",";
        }

        return magicNumbers.substring(0, magicNumbers.length() - 1);
    }
}
