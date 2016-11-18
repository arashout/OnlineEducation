package StacksQueues;

/**
 * Created by arash on 2016-11-16.
 */
// implements Iterable<Item>
public class Deque<Item>{
    private class Node{
        Item item;
        Node next;
    }
    private int size;
    private Node first;

    public Deque(){
        size = 0;
        first = new Node();
    }
    public boolean isEmpty(){
        return (size == 0);
    }
    public int size(){
        return size;
    }
    public void addFirst(Item item){
        if(size > 0){
            Node temp = new Node();
            temp.item = first.item;
            temp.next = first.next;
            first.next = temp;
        }
        else{ // Empty Queue case
            first.next = null;
        }
        first.item = item;
        size ++;
    }

    public void addLast(Item item)   {
        if(size == 0){
            this.addFirst(item);
            return;
        }
        else {
            Node temp = new Node();
            temp = first;
            while(temp.next != null){
                temp = temp.next;
            }
            temp.item = item;
            temp.next = null;
        }

        size++;
    }
    /*
    public Item removeFirst()                // remove and return the item from the front
    public Item removeLast()                 // remove and return the item from the end
    public Iterator<Item> iterator()         // return an iterator over items in order from front to end
    */
    public static void main(String[] args){
        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(5);
        deque.addFirst(8);
        deque.addFirst(9);
        deque.addLast(10);
    }   // unit testing
}
