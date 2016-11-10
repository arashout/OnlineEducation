import org.junit.Test;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.io.FileInputStream;
import java.io.File;
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
            StdOut.println(p + " <-> " + q);
        }
        //Test mostly everything in tinyUF
        assertTrue(qf.connected(4,3));
        assertTrue(qf.connected(6,7));
        assertTrue(!qf.connected(3,6));
        assertTrue(!qf.connected(0,8));
    }
}