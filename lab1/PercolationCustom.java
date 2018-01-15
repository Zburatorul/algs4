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
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
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
    private int[] id;
    private int[] sz;
    private boolean[] opn;
    private final int len, n;
    private int num_open;
    public Percolation(int n)  {
      if (n <= 0)
      throw new java.lang.IllegalArgumentException("Incorrect grid size given.");

      // Add two more elements,element 0 to be linked to all on top outside
      // and element n^2+1 to be linked with all on bottom
      len = n*n + 2;
      this.n = n;
      num_open = 0;
      id = new int[len];
      sz = new int[len];
      opn = new boolean[len];

      for( int i = 0; i < len; i++) {
        id[i] = i;
        sz[i] = 1;
        opn[i] = false;
      }

      // Create virtual top and bottom surface links
      for (int i = 1; i <= n; i++){
        union(0, i); // link virtual site 0 to top row
        union(len-1, n*(n-1) + i ); // link virtual site n^2+2 to bottom row
      }

    }
    // open site (row, col) if it is not open already
    public    void open(int row, int col){
        if (!validxy(row,col))
            throw new java.lang.IllegalArgumentException("Incorrent row or column index.");

        // Now open the element and consolidate components
        // Check whether each neighboring element is already open.
        if (isOpen(row,col)) return;

        opn[1 + (row-1) * n + col] = true;
        num_open += 1;
        if (row > 1 && isOpen(row-1, col)) union2D(row-1,col,row,col);
        if (row < n && isOpen(row+1, col)) union2D(row+1,col,row,col);
        if (col > 1 && isOpen(row,col-1)) union2D(row,col-1,row,col);
        if (col < n && isOpen(row,col+1)) union2D(row,col+1,row,col);

    }

    private int root(int p){
      int i = p;
      while (i != id[i]){
        id[i] = id[id[i]];
        i = id[i];
      }
      return i;
    }

    private void union(int p, int q){
      int r1 = root(p);
      int r2 = root(q);

      if (sz[r1] > sz[r2]){
        id[r2] = r1;
        sz[r1] += sz[r2];
      } else{
        id[r1] = r2;
        sz[r2] += sz[r1];
      }
    }

    private boolean connected(int p, int q){
      return (root(p) == root(q));
    }

    private boolean validxy(int row, int col){
      return ( row > 0 && row <= n && col > 0 && col <= n);
    }

    private void union2D(int row1, int col1, int row2, int col2){
      union(1 + (row1-1) *n + col1, 1 + (row2-1) * n + col2);
    }

    public boolean isOpen(int row, int col){
      if (!validxy(row,col))
        throw new java.lang.IllegalArgumentException("Incorrect row or column.");
      return opn[1 + (row-1) * n + col];
    }

    // A full site is an open site that is connected to a site on the
    // top row
    public boolean isFull(int row, int col){
      if (!validxy(row,col))
        throw new java.lang.IllegalArgumentException("Incorrect row or column.");
      return connected(0, 1 + (row-1) * n + col);
    }

    // number of open sites
    public     int numberOfOpenSites(){
      return num_open;
    }
    public boolean percolates(){
      return connected(0, len-1);
    }

     /**
     * @param args
     */
    public static void main(String[] args){
        // test client (optional)
    }

}
