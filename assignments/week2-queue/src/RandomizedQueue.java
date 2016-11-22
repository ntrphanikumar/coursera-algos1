import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private final Set<WrappedItem> set = new HashSet<>();

    public boolean isEmpty() {
        return set.isEmpty();
    }

    public int size() {
        return set.size();
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        set.add(new WrappedItem(item));
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        WrappedItem wi = set.iterator().next();
        set.remove(wi);
        return wi.item;
    }

    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return set.iterator().next().item;
    }

    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private final Iterator<WrappedItem> itr = set.iterator();

            @Override
            public boolean hasNext() {
                return itr.hasNext();
            }

            @Override
            public Item next() {
                return itr.next().item;
            }
        };
    }

    private class WrappedItem {
        private Item item;

        WrappedItem(Item item) {
            this.item = item;
        }

        @Override
        public String toString() {
            return item.toString();
        }
    }

    @Override
    public String toString() {
        return set.toString();
    }

    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        rq.enqueue("A");
        rq.enqueue("B");
        rq.enqueue("C");
        while (true) {
            System.out.println(rq);
        }
    }
}