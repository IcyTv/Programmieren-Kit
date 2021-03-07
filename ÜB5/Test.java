import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;

import de.icytv.santorini.Main;
import edu.kit.informatik.Terminal;

public class Test {
    public static void main(String[] args) throws FileNotFoundException {
        // Create a stream to hold the output
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        // IMPORTANT: Save the old System.out!
        PrintStream old = System.out;
        // Tell Java to use your special stream
        System.setOut(ps);
        Terminal.IN = new BufferedReader(new FileReader(new File("./test.txt")));
        try {
            Main.main(new String[] { "yellow;1;1", "red;3;2", "blue;1;2", "green;2;2" });

        } catch(Exception e) {
            System.out.println(e);
        }
        // Put things back
        System.out.flush();
        System.setOut(old);
        System.out.println(baos);
    }
}
