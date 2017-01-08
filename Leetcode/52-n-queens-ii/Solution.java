/*
Follow up for N-Queens problem.

Now, instead outputting board configurations, return the total number of 
distinct solutions.
*/

public class Solution {
    public int solCnt = 0;
    public void solve(int r, int n, int[] row, boolean[] col, boolean[] d1, boolean[] d2){
        if(r == n){
            solCnt++;
            return;
        }
        
        // for all rows, try to place the queen
        for(int c=0; c<n; c++){
            if(col[c] || d1[r+c] || d2[r-c+n-1])
                continue;
            // place the queen
            row[r] = c;
            col[c] = true;
            d1[r+c] = true;
            d2[r-c+n-1] = true;

            solve(r+1, n, row, col, d1, d2);
            
            row[r] = -1;
            col[c] = false;
            d1[r+c] = false;
            d2[r-c+n-1] = false;
        }
    }   
    public int totalNQueens(int n){
        int[] row = new int[n];
        Arrays.fill(row, -1);
        
        // All initialized to false by default
        boolean[] col = new boolean[n];
        // diagonal 1
        boolean[] d1 = new boolean[2*n];
        // diagonal 2
        boolean[] d2 = new boolean[2*n];
        
        solCnt = 0;
        solve(0, n, row, col, d1, d2);
        return solCnt;
    }
}
