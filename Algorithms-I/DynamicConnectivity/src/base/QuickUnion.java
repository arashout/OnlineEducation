package base;

import edu.princeton.cs.algs4.StdOut;

/**
 * Created by arash on 2016-09-04.
 */
public class QuickUnion {
    private int[] forestArray;
    private int[] sz; //Array that will maintain the size of trees

    public QuickUnion(int N){
        forestArray = new int[N];
        sz = new int[N];
        //Connect each node to itself
        for(int i = 0; i < forestArray.length; i++){
            forestArray[i] = i;
            sz[i] = 1;
        }
    }
    //Find the root of the tree
    private int root(int i){
        //Traverse up to tree until node points to itself
        while(i != forestArray[i]){
            forestArray[i] = forestArray[forestArray[i]];
            i = forestArray[i];
        }
        return i;
    }

    public void union(int p, int q){
        //Make root of p also root of q to merge trees
        int rootQ = root(q);
        int rootP = root(p);

        //If already connected!
        if (rootQ == rootP) {
            return;
        }
        //Connect smaller tree to root of larger tree to have flatter trees == faster find operations
        if(sz[p] > sz[q]){
            forestArray[rootQ] = rootP;
            sz[p] += sz[q];
        }
        else{
            forestArray[rootP] = rootQ;
            sz[q] += sz[p];
        }
        //StdOut.println("UnionQU(" + p + "," + q + ")");
    }

    public boolean connected(int p, int q){
        //Check if roots are the same == connected
        int rootQ = root(q);
        int rootP = root(p);
        //StdOut.print("ConnectedQU(" + p + "," + q + ") = ");
        //StdOut.println(rootP == rootQ);
        return rootP == rootQ;
    }
    //Just a quick function to display the current array
    public void ShowIds(){
        for(int i = 0; i <forestArray.length; i++){
            StdOut.print(" " + i + " ");
        }
        StdOut.println();
        for(int i = 0; i <forestArray.length; i++){
            StdOut.print(" " + forestArray[i] + " ");
        }
        StdOut.println("\n");
    }
}
