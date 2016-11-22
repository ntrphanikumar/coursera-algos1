import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Subset {
    public static void main(String[] args) {
        int subsets = Integer.parseInt(args[0]);
        RandomizedQueue<String> deque = new RandomizedQueue<>();
        for (String str : StdIn.readAllStrings()) {
            deque.enqueue(str);
        }
        for (int i = 0; i < subsets; i++) {
            StdOut.println(deque.dequeue());
        }
    }
}
