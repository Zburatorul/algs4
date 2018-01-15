/******************************************************************************
 *  Name:    Eugeniu Plamadeala
 *  NetID:   eugeniu
 *  Precept: P01
 *
 *  Partner Name:    N/A
 *  Partner NetID:   N/A
 *  Partner Precept: N/A
 *
 *  Description:  Model an n-by-n percolation system using the union-find
 *                data structure.
 ******************************************************************************/
import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {

    /**
     * Corner cases.
     * By convention, the row and column indices are integers between 1 and n,
     * where (1, 1) is the upper-left site:
     *
     * Throw a java.lang.IllegalArgumentException if any argument to open(),
     * isOpen(), or isFull() is outside its prescribed range.
     * The constructor should throw a java.lang.IllegalArgumentException
     * if n ≤ 0.
     */
    private boolean[] opn;
    private final int len;
    private final int n;
    private int numOpen;
    private final WeightedQuickUnionUF qu;

    public Percolation(int n)  {
      if (n <= 0)
      throw new java.lang.IllegalArgumentException("Incorrect grid size given.");

      // Add two more elements,element 0 to be linked to all on top outside
      // and element n^2+1 to be linked with all on bottom
      len = n*n + 2;
      this.n = n;
      numOpen = 0;
      opn = new boolean[len];

      qu = new WeightedQuickUnionUF(len);

      for (int i = 0; i < len; i++) {
        opn[i] = false;
      }

      /*
      // Create virtual top and bottom surface links
      for (int i = 1; i <= n; i++) {
        qu.union(0, i); // link virtual site 0 to top row
        qu.union(len-1, n*(n-1) + i); // link virtual site n^2+2 to bottom row
      }
      */

    }
    // open site (row, col) if it is not open already
    public    void open(int row, int col) {
        if (!validxy(row, col))
            throw new java.lang.IllegalArgumentException("Incorrent row or column index.");

        // Now open the element and consolidate components
        // Check whether each neighboring element is already open.
        if (isOpen(row, col)) return;

        opn[ind(row, col)] = true;
        numOpen += 1;

        if (row > 1 && isOpen(row-1, col)) union2D(row-1, col, row, col);
        if (row < n && isOpen(row+1, col)) union2D(row+1, col, row, col);
        if (col > 1 && isOpen(row, col-1)) union2D(row, col-1, row, col);
        if (col < n && isOpen(row, col+1)) union2D(row, col+1, row, col);

        // If on top surface, link to supernode
        if (row == 1) qu.union(0, ind(1, col));
        // if (row == n && qu.connected(0, ind(n, col))) qu.union(len-1, ind(n, col));
        //if (row == n && ! percolated) percolated = qu.connected(0, ind(n, col));
        if (row == n) qu.union(len-1, ind(n, col));

    }

    private boolean validxy(int row, int col) {
      return (row > 0 && row <= n && col > 0 && col <= n);
    }

    private int ind(int row, int col) {
        return (row-1) * n + col;
    }

    private void union2D(int row1, int col1, int row2, int col2) {
        qu.union(ind(row1, col1), ind(row2, col2));
    }

    public boolean isOpen(int row, int col) {
      if (!validxy(row, col))
        throw new java.lang.IllegalArgumentException("Incorrect row or column.");
      return opn[ind(row, col)];
    }

    // A full site is an open site that is connected to a site on the
    // top row
    public boolean isFull(int row, int col) {
      if (!validxy(row, col))
        throw new java.lang.IllegalArgumentException("Incorrect row or column.");
      return qu.connected(0, ind(row, col));
    }

    // number of open sites
    public     int numberOfOpenSites() {
      return numOpen;
    }

    public boolean percolates() {
        return qu.connected(0, len-1);
    }

     /**
     * @param args
     */
    public static void main(String[] args) {
        // test client (optional)
    }

}
