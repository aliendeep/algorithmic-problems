/*
Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets 
such that the sum of elements in both subsets is equal.
*/

// Memoization
class PartitionSubsetSumMemoization{
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

public class PartitionSubsetSum {
    // Time Complexity: O(n*Sum)
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