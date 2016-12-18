package StacksQueues;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by arash on 2016-11-16.
 */
public class Subset {
    public static void main(String[] args){
        int k = Integer.parseInt(args[0]);
        int n = 0;
        RandomizedQueue<String> q = new RandomizedQueue<>();
        while (!StdIn.isEmpty())
        {
            q.enqueue(StdIn.readString());
            n++;
        }
        for (int i = 0; i < k; i++) {
            StdOut.println(q.dequeue());
        }
    }
}