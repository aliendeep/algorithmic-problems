/*
Given a 2 * N Grid of numbers, choose numbers such that the sum of the numbers
is maximum and no two chosen numbers are adjacent horizontally, vertically or diagonally, and return it.

Example:

Grid:
  1 2 3 4
  2 3 4 5
so we will choose
3 and 5 so sum will be 3 + 5 = 8
*/

/*
V : 
1 |  2  |  3  | 4
2 |  3  |  4  | 5

Lets first try to reduce it into a simpler problem. 
We know that within a column, we can choose at max 1 element. 
And choosing either of those elements is going to rule out choosing anything from the previous or next column. 
This means that choosing V[0][i] or V[1][i] has identical bearing on the elements which are ruled out. 
So, instead we replace each column with a single element which is the max of V[0][i], V[1][i].

Now we have the list as : 
2 3 4 5

Here we can see that we have reduced our problem into another simpler problem.
Now we want to find maximum sum of values where no 2 values are adjacent. 
Now our recurrence relation will depend only on position i and,
 a "include_current_element" which will denote whether we picked last element or not.
  
MAX_SUM(pos, include_current_element) = 
IF include_current_element = FALSE THEN   
  max | MAX_SUM(pos - 1, FALSE) 
      | 
      | MAX_SUM(pos - 1, TRUE)

ELSE    |
  MAX_SUM(pos - 1, FALSE) + val(pos) 

*/

public class MaxSumWithoutAdjacentElements {
  public int adjacent(ArrayList<ArrayList<Integer>> a) {
      int r = a.size();
      if(r == 0)
          return 0;
      int n = a.get(0).size();
      int[] dp = new int[n];
      
      ArrayList<Integer> x = a.get(0);
      ArrayList<Integer> y = a.get(1);
      dp[0] = Math.max(x.get(0), y.get(0));
      if(n == 1)
          return dp[0];
        dp[1] = Math.max(dp[0], Math.max(x.get(1), y.get(1)));
        
        for(int i=2; i<n; i++){
            // include this column
            dp[i] = Math.max(x.get(i), y.get(i)) + dp[i-2];
            // Max of excluding this column and including this col 
            dp[i] = Math.max(dp[i-1], dp[i]);
        } 
        return dp[n-1];
  }
}
