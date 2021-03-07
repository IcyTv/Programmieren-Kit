package de.icytv.graphs;

/**
 * Edge
 */
public class Edge implements Comparable<Edge> {
    private Node startNode;
    private Node endNode;
    private int flow;
    private int capacity;

    public Edge(Node startNode, Node endNode, int flow, int capacity) {
        this.startNode = startNode;
        this.endNode = endNode;
        this.flow = flow;

    }

    public Edge(Node startNode, Node endNode, int capacity) {
        this(startNode, endNode, 0, capacity);
    }

    public int capacity() {
        return capacity;
    }

    public int flow() {
        return flow;
    }

    public void setFlow(int flow) {
        this.flow = flow;
    }

    public int restCapacity() {
        return this.capacity() - this.flow();
    }

    public Node getStartNode() {
        return startNode;
    }

    public void setStartNode(Node startNode) {
        this.startNode = startNode;
    }

    public Node getEndNode() {
        return endNode;
    }

    public void setEndNode(Node endNode) {
        this.endNode = endNode;
    }

    public Edge copy() {
        return new Edge(this.startNode, this.endNode, this.flow, this.capacity);
    }    

    public Edge reverse() {
        return new Edge(this.endNode, this.startNode, 0, 0);
    }

    @Override
    public int compareTo(Edge o) {
        return this.getStartNode().getId().compareTo(o.getStartNode().getId()) 
            + this.getStartNode().getId().compareTo(o.getStartNode().getId());
    }

    @Override
    public String toString() {
        return this.startNode.getId() +  this.capacity +  this.getEndNode().getId();
    }
    
}