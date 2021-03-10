/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        // StdOut.println("Value of 'k': " + k);
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        String s;
        while (!StdIn.isEmpty()) {
            s = StdIn.readString();
            // StdOut.println(s);
            queue.enqueue(s);
        }
        // StdOut.println("Count of entries: " + queue.size());
        for (int i = 0; i < k; i++) {
            StdOut.println(queue.dequeue());
        }
        // StdOut.println("Count after removal: " + queue.size());
    }
}
