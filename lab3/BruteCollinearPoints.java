/******************************************************************************
 *  Name:    Eugeniu Plamadeala
 *  NetID:   eugeniu
 *  Precept: P01
 *
 *  Partner Name:    N/A
 *  Partner NetID:   N/A
 *  Partner Precept: N/A
 *
 *  Description:  A deque that supports adding and removing at either last.
 *
 ******************************************************************************/
import edu.princeton.cs.algs4.StdOut;

 // finds all line segments containing 4 points
 public class BruteCollinearPoints {

     private int numLineSegments = 0;
     private LineSegment[] segments = null;
     private Point[][] seen = null;


    public BruteCollinearPoints(Point[] points)
    {
        // Check that no point is null
        // and no two points are the same.
        if (points == null) throw new java.lang.IllegalArgumentException();

        for (int i = 0; i < points.length; i++)
        {
            if (points[i] == null) throw new java.lang.IllegalArgumentException();
        }

        for (int i = 0; i < points.length; i++)
        {

            for (int j = i+1; j < points.length; j++)
            {
                if (Double.compare(points[i].slopeTo(points[j]), Double.NEGATIVE_INFINITY) == 0)
                    // the points are identical
                    throw new java.lang.IllegalArgumentException();
            }
        }


        // Checks are done, noq find segements
        int n = points.length;
        Point[] a = points;
        segments = new LineSegment[4];
        seen = new Point[4][2];

        if (points.length < 4) return;

        for (int i = 0; i < n; i++)
            for (int j = i+1; j < n; j++)
                for (int k = j+1; k < n; k++)
                    for (int q = k+1; q < n; q++)
                        {
                            if (collinearQ(a[i], a[j], a[k], a[q]))
                            {
                                Point[] seg = new Point[4];
                                seg[0] = a[i];
                                seg[1] = a[j];
                                seg[2] = a[k];
                                seg[3] = a[q];


                                seg = extremalPoints(seg);
                                addLineSegment(seg[0], seg[seg.length-1]);
                            }
                        }

    }


    private void addLineSegment(Point p, Point q)
    {
        for (int i = 0; i < numLineSegments; i++)
        {
            // has this segment been seen already
            if (seen[i][0] == p && seen[i][1] == q) return;
        }

        if (numLineSegments == segments.length) doubleArray();
        segments[numLineSegments] = new LineSegment(p, q);

        Point[] t = new Point[2];
        t[0] = p;
        t[1] = q;
        seen[numLineSegments++] = t;
    }

    private void doubleArray()
    {
        LineSegment[] a = new LineSegment[2*segments.length];
        Point[][] b = new Point[2*segments.length][2];
        for (int i = 0; i < numLineSegments; i++)
        {
            a[i] = segments[i];
            b[i] = seen[i];
        }

        segments = a;
        seen = b;
    }

    private static Point[] extremalPoints(Point[] points)
    {
        Point temp = null;
        int min;

        for (int i = 0; i < points.length; i++)
        {
            min = i;
            for (int j = i; j < points.length; j++)
                if (points[min].compareTo(points[j]) < 0) min = j;
            temp = points[i];
            points[i] = points[min];
            points[min] = temp;
        }

        return points;
    }

    private boolean collinearQ(Point a, Point b, Point c, Point d)
    {
        double slope2 = a.slopeTo(c);
        double slope3 = a.slopeTo(d);
        double slope1 = a.slopeTo(b);
        return (Double.compare(slope1, slope2) == 0 && Double.compare(slope2, slope3) == 0);
    }

    /*
    private static void sort(Object [] a, Comparator<Object> comparator)
    {
        int n = a.length;
        for (int i = 0; i < n; i++)
            for (int j = i; j > 0 && less(comparator, a[j], a[j-1]); j--)
                exch(a, j, j-1);
    }

    private static boolean less(Comparator<Object> c, Object a, Object b)
    {
        return (c.compare(a, b) < 0);
    }

    private static void exch(Object[] a, int i, int j)
    {
        Object temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    */

    /**
     * Find which two of the four points lie at the ends of the line segment.
     *
     * @param  points an array of 4 collinear Point objects
     * @return an array of 2 collinear points
     */


    public int numberOfSegments()  { return numLineSegments; }
    public LineSegment[] segments()
    {
        if (numLineSegments == 0) return new LineSegment[0];

        LineSegment[] res = new LineSegment[numLineSegments];
        for (int i = 0; i < numLineSegments; i++)
            res[i] = segments[i];

        return res;
    }
}
