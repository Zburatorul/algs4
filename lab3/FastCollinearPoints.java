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
import java.util.Arrays;
import java.util.Comparator;
import edu.princeton.cs.algs4.StdOut;


public class FastCollinearPoints {
    /**
    *  finds all line segments containing 4 or more points
    */

    private int numLineSegments = 0;
    private LineSegment[] segments = null;
    private Point[][] seen = null;

    public FastCollinearPoints(Point[] points)
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
                if (points[i].slopeTo(points[j]) == Double.NEGATIVE_INFINITY)
                    // the points are identical
                    throw new java.lang.IllegalArgumentException();
            }
        }

        segments = new LineSegment[4];
        seen = new Point[4][2];

        int n = points.length;
        int k, q;
        for (int i = 0; i < n; i++)
        {
            Point[] a = new Point[n];
            Comparator<Point> comp = points[i].slopeOrder();
            for (int j = 0; j < n; j++) a[j] = points[j];
            Arrays.sort(a, comp);

            int testval = -8;
            if (i == testval)
            {
                for (int j = 0; j < points.length; j++)
                StdOut.println("pts = "+a[j] +" and slope= "+points[i].slopeTo(a[j]));
            }
            // StdOut.println(a[0] == points[i]);

            // The first element of the sorted array a is just points[i]
            // because we defined the point to have NEGATIVE_INFINITY slope with itself
            q = 1;
            while (q < n)
            {
                // 0 < q < k, which immediately checks whether at least 3 points are collinear
                for (k = q+1; k < n && (comp.compare(a[k], a[q]) == 0); k++)
                {
                    // StdOut.println("slope to " + points[i] +": " + a[l] + " = " + a[k]);
                }

                if (k > q+2)
                {
                    // at least four collinear points (including a[0])
                    // but need to find the extrema
                    Point[] seq = new Point[k-q+1];
                    seq[0] = a[0];
                    // copy rest of points into array of collinear points
                    for (int j = 0; j < k-q; j++) seq[j+1] = a[j+q];
                    if (i == testval) for (int j = 0; j < seq.length; j++) StdOut.println(seq[j]);
                    seq = extremalPoints(seq);
                    if (i == testval) StdOut.println("sorted are");
                    if (i == testval) for (int j = 0; j < seq.length; j++) StdOut.println(seq[j]);
                    addLineSegment(seq[0], seq[seq.length-1]);

                }
                // proceed from the new distinct slope
                q = k;
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

    private void mergeSort(Point[] points, Point[] aux, int lo, int hi)
    {
        if (hi <= lo) return;

        int mid = lo + (hi-lo)/2;
        mergeSort(aux, points, lo, mid);
        mergeSort(aux, points, mid+1, hi);
        merge(points, aux, lo, mid, hi);
    }

    private void merge(Point[] a, Point[] aux, int lo, int mid, int hi)
    {
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++)
        {
            if (i > mid)    aux[k] = a[j++];
            else if (j > hi)    aux[k] = a[i++];
            else if (a[j].compareTo(a[i]) < 0) aux[k] = a[j++];
            else aux[k] = a[i++];
        }
    }

    private void insertionSort(Point[] pts, int low, int high)
    {
        Point temp = null;
        int min;

        for (int i = low; i <= high; i++)
        {
            min = i;
            for (int j = i+1; j <= high; j++)
                if (pts[min].compareTo(pts[j]) < 0) min = j;
            temp = pts[i];
            pts[i] = pts[min];
            pts[min] = temp;
        }
    }

    private Point[] extremalPoints(Point[] points)
    {
        if (points.length < 9)
        {
            insertionSort(points, 0, points.length-1);
        }
        else
        {
            Point[] aux = new Point[points.length];
            for (int j = 0; j < points.length; j++) aux[j] = points[j];
            mergeSort(points, aux, 0, points.length-1);
            points = aux;
        }
        return points;
    }

    public int numberOfSegments() { return numLineSegments; }
    public LineSegment[] segments()
    {
        if (numLineSegments == 0) return new LineSegment[0];

        LineSegment[] res = new LineSegment[numLineSegments];
        for (int i = 0; i < numLineSegments; i++)
            res[i] = segments[i];

        return res;
    }
}
