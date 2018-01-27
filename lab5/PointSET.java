/******************************************************************************
 *  Name:    Eugeniu Plamadeala
 *  NetID:   eugeniu
 *  Precept: P01
 *
 *  Partner Name:    N/A
 *  Partner NetID:   N/A
 *  Partner Precept: N/A
 *
 *  Description:
 *
 ******************************************************************************/
import java.util.TreeSet;
import java.util.Iterator;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;



public class PointSET {

    private TreeSet root;

    public         PointSET() { root = new TreeSet(); }

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
            root = new TreeSet();
            root.insert(p);
            return;
        }

        if (! root.contains(p)) root.insert(p);
    }

    // does the set contain point p?
    public           boolean contains(Point2D p)
    {
        if (p == null) throw new java.lang.IllegalArgumentException();();
        return (root == null ? false : root.contains(p));
    }

    // draw all points to standard draw
    public              void draw()
    {
        if (root == null) return;
        Iterator<Point2D> it = root.iterator();

        while (it.hasNext())
        {
            StdDraw.println(it.next());
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
        
    }
    public           Point2D nearest(Point2D p)             // a nearest neighbor in the set to point p; null if the set is empty

    public static void main(String[] args)                  // unit testing of the methods (optional)
}
