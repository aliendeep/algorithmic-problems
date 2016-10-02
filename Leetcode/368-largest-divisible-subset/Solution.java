/*
Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) of elements 
in this subset satisfies: Si % Sj = 0 or Sj % Si = 0.

If there are multiple solutions, return any subset is fine.

Example 1:

nums: [1,2,3]

Result: [1,2] (of course, [1,3] will also be ok)
Example 2:

nums: [1,2,4,8]

Result: [1,2,4,8]
*/
// Time: O(n^2)
// Memoization
public class Solution {
    int[] dp;
    int[] parent;
    // dp[i] = Length of the longest subset whose largest element is nums[i]
    // dp[i] = 1 + dp[j] if nums[j] % nums[i] == 0 
    // Otherwise, dp[i] = 1
    public int getLongestPath(int index, int[] nums) {
        if(dp[index] != -1)
            return dp[index];

        dp[index] = 1;
        for(int i=index-1;i>=0; i--){
            if(nums[index] % nums[i] == 0){
                int l = getLongestPath(i, nums) + 1;
                if(l > dp[index]){
                    dp[index] = l;
                    parent[index] = i;
                }
            }
        }
        return dp[index];
    }

    // We can extend a subset if:
    // If the new element is larger than all other elements in the subset, then we can extend the subset if the new element is divisible by the largest element of the subset 
    // If the new element is smaller than all other elements in the subset, then if new element can divide the smallest of the subset
    public List<Integer> largestDivisibleSubset(int[] nums) {
        int n = nums.length;
        if(n == 0)
            return Collections.EMPTY_LIST;
            
        // Sort the array
        Arrays.sort(nums);
        
        dp = new int[n];
        Arrays.fill(dp, -1);

        parent = new int[n];
        int endIndex = -1;
        int maxLength = 0;
        // Add smaller element at the front
        for(int i=n-1; i>=0; i--){
            int len = getLongestPath(i, nums);
            if(maxLength < len){
                maxLength = len;
                endIndex = i;
            }
        }
        
        // maxIndex points to the first element of the largest subset
        List<Integer> r = new ArrayList<>();
        int len = dp[endIndex];
        for(int i=0; i<len; i++){
            r.add(nums[endIndex]);
            endIndex = parent[endIndex];
        }
        Collections.reverse(r);
        return r;
    }
}