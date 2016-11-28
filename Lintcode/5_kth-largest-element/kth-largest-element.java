/*
@Copyright:LintCode
@Author:   mts
@Problem:  http://www.lintcode.com/problem/kth-largest-element
@Language: Java
@Datetime: 16-11-26 02:19
*/

class Solution {
    /*
     * @param k : description of k
     * @param nums : array of nums
     * @return: description of return
     */
    Random rand;
    void swap(int[] nums, int i, int j){
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
    
    int quickSelect(int[] nums, int left, int right, int pivotIndex){
        int n = nums.length;
        int pivot = nums[pivotIndex];
        // Move the pivot to the rightmost position
        swap(nums, pivotIndex, right);
        
        int newPivot = left;
        for(int i=left; i<right; i++){
            // Kth Largest Element in an Array, if was kth smallest element that it would be nums[i] < pivot
            if(nums[i] > pivot){
                swap(nums, i, newPivot);
                newPivot++;
            }
        }       
        
        // Pivot was in the rightmost position. Swap so that pivot lies between the two paritions
        swap(nums, right, newPivot);
        return newPivot;
    }
    
    public int kthLargestElement(int k, int[] nums) {
        rand =  new Random();
        int n = nums.length;
        int left = 0, right = n-1;
        while(left <= right){
            // Pick a random number between [left .. right]
            int pivot = left + rand.nextInt(right - left + 1);
            int nPivot = quickSelect(nums, left, right, pivot);
            // 0 indexing
            if(nPivot == k-1)             
                return nums[nPivot];
            else if(nPivot < k - 1)
                left = nPivot + 1;    
            // nPivot > k
            else{
                right = nPivot - 1;
            }
        }
        return -1;
    }
        
};