package wordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class SAP {
    private Digraph digraph;

    private class SAPResult {
        int ancesterID;
        int distance;

        SAPResult(int ancesterID, int distance) {
            this.distance = distance;
            this.ancesterID = ancesterID;
        }
    }
    private int getIntersection(Set<Integer> setA, Set<Integer> setB){
        for (Integer i : setA){
            if(setB.contains(i)){
                return i;
            }
        }
        return -1;
    }

    private SAPResult sapBFS(Iterable<Integer> v, Iterable<Integer> w) {
        // Error checking...
        for (Integer i : v) {
            if (!withinBounds(i)) {
                throw new IllegalArgumentException();
            }
        }
        for (Integer i : w) {
            if (!withinBounds(i)) {
                throw new IllegalArgumentException();
            }
        }

        HashMap<Integer, Integer> distToA = new HashMap<>();
        HashMap<Integer, Integer> distToB = new HashMap<>();
        HashSet<Integer> markedA = new HashSet<>();
        HashSet<Integer> markedB = new HashSet<>();
        Queue<Integer> qA = new ArrayDeque<>();
        Queue<Integer> qB = new ArrayDeque<>();

        for (Integer i : v) {
            markedA.add(i);
            distToA.put(i, 0);
            qA.add(i);
        }
        for (Integer i : w) {
            markedB.add(i);
            distToB.put(i, 0);
            qB.add(i);
        }
        int ancestor = getIntersection(markedA, markedB);
        if(ancestor != -1){
            return new SAPResult(ancestor, distToA.get(ancestor) + distToB.get(ancestor));
        }

        while (!qA.isEmpty() || !qB.isEmpty()) {
            // qA
            if (!qA.isEmpty()) {
                int curA = qA.remove();
                for (int u : digraph.adj(curA)) {
                    if (!markedA.contains(u)) {
                        distToA.put(u, distToA.get(curA) + 1);
                        markedA.add(u);
                        qA.add(u);
                    }
                    // Common ancestor found
                    if( markedB.contains(u) ){
                        return new SAPResult(u, distToA.get(u) + distToB.get(u));
                    }
                }
            }

            // qB
            if (!qB.isEmpty()) {
                int curB = qB.remove();
                for (int u : digraph.adj(curB)) {
                    if (!markedB.contains(u)) {
                        distToB.put(u, distToB.get(curB) + 1);
                        markedB.add(u);
                        qB.add(u);
                    }
                    // Common ancestor found
                    if( markedA.contains(u) ){
                        return new SAPResult(u, distToA.get(u) + distToB.get(u));
                    }
                }
            }
        }


        return new SAPResult(-1, -1);
    }

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph digraph) {
        this.digraph = digraph;
    }

    private boolean withinBounds(int i) {
        return (i >= 0 && i < digraph.V());
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        // Create singletons
        List<Integer> argA = new ArrayList<>();
        argA.add(v);
        List<Integer> argB = new ArrayList<>();
        argB.add(w);
        return sapBFS(argA, argB).distance;
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        // Create singletons
        List<Integer> argA = new ArrayList<>();
        argA.add(v);
        List<Integer> argB = new ArrayList<>();
        argB.add(w);
        return sapBFS(argA, argB).ancesterID;
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        return sapBFS(v, w).distance;
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        return sapBFS(v, w).ancesterID;
    }

    // do unit testing of this class
    public static void main(String[] args) {
        In in  = new In("test/digraph2.txt");
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        StdOut.println(sap.length(3, 3));
        StdOut.println(sap.length(3, 1));
    }
}
