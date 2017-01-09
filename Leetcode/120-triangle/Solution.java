/*
Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

For example, given the following triangle
[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

Note:
Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
*/

// [[2],[3,4],[6,5,7],[4,1,8,3]]

public class Solution {
    // dp[i][j] = min(triangle[i+1][j], triangle[i+1][j]);
    // Space: O(n^2)
    public int minimumTotal(List<List<Integer>> triangle) {
        int nrow = triangle.size();
        if(nrow == 0)
            return 0;
            
        int[][] dp = new int[nrow][nrow];
        // for all rows
        for(int i=0; i<nrow; i++){
            List<Integer> row = triangle.get(i);
            if(i == 0){
                dp[0][0] = row.get(0);
                continue;
            }
            // only one option, first column
            dp[i][0] = dp[i-1][0] + row.get(0);
            for(int j=1; j<row.size()-1; j++){
                dp[i][j] = Math.min(dp[i-1][j-1], dp[i-1][j]) + row.get(j);
            }   
            // last column, only one option
            dp[i][row.size()-1] = dp[i-1][row.size()-2] + row.get(row.size()-1);
        }
        int minSum = Integer.MAX_VALUE;
        // last row
        for(int j=0; j<nrow; j++){
            minSum = Math.min(minSum, dp[nrow-1][j]);
        }
        return minSum;
    }
}

class Solution2 {
    public int minimumTotal(List<List<Integer>> triangle) {
        int r = triangle.size();
        if(r == 0)
            return 0;
        int[][] dp = new int[r][r+1];
        
        dp[0][0] = triangle.get(0).get(0);
        for(int i=1; i<r; ++i){
            for(int j=0; j<=i; ++j){
                int a = Integer.MAX_VALUE;
                if(j < i) 
                    a = dp[i-1][j];
                int b = Integer.MAX_VALUE;
                if(j > 0)
                    b = dp[i-1][j-1];
                dp[i][j] = Math.min(a, b) + triangle.get(i).get(j);
            }
        }
        
        int min = Integer.MAX_VALUE;
        for(int j=0; j<r; ++j){
            min = Math.min(min, dp[r-1][j]);
        }
        return min;
    }
}

class Solution3 {
    // O(n) space
    // Only need the previous row info
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        if(n == 0)
            return 0;
        
        List<Integer> prev = new ArrayList<>();
        for(int i=0; i<n; i++){
            List<Integer> row = triangle.get(i);
            if(i == 0){
                prev.add(row.get(0));
                continue;
            }
            List cur = new ArrayList<>();
            // first column, only one option
            cur.add(prev.get(0) + row.get(0));
            for(int j=1; j<row.size()-1; j++){
                int minVal = Math.min(prev.get(j-1), prev.get(j)) + row.get(j);        
                cur.add(minVal);
            }
            // last column, only one option
            cur.add(prev.get(row.size()-2) + row.get(row.size()-1));
            // Assign cur to prev
            prev = cur;
        }
        
        int minSum = Integer.MAX_VALUE;
        for(int j=0; j<prev.size(); j++){
            minSum = Math.min(minSum, prev.get(j));
        }
        return minSum;
    }
}

// Memoization
class Solution4 {
    List<List<Integer>> grid;
    int[][] dp;
    
    public int getMinPath(int r, int c){
        if(r < 0 || c < 0)      return Integer.MAX_VALUE;
        if(c > r)               return Integer.MAX_VALUE;
        if(r == 0 && c == 0)    return grid.get(r).get(c);
        if(dp[r][c] != Integer.MAX_VALUE)
            return dp[r][c];
        
        dp[r][c] = grid.get(r).get(c) + Math.min(getMinPath(r-1, c-1), getMinPath(r-1, c));
        return dp[r][c];
    }
    
    public int minimumTotal(List<List<Integer>> triangle) {
        int r = triangle.size();
        if(r == 0)
            return 0;
        grid = triangle; 
        dp = new int[r][r];
        for(int[] t : dp){
            Arrays.fill(t, Integer.MAX_VALUE);
        }
        int ans = Integer.MAX_VALUE;
        for(int i=0; i<r; ++i){
            ans = Math.min(ans, getMinPath(r-1, i));
        }
        return ans;
    }
}
