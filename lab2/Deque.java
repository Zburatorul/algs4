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
import java.util.Iterator;

public class Deque<Item> implements Iterable<Item>
{

    private int size = 0;
    private Node first = null, last = null;

    private class LinkedListIterator implements Iterator<Item>
    {
        private Node current = first;
        public boolean hasNext() { return current != null; }

        public Item next()
        {
            if (current == null) throw new java.util.NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove()
        { throw new java.lang.UnsupportedOperationException(); }
    }

    private class Node
    {
        Item item;
        Node next = null;
        Node prev = null;
    }

    public Deque()
    {
        first = null;
        last = null;
        size = 0;
    }


    public boolean isEmpty() {
        return (first == null);
    }

    public int size() {
        return size;
    }


    private void init(Item item) {
        this.first = new Node();
        first.item = item;
        first.next = null;
        first.prev = null;
        last = first;
        size = 1;
    }

    public void addFirst(Item item) {

        if (item == null) throw new java.lang.IllegalArgumentException();

        if (first == null) {
            init(item);
            return;
        }

        // If here then list not empty
        Node n = new Node();
        n.item = item;
        n.prev = null;
        n.next = first;
        first.prev = n;
        first = n;
        size++;
    }



    public void addLast(Item item) {

        if (item == null) throw new java.lang.IllegalArgumentException();

        if (first == null) {
            init(item);
            return;
        }

        // If here then list not empty
        Node n = new Node();
        n.item = item;
        n.next = null;
        n.prev = last;
        last.next = n;
        last = n;
        size++;
    }

    public Item removeFirst() {

        if (first == null) throw new java.util.NoSuchElementException();

        Item result = first.item;
        first = first.next;
        if (first == null)
        {
            last = null;
        }
        else
        {
            first.prev = null;
        }
        size--;
        return result;
    }

    public Item removeLast() {

        if (first == null) throw new java.util.NoSuchElementException();

        Item result = last.item;
        last = last.prev;
        if (last == null)
        {
            first = null;
        }
        else
        {
            last.next = null;
        }
        
        size--;
        return result;
    }

    public Iterator<Item> iterator() {
        return new LinkedListIterator();
    }



}
