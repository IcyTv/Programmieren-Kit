package de.icytv;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.icytv.graphs.Edge;
import de.icytv.graphs.EdmondsKarpAlgorithm;
import de.icytv.graphs.EscapeRouteNetwork;
import de.icytv.graphs.Graph;
import de.icytv.graphs.Node;
import edu.kit.informatik.Terminal;

public final class CommandHandler {
    private static Pattern edgeId = Pattern.compile("([a-z]{1,6})");
    private static HashMap<String, EscapeRouteNetwork> escapeRouteNetworks = new HashMap<String, EscapeRouteNetwork>();

    private static HashMap<String, Integer> flows = new HashMap<String, Integer>();

    public static void handle(String command) {
        String[] split = command.split(" ", 2);
        System.out.println(Arrays.toString(split));

        if (split.length == 1) {
            split = new String[] { split[0], "" };
        }

        switch (split[0]) {
        case "add":
            add(split[1]);
            break;
        case "list":
            list(split[1]);
            break;
        case "print":
            print(split[1]);
            break;
        case "flow":
            flow(split[1]);
            break;
        default:
            Terminal.printError("Not a valid command");
        }
    }

    private static Edge getEdge(String edge) {
        System.out.println("Getting edge " + edge);
        Matcher edgeIdMatcher = edgeId.matcher(edge);
        // while (edgeIdMatcher.find()) {
        // // Get the matching string
        // String match = edgeIdMatcher.group();
        // System.out.println(match);
        // }
        System.out.println(edgeIdMatcher);
        edgeIdMatcher.find();
        String startId = edgeIdMatcher.group();
        edgeIdMatcher.find();
        String endId = edgeIdMatcher.group();

        int capacity = Integer.parseInt(edge.replace(startId, "").replace(endId, ""));

        Node startNode = new Node(startId);
        Node endNode = new Node(endId);

        Edge e = new Edge(startNode, endNode, capacity);
        return e;

    }

    public static void add(String arguments) {
        // TODO regex
        String[] splitArguments = arguments.split(" ");
        String id = splitArguments[0];

        EscapeRouteNetwork network = escapeRouteNetworks.get(id);

        if (network == null) {
            String[] edges = splitArguments[1].split(";");

            Graph g = new Graph();

            for (String edge : edges) {
                Edge e = getEdge(edge);
                g.addEdge(e);
            }

            network = new EscapeRouteNetwork(g, id);

            escapeRouteNetworks.put(id, network);
        } else {
            Edge e = getEdge(splitArguments[1]);
            network.getGraph().addEdge(e);
        }
    }

    public static void list(String argument) {
        if (argument.length() == 0) {
            ArrayList<EscapeRouteNetwork> networks = new ArrayList<EscapeRouteNetwork>(escapeRouteNetworks.values());

            if (networks.size() == 0) {
                Terminal.printLine("EMPTY");
                return;
            }

            networks.sort(null);

            for (EscapeRouteNetwork network : networks) {
                Terminal.printLine(network.getId() + " " + network.size());
            }
        } else {
            // TODO regex
            ArrayList<Map.Entry<String, Integer>> flowRates = new ArrayList<Map.Entry<String, Integer>>();

            for (Map.Entry<String, Integer> entry : flows.entrySet()) {

                if (entry.getKey().startsWith(argument)) {
                    flowRates.add(entry);
                }
            }

            if (flowRates.size() == 0) {
                Terminal.printLine("EMPTY");
                return;
            }

            flowRates.sort(Entry.comparingByValue());

            for (Map.Entry<String, Integer> entry : flowRates) {
                String[] split = entry.getKey().split(";"); // Network ID, Start node ID, End node ID
                Terminal.printLine(split[1] + entry.getValue() + split[2]);
            }
        }
    }

    public static void print(String argument) {
        // TODO regex (in class!)
        EscapeRouteNetwork network = escapeRouteNetworks.get(argument);
        for (Edge e : network.getSortedEdges()) {
            Terminal.printLine(e);
        }
    }

    public static void flow(String arguments) {
        // TODO Regex
        String[] split = arguments.split(" ");
        String networkId = split[0];
        String startNode = split[1];
        String endNode = split[2];

        if (flows.containsKey(networkId + ";" + startNode + ";" + endNode)) {
            Terminal.printLine(flows.get(networkId + ";" + startNode + ";" + endNode));
        } else {
            EscapeRouteNetwork network = escapeRouteNetworks.get(networkId);
            int flow = EdmondsKarpAlgorithm.run(network, startNode, endNode);
            flows.put(networkId + ";" + startNode + ";" + endNode, flow);
            Terminal.printLine(flow);
        }
    }
}
