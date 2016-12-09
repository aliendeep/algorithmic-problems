/*
Given an array nums containing n + 1 integers where each integer is between 1 and n 
(inclusive), prove that at least one duplicate number must exist. Assume that 
there is only one duplicate number, find the duplicate one.

Note:
You must not modify the array (assume the array is read only).
You must use only constant, O(1) extra space.
Your runtime complexity should be less than O(n2).
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
class Solution2 {
    // O(nlogn) Solution
    // Pigeonhole principle
    public int findDuplicate(int[] nums) {
        int n = nums.length;
        if(n == 0)
            return -1;
        int low = 1;
        int high = n - 1;
        while(low < high){
            int mid = (high - low)/2 + low;
            int cnt  = 0;
            // Count number of items < mid
            for(int i : nums){
                if(i <= mid)
                    cnt++;
            }
            // duplicate number is on the other side
            if(cnt <= mid){
                low = mid + 1;
            }
            else{
                high = mid;
            }
        }
        return low;
    }
}
