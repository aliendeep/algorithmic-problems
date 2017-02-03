/*
Given an array nums and a target value k, find the maximum length of a subarray 
that sums to k. If there isn't one, return 0 instead.

Note:
The sum of the entire nums array is guaranteed to fit within the 32-bit 
signed integer range.

Example 1:
Given nums = [1, -1, 5, -2, 3], k = 3,
return 4. (because the subarray [1, -1, 5, -2] sums to 3 and is the longest)

Example 2:
Given nums = [-2, -1, 2, 1], k = 1,
return 2. (because the subarray [-1, 2] sums to 1 and is the longest)

Follow Up:
Can you do it in O(n) time?
*/
/*
Sample Input:
[1,-1,5,-2,3]
3
[-1, 1, -1]
-1
[100]
100
[-2,0,1,-1]
0
[-1,5,6,1,0,0,-8,8,-1,3]
-1

Sample Output:
4
3
1
3
5
*/

import java.util.*;

public class Solution {
    // the sum from i to j is cumsum[j] - cumsum[i - 1] except that from 0 to j is sum[j].   
    // O(n) Solution
    public int maxSubArrayLen(int[] nums, int k) {
        int n = nums.length;
        if(n == 0)          return 0;

        int maxLen = 0;
        // 1 indexing
        int[] cumsum = new int[n+1];
        for(int i=1; i<=n; ++i){
            cumsum[i] = cumsum[i-1] + nums[i-1];
            if(cumsum[i] == k)
                maxLen = Math.max(maxLen, i);
        }
        
        Map<Integer, Integer> map = new HashMap<>();        
        for(int i=1; i<=n; ++i){
            int target = cumsum[i] - k;
            if(map.containsKey(target)){
                maxLen = Math.max(maxLen, i - map.get(target));
            }
            // contains the leftmost index (if multiple number occurs)
            if(!map.containsKey(cumsum[i]))
                map.put(cumsum[i], i);
        }
        return maxLen;
    }

    public static void main(String[] args){
        int[] a = {-2, -1, 2, 1};
        Solution ob = new Solution();
        System.out.println(ob.maxSubArrayLen(a, 1));
    }    
}

class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        int n = nums.length;
        if(n == 0)
            return 0;
            
        int[] cumsum = new int[n];
        cumsum[0] = nums[0];
        for(int i=1; i<n; i++){
            cumsum[i] = cumsum[i-1] + nums[i];
        }
        int maxLen = 0;
        // Sum to leftmost index entry
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for(int end=0; end<n; ++end){
            int target = cumsum[end] - k;
            // See if the target has occurred before
            if(map.containsKey(target)){
                maxLen = Math.max(maxLen, end - map.get(target));
            }
            // contains the left most index.
            if(!map.containsKey(cumsum[end])){
                map.put(cumsum[end], end);
            }
        }
        return maxLen;
    }
}
