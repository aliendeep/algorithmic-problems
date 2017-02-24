/*
Given an array of n positive integers and a positive integer s, find the minimal 
length of a subarray of which the sum ≥ s. If there isn't one, return 0 instead.

For example, given the array [2,3,1,2,4,3] and s = 7,
the subarray [4,3] has the minimal length under the problem constraint.

More practice:
If you have figured out the O(n) solution, try coding another solution of which 
the time complexity is O(n log n).
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

// One while loop
public class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        int minLength = Integer.MAX_VALUE;
        int sum = 0;
        int start = 0;
        for(int end=0; end<nums.length; ++end){
            sum += nums[end];
            while(sum >= s /*&& start <= end */){
                minLength = Math.min(minLength, end - start + 1);
                sum -= nums[start++];
            }
        }
        return (minLength == Integer.MAX_VALUE) ? 0 : minLength; 
    }
}

// Alternative: O(nlogn)
class Solution2 {
    // find the index of smallest number greater or equal to targetSum
    public int binarySearch(int targetSum, int[] cumsum){
        int n = cumsum.length;
        int l = 0, h = n-1;
        while(h - l > 3){
            int mid = (l+h)/2;
            if(cumsum[mid] < targetSum){
                l = mid  + 1;
            }
            else{
                h = mid;
            }
        }
        for(int i=l; i<=h; ++i){
            if(cumsum[i] >= targetSum)
                return i;
        }
        return -1;
    }
    
    public int minSubArrayLen(int s, int[] nums) {
        int n = nums.length;
        if(n == 0)          return 0;
        int[] cumsum = new int[n];
        int minLen = n+1;
        cumsum[0] = nums[0];
        if(cumsum[0] >= s)
            minLen = 1;

        for(int i=1; i<n; ++i){
            cumsum[i] = cumsum[i-1] + nums[i];
            if(cumsum[i] >= s){
                minLen = Math.min(minLen, i+1);
            }
        }
        
        // cumsum is sorted 
        for(int i=0; i<n; ++i){
            int target = s + cumsum[i];
            // cumsum[j] - cumsum[i] = s
            // cumsum[j] = s + cumsum[i]
            int index = binarySearch(target, cumsum);
            if(index != -1){
                minLen = Math.min(minLen, index - i);
            }
        }
        return minLen == n + 1 ? 0 : minLen;
    }
}
