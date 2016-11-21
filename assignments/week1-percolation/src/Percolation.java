import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final int n, start = 0, end;
    private final WeightedQuickUnionUF uf;
    private final boolean[] opennodes;

    public Percolation(int n) { // create n-by-n grid, with all sites blocked
        if (n <= 0) {
            throw new IllegalArgumentException("Invalid size of grid");
        }
        this.n = n;
        end = n * n + 1;
        uf = new WeightedQuickUnionUF(end + 1);
        opennodes = new boolean[end + 1];
    }

    public void open(int row, int col) { // open site (row, col) if it is not
                                         // open already
        validate(row, col);
        int node = node(row, col);
        if (!opennodes[node]) {
            opennodes[node] = true;
            if (row == 1) {
                uf.union(start, node);
            }
            connect(node, row - 1, col);
            connect(node, row + 1, col);
            connect(node, row, col - 1);
            connect(node, row, col + 1);
//            if (row == n && isFull(node)) {
//                uf.union(end, node);
//            }
        }
    }

    private void connect(int node, int row, int col) {
        if (isValid(row, col) && isOpen(row, col)) {
            uf.union(node, node(row, col));
        }
    }

    public boolean isOpen(int row, int col) { // is site (row, col) open?
        validate(row, col);
        return opennodes[node(row, col)];
    }

    public boolean isFull(int row, int col) { // is site (row, col) full?
        validate(row, col);
        return isFull(node(row, col));
    }

    private boolean isFull(int node) {
        return uf.connected(start, node);
    }

    public boolean percolates() { // does the system percolate?
        for (int node = node(n, 1); node <= node(n, n); node++) {
            if (opennodes[node] && uf.connected(start, node)) {
                return true;
            }
        }
        return false;
    }

    private void validate(int row, int col) {
        if (!isValid(row, col)) {
            throw new IndexOutOfBoundsException();
        }
    }

    private boolean isValid(int row, int col) {
        return 1 <= row && row <= n && 1 <= col && col <= n;
    }

    private int node(int row, int col) {
        return (row - 1) * n + col;
    }

    public static void main(String[] args) {
        Percolation percolation = new Percolation(1);
        System.out.println(percolation.percolates());
        percolation.open(1, 1);
        System.out.println(percolation.percolates());
    }
}
