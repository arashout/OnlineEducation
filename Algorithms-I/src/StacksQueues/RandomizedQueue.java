package StacksQueues;

import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by arash on 2016-11-16.
 */
//implements Iterable<Item>
public class RandomizedQueue<Item> {
    private int n;
    private Item[] queue;
    private int countEmpty;
    public RandomizedQueue(){
        countEmpty = 0;
        n = -1;
        queue = (Item[]) new Object[1];
    }
    public boolean isEmpty(){
        return n == -1;
    }
    public int size(){
        return n + 1;
    }
    public void enqueue(Item item){
        n++;
        if (n > queue.length / 2) {
            queue = doubleArray(queue);
        }
        queue[n] = item;
    }

    public Item dequeue(){
        //TODO throw error if size is 0
        int position = StdRandom.uniform(0,size());
        Item item;
        do {
            position = StdRandom.uniform(0,size());
            item = queue[position];
        }while(item == null);
        queue[position] = null;
        countEmpty++;
        n--;

        if(countEmpty > n * 0.75){
            queue = shrinkArray(queue, n/4);
            countEmpty = 0;
        }
        return item;
    }

    public Item sample(){
        //TODO throw error if size is 0
        int position = StdRandom.uniform(0,size());
        Item item;
        do {
            position = StdRandom.uniform(0,size());
            item = queue[position];
        }while(item == null);
        return item;
    }
    /*
    public Iterator<Item> iterator(){

    }
    */
    private Item[] shrinkArray (Item[] arr, int newSize){
        Item[] newArr = (Item[]) new Object[newSize];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] != null){
                newArr[index] = arr[i];
                index++;
            }
        }
        return newArr;
    }
    private Item[] doubleArray(Item[] arr){
        Item[] oldArr = arr.clone();
        arr = (Item[]) new Object[arr.length*2];
        for (int i = 0; i < oldArr.length; i++) {
            arr[i] = oldArr[i];
        }
        return arr;
    }
    public static void main(String[] args){
        RandomizedQueue<Integer> q = new RandomizedQueue<Integer>();
        for (int i = 0; i < 8; i++) {
            q.enqueue(i);
        }
    }
}
