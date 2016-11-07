import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.lang.System;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int[] whitelist = In.readInts(args[0]);
        Arrays.sort(whitelist);
        try {
            System.setIn(new FileInputStream(args[1]));
        } catch (FileNotFoundException e){
            StdOut.println("File not found");
        }
        while (!StdIn.isEmpty()) { // Read key, print if not in whitelist.
            int key = StdIn.readInt();
            if (BinarySearch.rank(key, whitelist) < 0) {
                StdOut.println(key);
            }
        }
    }
}
