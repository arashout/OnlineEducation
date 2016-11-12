import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Created by arash on 2016-11-11.
 */
public class Percolation {
    private Site[][] sites;
    private Site virtualTop, virtualBottom;
    private WeightedQuickUnionUF uf;

    public Percolation(int n) {
        uf = new WeightedQuickUnionUF(n * n + 2);
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
        return uf.connected(virtualTop.id, sites[row - 1][col - 1].id);
    }

    public boolean percolates() {
        return uf.connected(virtualTop.id, virtualBottom.id);
    }

    private void unionAdjacents(int row, int col) {
        row = row - 1;
        col = col - 1;
        //Top
        if (row != 0 && sites[row - 1][col].open) {
            uf.union(sites[row][col].id, sites[row - 1][col].id);
        }
        if (row == 0) uf.union(sites[row][col].id, virtualTop.id);
        //Bottom
        if (row < (sites.length - 1) && sites[row + 1][col].open) {
            uf.union(sites[row][col].id, sites[row + 1][col].id);
        }
        if (row == sites.length - 1) uf.union(sites[row][col].id, virtualBottom.id);
        //Right
        if (col < sites.length - 1 && sites[row][col + 1].open) {
            uf.union(sites[row][col].id, sites[row][col + 1].id);
        }
        //Left
        if (col != 0 && sites[row][col - 1].open) {
            uf.union(sites[row][col].id, sites[row][col - 1].id);
        }
    }

    private void validate(int row, int col) {
        if (row <= 0 || row > sites.length) throw new IndexOutOfBoundsException("Row index out of bounds");
        if (col <= 0 || col > sites.length) throw new IndexOutOfBoundsException("Col index out of bounds");
    }

    private class Site {
        public boolean open;
        public int id;

        public Site(int num) {
            id = num;
            open = false;
        }
    }
}
