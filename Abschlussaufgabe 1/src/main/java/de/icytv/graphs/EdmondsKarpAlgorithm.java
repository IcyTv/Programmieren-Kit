package de.icytv.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class EdmondsKarpAlgorithm {
    public static int run(EscapeRouteNetwork n, String startNodeId, String endNodeId) {
        int flow = 0;

        for(Edge edge: n.getGraph().getEdges()) {
            edge.setFlow(0);
        }

        Node start = new Node(startNodeId);
        Node end = new Node(endNodeId);

        Graph restcapacityNetwork = n.getGraph(); //TODO copy

        for(Edge e: restcapacityNetwork.getEdges()) {
            restcapacityNetwork.addEdge(e.reverse());
        }

        while(true) {
            HashMap<Node, Edge> path = new HashMap<Node, Edge>();

            LinkedList<Node> queue = new LinkedList<Node>();
            queue.addLast(start);

            while(!queue.isEmpty()) {
                Node current = queue.pollFirst();

                for(Edge e: restcapacityNetwork.getEdges()) {
                    if(e.getStartNode().equals(current) && path.get(e.getStartNode()) == null && !e.getEndNode().equals(start) && e.capacity() > e.flow()) {
                        path.put(current, e);
                        queue.add(e.getEndNode());
                    }
                }
            }

            if(path.get(end) == null) {
                break;
            }

            int pushFlow = Integer.MAX_VALUE;

            for(Edge e = path.get(end); e != null; e = path.get(e.getStartNode())) {
                pushFlow = Math.min(pushFlow, e.capacity() - e.flow());
            }

            for(Edge e = path.get(end); e != null; e = path.get(e.getStartNode())) {
                e.setFlow(e.flow() + pushFlow);

            }

            // ArrayList<Edge> edgePath = new ArrayList<Edge>();

            // Edge currentEdge = path.get(end);
            // while(currentEdge != null) {
            //     edgePath.add(currentEdge);
            //     currentEdge = path.get(currentEdge.getStartNode());
            // }
        }
        return flow;
    }


    /**
     *  <p>1  procedure BFS(G, root) is
 2      let Q be a queue
 3      label root as discovered
 4      Q.enqueue(root)
 5      while Q is not empty do
 6          v := Q.dequeue()
 7          if v is the goal then
 8              return v
 9          for all edges from v to w in G.adjacentEdges(v) do
10              if w is not labeled as discovered then
11                  label w as discovered
12                  Q.enqueue(w)</p>
     * @param g
     * @param start
     * @param end
     * @return
     */
    private static Node breadthFirstSearch(Graph g, Node start, Node end) {
        LinkedList<Node> queue = new LinkedList<Node>();
        queue.add(start);
        start.setDiscovered(true);
        while(!queue.isEmpty()) {
            Node v = queue.pollFirst();
            if(!v.equals(end)) {
                return v;
            } else {
                for(Edge e: g.getEdges()) {
                    if(e.getStartNode().equals(v) && !e.getEndNode().isDiscovered()) {
                        queue.addLast(e.getEndNode());
                    }
                }
            }
        }
        return null;
        // return new Path(null, 0);
    }
}
