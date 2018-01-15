/******************************************************************************
 *  Name:    Eugeniu Plamadeala
 *  NetID:   eugeniu
 *  Precept: P01
 *
 *  Partner Name:    N/A
 *  Partner NetID:   N/A
 *  Partner Precept: N/A
 *
 *  Description:  Model an n-by-n Percolation system using the union-find
 *                data structure.
 ******************************************************************************/
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class PercolationTest{

    public static void main(String[] args){
        int n = 0;
        Percolation grid;
        // The name of the file to open.
        String fileName = "temp.txt";
        // This will reference one line at a time
        String line = null;
        String[] tokens;
        boolean flagOpen = false, flagFull = false;

        if (args.length == 0) return;

        try {
            n =  Integer.parseInt(args[0]);
            grid = new Percolation(n);
            System.out.println("# open sites   = "+ grid.numberOfOpenSites());
            System.out.println("is Percolated  = "+ grid.percolates());

            while( ! grid.percolates()){
                grid.open(1 + StdRandom.uniform(n), 1 + StdRandom.uniform(n));
            }

            double fraction;
            fraction = 1.0 * grid.numberOfOpenSites()/ (1.0 * n * n);
            System.out.println("# open sites   = "+ grid.numberOfOpenSites());
            System.out.println("fraction open  = "+fraction);

        }
        catch (NumberFormatException nfe) {
            // Means we are giving it a filename instead
            try {
                fileName = args[0];

                // FileReader reads text files in the default encoding.
                FileReader fileReader =
                    new FileReader(fileName);

                // Always wrap FileReader in BufferedReader.
                BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

                line = bufferedReader.readLine();
                n = Integer.parseInt(line);

                grid = new Percolation(n);

                while((line = bufferedReader.readLine()) != null) {
                    //System.out.println(line);
                    tokens = line.split(" ");
                    //System.out.println("len = "+tokens.length);
                    int ind = 0;
                    int subind = 0;
                    int[] nums = new int[2];

                    while (ind < tokens.length) {
                        try {
                            nums[subind] = Integer.parseInt(tokens[ind]);
                            //System.out.println("num is = " + nums[subind]);
                            subind += 1;
                        }
                        catch (Exception e) {
                        }

                        ind += 1;
                    }


                    grid.open(nums[0], nums[1]);
                    System.out.println("opening (" + nums[0]+","+nums[1]+")");

                    if (grid.isOpen(9,1) && !flagOpen) {
                        flagOpen = true;
                        System.out.println("(9,1) is open.");
                    }

                    if (grid.isFull(9,1) && !flagFull) {
                        flagFull = true;
                        System.out.println("(9,1) is full.");
                    }
                }

                // Always close files.
                bufferedReader.close();
            }
            catch(IOException ex) {
                System.out.println(
                    "Error reading file '"
                    + fileName + "'");
                // Or we could just do this:
                // ex.printStackTrace();
            }
        }


    }

}
