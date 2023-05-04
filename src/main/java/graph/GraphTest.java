package graph;

import org.junit.Test;
import static org.junit.Assert.*;

public class GraphTest {

    private final String testFile = "input/USA.txt";
    private final Graph graph = new Graph(testFile);

    @Test
    public void testNodesCreation() {
        // make sure nodes are created correctly
        String[] cityNames = {"SanFrancisco", "LosAngeles", "Orlando"};
        double[] firstCoordinate = {0.28, 0.62, 5.4};
        double[] secondCoordinate = {2.31, 1.6, 0.5};

        for (int i = 0; i < cityNames.length; i++) {
            CityNode expectedNode = new CityNode(cityNames[i], firstCoordinate[i], secondCoordinate[i]);
            System.out.println(cityNames[i]);
            CityNode actualNode = graph.getNode(i);
            assertEquals(expectedNode.getCity(), actualNode.getCity());
            assertEquals(expectedNode.getLocation(), actualNode.getLocation());
        }
    }

    @Test
    public void printCities() {
        String[] cities = graph.getCities();
        for (String city : cities) {
            System.out.println(city);
        }
        assertNotNull(cities);
        assertEquals(20, cities.length);
    }
    @Test
    public void testArcsCreation() {
        int[][] edges = {
                {0, 1, 320},
                {0, 6, 351},
                {0, 18, 380},
                {0, 12, 530},
                {0, 11, 850}
        };

        for (int[] edge : edges) {
            int city1 = edge[0];
            int city2 = edge[1];
            int expectedDistance = edge[2];

            boolean edgeFound = false;
            for (Edge e = graph.getFirstEdge(city1); e != null; e = e.next()) {
                if (e.getId2() == city2) {
                    edgeFound = true;
                    assertEquals(expectedDistance, e.getCost());
                    break;
                }
            }

            assertTrue(edgeFound);
        }
    }

}
