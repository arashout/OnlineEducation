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
        int[] whitelist = {1,2,3,4,6,7,8,9};
        Arrays.sort(whitelist);
        while (!StdIn.isEmpty()) { // Read key, print if not in whitelist.
            int key = StdIn.readInt();
            StdOut.println(BinarySearch.recursiveRank(key, whitelist, 0, whitelist.length, 0));
        }
    }
}
