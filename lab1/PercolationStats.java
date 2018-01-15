/******************************************************************************
 *  Name:    Eugeniu Plamadeala
 *  NetID:   eugeniu
 *  Precept: P01
 *
 *  Partner Name:    N/A
 *  Partner NetID:   N/A
 *  Partner Precept: N/A
 *
 *  Description:  Model an n-by-n percolation system using the union-find
 *                data structure.
 ******************************************************************************/
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
// import edu.princeton.cs.algs4.Stopwatch;


public class PercolationStats {

    private static final double WIDTH95 = 1.96;
    private final int trials;
    private final double mean;
    private final double sigma2;

    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int numTrials) {
        if (n <= 0 || numTrials <= 0)
            throw new java.lang.IllegalArgumentException("n <= 0 or trails <= 0.");

        // Initialize
        trials = numTrials;
        double[] fraction = new double[trials];
        int i = 0;
        Percolation perc;

        // Now run the Monte-Carlo simulation?
        while (numTrials > 0) {
            // Run a single trial.
            perc = new Percolation(n);
            while (!perc.percolates()) {
                // open a site at random
                perc.open(1 + StdRandom.uniform(n), 1 + StdRandom.uniform(n));
            }
            fraction[i] = (1.0 * perc.numberOfOpenSites()) / (1.0 * n * n);

            numTrials -= 1;
            i += 1;
        }

        this.mean = StdStats.mean(fraction);
        this.sigma2 = StdStats.stddev(fraction);


    }
    // sample mean of percolation threshold
    public double mean() {
        return this.mean;
    }
    public double stddev() {
        return sigma2;
    }
     // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return (this.mean - WIDTH95 * java.lang.Math.sqrt(sigma2 / trials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return (this.mean + WIDTH95 * java.lang.Math.sqrt(sigma2 / trials));
    }

    // test client (described below)
    public static void main(String[] args) {
        int num = -1, nTrials = -1;
        int len = 0;

        if (args.length < 2) {
            System.out.println("Must provide at least 2 arguments.");
            return;
        }

        try {
            num = Integer.parseInt(args[0]);
            nTrials = Integer.parseInt(args[1]);
            len = nTrials;
        }
        catch (NumberFormatException nfe) {
            System.out.println("The first and second arguments must be integers.");
            return;
        }

        if (num <= 0 && nTrials <= 0)
            throw new java.lang.IllegalArgumentException("n <= 0 or trails <= 0.");

        // Initialize
        double[] fraction = new double[nTrials];
        int i = 0;
        Percolation perc;

        // Now run the Monte-Carlo simulation?
        // Stopwatch swatch = new Stopwatch();
        while (nTrials > 0) {
            // Run a single trial.
            perc = new Percolation(num);
            while (!perc.percolates()) {
                // open a site at random
                perc.open(1 + StdRandom.uniform(num), 1 + StdRandom.uniform(num));
            }
            fraction[i] = (1.0 * perc.numberOfOpenSites()) / (1.0 * num * num);

            nTrials -= 1;
            i += 1;
        }

        // System.out.println("(n,T)=("+num+","+len+") instance ran in time = "+(swatch.elapsedTime()));

        double mean = StdStats.mean(fraction);
        double sigma2 = StdStats.stddev(fraction);
        double low = mean - WIDTH95 * java.lang.Math.sqrt(sigma2 / len);
        double high = mean + WIDTH95 * java.lang.Math.sqrt(sigma2 / len);

        System.out.println("mean                    = "+ mean);
        System.out.println("stddev                  = "+ sigma2);
        System.out.println("95% confidence interval = ["+ low  +", "+high+"]");
    }
}
