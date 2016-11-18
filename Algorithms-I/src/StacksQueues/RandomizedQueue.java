package StacksQueues;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by arash on 2016-11-16.
 */
//implements Iterable<Item>
public class RandomizedQueue<Item> {
    private int index;
    private Item[] queue;
    public RandomizedQueue(){
        index = -1;
        queue = (Item[]) new Object[1];
    }
    public boolean isEmpty(){
        return index == -1;
    }
    public int size(){
        return index + 1;
    }
    public void enqueue(Item item){
        index++;
        if (index > queue.length / 2) {
            Item[] oldQueue = queue.clone();
            queue = (Item[]) new Object[queue.length*2];
            for (int i = 0; i < oldQueue.length; i++) {
                queue[i] = oldQueue[i];
            }
        }
        queue[index] = item;
    }
    /*
    public Item dequeue(){
        //TODO remove random element + shrink array when 25% or 100% full
        int j = StdRandom.uniform(0, index);
        return 0;
    }
    public Item sample(){
        return queue[StdRandom.uniform(0,index)];
    }
    public Iterator<Item> iterator(){

    }
    */
    private void shrinkArray (Item[] arr){

    }
    private void expandAray(Item[] arr){

    }
    public static void main(String[] args){

    }
}
