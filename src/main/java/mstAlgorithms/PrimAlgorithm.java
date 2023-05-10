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
        boolean[] visited = new boolean[numNodes];
        MinHeap minHeap = new MinHeap(numNodes);
        Edge[] parentEdges = new Edge[numNodes];

        for (int i = 0; i < numNodes; i++) {
            if (i == sourceVertex) {
                minHeap.insert(i, 0);
            } else {
                minHeap.insert(i, Integer.MAX_VALUE);
            }
        }

        while (!minHeap.isEmpty()) {
            int currentNode = minHeap.removeMin();

            if (visited[currentNode]) {
                continue;
            }

            visited[currentNode] = true;
            if (parentEdges[currentNode] != null) {
                addMSTEdge(parentEdges[currentNode]);
            }

            Edge edge = graph.getFirstEdge(currentNode);
            while (edge != null) {
                int neighbor = edge.getId2();

                if (!visited[neighbor]) {
                    int edgeCost = edge.getCost();
                    int currentPriority = minHeap.getPriority(neighbor);

                    if (edgeCost < currentPriority) {
                        minHeap.reduceKey(neighbor, edgeCost);
                        parentEdges[neighbor] = edge;
                    }
                }

                edge = edge.next();
            }
        }

    }
}