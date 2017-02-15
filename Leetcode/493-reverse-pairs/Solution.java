/*
Given an array nums, we call (i, j) an important reverse pair if i < j and 
nums[i] > 2*nums[j].

You need to return the number of important reverse pairs in the given array.

Example1:

Input: [1,3,2,3,1]
Output: 2
Example2:

Input: [2,4,3,5,1]
Output: 3
Note:
The length of the given array will not exceed 50,000.
All the numbers in the input array are in the range of 32-bit integer.
*/
/*
Sample Input:
[1,3,2,3,1]
[2,4,3,5,1]
[2147483647,2147483647,2147483647,2147483647,2147483647,2147483647]
Sample Output:
2
3
0
*/
public class Solution {
    int ret;
    public int reversePairs(int[] nums) {
        ret = 0;
        countInversion(nums, 0, nums.length-1);        
        return ret;
    }
    
    void countInversion(int[] nums, int l, int h){
        if(h <= l)      return;
        int mid = (h-l)/2 + l;
        countInversion(nums, l, mid);
        countInversion(nums, mid+1, h);
        merge(nums, l, mid, h);
    }
    
    void merge(int[] nums, int start, int mid, int end){
        List<Integer> sorted = new ArrayList<>();
        // Count inversion
        int inversionCnt = 0;
        int cnt = 0;
        int l = start;
        int r = mid+1;
        while(l <= mid){
            if(r > end || (long)nums[l] <= (long)2*nums[r]){
                l++;
                ret += cnt;
            }
            // nums[l] > 2*nums[r]
            else{
                r++;
                cnt++;
            }
        }
        // Merge
        Arrays.sort(nums, start, end + 1);   
    }
}
