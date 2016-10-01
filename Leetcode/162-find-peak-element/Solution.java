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