package priorityQueue;

import graph.Edge;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MinHeapTest {

    private MinHeap minHeap;

    @BeforeEach
    public void setUp() {
        minHeap = new MinHeap(5);
    }

    @Test
    public void testInsert() {
        minHeap.insert(new Edge(0, 1, 5));
        minHeap.insert(new Edge(1, 2, 3));
        minHeap.insert(new Edge(2, 3, 8));

        assertEquals(3, minHeap.size);
    }

    @Test
    public void testIsEmpty() {
        assertTrue(minHeap.isEmpty());
        minHeap.insert(new Edge(0, 1, 5));
        assertTrue(!minHeap.isEmpty());
    }
}