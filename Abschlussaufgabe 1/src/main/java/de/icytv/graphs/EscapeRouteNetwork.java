package de.icytv.graphs;

import java.util.ArrayList;

public class EscapeRouteNetwork implements Comparable<EscapeRouteNetwork> {
    private Graph graph;
    private String id;

    public EscapeRouteNetwork(Graph graph, String id) {
        this.graph = graph;
        this.id = id;
    }

    public boolean isNameUsed(String name) {
        for(Node n: graph.getNodes()) {
            if(n.getId().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public Graph getGraph() {
        return graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int size() {
        return graph.size();
    }

    public ArrayList<Edge> getSortedEdges() {
        ArrayList<Edge> edges = this.graph.getEdges();
        edges.sort(null);

        return edges;
    }

    @Override
    public int compareTo(EscapeRouteNetwork o) {
        return this.size() - o.size();
    }

    
}
