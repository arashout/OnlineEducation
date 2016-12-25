/******************************************************************************
 *  Compilation:  javac PuzzleChecker.java
 *  Execution:    java PuzzleChecker filename1.txt filename2.txt ...
 *  Dependencies: Board.java Solver.java
 *
 *  This program creates an initial board from each filename specified
 *  on the command line and finds the minimum number of moves to
 *  reach the goal state.
 *
 *  % java PuzzleChecker puzzle*.txt
 *  puzzle00.txt: 0
 *  puzzle01.txt: 1
 *  puzzle02.txt: 2
 *  puzzle03.txt: 3
 *  puzzle04.txt: 4
 *  puzzle05.txt: 5
 *  puzzle06.txt: 6
 *  ...
 *  puzzle3x3-impossible: -1
 *  ...
 *  puzzle42.txt: 42
 *  puzzle43.txt: 43
 *  puzzle44.txt: 44
 *  puzzle45.txt: 45
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PuzzleChecker {

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new File("C:\\Users\\arash\\Github\\OnlineEducation\\Algorithms-I\\tests\\8puzzle\\puzzle2x2-00.txt"));
            int n = sc.nextInt();
            int [][] tiles = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++){
                    tiles[i][j] = sc.nextInt();
                }
            }
            // solve the slider puzzle
            Board initial = new Board(tiles);
            Solver solver = new Solver(initial);
            //StdOut.println(filename + ": " + solver.moves());

        }
        catch (FileNotFoundException e){
            StdOut.println("File not found cuh");
        }
    }
}
