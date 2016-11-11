import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.lang.System;
import java.util.Arrays;

public class Main {
    public static void passChildObject(BaseUF bUF){
        bUF.union(1,2);
        StdOut.println(bUF.connected(1,2));
        StdOut.println(bUF.connected(0,1));
    }
    public static void main(String[] args) {
        QuickFindUF qf = new QuickFindUF(3);
        passChildObject(qf);

    }
}
