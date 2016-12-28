/*
https://www.interviewbit.com/problems/median-of-array/

Split arrays into two parts
Left part                                |  Right Part
{ nums1[0], nums1[1], … , nums1[i - 1] } |  { nums1[i], nums1[i + 1], … , nums1[m - 1] }
{ nums2[0], nums2[1], … , nums2[j - 1] } |  { nums2[j], nums2[j + 1], … , nums2[n - 1] }

Condition 1:
Length of left part = Length of right part or 
Length of left part = Length of right part + 1 
Search for i in [0...m] such that
   i + j    = (m-i) + (n-j) + 1
=> i + j    = m + n + 1 + i + j 
=> 2(i + j) = m + n + 1 
=> j        = (m + n + 1)/2 - i

Condition 2:
- nums1[i-1] <= nums2[j] or i == 0 or j == n and
- nums2[j-1] <= nums1[i] or j == 0 or i == m

Binary Search
- if nums1[i'-1]   > nums2[j'], then we need to search in [0 ... i'-1]
- if nums2[j' - 1] > nums1[i'], then we need to search in [i'+1 .. m]
*/
public class Solution {
    // nums1 is the smaller array
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if(m > n)
            return findMedianSortedArrays(nums2, nums1);
        int i, j;
        int ilow = 0;
        int ihigh = m;
        while(ilow <= ihigh){
            i = (ilow + ihigh)/2;
            j = (m + n + 1)/2 - i;
            if(i > 0 && j < n && nums1[i-1] > nums2[j]){
                ihigh = i-1;
            }
            else if(j > 0 && i < m && nums2[j-1] > nums1[i]){
                ilow = i+1;
            }
            else{
                double median1 = Math.max(i == 0 ? Integer.MIN_VALUE: nums1[i-1], j == 0? Integer.MIN_VALUE : nums2[j-1]);
                // odd
                if((m + n) % 2 == 1)
                    return median1;    
                double median2 = Math.min(i == m ? Integer.MAX_VALUE: nums1[i], j == n ? Integer.MAX_VALUE : nums2[j]);
                return (median1 + median2)/2.0;
            }
        }
        return 1;
    }
}

class Solution2 {
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

class Solution3 {
    // nums1 is the smaller array
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if(m > n)
            return findMedianSortedArrays(nums2, nums1);
        int i, j;
        int ilow = 0;
        int ihigh = m;            
        int size_sum = ((m+n) % 2 == 0) ? (m+n)/2 : (m+n+1)/2;  
        while(ihigh - ilow > 3){
            i = (ilow + ihigh)/2;
            j = size_sum - i;
            if(i > 0 && j < n && nums1[i-1] > nums2[j]){
                ihigh = i-1;
            }
            else if(j > 0 && i < m && nums2[j-1] > nums1[i]){
                ilow = i+1;
            }
            else
                break;
        }
        for(i=ilow; i<=ihigh; ++i){
            j = size_sum - i;            
            if(i > 0 && j < n && nums1[i-1] > nums2[j])
                continue;
            else if(j > 0 && i < m && nums2[j-1] > nums1[i])
                continue;
            
            double median1 = Math.max(i == 0 ? Integer.MIN_VALUE: nums1[i-1], j == 0? Integer.MIN_VALUE : nums2[j-1]);
            // odd
            if((m + n) % 2 == 1)
                return median1;    
            double median2 = Math.min(i == m ? Integer.MAX_VALUE: nums1[i], j == n ? Integer.MAX_VALUE : nums2[j]);
            return (median1 + median2)/2.0;
        }        
        return -1;
    }
}
