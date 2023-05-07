package mstAlgorithms;

import graph.Edge;
import graph.Graph;
import priorityQueue.MinHeap;

/** Subclass of MSTAlgorithm. Uses Prim's algorithm to compute MST of the graph. */
public class PrimAlgorithm extends MSTAlgorithm {
    private int sourceVertex;
    private final Graph graph;



    /**
     * Constructor for PrimAlgorithm. Takes the graph
     * @param graph input graph
     * @param sourceVertex the first vertex of MST
     */
    public PrimAlgorithm(Graph graph, int sourceVertex) {
        super(graph);
        this.sourceVertex = sourceVertex;
        this.graph = graph;
    }

    /**
     * Compute minimum spanning tree for this graph using Prim's algorithm.
     * Add edges of MST using the addMSTEdge method inherited from the parent
     * class MSTAlgorithm.
     * */
    @Override
    public void computeMST() {
        int numNodes = graph.numNodes();
        int maxEdges = numNodes * (numNodes - 1) / 2;
        boolean[] visited = new boolean[numNodes];
        MinHeap minHeap = new MinHeap(maxEdges);

        minHeap.insert(new Edge(sourceVertex, sourceVertex, 0));

        while (!minHeap.isEmpty()) {
            Edge currentEdge = minHeap.removeMin();
            int u = currentEdge.getId2();

            if (visited[u]) {
                continue;
            }

            visited[u] = true;
            if (u != sourceVertex) {
                addMSTEdge(currentEdge);
            }

            Edge edge = graph.getFirstEdge(u);
            while (edge != null) {
                int v = edge.getId2();
                if (!visited[v]) {
                    minHeap.insert(edge);
                }
                edge = edge.next();
            }
        }
    }
}
