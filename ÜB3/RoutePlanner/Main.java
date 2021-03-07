/**
 * Implementation of Main as per Specification
 * 
 * @author Michael Finger
 * @version 1.0.0
 */
public class Main {
    /**
     * Main method, initializes a routeplanner and returns the number of desired
     * paths into System.out, as per Exercise Specification
     * 
     * @param args Usage [path to graph, starting city, end city, path length]
     */
    public static void main(String[] args) {
        RoutePlanner planner = RoutePlanner.fromStringArray(args);
        System.out.println(planner.getNumOfPaths());
    }
}
