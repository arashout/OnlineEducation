package base;

import edu.princeton.cs.algs4.StdOut;

/**
 * Created by arash on 2016-09-03.
 */
public class QuickFind {
    private int[] idArray;
    //Constructor
    public QuickFind(int N) {
        idArray = new int[N];
        //Connect each node to itself
        for(int i = 0; i < idArray.length; i++){
            idArray[i] = i;
        }
    }
    //union operation takes N array access to convert each id one at a time
    public void union(int p, int q){
        //StdOut.println("UnionQF(" + p + "," + q + ")");
        int pId = idArray[p];
        int qId = idArray[q];
        for(int i = 0; i < idArray.length; i++){
            if(idArray[i] == qId){
                idArray[i] = pId;
            }
        }
    }
    //To check connection simply have to check idArray
    public boolean connected(int p, int q){
        //StdOut.print("ConnectedQF(" + p + "," + q + ") = ");
        //StdOut.println(idArray[p] == idArray[q]);
        return idArray[p] == idArray[q];
    }

    //Just a quick function to display the current array
    public void ShowIds(){
        for(int i = 0; i <idArray.length; i++){
            StdOut.print(" " + i + " ");
        }
        StdOut.println();
        for(int i = 0; i <idArray.length; i++){
            StdOut.print(" " + idArray[i] + " ");
        }
        StdOut.println("\n");
    }
}
