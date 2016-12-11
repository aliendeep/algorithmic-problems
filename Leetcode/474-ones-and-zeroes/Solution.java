/*
In the computer world, use restricted resource you have to generate maximum 
benefit is what we always want to pursue.

For now, suppose you are a dominator of m 0s and n 1s respectively. On the other 
hand, there is an array with strings consisting of only 0s and 1s.

Now your task is to find the maximum number of strings that you can form with 
given m 0s and n 1s. Each 0 and 1 can be used at most once.

Note:
The given numbers of 0s and 1s will both not exceed 100
The size of given string array won't exceed 600.
Example 1:
Input: Array = {"10", "0001", "111001", "1", "0"}, m = 5, n = 3
Output: 4

Explanation: This are totally 4 strings can be formed by the using of 5 0s and 
3 1s, which are “10,”0001”,”1”,”0”
Example 2:
Input: Array = {"10", "0", "1"}, m = 1, n = 1
Output: 2

Explanation: You could form "10", but then you'd have nothing left. Better form 
"0" and "1".
*/
public class Solution {
    public int getValue(int[] weight0, int[] weight1, int capacity1, int capacity2, int i, int[][][] dp){
        if(capacity1 < 0 || capacity2 < 0 || i < 0)
            return 0;
        if(dp[i][capacity1][capacity2] != -1)
            return dp[i][capacity1][capacity2];
        
        // don't take
        int a = getValue(weight0, weight1, capacity1, capacity2, i-1, dp);
        int b = 0;
        if(weight0[i] <= capacity1 && weight1[i] <= capacity2){
            // take this
            b = getValue(weight0, weight1, capacity1 - weight0[i], capacity2 - weight1[i], i-1, dp) + 1;
        }
        dp[i][capacity1][capacity2] = Math.max(a, b);
        return dp[i][capacity1][capacity2];
    }

    public int findMaxForm(String[] strs, int m, int n) {
        int len = strs.length;
        if(len == 0)
            return 0;
        int[] weight0 = new int[len];        
        int[] weight1 = new int[len];        
        for(int i=0; i<len; ++i){
            String s = strs[i];
            for(int j=0; j<s.length(); ++j){
                if(s.charAt(j) == '1')
                    weight1[i]++;
                else
                    weight0[i]++;
            }
        } 
        int[][][] dp = new int[len][m+1][n+1];
        for(int[][] x : dp){
            for(int[] y : x)
                Arrays.fill(y, -1);
        }
        return getValue(weight0, weight1, m, n, len-1, dp);
    }
}
