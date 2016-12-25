import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class Board {
    private static final int SPACE = 0;
    private int[][] tiles;
    private int[][] goalBoard;

    public Board(int[][] blocks) {
        tiles = Board.deepCopyIntMatrix(blocks);
        goalBoard = new int[dimension()][dimension()];
        //Initialize what the goal board, for future methods
        int tile = 1;
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                if (i == dimension() - 1 && j == dimension() - 1) goalBoard[i][j] = SPACE;
                else goalBoard[i][j] = tile;
                tile += 1;
            }
        }
    }

    private static int[][] deepCopyIntMatrix(int[][] input) {
        if (input == null)
            return null;
        int[][] result = new int[input.length][];
        for (int r = 0; r < input.length; r++) {
            result[r] = input[r].clone();
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] arr = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
        Board b = new Board(arr);
        StdOut.println(b);
        for (Board a : b.neighbors()) {
            StdOut.println(a);
        }
    }

    public int dimension() {
        return tiles.length;
    }

    public int hamming() {
        int hammingCount = 0;
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                if (tiles[i][j] != goalBoard[i][j] && tiles[i][j] != SPACE)
                    hammingCount++; //Ignore 0, which doesn't count as tiles
            }
        }
        return hammingCount;
    }

    public int manhattan() {
        int manhattanCount = 0;
        int a, b, val;
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                if (tiles[i][j] != SPACE) {
                    val = tiles[i][j];
                    a = (val - 1) / dimension();
                    b = (val - 1) - a * dimension();
                    manhattanCount += Math.abs(i - a); //x-coordinates
                    manhattanCount += Math.abs(j - b); //y-coordinates
                }

            }
        }
        return manhattanCount;
    }

    public boolean isGoal() {
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                if (tiles[i][j] != goalBoard[i][j]) return false;
            }
        }
        return true;
    }

    public Board twin() {
        int j = 0;
        for (int i = 0; i < dimension(); i++) {
            if (tiles[i][j] != SPACE && tiles[i][j + 1] != SPACE) {
                return new Board(swap(i, j, i, j + 1));
            }
        }
        throw new RuntimeException();
    }

    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null || !(y instanceof Board) || ((Board) y).tiles.length != tiles.length) return false;
        for (int row = 0; row < tiles.length; row++)
            for (int col = 0; col < tiles.length; col++)
                if (((Board) y).tiles[row][col] != this.tiles[row][col]) return false;

        return true;
    }

    public Iterable<Board> neighbors() {
        //Find space
        int[] spaceLoc = spaceCoords(tiles);
        int r = spaceLoc[0];
        int c = spaceLoc[1];
        Queue<Board> neigh = new Queue<>();
        //All Cases
        if (r > 0) neigh.enqueue(new Board(swap(r, c, r - 1, c)));
        if (c > 0) neigh.enqueue(new Board(swap(r, c, r, c - 1)));
        if (r < dimension() - 1) neigh.enqueue(new Board(swap(r, c, r + 1, c)));
        if (c < dimension() - 1) neigh.enqueue(new Board(swap(r, c, r, c + 1)));

        return neigh;
    }     // all neighboring boards

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(dimension() + "\n");
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                s.append(String.format("%2d ", tiles[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }               // string representation of this board (in the output format specified below)

    private int[][] swap(int i, int j, int a, int b) {
        int[][] swapTiles = Board.deepCopyIntMatrix(tiles);
        int tempVal = swapTiles[i][j];
        swapTiles[i][j] = swapTiles[a][b];
        swapTiles[a][b] = tempVal;
        return swapTiles;
    }

    private int[] spaceCoords(int[][] arr) {
        //Find space
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[i][j] == SPACE) {
                    int[] coords = {i, j};
                    return coords;
                }
            }
        }
        throw new RuntimeException();
    }
}
