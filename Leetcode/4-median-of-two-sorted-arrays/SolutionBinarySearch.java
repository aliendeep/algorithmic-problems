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