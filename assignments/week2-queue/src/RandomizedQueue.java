import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private int size = 0;
    private Item[] itemArr = (Item[]) new Object[4];

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        if (size < itemArr.length) {
            itemArr[size++] = item;
        }
        resizeIfNeeded();
    }

    private void resizeIfNeeded() {
        if ((size == itemArr.length || size < itemArr.length / 4) && size > 0) {
            Item[] newItemArr = (Item[]) new Object[size * 2];
            for (int idx = 0; idx < size; idx++) {
                newItemArr[idx] = itemArr[idx];
            }
            itemArr = newItemArr;
        }
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int idxToDeque = StdRandom.uniform(size);
        Item item = itemArr[idxToDeque];
        itemArr[idxToDeque] = itemArr[--size];
        itemArr[size] = null;
        resizeIfNeeded();
        return item;
    }

    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return itemArr[StdRandom.uniform(size)];
    }

    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private int nextIdx = 0;

            @Override
            public boolean hasNext() {
                return nextIdx < size;
            }

            @Override
            public Item next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return itemArr[nextIdx++];
            }
        };
    }

    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        rq.enqueue("A");
        rq.enqueue("B");
        rq.enqueue("C");
        rq.enqueue("D");
        rq.enqueue("E");
        rq.dequeue();
        rq.enqueue("F");
        rq.dequeue();
    }
}