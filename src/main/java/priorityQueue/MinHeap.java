package priorityQueue;

import graph.Edge;

/** A priority queue: represented by the min heap.
 *  Used in Prim's algorithm. */
public class MinHeap {
    // FILL IN CODE
    private Edge[] heap;
    int size;

    public MinHeap(int capacity) {
        heap = new Edge[capacity];
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void insert(Edge edge) {
        int i = size;
        size++;

        while (i > 0 && edge.getCost() < heap[parent(i)].getCost()) {
            heap[i] = heap[parent(i)];
            i = parent(i);
        }
        heap[i] = edge;
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private void swap(int i, int j) {
        Edge temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    private void restoreMinHeap(int i) {
        int left = leftChild(i);
        int right = rightChild(i);
        int smallest = i;

        if (left < size && heap[left].getCost() < heap[smallest].getCost()) {
            smallest = left;
        }
        if (right < size && heap[right].getCost() < heap[smallest].getCost()) {
            smallest = right;
        }
        if (smallest != i) {
            swap(i, smallest);
            restoreMinHeap(smallest);
        }
    }
    public Edge removeMin() {
        if (isEmpty()) {
            return null;
        }

        Edge min = heap[0];
        size--;

        if (size > 0) {
            heap[0] = heap[size];
            restoreMinHeap(0);
        }
        return min;
    }

    private int leftChild(int i) {
        return 2 * i + 1;
    }

    private int rightChild(int i) {
        return 2 * i + 2;
    }


}
