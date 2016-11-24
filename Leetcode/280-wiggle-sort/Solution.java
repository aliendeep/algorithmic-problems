/*
Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....

For example, given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4].
*/

public class Solution {
    public void swap(int[] nums, int i){
        int t = nums[i];
        nums[i] = nums[i-1];
        nums[i-1] = t;
    }
    public void wiggleSort(int[] nums) {
        for(int i=0; i<nums.length; i++){
            // if index is odd
            if(i % 2 == 1){
                if(nums[i-1] > nums[i]) 
                    swap(nums, i);
            }
            // index is even and > 0
            else if(i != 0 && nums[i-1] < nums[i])
                swap(nums, i);
        }
    }
}


class Solution2 {
    // Alternative: O(n) Solution
    public void swap(int[] nums, int i, int j){
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
    
    public int getMax(int[] nums, int a, int b, int c){
        int n = nums.length;
        int x = (a >=0 && a < n) ? nums[a] : Integer.MIN_VALUE;
        int y = (b >=0 && b < n) ? nums[b] : Integer.MIN_VALUE;
        int z = (c >=0 && c < n) ? nums[c] : Integer.MIN_VALUE;
        int max = Math.max(x, Math.max(y,z)); 
        if(max == x)    return a;
        else if(max == y)    return b;
        return c;
    }
    
    public void wiggleSort(int[] nums) {
        for(int i=1; i<nums.length; i+=2){
            int maxIndex = getMax(nums, i-1, i, i+1);
            if(i != maxIndex){
                swap(nums, i, maxIndex);
            }
        }
    }
}