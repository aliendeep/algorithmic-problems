/*
A peak element is an element that is greater than its neighbors.

Given an input array where num[i] ≠ num[i+1], find a peak element and return its 
index.

The array may contain multiple peaks, in that case return the index to any one of 
the peaks is fine.

You may imagine that num[-1] = num[n] = -∞.

For example, in array [1, 2, 3, 1], 3 is a peak element and your function should 
return the index number 2.
*/
public class Solution {
    // Ternary Search
    // high inclusive
    public int ternarySearch(int[] A, int low, int high){
        if(high - low < 3){
            int peakIndex = low;
            for(int i=low+1; i<=high; i++)
                if(A[i] > A[peakIndex])
                    peakIndex = i;
            return peakIndex;
        }
        
        int m1 = (2*low + high)/3;
        int m2 = (low + 2*high)/3;
        if(A[m1] < A[m2])
            return ternarySearch(A, m1+1, high);
        return ternarySearch(A, low, m2-1);
    }
    public int findPeakElement(int[] nums) {
        if(nums.length == 0)    return -1;
        return ternarySearch(nums, 0, nums.length-1);
    }
}

// Binary Search
class Solution2 {
    public int findPeakElement(int[] nums) {
        int l = 0, h = nums.length-1;
        while(h - l > 3){
            int mid = (l+h)/2;
            if(nums[mid] > nums[mid+1])
                h = mid;
            else
                l = mid + 1;
        }
        int max = nums[l];
        int index = l;
        for(int i=l+1; i<=h; ++i){
            if(max < nums[i]){
                max = nums[i];
                index = i;
            }
        }
        return index;
    }
}
