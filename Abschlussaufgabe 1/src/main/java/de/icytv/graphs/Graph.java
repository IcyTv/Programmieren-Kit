package de.icytv.graphs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Graph
 */
public class Graph {
    private String id;
    private ArrayList<Edge> edges;
    private ArrayList<Node> nodes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public void setEdges(ArrayList<Edge> edges) {
        this.edges = edges;
    }

    public void addEdge(Edge edge) {
        this.edges.add(edge);

        Node startNode = edge.getStartNode();
        if (this.nodes.indexOf(startNode) < 0) {
            this.nodes.add(startNode);
        }

        Node endNode = edge.getEndNode();
        if (this.nodes.indexOf(endNode) < 0) {
            this.nodes.add(endNode);
        }
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public void setNodes(ArrayList<Node> nodes) {
        this.nodes = nodes;
    }

    public int size() {
        return this.nodes.size();
    }

    public Graph() {
        this.edges = new ArrayList<Edge>();
        this.nodes = new ArrayList<Node>();
    }

}