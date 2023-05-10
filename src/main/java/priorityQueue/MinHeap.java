package priorityQueue;

/**
 * A priority queue: represented by the min heap.
 * Used in Prim's algorithm.
 */
public class MinHeap {
    private static class HeapElement {
        int nodeId;
        int priority;

        HeapElement(int nodeId, int priority) {
            this.nodeId = nodeId;
            this.priority = priority;
        }
    }

    private final HeapElement[] heap;
    private final int[] positions;
    private int size;

    public MinHeap(int capacity) {
        heap = new HeapElement[capacity];
        positions = new int[capacity];
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void insert(int nodeId, int priority) {
        int i = size;
        size++;

        while (i > 0 && priority < heap[parent(i)].priority) {
            heap[i] = heap[parent(i)];
            positions[heap[i].nodeId] = i;
            i = parent(i);
        }
        heap[i] = new HeapElement(nodeId, priority);
        positions[nodeId] = i;
    }

    public int removeMin() {
        if (isEmpty()) {
            return -1;
        }

        int minNodeId = heap[0].nodeId;
        size--;

        if (size > 0) {
            heap[0] = heap[size];
            positions[heap[0].nodeId] = 0;
            restore(0);
        }
        return minNodeId;
    }

    public int getPriority(int nodeId) {
        return heap[positions[nodeId]].priority;
    }

    public void reduceKey(int nodeId, int newPriority) {
        int index = positions[nodeId];
        while (index > 0 && newPriority < heap[parent(index)].priority) {
            heap[index] = heap[parent(index)];
            positions[heap[index].nodeId] = index;
            index = parent(index);
        }
        heap[index] = new HeapElement(nodeId, newPriority);
        positions[nodeId] = index;
    }

    private void restore(int i) {
        int left = leftChild(i);
        int right = rightChild(i);
        int smallest = i;

        if (left < size && heap[left].priority < heap[smallest].priority) {
            smallest = left;
        }
        if (right < size && heap[right].priority < heap[smallest].priority) {
            smallest = right;
        }
        if (smallest != i) {
            swap(i, smallest);
            restore(smallest);
        }
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private int leftChild(int i) {
        return 2 * i + 1;
    }

    private int rightChild(int i) {
        return 2 * i + 2;
    }

    private void swap(int i, int j) {
        HeapElement temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
        positions[heap[i].nodeId] = i;
        positions[heap[j].nodeId] = j;
    }
}
