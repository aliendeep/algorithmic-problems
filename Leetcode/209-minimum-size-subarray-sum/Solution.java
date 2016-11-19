/*
Given an array of n positive integers and a positive integer s, find the minimal length of a subarray of which the sum ≥ s. If there isn't one, return 0 instead.

For example, given the array [2,3,1,2,4,3] and s = 7,
the subarray [4,3] has the minimal length under the problem constraint.

click to show more practice.

More practice:
If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).
*/

// O(n) Solution
public class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int start = 0;
        int minLength = Integer.MAX_VALUE;
        int i = 0;
        int sum = 0;
        int n = nums.length;
        while(i < n){
            if(sum < target)
                sum += nums[i++];
            if(sum >= target && start < n){
                minLength = Math.min(minLength, i-start);
                sum -= nums[start++];
            }
        }
        
        // Last window        
        while(sum >= target && start < n){
            minLength = Math.min(minLength, i-start);
            sum -= nums[start++];
        }
        return (minLength == Integer.MAX_VALUE) ? 0 : minLength; 
    }
}