/*
Given an unsorted array of integers, find the length of longest increasing 
subsequence.

For example,
Given [10, 9, 2, 5, 3, 7, 101, 18],
The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. 
Note that there may be more than one LIS combination, it is only necessary for 
you to return the length.

Your algorithm should run in O(n2) complexity.
*/

public class Solution {
    // O(n^2)
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        if(n == 0)
            return 0;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        
        for(int i=1; i<n; i++){
            for(int j=0; j<i; j++){
                if(nums[i] > nums[j])
                    dp[i] = Math.max(dp[i],dp[j]+1); 
            }
        }
        
        int maxLength = 1;
        for(int i=0; i<n; i++)
            maxLength = Math.max(maxLength, dp[i]);
        return maxLength;
    }
}

// O(nlogn) Solution (Binary Search)
class Solution2 {
    // find the leftmost index where the element should be inserted
    // len of the array (exclusive)
    public int binarySearch(int[] a, int len, int target){
        int l = 0, h = len - 1;
        while(h - l > 3){
            int mid = (l+h)/2;
            if(a[mid] == target){
                h = mid;
            }
            else if(a[mid] < target){
                l = mid + 1;
            }
            // a[mid] > target
            else{
                h = mid - 1;
            }
        }
        for(int i=l; i<=h; ++i){
            if(a[i] >= target)
                return i;
        }
        return h+1;
    }
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        if(nums == null || n == 0)
            return 0;
        int len = 0;
        // The array contains the min value of the last element of i-length 
        // increasing subsequence
        int[] minValueAtLength= new int[n];
        for(int num : nums){
            int index = binarySearch(minValueAtLength, len, num);
            // If current element
            minValueAtLength[index] = num;
            
            // If the insertion point of the current element is equal to the longest 
            //  length found so far, then we can extend the subsequence.
            if(len == index)
                len++;
        }
        return len;
    }
}

// Arrays.binary Search. Better to write own binary search because
// If the range contains multiple elements with the specified value, 
// there is no guarantee which one will be found.
class Solution2 {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        if(nums == null || n == 0)
            return 0;
        int len = 0;
        // The array contains the min value of the last element of 
        // i-length increasing subsequence
        int[] minValueForLength= new int[n];
        for(int num : nums){
            // public static int binarySearch(char[] a, int fromIndex, int toIndex (exclusive), char key)
            int index = Arrays.binarySearch(minValueForLength, 0, len, num);
            // Returns: index of the search key, if it is contained in the array 
            // within the specified range; otherwise, (-(insertion point) - 1).
            if(index < 0)
                index = -(index + 1);
            // If current element
            minValueForLength[index] = num;
            
            // If the insertion point of the current element is equal to the longest 
            // length found so far, then we can extend the subsequence.
            if(len == index)
                len++;
        }
        return len;
    }
}
