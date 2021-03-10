/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private int n; // size of deque
    private Node first;
    private Node last;

    private class Node {
        private final Item value;
        private Node next;
        private Node prev;

        Node(Item data) {
            value = data;
        }
    }

    public Deque() {
        n = 0;
        first = null;
        last = null;
    }

    // Is the deque empty?
    public boolean isEmpty() {
        return first == null;
    }

    // Return the number of items on the deque
    public int size() {
        return n;
    }

    // Add the item to the front (i.e., beginning/start)
    public void addFirst(Item item) {
        if (item == null)
            throw new IllegalArgumentException("Cannot add null Item");

        Node newNode = new Node(item);
        if (isEmpty()) {
            first = newNode;
            last = first;
        } else {
            newNode.next = first;
            first.prev = newNode;
            first = newNode;
        }
        n++;
    }

    // Add the item to the back
    public void addLast(Item item) {
        if (item == null)
            throw new IllegalArgumentException("Cannot add null Item");
        Node newLast = new Node(item);
        if (last == null) {
            last = newLast;
            first = last;
        } else {
            newLast.prev = last;
            last.next = newLast;
            last = last.next;
        }
        n++;
    }

    // Remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException("No element found. Deque is empty");
        Node rmvFirst = first;
        first = first.next;
        if (first != null) {
            first.prev = null;
        }
        rmvFirst.next = null;
        n--;
        if (size() < 2) {
            last = first;
        }
        return rmvFirst.value;
    }

    // Remove and return the item from the back
    public Item removeLast() {
        if (isEmpty())
            throw new NoSuchElementException("No element found. Deque is empty");
        Node rmvLast = last;
        last = last.prev;
        if (last != null) {
            last.next = null;
        }
        n--;
        if (size() < 2) {
            first = last;
        }
        return rmvLast.value;
    }

    // Return an iterator over item in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private void printDeque() {
        Node curr = first;
        StringBuilder sb = new StringBuilder();
        while (curr != null) {
            sb.append(curr.value + " ");
            curr = curr.next;
        }
        StdOut.println("Deque: " + sb);
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;
        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Item item = current.value;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException("Cannot remove item from Deque");
        }
    }

    // Unit testing (required)
    public static void main(String[] args) {
        Deque<String> deque = new Deque<>();
        // deque.removeFirst();
        // deque.removeLast();
        while (!StdIn.isEmpty()) {
            String val = StdIn.readString();
            deque.addLast(val);
        }
        deque.printDeque();
        StdOut.println("Starting size: " + deque.size());
        StdOut.println("RemoveFirst: " + deque.removeFirst());
        StdOut.println("RemoveLast: " + deque.removeLast());
        deque.printDeque();
        StdOut.println("RemoveLast (again): " + deque.removeLast());
        StdOut.println("Size: " + deque.size());
        StdOut.println("Starting iterator...");
        Iterator<String> i = deque.iterator();
        while (i.hasNext())
        {
            String s = i.next();
            StdOut.print(s + " ");
            // if (Integer.parseInt(s) == 4) {
            //     i.remove();
            // }
        }
    }
}
