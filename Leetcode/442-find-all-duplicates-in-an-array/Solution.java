import java.util.*;

/*
Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements 
appear twice and others appear once.

Find all the elements that appear twice in this array.

Could you do it without extra space and in O(n) runtime?

Example:
Input:
[4,3,2,7,8,2,3,1]

Output:
[2,3]
*/

public class Solution {
    // Time complexity: O(n)
    public void swap(int[] nums, int i, int j){
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    public List<Integer> findDuplicates(int[] nums) {
        int i = 0;
        while(i < nums.length){
            // Important: nums[nums[i]-1] != nums[i] to avoid infinite loops
            if(nums[i] != i+1 && nums[nums[i]-1] != nums[i]){
                swap(nums, i, nums[i]-1);
            }
            else{
                ++i;
            }
        }
        
        List<Integer> r = new ArrayList<>();
        for(i=0; i<nums.length; ++i){
            if(nums[i] != i+1)
                r.add(nums[i]);
        }
        return r;
    }
}

// Flip the corresponing position to negative
class Solution2 {
    // Alternative : One pass
    public List<Integer> findDuplicates(int[] nums) {
        int n = nums.length;
        List<Integer> r = new ArrayList<>();
        for(int i=0; i<n; ++i){
            int index = Math.abs(nums[i]) - 1;
            // If already negative, then it appeared before
            if(nums[index] < 0){
                r.add(index + 1);
            }
            nums[index] = -nums[index];
        }
        return r;
    }
}
