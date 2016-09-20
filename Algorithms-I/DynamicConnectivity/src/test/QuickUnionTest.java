package test;
import base.QuickFind;
import base.QuickUnion;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class QuickUnionTest {
    /*
    USE QuickFind as CONTROL
    QuickFind is simple and brute-force way of solution
     */

    //Testing Parameters
    private static int sizeArrays = 100000; //This determines the size of all my arrays used for testing
    private static int numLoops = 10;

    //Initialize arrays for testing
    private int[] aUnion = new int[sizeArrays];
    private int[] bUnion = new int[sizeArrays];
    private int[] aConnect = new int[sizeArrays];
    private int[] bConnect = new int[sizeArrays];

    //Reason why I use arrays is to isolate the amount of time it takes QU - structure which I want to test
    private boolean[] resultQF = new boolean[sizeArrays];
    private boolean[] resultQU = new boolean[sizeArrays];

    //Create time array so that I can average time
    private long[] timeArray = new long[sizeArrays];
    private int count = 0;

    private QuickFind quickF = new QuickFind(sizeArrays);
    private QuickUnion quickU = new QuickUnion(sizeArrays);

    @Test
    public void testUnion(){
        for(int j = 0; j < numLoops; j++) {
            //Populate Test Arrays
            for (int i = 0; i < sizeArrays; i++) {
                aUnion[i] = StdRandom.uniform(0, sizeArrays);
                bUnion[i] = StdRandom.uniform(0, sizeArrays);
                aConnect[i] = StdRandom.uniform(0, sizeArrays);
                bConnect[i] = StdRandom.uniform(0, sizeArrays);
            }
            //Test QF - Store result in array
            for (int i = 0; i < sizeArrays; i++) {
                quickF.union(aUnion[i], bUnion[i]);
                resultQF[i] = quickF.connected(aConnect[i], bConnect[i]);
            }
            //This is the structure I need to time
            long startTime = System.nanoTime();
            //Test QU - Store result in array
            for (int i = 0; i < sizeArrays; i++) {
                quickU.union(aUnion[i], bUnion[i]);
                resultQU[i] = quickU.connected(aConnect[i], bConnect[i]);
            }
            long endTime = System.nanoTime();
            timeArray[count] = (endTime - startTime) / 1000000;
            count++;

            for (int i = 0; i < sizeArrays; i++) {
                assertEquals(resultQF[i], resultQU[i]);
            }
            
        }
        long sum = 0;
        long average = 0;
        for (long k: timeArray) {
            sum += k;
        }
        average = sum/count;
        StdOut.println("The average time per QuickUnion run was: " + average + " ms");
    }
}