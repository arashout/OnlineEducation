import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.System;
import java.sql.Time;

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
        int start = 200;
        int end = 500;

        //Pick a random size from 'start' to 'end'
        int n = StdRandom.uniform(start,end);
        int numUnions = StdRandom.uniform(start,end);
        int numConnects = StdRandom.uniform(numUnions, end);

        //Initialize arrays that will contain numbers to union and to test connections
        int[] pArr = new int[numUnions];
        int[] qArr = new int[numUnions];
        int[] aArr = new int[numConnects];
        int[] bArr = new int[numConnects];
        for(int i=0;i<numUnions;i++){
            pArr[i] = StdRandom.uniform(0,n);
            qArr[i] = StdRandom.uniform(0,n);
        }
        for(int i=0;i<numConnects;i++){
            aArr[i] = StdRandom.uniform(0,n);
            bArr[i] = StdRandom.uniform(0,n);
        }
        //Build QuickFind Structure
        QuickFindUF qf = new QuickFindUF(n);
        //Do unions
        for(int i=0;i<numUnions;i++){
            qf.union(pArr[i],qArr[i]);
        }
        //Using connected results from Quick Find we will check other UF variants
        boolean[] checkArr = new boolean[numConnects];
        for(int i=0;i<numConnects;i++){
            checkArr[i] = qf.connected(aArr[i],bArr[i]);
        }

        //Build QuickUnion Structure
        QuickUnionUF qu = new QuickUnionUF(n);
        //Do unions
        for(int i=0;i<numUnions;i++){
            qu.union(pArr[i],qArr[i]);
        }
        //Check results of quickUnion
        for(int i=0;i<numConnects;i++) {
            StdOut.println(aArr[i] + " <-> " + bArr[i] + "  " + checkArr[i]);
            assertTrue(checkArr[i] == qu.connected(aArr[i], bArr[i]));
        }

    }
    private void executeUFOperations(){

    }
}