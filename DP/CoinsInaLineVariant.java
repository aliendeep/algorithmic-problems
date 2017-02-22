/*
https://www.interviewbit.com/problems/coins-in-a-line/
There are N coins (Assume N is even) in a line. Two players take turns to take 
a coin from one of the ends of the line until there are no more coins left. 
The player with the larger amount of money wins. Assume that you go first.

Write a program which computes the maximum amount of money you can win.

Example:

suppose that there are 4 coins which have value
1 2 3 4
now you are first so you pick 4
then in next term
next person picks 3 then
you pick 2 and
then next person picks 1
so total of your money is 4 + 2 = 6
next/opposite person will get 1 + 3 = 4
so maximum amount of value you can get is 6
*/

public class Solution {
    public int getValue(ArrayList<Integer> values, int start, int end, int[][] dp){
        if(start > end)
            return 0;
        if(start == end)
            return values.get(start);
            
        if(dp[start][end] != -1)
            return dp[start][end];
            
        // take the start coin
        // next player can take either start + 1 coin or end coin
        // Current player can pick up the second optimal coin
        int x = values.get(start) + 
                Math.min(getValue(values, start+2, end, dp), getValue(values, start+1, end-1, dp));
        // take the end coin
        // next player can take either start coin or end-2 coin
        int y = values.get(end) + 
                Math.min(getValue(values, start+1, end-1, dp), getValue(values, start, end-2, dp));
                
        dp[start][end] = Math.max(x, y);
        return dp[start][end];
    }
    public int maxcoin(ArrayList<Integer> a) {
        int n = a.size();
        if(n == 0)
            return 0;
        int[][] dp = new int[n][n];
        for(int[] t : dp)
            Arrays.fill(t, -1);
        
        return getValue(a, 0, n-1, dp);
  }
}

class Solution2{
    int[] cumSum;
    int[][] dp;
    ArrayList<Integer> values;
    
    int sum(int i, int j){
        return cumSum[j] - (i > 0 ? cumSum[i-1] : 0);         
    }
    
    public int getValue(int i, int j){
        if(i > j)
            return 0;
        if(i == j)
            return values.get(i);
            
        if(dp[i][j] != -1)
            return dp[i][j];
        
        // Get values return the optimal coin to be picked up in range[i..j]
        // Take ith item        
        int x = sum(i, j) - getValue(i+1, j);
        // Take jth item        
        int y = sum(i, j) - getValue(i, j-1);
        dp[i][j] = Math.max(x, y);
        return dp[i][j];
    }
    public int maxcoin(ArrayList<Integer> a) {
        values = a;
        int n = a.size();
        if(n == 0)
            return 0;
        cumSum = new int[n];
        cumSum[0] = a.get(0);
        for(int i=1; i<n; ++i){
            cumSum[i] = cumSum[i-1] + a.get(i); 
        }

        dp = new int[n][n];
        for(int[] t : dp)
            Arrays.fill(t, -1);
        
        return getValue(0, n-1);
    }
}
