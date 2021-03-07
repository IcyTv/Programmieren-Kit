package de.icytv.graphs;

public class Node {
    private String id;
    private boolean discovered = false;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isDiscovered() {
        return discovered;
    }

    public void setDiscovered(boolean discovered) {
        this.discovered = discovered;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Node)) {
            return false;
        } else {
            return ((Node)obj).getId().equals(this.id);
        }
    }

    public Node(String id) {
        this.id = id;
    }
    
}
