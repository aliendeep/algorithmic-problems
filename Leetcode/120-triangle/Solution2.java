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
public class Solution {
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