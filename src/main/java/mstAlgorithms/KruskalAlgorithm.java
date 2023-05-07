package mstAlgorithms;

import graph.Edge;
import graph.Graph;
import sets.DisjointSets;

import java.util.ArrayList;
import java.util.Arrays;

/** Subclass of MSTAlgorithm. Computes MST of the graph using Kruskal's algorithm. */
public class KruskalAlgorithm extends MSTAlgorithm {

    private final Graph graph;

    /**
     * Constructor for KruskalAlgorithm. Takes the graph
     * @param graph input graph
     */
    public KruskalAlgorithm(Graph graph) {
        super(graph);
        this.graph = graph;
    }

    /**
     * Compute minimum spanning tree for this graph.
     * Add edges of MST using the addMSTEdge method inherited from the parent
     * class MSTAlgorithm.
     * Should use Kruskal's algorithm and DisjointSets class.
     */
    @Override
    public void computeMST() {
        int numNodes = graph.numNodes();

        DisjointSets ds = new DisjointSets();
        ds.createSets(numNodes);

        ArrayList<Edge> edges = getEdgeList(numNodes);

        Edge[] sortedEdges = edges.toArray(new Edge[0]);
        Arrays.sort(sortedEdges, (edge1, edge2) -> edge1.getCost() - edge2.getCost());

        int mstSize = 0;
        for (Edge edge : sortedEdges) {
            int id1 = edge.getId1();
            int id2 = edge.getId2();

            int setId1 = ds.find(id1);
            int setId2 = ds.find(id2);

            if (setId1 != setId2) {
                addMSTEdge(edge);
                ds.union(setId1, setId2);
                mstSize++;

                if (mstSize == numNodes - 1) { // MST should have numNodes-1 edges
                    break;
                }
            }
        }
    }

    private ArrayList<Edge> getEdgeList(int numNodes) {
        ArrayList<Edge> edges = new ArrayList<>();
        for (int i = 0; i < numNodes; i++) {
            Edge e = graph.getFirstEdge(i);
            while (e != null) {
                edges.add(e);
                e = e.next();
            }
        }
        return edges;
    }
}

