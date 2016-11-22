/*
Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) of elements in this subset satisfies: Si % Sj = 0 or Sj % Si = 0.

If there are multiple solutions, return any subset is fine.

Example 1:

nums: [1,2,3]

Result: [1,2] (of course, [1,3] will also be ok)
Example 2:

nums: [1,2,4,8]

Result: [1,2,4,8]
*/

// Memoization
// Alternative
public class Solution {
    int[] dp;
    int[] parent;
    public int getLongestPath(int index, int[] nums) {
        if(dp[index] != -1)
            return dp[index];

        dp[index] = 1;
        for(int i=index+1;i<nums.length; i++){
            if(nums[i] % nums[index] == 0){
                int l = getLongestPath(i, nums) + 1;
                if(l > dp[index]){
                    dp[index] = l;
                    parent[index] = i;
                }
            }
        }
        return dp[index];
    }

    public List<Integer> largestDivisibleSubset(int[] nums) {
        int n = nums.length;
        if(n == 0)
            return Collections.EMPTY_LIST;
            
        // Sort the array
        Arrays.sort(nums);
        
        dp = new int[n];
        Arrays.fill(dp, -1);

        parent = new int[n];
        int startIndex = -1;
        int maxLength = 0;
        for(int i=0; i<n; i++){
            int len = getLongestPath(i, nums);
            if(maxLength < len){
                maxLength = len;
                startIndex = i;
            }
        }
        
        // maxIndex points to the first element of the largest subset
        List<Integer> r = new ArrayList<>();
        int len = dp[startIndex];
        for(int i=0; i<len; i++){
            r.add(nums[startIndex]);
            startIndex = parent[startIndex];
        }
        return r;
    }
}