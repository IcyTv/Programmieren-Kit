import edu.kit.informatik.Terminal;

/**
 * Implementation of RoutePlanner as per Specification
 * 
 * @author Michael Finger
 * @version 1.0.0
 */
public class RoutePlanner {
    private int[][] adjecancyMatrix;
    private int startCity;
    private int endCity;
    private int pathLength;

    /**
     * Constructor for RoutePlanner. Generates the adjecancy Matrix from a file
     * 
     * @param path       filepath for graph
     * @param startCity  number of the starting city
     * @param endCity    number of the end city
     * @param pathLength length of paths to search for
     */
    public RoutePlanner(String path, int startCity, int endCity, int pathLength) {
        this.adjecancyMatrix = this.getAdjacencyMatrix(Terminal.readFile(path));
        this.startCity = startCity;
        this.endCity = endCity;
        this.pathLength = pathLength;
    }

    /**
     * Get number of paths with length <code>this.pathLength</code>
     * 
     * @return Amount of paths
     */
    public int getNumOfPaths() {
        int[][] cAdjacencyMatrix = new int[adjecancyMatrix.length][adjecancyMatrix.length];
        for (int i = 0; i < pathLength; i++) {
            for (int x = 0; x < adjecancyMatrix.length; x++) {
                for (int y = 0; y < adjecancyMatrix.length; y++) {
                    cAdjacencyMatrix[x][y] = 0;
                    for (int k = 0; k < adjecancyMatrix.length; k++) {
                        cAdjacencyMatrix[x][y] += adjecancyMatrix[x][k] * adjecancyMatrix[k][y];
                    }
                }
            }
        }

        return cAdjacencyMatrix[startCity][endCity];
    }

    /**
     * Generates the adjacency Matrix
     * 
     * @param graphAsInt int[][] of the graph with int[][2]
     * @param max        the max value of a node
     * @return adjacency matrix as int[max][max]
     */
    private int[][] getAdjacencyMatrix(int[][] graphAsInt, int max) {

        int[][] adjacencyMatrix = new int[max][max];

        for (int i = 0; i < max; i++) {
            for (int j = 0; j < max; j++) {
                adjacencyMatrix[i][j] = 0;
                for (int[] edge : graphAsInt) {
                    if (edge[0] == i && edge[1] == j) {
                        adjacencyMatrix[i][j] = 1;
                    }
                }
            }
        }

        return adjacencyMatrix;
    }

    /**
     * Generates the adjecancy Matrix from a String[] where the String Array is of
     * format ["nodeA nodeB"]
     * 
     * @param graph edges of the graph as String[]
     * @return AdjacencyMatrix as int[][] with int[max node value + 1][max node
     *         value + 1], because nodes start as 0
     */
    private int[][] getAdjacencyMatrix(String[] graph) {

        int[][] graphAsInt = new int[graph.length][2];
        int max = -1;

        for (int i = 0; i < graph.length; i++) {
            String[] knoten = graph[i].split(" ");
            int knoten1 = Integer.parseInt(knoten[0]);
            int knoten2 = Integer.parseInt(knoten[1]);
            graphAsInt[i] = new int[]{knoten1, knoten2};
            max = Math.max(Math.max(knoten1, knoten2), max);
        }

        return getAdjacencyMatrix(graphAsInt, max + 1);
    }

    // Static methods

    /**
     * Factory method for Route Planner
     * 
     * @param arr Arguments for Route Planner constructor as String[]
     * @return Instance of RoutePlanner
     */
    static RoutePlanner fromStringArray(String[] arr) {
        return new RoutePlanner(arr[0], Integer.parseInt(arr[1]), Integer.parseInt(arr[2]), Integer.parseInt(arr[3]));
    }
}
