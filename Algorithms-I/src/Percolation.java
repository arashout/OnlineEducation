import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Created by arash on 2016-11-11.
 */
public class Percolation {
    private Site[][] sites;
    private Site virtualTop, virtualBottom;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF uf2;

    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("Size needs to be natural number");
        uf = new WeightedQuickUnionUF(n * n + 2);
        uf2 = new WeightedQuickUnionUF(n * n + 1);
        sites = new Site[n][n];

        virtualTop = new Site(n * n);
        virtualBottom = new Site(n * n + 1);
        virtualTop.open = true;
        virtualBottom.open = true;

        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sites[i][j] = new Site(count);
                count++;
            }
        }
    }

    public void open(int row, int col) {
        validate(row, col);
        sites[row - 1][col - 1].open = true;
        unionAdjacents(row, col);
    }

    public boolean isOpen(int row, int col) {
        validate(row, col);
        return sites[row - 1][col - 1].open;
    }

    public boolean isFull(int row, int col) {
        validate(row, col);
        return uf2.connected(sites[row - 1][col - 1].id, virtualTop.id);
    }

    public boolean percolates() {
        return uf.connected(virtualTop.id, virtualBottom.id);
    }

    private void unionAdjacents(int row, int col) {
        row = row - 1;
        col = col - 1;
        Site cur = sites[row][col];
        // Top
        if (row != 0 && sites[row - 1][col].open) {
            Site top = sites[row - 1][col];
            uf.union(cur.id, top.id);
            uf2.union(cur.id, top.id);
        }
        // Connect to virtual top
        if (row == 0) {
            uf.union(cur.id, virtualTop.id);
            uf2.union(cur.id, virtualTop.id);
        }
        // Bottom
        if (row < (sites.length - 1) && sites[row + 1][col].open) {
            Site bottom = sites[row + 1][col];
            uf.union(cur.id, bottom.id);
            uf2.union(cur.id, bottom.id);
        }
        // Connect to virtual bottom
        if (row == sites.length - 1) {
            uf.union(cur.id, virtualBottom.id);
        }
        // Right
        if (col < sites.length - 1 && sites[row][col + 1].open) {
            Site right = sites[row][col + 1];
            uf.union(cur.id, right.id);
            uf2.union(cur.id, right.id);
        }
        // Left
        if (col != 0 && sites[row][col - 1].open) {
            Site left = sites[row][col - 1];
            uf.union(cur.id, left.id);
            uf2.union(cur.id, left.id);
        }
    }

    private void validate(int row, int col) {
        if (row <= 0 || row > sites.length) throw new IndexOutOfBoundsException("Row index out of bounds");
        if (col <= 0 || col > sites.length) throw new IndexOutOfBoundsException("Col index out of bounds");
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
