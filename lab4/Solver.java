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
import java.util.Iterator;
import java.util.Comparator;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;


public class Solver {

    private Node solution;
    private boolean solvable;

    private class Node
    {
        public Board b;
        public int moves;
        public Node prev;
        public int dist;

        public Node(Board b, int moves, Node prev)
        {
            this.b = b;
            this.moves = moves;
            this.prev = prev;
            this.dist = b.manhattan();
        }
    }

    private class BoardComparator implements Comparator<Node>
    {
        public int compare(Node p, Node q)
        {
            return (p.dist + p.moves - q.dist - q.moves);
        }
    }

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial)
    {
        if (initial == null) throw new java.lang.IllegalArgumentException();

        Comparator<Node> comp = new BoardComparator();
        MinPQ<Node> pq = new MinPQ<Node>(comp);
        MinPQ<Node> pqSwap = new MinPQ<Node>(comp);

        Node node = new Node(initial, 0, null);
        Node nodeSwap = new Node(initial.twin(), 0, null);
        // swap two elements

        pq.insert(node);
        pqSwap.insert(nodeSwap);

        node = pq.delMin();
        nodeSwap = pqSwap.delMin();

        int moves = 0;
        while (!node.b.isGoal() && !nodeSwap.b.isGoal())
        {
            moves = node.moves + 1;
            Iterator<Board> it = node.b.neighbors().iterator();
            Iterator<Board> itSwap = nodeSwap.b.neighbors().iterator();

            // StdOut.println("About to add neighboring boards.");
            while (it.hasNext())
            {
                Board b = it.next();
                // Don't enq a board if it was predecessor board.

                if ((node.prev != null && !node.prev.b.equals(b)) || node.prev == null)
                {
                    Node nn = new Node(b, moves, node);
                    pq.insert(nn);
                    // StdOut.println("Enq-ing:");
                    // StdOut.println(b.toString());

                }
            }


            while (itSwap.hasNext())
            {
                Board b = itSwap.next();
                // Don't enq a board if it was predecessor board.

                if ((nodeSwap.prev != null && !nodeSwap.prev.b.equals(b)) || nodeSwap.prev == null)
                {
                    Node nn = new Node(b, moves, nodeSwap);
                    pqSwap.insert(nn);
                    // StdOut.println("Enq-ing:");
                    // StdOut.println(b.toString());

                }
            }

            node = pq.delMin();
            nodeSwap = pqSwap.delMin();
        }

        solvable = !nodeSwap.b.isGoal();
        if (!solvable) return;

        // If here, means we found a solution.
        solution = node;

    }

    public boolean isSolvable()
    {
        /*
         * Need to compute the parity of the permutations of 1..n^2.
         * A cycle with no fixed-points of length k+1 is made of k transpositions and therefore has
         * parity (-1)^k.
         * Cannot be done because Board does not expose blocks. Must do trial and error.
         */

        return solvable; // for now
    }

    public int moves()
    {
        if (!isSolvable()) return -1;
        return solution.moves;
    }


    public Iterable<Board> solution()
    {
         // sequence of boards in a shortest solution; null if unsolvable
        if (!isSolvable()) return null;
        Stack<Board> stack = new Stack<Board>();

        Node nn = solution;
        while (nn != null)
        {
            stack.push(nn.b);
            nn = nn.prev;
        }

        return stack;

    }
}
