/*
The n-queens puzzle is the problem of placing n queens on an n×n chessboard such 
that no two queens attack each other.

Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, 
where 'Q' and '.' both indicate a queen and an empty space respectively.

For example,
There exist two distinct solutions to the 4-queens puzzle:

[
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
*/

public class Solution {
    public void solve(int r, int n, int[] row, boolean[] col, boolean[] d1, boolean[] d2, 
                        List<List<String>> result){
        if(r == n){
            // Process Solution            
            List<String> oneSol = new ArrayList<>();
            for(int i=0; i<n; i++){
                StringBuilder s = new StringBuilder();
                for(int j=0; j<n; j++){
                    if(row[i] == j)
                        s.append("Q");
                    else
                        s.append(".");
                }
                oneSol.add(s.toString());
            }
            result.add(oneSol);
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

            solve(r+1, n, row, col, d1, d2, result);
            
            row[r] = -1;
            col[c] = false;
            d1[r+c] = false;
            d2[r-c+n-1] = false;
        }
    }   
    
    public List<List<String>> solveNQueens(int n) {
        int[] row = new int[n];
        Arrays.fill(row, -1);
        
        // All initialized to false by default
        boolean[] col = new boolean[n];
        // diagonal 1
        boolean[] d1 = new boolean[2*n];
        // diagonal 2
        boolean[] d2 = new boolean[2*n];
        
        List<List<String>> result = new ArrayList<>();
        solve(0, n, row, col, d1, d2, result);
        return result;
    }
}
