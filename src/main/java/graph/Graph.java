package graph;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * A class that represents a graph: stores the array of city nodes, the
 * adjacency list, as well as the hash table that maps city names to node ids.
 * Nodes are cities (of type CityNode); edges connect them and the cost of each edge
 * is the distance between the cities.
 * Fill in code in this class. You may add additional methods and variables.
 * You are required to implement a MinHeap from scratch, instead of using Java's built in PriorityQueue.
 */
public class Graph {
    private CityNode[] nodes;
    private Edge[] adjacencyList;
    private int numEdges;

    /**
     * Constructor. Read graph info from the given file,
     * and create nodes and edges of the graph.
     *
     * @param filename name of the file that has nodes and edges
     */
    public Graph(String filename) {
        HashMap<String, Integer> cityNameToNodeIdMap = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            br.readLine(); // read "NODES"
            String line;
            int numNodes = Integer.parseInt(br.readLine().trim()); // read the number of nodes

            nodes = new CityNode[numNodes];
            adjacencyList = new Edge[numNodes];

            for (int i = 0; i < numNodes; i++) { // loop through each line that specifies a node
                line = br.readLine();
                StringTokenizer st = new StringTokenizer(line); // split line into tokens
                String cityName = st.nextToken();
                double x = Double.parseDouble(st.nextToken());
                double y = Double.parseDouble(st.nextToken());
                nodes[i] = new CityNode(cityName, x, y); // add node to nodes
                cityNameToNodeIdMap.put(cityName, i); // add node to hashmap
            }

            br.readLine(); // read "ARCS"
            while ((line = br.readLine()) != null) { // loop through each line that specifies an arc (edge)
                StringTokenizer st = new StringTokenizer(line);
                String city1 = st.nextToken();
                String city2 = st.nextToken();
                int cost = Integer.parseInt(st.nextToken()); // the third argument is the cost

                int id1 = cityNameToNodeIdMap.get(city1);
                int id2 = cityNameToNodeIdMap.get(city2);

                Edge edge = new Edge(id1, id2, cost);
                edge.setNext(adjacencyList[id1]);
                adjacencyList[id1] = edge;

                Edge reverseEdge = new Edge(id2, id1, cost); // need to make each edge bidirectional
                reverseEdge.setNext(adjacencyList[id2]);
                adjacencyList[id2] = reverseEdge;

                numEdges += 2;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Return the number of nodes in the graph
     *
     * @return number of nodes
     */
    public int numNodes() {
        return nodes.length;
    }

    /**
     * Return the head of the linked list that contains all edges outgoing
     * from nodeId
     *
     * @param nodeId id of the node
     * @return head of the linked list of Edges
     */
    public Edge getFirstEdge(int nodeId) {
        return adjacencyList[nodeId];
    }

    /**
     * Return the edges of the graph as a 2D array of points.
     * Called from GUIApp to display the edges of the graph.
     *
     * @return a 2D array of Points.
     * For each edge, we store an array of two Points, v1 and v2.
     * v1 is the source vertex for this edge, v2 is the destination vertex.
     * This info can be obtained from the adjacency list
     */
    public Point[][] getEdges() {
        if (adjacencyList == null || adjacencyList.length == 0) {
            System.out.println("Adjacency list is empty. Load the graph first.");
            return null;
        }
        Point[][] edges2D = new Point[numEdges][2];
        int idx = 0;
        for (Edge edge : adjacencyList) {
            for (Edge tmp = edge; tmp != null; tmp = tmp.next(), idx++) {
                edges2D[idx][0] = nodes[tmp.getId1()].getLocation();
                edges2D[idx][1] = nodes[tmp.getId2()].getLocation();
            }
        }

        return edges2D;
    }

    /**
     * Get the nodes of the graph as a 1D array of Points.
     * Used in GUIApp to display the nodes of the graph.
     *
     * @return a list of Points that correspond to nodes of the graph.
     */
    public Point[] getNodes() {
        if (nodes == null) {
            System.out.println("Array of nodes is empty. Load the graph first.");
            return null;
        }
        Point[] nodes = new Point[this.nodes.length];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = this.nodes[i].getLocation();
        }

        return nodes;
    }

    /**
     * Used in GUIApp to display the names of the cities.
     *
     * @return the list that contains the names of cities (that correspond
     * to the nodes of the graph)
     */
    public String[] getCities() {
        if (nodes == null) {
            return null;
        }
        String[] labels = new String[nodes.length];
        for (int i = 0; i < nodes.length; i++) {
            labels[i] = nodes[i].getCity();
        }

        return labels;

    }

    /**
     * Return the CityNode for the given nodeId
     *
     * @param nodeId id of the node
     * @return CityNode
     */
    public CityNode getNode(int nodeId) {
        return nodes[nodeId];
    }

}
