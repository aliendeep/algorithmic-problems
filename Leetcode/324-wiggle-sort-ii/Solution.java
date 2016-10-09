/*
Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....

Example:
(1) Given nums = [1, 5, 1, 1, 6, 4], one possible answer is [1, 4, 1, 5, 1, 6]. 
(2) Given nums = [1, 3, 2, 2, 3, 1], one possible answer is [2, 3, 1, 3, 1, 2].

Note:
You may assume all input has valid answer.

Follow Up:
Can you do it in O(n) time and/or in-place with O(1) extra space?
*/

// Input: [1,2,2,1,2,1,1,1,1,2,2,2]
// Output: [1,2,1,2,1,2,1,2,1,2,1,2]
import java.util.*;

public class Solution {
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
    
    public int findKthLargest(int[] nums, int k) {
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
    
    public static int index(int i, int n){
        return (1 + 2*i) % (n | 1);
    }
    
    public void wiggleSort(int[] nums) {
        int n = nums.length;
        // Use the find Kth largest element to find the median
        int median = findKthLargest(nums, n/2);
        System.out.println("Median: "+median);
        int left = 0, right = n-1;
        int i = 0;

        while(i <= right){
            if(nums[index(i, n)] > median){
                swap(nums, index(left, n), index(i, n));
                left++;
                i++;
            }    
            else if(nums[index(i, n)] < median){
                swap(nums, index(right, n), index(i, n));
                right--;
            }
            else
                i++;
        }   
    }

    public static void main(String[] args){
        int[] a = {4,5,5,6};
        Solution s = new Solution();
        s.wiggleSort(a);
        for(int i=0; i<a.length; i++){
             System.out.print(a[i] + " ");
        }
    }
}