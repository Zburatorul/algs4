/******************************************************************************
 *  Name:    Eugeniu Plamadeala
 *  NetID:   eugeniu
 *  Precept: P01
 *
 *  Partner Name:    N/A
 *  Partner NetID:   N/A
 *  Partner Precept: N/A
 *
 *  Description:  8-puzzle solver through Priority Queue
 *
 ******************************************************************************/
import edu.princeton.cs.algs4.Queue;
// import edu.princeton.cs.algs4.StdOut;

public final class Board {
    private int[][] blocks;
    private final int n;
    private int ham = -1;
    private int man = -1;
    private Queue<Board> que;

    public Board(int[][] blocks)
    {
        this.n = blocks.length;
        this.blocks = new int[n][n];

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                this.blocks[i][j] = blocks[i][j];
            }
        }
    }

    private int ind(int i, int j)
    {
        // converts from zero-indexed (row, column) to array index of array
        // representation of board.
        // e.g. (n-1, n-1) = n*(n-1) + n-1 = n^2 - n + n -1 = n^2 -1
        return n*i + j;
    }

    public int dimension() { return n; }

    /*
     *  @returns The Hamming distance from this board to the goal.
     *  The empty block is not included in the calculation.
     */
    public int hamming()
    {
        if (ham == -1)
        {
            int count = 0;


            for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
            {
                if (blocks[i][j] != ind(i, j)+1 && blocks[i][j] != 0) count++;
            }

            ham = count;
            return count;

        }
        else return ham;
    }

    private int abs(int k)
    {
        if (k < 0) return -k;
        return k;
    }

    public int manhattan()
    {
        if (man == -1)
        {
            /*
             *  If block m = (a, b) is at position (c, d), its manhattan distance
             *  is |a-c| + |b-d|
             */

             int dist = 0;

             for (int i = 0; i < n; i++)
             for (int j = 0; j < n; j++)
             {
                 if (blocks[i][j] != 0)
                 {
                     int a = (blocks[i][j]-1) / n; // desired coordinates of block
                     int b = (blocks[i][j]-1) % n; // block 1 is in position (0, 0)
                     dist += abs(a-i) + abs(b-j);
                     // StdOut.println("(i,j)=("+i +", "+j+") contributes " +abs(a-i) + abs(b-j) + "by (a,b) = ("+a+", "+b+")" );
                 }
             }
             man = dist;
             return dist;
        }
        else return man;
    }

    public boolean isGoal()
    {
        for (int i = 0; i < n*n-1; i++)
            if (blocks[i / n][i % n] != i+1) return false;

        return true;
    }

    public Board twin()
    {
        // Choose random positions to exchange
        int r1 = 0, c1 = 0;
        while (blocks[r1][c1] == 0)
        {
            if (c1 == n)
            {
                c1 = 0;
                r1++;
            }
            else c1++;
        }

        int r2 = r1, c2 = c1;
        while (blocks[r2][c2] == 0 || (r1 == r2 && c1 == c2))
        {
            if (c2 == n)
            {
                c2 = 0;
                r2++;
            }
            else c2++;
        }

        // Now swap
        int t = blocks[r1][c1];
        blocks[r1][c1] = blocks[r2][c2];
        blocks[r2][c2] = t;

        Board res = new Board(blocks);

        // Now undo
        blocks[r2][c2] = blocks[r1][c1];
        blocks[r1][c1] = t;

        return res;
    }

    public boolean equals(Object y)
    {
        /*
         * Since no internals are exposed, seems we can only check indirectly
         * by comparing the string representations for example.
         */
         if (y == null) return false;
         if (y instanceof Board)
         {
             Board other = (Board) y;
             if (n != other.n) return false;

             for (int i = 0; i < n; i++)
             for (int j = 0; j < n; j++)
              if (blocks[i][j] != other.blocks[i][j]) return false;

              return true;
         }
         else return false;
    }

    private void addNeighborBoard(int zeroRow, int zeroCol, int dx, int dy)
    {
        int t = blocks[zeroRow][zeroCol];
        blocks[zeroRow][zeroCol] = blocks[zeroRow+dx][zeroCol+dy];
        blocks[zeroRow+dx][zeroCol+dy] = t;

        // StdOut.println("Iterator: Adding board:");
        Board nextBoard = new Board(blocks);
        que.enqueue(nextBoard);

        blocks[zeroRow+dx][zeroCol+dy] = blocks[zeroRow][zeroCol];
        blocks[zeroRow][zeroCol] = t;

        // StdOut.println(nextBoard.toString());
    }

    public Iterable<Board> neighbors()
    {
        que = new Queue<Board>();
        int zeroRow = -1, zeroCol = -1;

        for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
        {
            if (blocks[i][j] == 0)
            {
                zeroRow = i;
                zeroCol = j;
            }
        }

        if (zeroRow > 0)
        {
            // north
            addNeighborBoard(zeroRow, zeroCol, -1, 0);
        }

        if (zeroCol < n-1)
        {
            // east
            addNeighborBoard(zeroRow, zeroCol, 0, 1);
        }

        if (zeroRow < n-1)
        {
            // south
            addNeighborBoard(zeroRow, zeroCol, 1, 0);
        }

        if (zeroCol > 0)
        {
            // west
            addNeighborBoard(zeroRow, zeroCol, 0, -1);
        }

        return que;
    }

    public String toString()
    {
        StringBuilder out = new StringBuilder(Integer.toString(n) + "\n");

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                out.append(" " + Integer.toString(blocks[i][j]));
            }
            out.append("\n");
        }

        return out.toString();

    }
}
