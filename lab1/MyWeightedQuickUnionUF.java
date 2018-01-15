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

// My implementation of the weighted-quick union algorithm.
 public class MyWeightedQuickUnionUF {

     private int[] id;
     private int[] sz;
     private int components;

     public MyWeightedQuickUnionUF(int n) {
         if (n <= 0)
         throw new java.lang.IllegalArgumentException("Incorrect set size given.");

         id = new int[n];
         sz = new int[n];
         components = n;

         for (int i = 0; i < n; i++) {
             id[i] = i;
             sz[i] = 1;
         }
     }

     /*
      * returns component identifier for component containing site p
      */
     public int find(int p){
         // Compress path along the way
         int i = p;

         while (i != id[i]) {
             id[i] = id[id[i]];
             i = id[i];
         }

         return i;
     }

     public void union(int p, int q) {
         int r1 = find(p);
         int r2 = find(q);

         // If already in same component, nothing to do.
         if (r1 == r2) return;

         if (sz[r1] >= sz[r2]) {
             id[r2] = r1;
             sz[r1] += sz[r2];
         }
         else {
             id[r1] = r2;
             sz[r2] += sz[r1];
         }

         components -= 1;
     }

     public boolean connected(int p, int q) {
         return (find(p) == find(q));
     }

     /*
      * Returns the number of components.
      */
     public int count() {
         return components;
     }



 }
