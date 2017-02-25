/*
Given an array nums containing n + 1 integers where each integer is between 1 and n 
(inclusive), prove that at least one duplicate number must exist. Assume that 
there is only one duplicate number, find the duplicate one.

Note:
You must not modify the array (assume the array is read only).
You must use only constant, O(1) extra space.
Your runtime complexity should be less than O(n^2).
There is only one duplicate number in the array, but it could be repeated 
more than once.
*/
// O(n) Time Solution (Similar to find cycle in a linked list)
// O(1) Space
public class Solution {
    // https://discuss.leetcode.com/topic/25685/java-o-n-time-and-o-1-space-solution-similar-to-find-loop-in-linkedlist
    public int findDuplicate(int[] nums) {
       int slow = nums[0]; 
       int fast = nums[nums[0]];
       while(slow != fast){
           slow = nums[slow];
           fast = nums[nums[fast]];
       }
       // Find the entry point
       fast = 0;
       while(slow != fast){
           fast = nums[fast];
           slow = nums[slow];
       }
       return slow;
    }
}

// O(1) Space
// Binary Search
class Solution2 {
    // O(nlogn) Solution
    // Pigeonhole principle
    public int cntElementsLessOrEqual(int[] nums, int target){
        int cnt = 0;
        for(int n : nums){
            if(n <= target)
                cnt++;
        }
        return cnt;
    }
    public int cntElements(int[] nums, int target){
        int cnt = 0;
        for(int n : nums){
            if(n == target)
                cnt++;
        }
        return cnt;
    }
    
    public int findDuplicate(int[] nums) {
        int n = nums.length;
        if(n == 0)
            return -1;
        int low = 1;
        int high = n - 1;
        while(high - low > 3){
            int mid = (high - low)/2 + low;
            int cnt  = cntElementsLessOrEqual(nums, mid);
            if(cnt <= mid){
                low = mid + 1;
            }
            // cnt > mid
            else{
                high = mid;
            }
        }
        for(int dup = low; dup <= high; ++dup){
            if(cntElements(nums, dup) > 1)
                return dup;
        }
        return -1;
    }
}
