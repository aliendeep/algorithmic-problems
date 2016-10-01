/*
There are two sorted arrays nums1 and nums2 of size m and n respectively.

Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

Example 1:
nums1 = [1, 3]
nums2 = [2]

The median is 2.0
Example 2:
nums1 = [1, 2]
nums2 = [3, 4]

The median is (2 + 3)/2 = 2.5

*/
public class Solution {
    // Cut based approach
    // https://discuss.leetcode.com/topic/16797/very-concise-o-log-min-m-n-iterative-solution-with-detailed-explanation
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        // Make sure that nums1 is the larger array
        if(n1 < n2)
            return findMedianSortedArrays(nums2, nums1);
        
        if(n2 == 0)
            return (double)(nums1[(n1-1)/2] + nums1[n1/2])/2.0;
        
        // Move the cut in the nums2 around and computer the corresponding cut in nums1 accordingly    
        // There are 2*n + 1 cut positions regardless of the length of the array
        int low = 0;
        int high = n2*2;
        while(low <= high){
            // Cut in the nums2
            int mid2 = (low + high)/2;
            // Corresponding cut in the nums1
            int mid1 = n1 + n2 - mid2;
            
            // l1 is the highest number in the left half of nums1
            double l1 = (mid1 == 0) ? Integer.MIN_VALUE : nums1[(mid1-1)/2];
            // r1 is the lowest number in the right half of nums1
            double r1 = (mid1 == n1*2) ? Integer.MAX_VALUE : nums1[mid1/2];

            // l2 is the highest number in left half of nums2
            double l2 = (mid2 == 0) ? Integer.MIN_VALUE : nums2[(mid2-1)/2];
            // r2 is the lowest number in the right half of nums2
            double r2 = (mid2 == n2*2) ? Integer.MAX_VALUE : nums2[mid2/2];
            
            // l1 should be less than r2
            // l2 should be less than r1
            
            // Lower half of nums1 need to be reduced
            if(l1 > r2)
                low = mid2 + 1;
            // Lower half of num2 is big
            else if(l2 > r1)
                high = mid2 - 1;
            else
                return (double)(Math.max(l1, l2) + Math.min(r1, r2))/2.0;
        }
        // Shouldn't reach here
        return -1;
    }
}