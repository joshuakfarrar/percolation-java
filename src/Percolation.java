public class Percolation {
    private static int VIRTUAL_TOP = 0;
    private final int VIRTUAL_BOTTOM;

    private boolean[] grid;
    private WeightedQuickUnionUF weightedQuickUnionUF;
    private int N;

    public Percolation(int N) {
        if (N <= 0) throw new IllegalArgumentException();

        this.N = N;
        this.VIRTUAL_BOTTOM = N * N + 1;
        this.weightedQuickUnionUF = new WeightedQuickUnionUF((VIRTUAL_BOTTOM + 1));
        this.grid = new boolean[N * N];
    }

    public void open(int i, int j) {
        checkInBounds(i, j);

        int p = pointFromCoordinates(i, j);

        grid[p - 1] = true;

        // top neighbor
        if (i == 1) {
            // if p is in the top row, connect it to VIRTUAL_TOP
            weightedQuickUnionUF.union(p, VIRTUAL_TOP);
        } else {
            int k = i - 1;
            if (isOpen(k, j)) {
                int q = pointFromCoordinates(k, j);
                weightedQuickUnionUF.union(p, q);
            }
        }

        // right
        // if p is on the right-most edge, it has no neighbor to its right
        if (!(j == N)) {
            int k = j + 1;
            if (isOpen(i, k)) {
                int q = pointFromCoordinates(i, k);
                weightedQuickUnionUF.union(p, q);
            }
        }

        // bottom neighbor
        if (i == N) {
            // if p is in the bottom row, connect it to VIRTUAL_BOTTOM
            weightedQuickUnionUF.union(p, VIRTUAL_BOTTOM);
        } else {
            int k = i + 1;
            if (isOpen(k, j)) {
                int q = pointFromCoordinates(k, j);
                weightedQuickUnionUF.union(p, q);
            }
        }

        // left
        // if p is on the left-most edge, it has no neighbor to its left
        if (!(j == 1)) {
            int k = j - 1;
            if (isOpen(i, k)) {
                int q = pointFromCoordinates(i, k);
                weightedQuickUnionUF.union(p, q);
            }
        }
    }

    private void checkInBounds(int i, int j) {
        if ((i < 1 || i > N) || (j < 1 || j > N))
            throw new java.lang.IndexOutOfBoundsException();
    }

    public boolean isOpen(int i, int j) {
        checkInBounds(i, j);

        int p = pointFromCoordinates(i, j);
        return grid[p - 1];
    }

    public boolean isFull(int i, int j) {
        checkInBounds(i, j);

        if (!isOpen(i, j))
            return false;

//        for (int k = 1; k < N; k++) {
//
//        }

        int p = pointFromCoordinates(i, j);
        return weightedQuickUnionUF.connected(p, VIRTUAL_TOP);
    }

    public boolean percolates() {
        return weightedQuickUnionUF.connected(VIRTUAL_TOP, VIRTUAL_BOTTOM);
    }

    private int pointFromCoordinates(int i, int j) {
        /*****
         * Show your work:
         *
         * let n = 4
         *
         *   j  1    2   3   4
         *  i
         *  1   1    2   3   4  // p = j
         *  2   5    6   7   8  // p = j + n
         *  3   9   10  11  12  // p = n * (i - 1) + j
         *  4   13  14  15  16  // p = n * (i - 1) + j
         */

        return N * (i - 1) + j;
    }
}