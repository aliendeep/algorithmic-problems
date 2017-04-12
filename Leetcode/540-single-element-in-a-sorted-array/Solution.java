/*
Given a sorted array consisting of only integers where every element appears twice 
except for one element which appears once. Find this single element that appears 
only once.

Example 1:
Input: [1,1,2,3,3,4,4,8,8]
Output: 2
Example 2:
Input: [3,3,7,7,10,11,11]
Output: 10
Note: Your solution should run in O(log n) time and O(1) space.
*/
public class Solution {
    // Return the value at the first even index, not followed by the same number
    public int singleNonDuplicate(int[] nums) {
        int n = nums.length-1;
        int l = 0, h = n/2;
        
        while(h > l) {
            int mid = (l + h)/2;
            if(nums[2*mid] != nums[2*mid + 1]) 
                h = mid;
            else
                l = mid + 1;    
        }
        
        return nums[2*l];        
    }
}
