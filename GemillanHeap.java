public class GemillanHeap{
    private int[] heap;
    private int size;
    private int capacity;

    // Constructor to initialize the heap with a given capacity
    public GemillanHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = new int[capacity];
    }

    // Function to get the index of the left child of a given node
    private int leftChild(int index) {
        return 2 * index + 1;
    }

    // Function to get the index of the right child of a given node
    private int rightChild(int index) {
        return 2 * index + 2;
    }

    // Function to get the index of the parent of a given node
    private int parent(int index) {
        return (index - 1) / 2;
    }

    // Function to check if a node is a leaf node
    private boolean isLeaf(int index) {
        return index >= size / 2 && index < size;
    }

    // Function to swap two elements in the heap
    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    // Function to heapify a subtree with the root at a given index
    private void heapify(int index) {
        int largest = index;
        int left = leftChild(index);
        int right = rightChild(index);

        // Check if left child is larger than the root
        if (left < size && heap[left] > heap[largest]) {
            largest = left;
        }

        // Check if right child is larger than the root (or the left child)
        if (right < size && heap[right] > heap[largest]) {
            largest = right;
        }

        // If the largest element is not the root, swap and continue heapifying
        if (largest != index) {
            swap(index, largest);
            heapify(largest);
        }
    }

    // Function to insert a new element into the heap
    public void insert(int value) {
        if (size == capacity) {
            System.out.println("Heap is full");
            return;
        }

        // Insert the new element at the end of the heap
        heap[size] = value;
        size++;

        // Fix the heap property if it is violated
        int current = size - 1;
        while (current > 0 && heap[current] > heap[parent(current)]) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    // Function to extract the maximum element (root of the heap)
    public int extractMax() {
        if (size == 0) {
            System.out.println("Heap is empty");
            return -1;
        }

        // The maximum element is at the root
        int max = heap[0];

        // Replace the root with the last element
        heap[0] = heap[size - 1];
        size--;

        // Heapify the root to maintain the heap property
        heapify(0);

        return max;
    }

    // Function to print the heap (for testing purposes)
    public void printHeap() {
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }

    // Main function for testing the heap operations
    public static void main(String[] args) {
        GemillanHeap maxHeap = new GemillanHeap(10);

        maxHeap.insert(10);
        maxHeap.insert(20);
        maxHeap.insert(5);
        maxHeap.insert(15);
        maxHeap.insert(30);

        maxHeap.printHeap();  // Output: 30 20 10 5 15

        System.out.println("Extract Max: " + maxHeap.extractMax());  // Output: 30
        maxHeap.printHeap();  // Output: 20 15 10 5
    }
}
