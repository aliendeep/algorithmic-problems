/*
Given an array with n objects colored red, white or blue, sort them so that 
objects of the same color are adjacent, with the colors in the order red, white 
and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, 
and blue respectively.
*/
public class Solution {
    public void swap(int[] A, int i, int j){
        int t = A[i];
        A[i] = A[j];
        A[j] = t;
    }
    
    public void sortColors(int[] nums) {
        int start = 0, n = nums.length;
        int i = 0;
        while(i < n){
            if(nums[i] == 0){
                swap(nums, start, i);
                start++;
                i++;
            }
            else if(nums[i] == 2){
                swap(nums, i, --n);
            }
            else
                i++;
        }        
    }
}

class Solution2 {
    public void sortColors(int[] nums) {
        int n = nums.length;
        int s = 0, e = n-1;
        int i = 0;
        while(i <= e){
            if(nums[i] == 0){
                nums[i] = nums[s];
                nums[s] = 0;
                i++;
                s++;
            }
            else if(nums[i] == 2){
                nums[i] = nums[e];
                nums[e--] = 2;
            }
            else
                ++i;
        }
    }
}
