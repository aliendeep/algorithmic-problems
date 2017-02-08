/*
Implement next permutation, which rearranges numbers into the lexicographically 
next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible 
order (ie, sorted in ascending order).

The replacement must be in-place, do not allocate extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding 
outputs are in the right-hand column.
1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1
*/

public class Solution {
    public void reverse(int[] nums, int k){
        for(int j=nums.length-1, i=k; i<j; i++, j--){
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
        }
    }
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        int k = n-1;
        // find the largest decreasing suffix
        while(k > 0 && nums[k] <= nums[k-1]){
            k--;
        }
        // 3 2 1
        if(k == 0){
            Arrays.sort(nums);
            return;
        }
        
        // The suffix is already the maximum it can be, we need to change the prefix
        // Find the smallest element > nums[k-1] (number right before the longest decreasing suffix)  
        // in the suffix and swap it such than prefix can be next smallest as possible 
        int x = k-1;
        for(int i = n-1; i>=k; i--){
            if(nums[i] > nums[x]){
                int t = nums[i];
                nums[i] = nums[x];
                nums[x] = t;
                break;
            }
        }

        // prefix is the smallest, but suffix is not, we need to sort the part of suffix or reverse
        reverse(nums, k);
    }
}
