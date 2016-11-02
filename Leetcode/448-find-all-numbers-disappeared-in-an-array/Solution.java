/*
Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

Find all the elements of [1, n] inclusive that do not appear in this array.

Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.

Example:

Input:
[4,3,2,7,8,2,3,1]

Output:
[5,6]
*/

public class Solution {
    public void swap(int[] nums, int i, int j){
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    public List<Integer> findDisappearedNumbers(int[] nums) {
        int n = nums.length;
        int i = 0;
        while(i < n){
            if(nums[i] != i+1 && nums[nums[i]-1] != nums[i]){
                swap(nums, i, nums[i]-1);
            }
            else{
                i++;    
            }
        }
        
        List<Integer> r = new ArrayList<>();
        for(i=0; i<n; ++i){
            if(nums[i] != i+1)
                r.add(i+1);
        }
        return r;
    }
}