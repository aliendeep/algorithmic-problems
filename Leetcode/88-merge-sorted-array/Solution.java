/*
Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one 
sorted array.

Note:
You may assume that nums1 has enough space (size that is greater or equal to m + n) 
to hold additional elements from nums2. 
The number of elements initialized in nums1 and nums2 are m and n respectively.
*/

public class Solution {
    // backward
    public void merge(int[] nums1, int m, int[] nums2, int n) {
      int iA = m - 1;
      int iB = n - 1;
      int iM = m + n - 1;
    
      while(iA >=0 && iB >=0){
        if(nums1[iA] > nums2[iB]){
          nums1[iM--] = nums1[iA--];
        }
        else{
          nums1[iM--] = nums2[iB--];
        }
      }
    
      while(iB >= 0){
        nums1[iM--] = nums2[iB--];
      }       
    }
}
