import Percolation.Percolation;
import edu.princeton.cs.algs4.StdOut;

public class Main {
    public static void main(String[] args) {
        Percolation perc = new Percolation(4);
        perc.open(1,2);
        perc.open(1,3);
        perc.open(2,3);
        perc.open(3,3);
        perc.open(4,3);
        StdOut.println(perc.percolates());

    }
}
