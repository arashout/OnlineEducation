import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import Puzzle.Board;

public class Solver {
    private MinPQ<Node> initPQ;
    private MinPQ<Node> twinPQ;
    private int minMoves;
    private Node finalBoard;
    private boolean isSolvable;

    public Solver(Board initial) {
        //Set-up
        Board initialTwin = initial.twin();
        initPQ = new MinPQ<Node>();
        twinPQ = new MinPQ<Node>();

        //Priority Queue Initial
        Node curNode, twinCurNode;
        curNode = new Node(initial, 0, null);
        twinCurNode = new Node(initialTwin, 0, null);
        initPQ.insert(curNode);
        twinPQ.insert(twinCurNode);


        while (true) {
            //Process Node closest to goal
            curNode = initPQ.delMin();
            twinCurNode = twinPQ.delMin();
            //Check if goal reached
            if (curNode.isGoal()) {
                minMoves = curNode.numMoves;
                isSolvable = true;
                finalBoard = curNode;
                break;
            } else if (twinCurNode.isGoal()) {
                minMoves = -1;
                isSolvable = false;
                break;
            }

            //Add Neighbors
            Node n;
            for (Board b : curNode.getNeighbors()) {
                if(curNode.prevNode != null && curNode.prevNode.equals(b)) continue;
                if (b.equals(initial)) continue;
                n = new Node(b, curNode.numMoves + 1, curNode);
                initPQ.insert(n);

            }
            for (Board b : twinCurNode.getNeighbors()) {
                if(twinCurNode.prevNode != null && twinCurNode.prevNode.equals(b)) continue;
                if (b.equals(initialTwin)) continue;
                n = new Node(b, twinCurNode.numMoves + 1, twinCurNode);
                twinPQ.insert(n);
            }

        }

    }           // find a solution to the initial board (using the A* algorithm)

    public static void main(String[] args) {

    } // solve a slider puzzle (given below)

    public boolean isSolvable() {
        return isSolvable;
    }            // is the initial board solvable?

    public int moves() {
        return minMoves;
    }                     // min number of moves to solve initial board; -1 if unsolvable

    public Iterable<Board> solution() {
        Stack<Board> q = new Stack<Board>();
        Node curNode = finalBoard;
        if (isSolvable()) {
            //Step back through final solution
            while (true) {
                if (curNode == null) break;
                q.push(curNode.getBoard());
                curNode = curNode.prevNode;
            }
            return q;

        }
        return null;
    }      // sequence of boards in a shortest solution; null if unsolvable

    private class Node implements Comparable<Node> {
        private int numMoves;
        private Board board;
        private Node prevNode;
        private int priority;


        public Node(Board b, int m, Node prev) {
            prevNode = prev;
            board = b;
            numMoves = m;
            priority = -1;
        }
        //Don't recompute manhattan score
        private int score(){
            if(priority == -1) {
                priority = this.board.manhattan();
                return priority;
            }
            else return priority;
        }
        @Override
        public int compareTo(Node other) {
            int thisNodeScore = this.score() + this.numMoves;
            int otherNodeScore = other.score() + other.numMoves;
            if(thisNodeScore > otherNodeScore) return 1;
            else if(thisNodeScore < otherNodeScore) return -1;
            //Break ties
            else{
                if(this.numMoves > other.numMoves) return 1;
                else return -1;
            }
        }

        public boolean isGoal() {
            return board.isGoal();
        }

        public boolean equals(Object y) {
            return board.equals(y);
        }

        public Iterable<Board> getNeighbors() {
            return board.neighbors();
        }

        public Board getBoard() {
            return board;
        }
    }
}
