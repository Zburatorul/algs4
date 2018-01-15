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
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class SolverClient
{
    public static void main(String[] args) {

        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);


        int[][] tblocks = new int[5][5];
        for (int i = 0; i < 5; i++)
        for (int j = 0; j < 5; j++)
        {
            tblocks[i][j] = 5*i + j+1;
        }
        tblocks[4][4] = 0;
        Board tb = new Board(tblocks);
        StdOut.println(tb.toString());
        StdOut.println("Manhattan distance = " + tb.manhattan());

        // solve the puzzle
        Solver solver = new Solver(initial);

        double a = Double.NaN; Double x = Double.NaN;
        double b = Double.NaN; Double y = Double.NaN;
        StdOut.println("a==b gives " + (a==b));
        StdOut.println("equals gives " + x.equals(y));

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
