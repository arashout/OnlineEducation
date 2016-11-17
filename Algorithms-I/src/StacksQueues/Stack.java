package StacksQueues;

import edu.princeton.cs.algs4.StdOut;

/**
 * Created by arash_000 on 2016-11-16.
 */
public class Stack<Item> {
    private int index;
    private int size;
    private Item[] stack;

    public Stack() {
        size = 1;
        index = -1;
        stack = (Item[]) new Object[size];
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(4);
        stack.push(3);
        stack.push(3);
        stack.stringRep();
    }

    public void push(Item item) {
        index++;
        /* TODO:
        * Need to resize array when index is 1/2 size*/
        if (index > size / 2) {
            size *= 2;
            Item[] oldStack = stack.clone();
            stack = (Item[]) new Object[size];
            for (int i = 0; i < oldStack.length; i++) {
                stack[i] = oldStack[i];
            }
        }
        stack[index] = item;
    }

    public Item pop() {
        return stack[index--];
    }

    public boolean isEmpty() {
        return index == -1;
    }

    public int size() {
        return index + 1;
    }

    public void stringRep() {
        for (int i = 0; i <= index; i++) {
            StdOut.print(stack[i].toString() + ' ');
        }
    }
}