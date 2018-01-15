/******************************************************************************
 *  Name:    Eugeniu Plamadeala
 *  NetID:   eugeniu
 *  Precept: P01
 *
 *  Partner Name:    N/A
 *  Partner NetID:   N/A
 *  Partner Precept: N/A
 *
 *  Description:  takes an integer k as a command-line argument;
 *  reads in a sequence of strings from standard input using StdIn.readString()
 *  and prints exactly k of them, uniformly at random
 ******************************************************************************/
import edu.princeton.cs.algs4.StdIn;


public class Permutation
{
    public static void main(String[] args)
    {
        int k = 0;

        if (args.length == 0) return;

        try {
            k = Integer.parseInt(args[0]);
            if (k < 0) throw new java.lang.IllegalArgumentException();
        }
        catch (NumberFormatException nfe) {
            System.out.println("The first argument must be integer.");
            return;
        }

        RandomizedQueue<String> dec = new RandomizedQueue<String>();
        while (!StdIn.isEmpty())
        {
            String line = StdIn.readString();
            dec.enqueue(line);
        }

        // Now generate permutation
        for (int i = 0; i < k; i++)
        {
            System.out.println(dec.dequeue());
        }
    }
}
