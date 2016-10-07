/*
Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.

Note:
The sum of the entire nums array is guaranteed to fit within the 32-bit signed integer range.

Example 1:
Given nums = [1, -1, 5, -2, 3], k = 3,
return 4. (because the subarray [1, -1, 5, -2] sums to 3 and is the longest)

Example 2:
Given nums = [-2, -1, 2, 1], k = 1,
return 2. (because the subarray [-1, 2] sums to 1 and is the longest)

Follow Up:
Can you do it in O(n) time?
*/

public class Solution {
    // nums[i] = Sum of elements from 0 to i inclusively
    // Sum of elements from i to j is nums[j] - nums[i - 1] 
    // Exception: Sum of elements from 0 to i: dp[i]
    public int maxSubArrayLen(int[] nums, int k) {
        int n = nums.length;
        for(int i=1; i<n; i++){
            nums[i] += nums[i-1];
        }
        int maxLen = 0;
        // Sum to leftmost index entry
        Map<Integer, Integer> map = new HashMap<>();
        // to make sum from 0 to i consistent
        map.put(0, -1);
        for(int end=0; end<n; end++){
            int target = nums[end] - k;
            // See if the target has occurred before
            if(map.containsKey(target)){
                maxLen = Math.max(maxLen, end - map.get(target));
            }
            // contains the left most index.
            if(!map.containsKey(nums[end])){
                map.put(nums[end], end);
            }
        }
        return maxLen;
    }
}