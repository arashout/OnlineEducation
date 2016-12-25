import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
    private MinPQ initPQ;
    private MinPQ twinPQ;
    private int minMoves;
    private boolean isSolvable;

    private class Node implements Comparable<Node>{
        private int numMoves;
        private Board board;
        private Node prevNode;


        public Node(Board b, int m, Node prev){
            prevNode = prev;
            board = b;
            numMoves = m;
        }

        public int compareTo(Node other){
            int thisNodeScore =  this.board.manhattan() + this.numMoves;
            int otherNodeScore = other.board.manhattan() + other.numMoves;
            return thisNodeScore - otherNodeScore;
        }
        public boolean isGoal(){
            return board.isGoal();
        }
        public boolean equals(Object y){
            return board.equals(y);
        }
        public Iterable<Board> getNeighbors(){
            return board.neighbors();
        }
    }
    public Solver(Board initial){
        //Set-up
        Board initialTwin = initial.twin();
        initPQ = new MinPQ<Node>();
        twinPQ = new MinPQ<Node>();

        //Priority Queue
        initPQ.insert(new Node(initial, 0, null));
        twinPQ.insert(new Node(initialTwin , 0, null));

        Node curNode, twinCurNode;
        while(true){
            //Process Node closest to goal
            curNode = (Node) initPQ.delMin();
            twinCurNode = (Node) twinPQ.delMin();
            //Check if goal reached
            if(curNode.isGoal()) break;
            else if(twinCurNode.isGoal()) break;

            //Add Neighbors
            for(Board b : curNode.getNeighbors()){
                if(!b.equals(initial)) initPQ.insert(new Node(b, curNode.numMoves + 1, curNode));
            }
            for(Board b : twinCurNode.getNeighbors()){
                if(!b.equals(initialTwin)) twinPQ.insert(new Node(b, twinCurNode.numMoves + 1, twinCurNode));
            }

        }

    }           // find a solution to the initial board (using the A* algorithm)
    public boolean isSolvable(){
        return isSolvable;
    }            // is the initial board solvable?
    public int moves(){
        return minMoves;
    }                     // min number of moves to solve initial board; -1 if unsolvable
    public Iterable<Board> solution(){
        return null;
    }      // sequence of boards in a shortest solution; null if unsolvable
    public static void main(String[] args){

    } // solve a slider puzzle (given below)
}
