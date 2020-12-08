/**
 * Main Class.
 * 
 * @author Michael Finger
 * @version 1.0.0
 */
public class Main {
    /**
     * Main method, usage: Main {command} [arguments].
     * 
     * Commands:
     * <ul>
     * <li>isMagicSquare: tests magicality of a square (args: cell,cell;cell,cell,
     * out: [not magical | semimagic square | magic square])</li>
     * <li>showMagicNumbers: shows the sums from 0 to n (args: int n)</li>
     * <li>complement: shows a representation of the square (args:
     * cell,cell;cell,cell, out: nxn complementary square)</li>
     * </ul>
     * 
     * @param args
     */
    public static void main(String[] args) {
        MagicSquare square;
        switch (args[0]) {
            case "isMagicSquare?":
                square = MagicSquare.fromString(args[1]);
                if (!square.isSemiMagicSquare()) {
                    System.out.println("not magical");
                } else {
                    System.out.println(square.isMagicSquare(false) ? "magic square" : "semimagic square");
                }
                break;
            case "showMagicNumbers":
                System.out.println(MagicSquare.showMagicNumbers(Integer.parseInt(args[1])));
                break;
            case "complement":
                square = MagicSquare.fromString(args[1]);
                System.out.println(square.complement());
                break;
            default:
                System.err.println("Wrong usage");
                break;
        }
    }
}