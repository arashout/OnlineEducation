package StacksQueues;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.Scanner;

/**
 * Created by arash on 2016-11-16.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] queue;
    private int N;
    private int capacity;

    public RandomizedQueue() {
        // construct an empty randomized queue
        N = 0; //num items in q
        capacity = 1; //Capacity of array
        queue = (Item[]) new Object[capacity];
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> q = new RandomizedQueue<Integer>();

        Scanner input = new Scanner(System.in);
        int n;
        int num;
        while (true) {
            n = input.nextInt();
            switch (n) {
                case 1:
                    num = StdRandom.uniform(-5, 5);
                    q.enqueue(num);
                    StdOut.println(num);
                    StdOut.println("Current: " + q.toString());
                    break;
                case 2:
                    StdOut.println(q.dequeue());
                    StdOut.println("Current: " + q.toString());
                    break;
                default:
                    for (int item : q) {
                        StdOut.println(item);
                    }
                    break;
            }
            if (n == 0) {
                break;
            }
        }
    }

    public boolean isEmpty() {
        // is the queue empty?
        return N == 0;
    }

    public int size() {
        // return the number of items on the queue
        return N;
    }

    public void enqueue(Item item) {
        // add the item
        if (item == null) throw new java.lang.NullPointerException();
        if (N + 1 > capacity) {//If the size is bigger than the capacity
            queue = resize(queue, capacity*2);
            capacity = queue.length;
        }
        queue[N] = item;
        N++;
    }

    public Item dequeue() {
        // delete and return a random item
        if (isEmpty()) throw new java.util.NoSuchElementException();
        int i = StdRandom.uniform(N);
        Item ret = queue[i]; //Save the element to return

        //Replace the extracted element with last non-null element in the array
        //Make the last element null
        //Effectively truncating the array
        N--;
        queue[i] = queue[N];
        queue[N] = null;
        if (capacity / 4 > N) {
            queue = resize(queue, capacity/2);
            capacity = queue.length;
        }
        return ret;
    }
    private Item[] resize(Item[] arr,  int newCapacity) {
        Item[] newArr = (Item[]) new Object[newCapacity];
        int index = 0;
        // Expand or shrink array
        int terminator;
        if(newCapacity > arr.length) terminator = arr.length;
        else terminator = newCapacity;
        for (int i = 0; i < terminator; i++) {
            newArr[index] = arr[i];
            index++;
        }
        return newArr;
    }

    public Item sample() {
        // return (but do not delete) a random item
        if (isEmpty()) throw new java.util.NoSuchElementException();
        return queue[StdRandom.uniform(N)];
    }

    /**
     * Returns a string representation of this queue.
     *
     * @return the sequence of items in FIFO order, separated by spaces
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    }

    public Iterator<Item> iterator() {
        // return an iterator over items in order from front to end
        return new ListIterator();
    }
    private class ListIterator implements Iterator<Item> {
        private int current = 0;
        private int[] shuffledIndexes = new int[N];

        // Since remove is not supported then we only need to shuffle once
        // In hasNext
        public boolean hasNext() {
            if (current == 0) {
                for (int i = 0; i < N; i++)
                    shuffledIndexes[i] = i;
                StdRandom.shuffle(shuffledIndexes);
            }
            return current < N;
        }

        public Item next() {
            if (current >= N || size() == 0) throw new java.util.NoSuchElementException();
            return queue[shuffledIndexes[current++]];
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
    }
}
