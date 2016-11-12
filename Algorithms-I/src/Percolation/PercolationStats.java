package Percolation;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * Created by arash on 2016-11-11.
 */
public class PercolationStats {
    private double[] arrPerc;

    public PercolationStats(int n, int trials) {
        if (n <= 0) throw new IllegalArgumentException("Size needs to be natural number");
        if (trials <= 0) throw new IllegalArgumentException("Trials needs to be natural number");
        arrPerc = new double[trials];
        for (int i = 0; i < trials; i++) {
            arrPerc[i] = percSim(n);
        }
    }    // perform trials independent experiments on an n-by-n grid

    public static void main(String[] args) {
        int size = Integer.parseInt(args[0]);
        int numTrials = Integer.parseInt(args[1]);
        PercolationStats percStats = new PercolationStats(size, numTrials);
        StdOut.println("mean                    = " + percStats.mean());
        StdOut.println("stddev                  = " + percStats.stddev());
        StdOut.println("95% confidence interval = " + percStats.confidenceLo() + ", " + percStats.confidenceHi());
    }    // test client (described below)

    private double percSim(int n) {
        Percolation per = new Percolation(n);
        int siteRow;
        int siteCol;
        double countOpen = 0.0;
        while (!per.percolates()) {
            // Coordinates of site to test
            siteRow = StdRandom.uniform(1, n + 1);
            siteCol = StdRandom.uniform(1, n + 1);
            if (!per.isOpen(siteRow, siteCol)) {
                per.open(siteRow, siteCol);
                countOpen++;
            }
        }
        return countOpen / (n * n);
    }

    private double confidence() {
        double zCoeff = 1.96; // For 95%
        double std = stddev();
        int sampleSize = arrPerc.length;
        return zCoeff * std / (Math.sqrt(sampleSize));
    }

    public double mean() {
        return StdStats.mean(arrPerc);
    }                    // sample mean of percolation threshold

    public double stddev() {
        return StdStats.stddev(arrPerc);
    }                        // sample standard deviation of percolation threshold

    public double confidenceLo() {
        return mean() - confidence();

    }                // low  endpoint of 95% confidence interval

    public double confidenceHi() {
        return mean() + confidence();
    }                  // high endpoint of 95% confidence interval
}
