package StacksQueues;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node<Item> first;    // beginning of queue
    private Node<Item> last;     // end of queue
    private int n;               // number of elements on queue

    /**
     * Initializes an empty queue.
     */
    public Deque() {
        n = 0;
    }

    /**
     * Unit tests the {@code StacksQueues.Deque} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
    }

    /**
     * Returns true if this queue is empty.
     *
     * @return {@code true} if this queue is empty; {@code false} otherwise
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * Returns the number of items in this queue.
     *
     * @return the number of items in this queue
     */
    public int size() {
        return n;
    }

    /**
     * Adds the item to start of queue
     *
     * @param item the item to add
     */
    public void addFirst(Item item) {
        if (item == null) throw new java.lang.NullPointerException();
        if (isEmpty()) {
            first = new Node<Item>();
            first.item = item;
            last = first;
        } else {
            Node<Item> oldFirst = first;
            first = new Node<Item>();
            first.item = item;
            first.prev = null;
            first.next = oldFirst;
            oldFirst.prev = first;
        }
        n++;
    }

    /**
     * Adds the item to this queue.
     *
     * @param item the item to add
     */
    public void addLast(Item item) {
        if (isEmpty()) {
            addFirst(item);
            return;
        }
        if (item == null) throw new java.lang.NullPointerException();
        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        last.prev = oldlast;
        last.next = null;
        oldlast.next = last;
        n++;
    }

    /**
     * Removes and returns element at the front.
     *
     * @return at the front
     * @throws NoSuchElementException if this queue is empty
     */
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("StacksQueues.Deque underflow");
        Item item = first.item;
        if (size() > 1) {
            first = first.next;
            first.prev = null;
        } else {
            first = null;
            last = first;
        }
        n--;
        return item;
    }

    /**
     * Removes and returns element at the end.
     *
     * @return at the end
     * @throws NoSuchElementException if this queue is empty
     */
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("StacksQueues.Deque underflow");
        Item item = last.item;
        if (size() == 1) {
            return removeFirst();
        } else {
            last = last.prev;
            last.next = null;
        }
        n--;
        return item;
    }

    /**
     * Returns a string representation of this queue.
     *
     * @return the sequence of items in FIFO order, separated by spaces
     */
    /*
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    }
    */
    /**
     * Returns an iterator that iterates over the items in this queue in FIFO order.
     *
     * @return an iterator that iterates over the items in this queue in FIFO order
     */
    public Iterator<Item> iterator() {
        return new ListIterator<Item>(first);
    }

    // helper linked list class
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
        private Node<Item> prev;
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}