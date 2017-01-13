/*
Given a non-empty array containing only positive integers, find if the array 
can be partitioned into two subsets such that the sum of elements in both subsets is equal.

Note:
Both the array size and each of the array element will not exceed 100.

Example 1:

Input: [1, 5, 11, 5]

Output: true

Explanation: The array can be partitioned as [1, 5, 5] and [11].
Example 2:

Input: [1, 2, 3, 5]

Output: false
*/
/*
Sample Input:
[1,5,11,5]
[1, 2, 3, 5]
[28,63,95,30,39,16,36,44,37,100,61,73,32,71,100,2,37,60,23,71,53,70,69,82,97,
43,16,33,29,5,97,32,29,78,93,59,37,88,89,79,75,9,74,32,81,12,34,13,16,15,16,40,
90,70,17,78,54,81,18,92,75,74,59,18,66,62,55,19,2,67,30,25,64,84,25,76,98,59,
74,87,5,93,97,68,20,58,55,73,74,97,49,71,42,26,8,87,99,1,16,79]

Sample Output:
true
false
true
*/
// Memoization
public class Solution {
    boolean[][] dp;
    
    public boolean isSubsetSum(int[] nums, int n, int sum){
        if(sum == 0)             return true;
        if(n < 0)                return (sum == 0);
        if(dp[n][sum] == true)   return dp[n][sum];
        
        if(nums[n] > sum){
             dp[n][sum] = isSubsetSum(nums, n-1, sum);    
        }
        else{
            // Including nth number or not
            dp[n][sum] = isSubsetSum(nums, n-1, sum - nums[n]) || isSubsetSum(nums, n-1, sum);
        }
        return dp[n][sum];
    }
    
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for(int i : nums){
            sum += i;
        }
        // if sum of all of the numbers is odd, then return false;
        if(sum % 2 == 1)
            return false;
        
        int n = nums.length;
        // Both the array size and each of the array element will not exceed 100.
        dp = new boolean[n][10001];
        for(int i=0; i<n; i++)
            Arrays.fill(dp[i], false);
        
        // Calculate sum/2 and find a subset of array with sum equal to sum/2
        return isSubsetSum(nums, n-1, sum/2);
    }
}

class Solution2 {
    // Time Complexity: O(n*Sum)
    public boolean subsetSum(int[] nums, int sum){
        int n = nums.length;
        // Both the array size and each of the array element will not exceed 100.
        boolean[][] dp = new boolean[sum+1][n+1];
        for(int i=0; i<=n; i++){
            // if sum is 0, then true
            dp[0][i] = true;
        }
        
        for(int s=0; s<=sum; s++){
            // if sum is > 0 and number of elements is 0, then false
            dp[s][0] = false;
        }

        for(int s=1; s<=sum; s++){
            for(int j=1; j<=n; j++){
                dp[s][j] = dp[s][j-1];
                if(nums[j-1] <= s){
                    dp[s][j] = dp[s][j] || dp[s-nums[j-1]][j-1];
                }
            }            
        }
        return dp[sum][n];
    }
    
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for(int i : nums){
            sum += i;
        }
        // if sum of all of the numbers is odd, then return false;
        if(sum % 2 == 1)
            return false;
        
        int targetSum = sum/2;
        return subsetSum(nums, targetSum);
   }
}
