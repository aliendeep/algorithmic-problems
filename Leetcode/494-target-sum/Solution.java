/*
You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. 
Now you have 2 symbols + and -. For each integer, you should choose one from + and 
- as its new symbol.

Find out how many ways to assign symbols to make sum of integers equal to target S.

Example 1:
Input: nums is [1, 1, 1, 1, 1], S is 3. 
Output: 5
Explanation: 

-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3

There are 5 ways to assign symbols to make the sum of nums be target 3.
Note:
The length of the given array is positive and will not exceed 20.
The sum of elements in the given array will not exceed 1000.
Your output answer is guaranteed to be fitted in a 32-bit integer.
*/

public class Solution {
    int[] nums;
    // Sum, Number of ways to make that sum
    Map<Integer, Integer>[] dp;
    int n;
    
    public int getCount(int i, int target){
        if(i < 0) return target == 0 ? 1 : 0;
        
        if(dp[i].containsKey(target))
            return dp[i].get(target);
        // +
        int ways = getCount(i-1, target - nums[i]);
        // -
        ways += getCount(i-1, target + nums[i]);
        dp[i].put(target, ways);
        return ways;
    }
    
    public int findTargetSumWays(int[] nums, int S) {
        this.nums = nums;
        n = nums.length;    
        dp = new HashMap[n];
        for(int i=0; i<n; ++i){
            dp[i] = new HashMap<>();
        }
        return getCount(n-1, S);
    }
}

// Iterative
public class Solution {
    public int findTargetSumWays(int[] nums, int targetSum) {
        if(targetSum < -1000 || targetSum > 1000)   return 0;
        int[][] dp = new int[30][2010];
        int offset = 1000;
        int n = nums.length;
        // number of ways to make 0 targetsum using 0 elements = 1
        dp[0][offset] = 1;
        // for all numbers
        for(int i=1; i<=n; ++i){
            int num = nums[i-1];
            for(int sum = -1000; sum <= 1000; ++sum){
                if(sum - num >= -1000)
                    dp[i][sum + offset] += dp[i-1][sum - num + offset];  
                if(sum + num <= 1000)
                    dp[i][sum + offset] += dp[i-1][sum + num + offset];  
            }
        }
        return dp[n][targetSum + offset];
    }
}
