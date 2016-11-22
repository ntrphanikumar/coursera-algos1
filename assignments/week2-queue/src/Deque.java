import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first = null, last = null;
    private int size = 0;

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException("Cannot add null to addFirst");
        }
        first = new Node(item, first, null);
        if (last == null) {
            last = first;
        }
        size++;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException("Cannot add null to addLast");
        }
        last = new Node(item, null, last);
        if (first == null) {
            first = last;
        }
        size++;
    }

    public Item removeFirst() {
        if (first == null) {
            throw new NoSuchElementException();
        }
        Item item = first.item;
        first = first.next;
        if (first != null) {
            first.prev = null;
        } else {
            last = null;
        }
        size--;
        return item;
    }

    public Item removeLast() {
        if (last == null) {
            throw new NoSuchElementException();
        }
        Item item = last.item;
        last = last.prev;
        if (last != null) {
            last.next = null;
        } else {
            first = null;
        }
        size--;
        return item;
    }

    public Iterator<Item> iterator() {
        return new DequeueIterator();
    }

    private class Node {
        private Item item;
        private Node next, prev;

        Node(Item item, Node next, Node prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
            if (next != null) {
                next.prev = this;
            }
            if (prev != null) {
                prev.next = this;
            }
        }
    }

    private class DequeueIterator implements Iterator<Item> {
        private final Node dummy = Deque.this.new Node(null, null, null);
        private Node itrNode = dummy;

        @Override
        public boolean hasNext() {
            if (itrNode == dummy) {
                return first != null;
            }
            return itrNode.next != null;
        }

        @Override
        public Item next() {
            Node next = itrNode == dummy ? first : itrNode.next;
            if (next == null) {
                throw new NoSuchElementException();
            }
            itrNode = next;
            return next.item;
        }
    }
}
