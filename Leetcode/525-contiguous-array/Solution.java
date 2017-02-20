/*
Given a binary array, find the maximum length of a contiguous subarray with equal 
number of 0 and 1.

Example 1:
Input: [0,1]
Output: 2
Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.
Example 2:
Input: [0,1,0]
Output: 2
Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
Note: The length of the given binary array will not exceed 50,000.
*/
public class Solution {
    public int findMaxLength(int[] nums) {
        // counter, leftmost index
        Map<Integer, Integer> map = new HashMap<>();
        int cnt = 0;
        int maxLen = 0;
        map.put(0, -1);
        
        for(int i=0; i<nums.length; ++i){
            int num = nums[i];
            if(num == 0)        cnt++;
            else                cnt--;
            if(map.containsKey(cnt))
                maxLen = Math.max(maxLen, i - map.get(cnt));
            else
                map.put(cnt, i);
        }
        return maxLen;
    }
}
