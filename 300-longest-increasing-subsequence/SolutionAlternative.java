/*
Given an unsorted array of integers, find the length of longest increasing subsequence.

For example,
Given [10, 9, 2, 5, 3, 7, 101, 18],
The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. Note that there may be more than one LIS combination, it is only necessary for you to return the length.

Your algorithm should run in O(n2) complexity.
Follow up: Could you improve it to O(n log n) time complexity?
*/

// O(nlogn) Solution
public class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        if(nums == null || n == 0)
            return 0;
        int len = 0;
        // The array contains the min value of the last element of i-length increasing subsequence
        int[] minValueForLength= new int[n];
        for(int num : nums){
            // public static int binarySearch(char[] a, int fromIndex, int toIndex (exclusive), char key)
            int index = Arrays.binarySearch(minValueForLength, 0, len, num);
            // Returns: index of the search key, if it is contained in the array within the specified range; otherwise, (-(insertion point) - 1).
            if(index < 0)
                index = -(index + 1);
            // If current element
            minValueForLength[index] = num;
            
            // If the insertion point of the current element is equal to the longest length found so far, then we can extend the subsequence.
            if(len == index)
                len++;
        }
        return len;
    }
}