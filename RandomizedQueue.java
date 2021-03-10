/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private static final int INIT_CAPACITY = 1;

    private Item[] queue;
    private int size;

    // Construct an empty RandomizedQueue
    public RandomizedQueue() {
        queue = (Item[]) new Object[INIT_CAPACITY];
    }

    // Is the RandomizedQueue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // Return the number of items on the RandomizedQueue
    public int size() {
        return size;
    }

    // Add the item
    public void enqueue(Item item) {
        if (item == null)
            throw new IllegalArgumentException("Cannot add null item to queue");
        if (size == queue.length) {
            resize(queue.length * 2);
        }
        queue[size++] = item;
    }

    // Remove and return a random item
    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException("Queue is empty");
        int idx = StdRandom.uniform(size);
        Item item = queue[idx];
        queue[idx] = null;
        size--;
        if (size > 0) {
            swap(queue, size, idx);
        }
        if (size > 0 && (size == queue.length / 4)) {
            resize(queue.length / 2);
        }
        return item;
    }

    // Return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException("Queue is empty");
        int idx = StdRandom.uniform(size);
        Item item = queue[idx];
        return item;
    }

    // Return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    private void resize(int capacity) {
        Item[] resized = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            resized[i] = queue[i];
        }
        queue = resized;
    }

    private void swap(Item[] items, int currIdx, int destinationIdx) {
        Item temp = items[currIdx];
        items[currIdx] = items[destinationIdx];
        items[destinationIdx] = temp;
    }

    private void printQueue() {
        StringBuilder sb = new StringBuilder();
        for (Item i : queue) {
            sb.append(i + " ");
        }
        StdOut.println(sb);
    }

    /**
     * Produces an iterator that generates values from randomly
     * selected indexes of array with <em>contiguous</em> values
     */
    private class RandomIterator implements Iterator<Item> {
        private int entryCount;
        private Item[] copy;

        public RandomIterator() {
            entryCount = size;
            copy = (Item[]) new Object[size];
            // Create a duplicate copy to manipulate as we iterate,
            // swapping positions of selected index with last non-null element
            // to avoid fragmentation (i.e., gaps in array)
            System.arraycopy(queue, 0, copy, 0, size);
        }

        public boolean hasNext() {
            return entryCount > 0;
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException("Item does not exist");
            int idx = StdRandom.uniform(entryCount--);
            Item item = copy[idx];
            copy[idx] = null;
            swap(copy, idx, entryCount);
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException("Cannot remove item from queue.");
        }
    }
    public static void main(String[] args) {
        RandomizedQueue<Integer> randQ = new RandomizedQueue<>();
        RandomizedQueue<Integer> randQ2 = new RandomizedQueue<>();
        Integer[] entries = {3}; // {3, 0, 2, 5, 1, 4};
        for (int e : entries) {
            randQ.enqueue(e);
            randQ2.enqueue(e);
        }
        randQ.printQueue();
        randQ.sample();
        StdOut.println("----- First iterator -----");
        Iterator<Integer> i = randQ.iterator();
        while (i.hasNext()) {
            StdOut.println(i.next());
        }
        StdOut.println("----- Second iterator -----");
        Iterator<Integer> i2 = randQ2.iterator();
        boolean firstRun = true;
        while (i2.hasNext()) {
            if (firstRun) {
                // i2.remove();
                firstRun = false;
            }
            StdOut.println(i2.next());
        }
        // Remove entries
        int operations = 3;
        while (Math.min(operations, randQ.size()) > 0) {
            randQ.dequeue();
            randQ.printQueue();
            operations--;
        }
        // randQ.enqueue(3);
        // randQ.dequeue();
        // randQ.enqueue(4);
        StdOut.println("IsEmpty? " + randQ.isEmpty());
        StdOut.println("Size: " + randQ.size);
    }
}
