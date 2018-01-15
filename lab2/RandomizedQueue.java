/******************************************************************************
 *  Name:    Eugeniu Plamadeala
 *  NetID:   eugeniu
 *  Precept: P01
 *
 *  Partner Name:    N/A
 *  Partner NetID:   N/A
 *  Partner Precept: N/A
 *
 *  Description:  A randomized queue that returns a uniformly random queue
 *  element when dequeuing.
 ******************************************************************************/

import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item>
{
    private int len = 0;
    private Item[] ar = null;    // array of references to other arrays

    private class RQIterator implements Iterator<Item>
    {
        private Item[] array = null;
        private int index = 0;

        public RQIterator()
        {
            if (len == 0) return;

            array = (Item []) new Object[len];
            for (int i = 0; i < len; i++)
            {
                array[i] = ar[i];
            }
            StdRandom.shuffle(array);
        }

        public boolean hasNext()
        { return (array != null && index < array.length); }

        public Item next()
        {
            if (index >= array.length) throw new java.util.NoSuchElementException();
            Item item = array[index];
            index++;
            return item;
        }

        public void remove()
        { throw new java.lang.UnsupportedOperationException(); }
    }


    public boolean isEmpty()
    {
        return (len == 0);
    }

    public int size()
    {
        return len;
    }


    /**
     * Adds an element to the queue.
     * @param elem the new queue entry.
     */
    public void enqueue(Item item)
    {
        if (item == null) throw new java.lang.IllegalArgumentException();

        if (len == 0)
        {
            // Must create the list
            ar = (Item []) new Object[2];
            ar[0] = item;
            len = 1;
            return;
        }

        if (len == ar.length)
        {
            // Must double the list
            Item[] tempArray = (Item []) new Object[2 * ar.length];
            for (int i = 0; i < len; i++)
            {
                tempArray[i] = ar[i];
            }
            ar = tempArray;
        }

        ar[len] = item;
        len++;

    }


    public Item dequeue()
    {
        if (len == 0) throw new java.util.NoSuchElementException();

        int randomElement = StdRandom.uniform(len);
        Item result = ar[randomElement];
        ar[randomElement] = ar[len-1];
        len--;

        if (4*len <= ar.length)
        {
            // Array is a quarter full, halve it
            Item[] tempArray = (Item []) new Object[2*len];
            for (int i = 0; i < len; i++)
            {
                tempArray[i] = ar[i];
            }
            ar = tempArray;
        }
        return result;
    }

    public Item sample()
    {
        if (len == 0) throw new java.util.NoSuchElementException();

        int randomElement = StdRandom.uniform(len);
        Item result = ar[randomElement];
        return result;
    }

    public Iterator<Item> iterator()
    {
        return new RQIterator();
    }


}
