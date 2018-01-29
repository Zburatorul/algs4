/******************************************************************************
 *  Name:    Eugeniu Plamadeala
 *  NetID:   eugeniu
 *  Precept: P01
 *
 *  Partner Name:    N/A
 *  Partner NetID:   N/A
 *  Partner Precept: N/A
 *
 *  Description: Represents a set of points in the unit square.
 *  supports a range search, and nearest neighbor search.
 *  Uses a Red-Black tree internally to store the points.
 ******************************************************************************/
import java.util.TreeSet;
import java.util.Iterator;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Stack;


public class PointSET {

    private TreeSet<Point2D> root;

    public         PointSET() { root = new TreeSet<Point2D>(); }

    public  boolean isEmpty()
    {
        if (root == null) return true;
        return root.isEmpty();
    }
    public               int size()
    {
        if (root == null) return 0;
        return root.size();
    }

    // add the point to the set (if it is not already in the set)
    public              void insert(Point2D p)
    {
        if (p == null) throw new java.lang.IllegalArgumentException();
        if (root == null)
        {
            root = new TreeSet<Point2D>();
            root.add(p);
            return;
        }

        if (! root.contains(p)) root.add(p);
    }

    // does the set contain point p?
    public           boolean contains(Point2D p)
    {
        if (p == null) throw new java.lang.IllegalArgumentException();
        return (root == null ? false : root.contains(p));
    }

    // draw all points to standard draw
    public              void draw()
    {
        if (root == null) return;
        Iterator<Point2D> it = root.iterator();

        while (it.hasNext())
        {
            Point2D p = it.next();
            StdDraw.point(p.x(), p.y());
        }
    }

    private void nullQ(Object pointer)
    {
        if (pointer == null) throw new java.lang.IllegalArgumentException();
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect)
    {
        nullQ(rect);

        Stack<Point2D> stack = new Stack<Point2D>();
        Iterator<Point2D> it = root.iterator();

        while (it.hasNext())
        {
            Point2D p = it.next();
            if (rect.contains(p)) stack.push(p);
        }
        return stack;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public           Point2D nearest(Point2D p)
    {
        nullQ(p);
        Iterator<Point2D> it = root.iterator();
        Point2D closest = null;
        double dist = Double.POSITIVE_INFINITY;

        while (it.hasNext())
        {
            Point2D curr = it.next();
            if (dist > p.distanceTo(curr))
            {
                dist = p.distanceTo(curr);
                closest = curr;
            }
        }

        return closest;
    }

}
