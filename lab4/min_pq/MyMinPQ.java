/******************************************************************************
 *  Name:    Eugeniu Plamadeala
 *  NetID:   eugeniu
 *  Precept: P01
 *
 *  Partner Name:    N/A
 *  Partner NetID:   N/A
 *  Partner Precept: N/A
 *
 *  Description:  Min-ordered priority queue.
 *
 ******************************************************************************/

public class MinPQ<Key extends Comparable<Key>>
{
    private Key[] pq = new Key[4];
    private int N = 0; // this is the number of elements in the heap


    private class MinPQIterator<Key> implements Iterator<Key>
    {
        private Key[] b = null;
        private int index = 1, localN = N;

        public MinPQIterator()
        {
            for (int i = 0; i <= N; i++) b[i] = pq[i];
        }
        public boolean hasNext( return (localN > 0); )

        public Key next()
        {
            if (localN == 0) throw new java.util.NoSuchElementException();
            Key res = b[1];
            exchIterator(1, N--);
            sinkInterator(1);
            return res;
        }

        private sinkInterator(int k)
        {
            while (k <= localN)
            {
                int j = 2k;
                if (j+1 <= localN && lessIterator(j, j+1)) j++;
                if (!lessIterator(k, j)) exchIterator(k,j);
                k = j;
            }
        }

        private lessIterator(int i, int j)
        {
            return b[j].compareTo(b[i]);
        }

        private exchIterator(int i, int j)
        {
            Key temp = b[i];
            b[i] = b[j];
            b[j] = temp;
        }


        public void remove() { throw new java.lang.UnsupportedOperationException(); }
    }

    public MinPQ(Key[] keys)
    {
        for (int i = 0; i < keys.length; i++) insert(keys[i]);
    }

    public MinPQ(int capacity)
    {
        pq = (Key []) new Comparable[capacity + 1];
    }

    private doubleArray()
    {
        Key[] t = new Key[2*a.length];
        for (int i = 0; i <= N; i++) t[i] = pq[i];
        pq =  t;
    }

    private halveArray()
    {
        Key[] t = new Key[2*N];
        for (int i = 0; i <= N; i++) t[i] = pq[i];
        pq =  t;
    }

    private swim(int k)
    {
        while (k > 1 && less(k/2, k))
        {
            exch(k/2, k);
            k = k/2;
        }
    }

    private sink(int k)
    {
        while (k <= N)
        {
            int j = 2k;
            if (j+1 <= N && less(j, j+1)) j++;
            if (!less(k, j)) exch(k,j);
            k = j;
        }
    }


    private less(int i, int j)
    {
        return pq[j].compareTo(pq[i]);
    }

    private exch(int i, int j)
    {
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    public Key delMin()
    {
        if (N == 0) throw new java.util.NoSuchElementException();
        Key res = pq[1];
        exch(1, N--);
        sink(1);
        pq[N+1] = null;
        if (N < pq.length/4) halveArray();
        return res;
    }

    public min()
    {
        return pq[1];
    }

    public insert(Key x)
    {
        if (N == pq.length - 1) doubleArray();
        pq[++N] = x;
        swim(N);
    }

    public boolean isEmpty()
    {
        return (N == 0);
    }

    public size()
    {
        return (N-1);
    }

    public Iterator<Key> iterator()
    {
        return new MinPQIterator();
    }

}
