package Percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Created by arash on 2016-11-11.
 */
public class Percolation {
    private boolean[][] openArray;
    private int[][] idArray;
    private int virtualTop;
    private int virtualBottom;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF uf2;

    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("Size needs to be natural number");
        uf = new WeightedQuickUnionUF(n * n + 2);
        uf2 = new WeightedQuickUnionUF(n * n + 1);
        openArray = new boolean[n][n];
        idArray = new int[n][n];

        virtualTop = n * n;
        virtualBottom = n * n + 1;

        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                idArray[i][j] = count;
                count++;
            }
        }
    }

    public void open(int row, int col) {
        validate(row, col);
        openArray[row - 1][col - 1] = true;
        unionAdjacents(row, col);
    }

    public boolean isOpen(int row, int col) {
        validate(row, col);
        return openArray[row - 1][col - 1];
    }

    public boolean isFull(int row, int col) {
        validate(row, col);
        return uf2.connected(idArray[row - 1][col - 1], virtualTop);
    }

    public boolean percolates() {
        return uf.connected(virtualTop, virtualBottom);
    }

    private void unionAdjacents(int row, int col) {
        row = row - 1;
        col = col - 1;
        int top, bottom, right, left;
        int cur = idArray[row][col];
        // Top
        if (row != 0 && openArray[row - 1][col]) {
            top = idArray[row - 1][col];
            uf.union(cur, top);
            uf2.union(cur, top);
        }
        // Connect to virtual top
        if (row == 0) {
            uf.union(cur, virtualTop);
            uf2.union(cur, virtualTop);
        }
        // Bottom
        if (row < (idArray.length - 1) && openArray[row + 1][col]) {
            bottom = idArray[row + 1][col];
            uf.union(cur, bottom);
            uf2.union(cur, bottom);
        }
        // Connect to virtual bottom
        if (row == idArray.length - 1) {
            uf.union(cur, virtualBottom);
        }
        // Right
        if (col < idArray.length - 1 && openArray[row][col + 1]) {
            right = idArray[row][col + 1];
            uf.union(cur, right);
            uf2.union(cur, right);
        }
        // Left
        if (col != 0 && openArray[row][col - 1]) {
            left = idArray[row][col - 1];
            uf.union(cur, left);
            uf2.union(cur, left);
        }
    }

    private void validate(int row, int col) {
        if (row <= 0 || row > idArray.length) throw new IndexOutOfBoundsException("Row index out of bounds");
        if (col <= 0 || col > idArray.length) throw new IndexOutOfBoundsException("Col index out of bounds");
    }

    private class Site {
        private boolean open;
        private int id;

        Site(int num) {
            id = num;
            open = false;
        }
    }
}
