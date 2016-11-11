import UF.BaseUF;
import UF.QuickFindUF;
import UF.WQUPathCompressionUF;
import UF.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Test;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.System;

import static org.junit.Assert.assertTrue;

public class TestUF {

    //Test to check that quickFind is working properly
    @Test
    public void testQuickFind() {
        String path = "C:\\Users\\arash\\Github\\OnlineEducation\\Algorithms-I\\tests\\datasets\\tinyUF.txt";

        System.out.println(path);

        try {
            System.setIn(new FileInputStream(path));
        }
        catch (FileNotFoundException e){
            StdOut.println(e);
        }

        int size = StdIn.readInt();
        QuickFindUF qf = new QuickFindUF(size);

        while (!StdIn.isEmpty()) { // Read key, print if not in whitelist.
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            qf.union(p,q);
        }
        //Test mostly everything in tinyUF
        assertTrue(qf.connected(4,3));
        assertTrue(qf.connected(6,7));
        assertTrue(!qf.connected(3,6));
        assertTrue(!qf.connected(0,8));
    }

    //Test UF variants using Quick Find as a base line
    @Test
    public void testUFVariants(){
        //Parameters
        int n = 4836470;
        int seed = 0;
        //boolean[] qfResults;
        //boolean[] quResults;
        boolean[] wquResults;
        boolean[] wqupcResults;

        Stopwatch sw = new Stopwatch();
        double start;
        double end;
        /*
        start = sw.elapsedTime();
        UF.QuickFindUF qf = new UF.QuickFindUF(n);
        qfResults = returnUFResults(qf, seed, n); //This will be base-line
        end = sw.elapsedTime();
        StdOut.println("QF took: " + (end - start));

        start = sw.elapsedTime();
        UF.QuickUnionUF qu = new UF.QuickUnionUF(n);
        quResults = returnUFResults(qu, seed, n);
        end = sw.elapsedTime();
        StdOut.println("QU took: " + (end - start));
        */
        start = sw.elapsedTime();
        WeightedQuickUnionUF wqu = new WeightedQuickUnionUF(n);
        wquResults = returnUFResults(wqu, seed, n);
        end = sw.elapsedTime();
        StdOut.println("WQU took: " + (end - start));

        start = sw.elapsedTime();
        WQUPathCompressionUF wqupc = new WQUPathCompressionUF(n);
        wqupcResults = returnUFResults(wqupc, seed, n);
        end = sw.elapsedTime();
        StdOut.println("WQUPC took: " + (end - start));

        for(int k = 0; k<wquResults.length; k++){
            //assertTrue(qfResults[k] == quResults[k]);
            assertTrue(wquResults[k] == wqupcResults[k]);
        }
    }

    private boolean[] returnUFResults(BaseUF buf, int s, int size){
        StdRandom.setSeed(s);
        int nUnions = StdRandom.uniform(0, size);
        int nConnects = StdRandom.uniform(size);

        int p,q;
        for(int i=0;i<nUnions;i++){
            p = StdRandom.uniform(0,size);
            q = StdRandom.uniform(0,size);
            buf.union(p,q);
        }
        boolean[] results = new boolean[nConnects];
        int a,b;
        for(int j=0;j<nConnects;j++){
            a = StdRandom.uniform(0,size);
            b = StdRandom.uniform(0,size);
            results[j] = buf.connected(a,b);
        }
        return results;
    }
}