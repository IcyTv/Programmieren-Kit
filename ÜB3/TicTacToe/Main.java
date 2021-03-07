/**
 * Implementation of Main, as per exercise specifications
 * 
 * @version 1.0.0
 * @author Michael Finger
 */
public class Main {
    /**
     * Main method
     * 
     * @param args moves as per Excercise specification
     */
    public static void main(String[] args) {
        TicTacToe ticTacToe = TicTacToe.fromString(args);
        System.out.println(ticTacToe.getWinner());
    }
}
