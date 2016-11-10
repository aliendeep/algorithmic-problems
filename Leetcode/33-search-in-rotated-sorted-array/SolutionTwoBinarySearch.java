/*
Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.
*/
public class Solution {
    public int findMin(int[] A) {
        int low = 0, high = A.length - 1;
        while(high - low > 3){
            int mid = (low+high)/2;
            // Min in the right side
            if(A[mid] > A[high]){
                low = mid + 1;
            }
            // A[mid] < A[high]
            else{
                high = mid;
            }
        }
        
        int minIndex = low;
        for(int i=low+1; i<=high; ++i){
            if(A[i] < A[minIndex])
                minIndex = i;
        }
        return minIndex;
    }
    
    public int binarySearch(int[] A, int low, int high, int target){
        while(high - low > 3){
            int mid = (low+high)/2;
            if(A[mid] == target)
                return mid;
            else if(A[mid] < target)
                low = mid + 1;
            else
                high = mid - 1;
        }        
        for(int i=low; i<=high; ++i){
            if(A[i] == target)
                return i;
        }
        return -1;
    }
    
    public int search(int[] A, int target) {
        int n = A.length;
        if(n == 0)
            return -1;
        // Find the min
        int mIndex = findMin(A);
        if(A[mIndex] == target)
            return mIndex;
        
        // Two Binary Search
        int in = binarySearch(A, 0, mIndex-1, target);
        if(in != -1){
            return in;
        } 
        return binarySearch(A, mIndex, n-1, target);
    }
}