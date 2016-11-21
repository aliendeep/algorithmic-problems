/*
Given two sorted arrays A, B of size m and n respectively. 
Find the k-th smallest element in the union of A and B. You can assume that there are no duplicate elements.
*/
/*
Split arrays into two parts
Left part                                |  Right Part
{ nums1[0], nums1[1], … , nums1[i - 1] } |  { nums1[i], nums1[i + 1], … , nums1[m - 1] }
{ nums2[0], nums2[1], … , nums2[j - 1] } |  { nums2[j], nums2[j + 1], … , nums2[n - 1] }

Condition 1:
Search for i in [0...m] such that
   i + j    = k - 1

Condition 2:
- nums1[i-1] <= nums2[j] or i == 0 or j == n and
- nums2[j-1] <= nums1[i] or j == 0 or i == m

Binary Search
- if nums1[i'-1]   > nums2[j'], then we need to search in [0 ... i'-1]
- if nums2[j' - 1] > nums1[i'], then we need to search in [i'+1 .. m]
*/
import java.util.*;

public class KthSmallestElementArray{
    // TODO
    // nums1 is the smaller array
    public int findKthSmallestSortedArray(int[] nums1, int[] nums2, int k) {
        int m = nums1.length;
        int n = nums2.length;
        if(m > n)
            return findKthSmallestSortedArray(nums2, nums1, k);
        int i, j;
        int ilow = 0;
        int ihigh = m;
        while(ilow <= ihigh){
            i = (ilow + ihigh)/2;
            j = (k - 1 - i);
            System.out.println(i + " j " + j);
            if(i > 0 && j < n && nums1[i-1] > nums2[j]){
                ihigh = i-1;
            }
            else if(j > 0 && i < m && nums2[j-1] > nums1[i]){
                ilow = i+1;
            }
            else{
                // Find the kth smallest
                return Math.min(nums1[i], nums2[j]);
            }
        }    
        return -1;    
    }
    public static void main(String[] args){
        int[] a = {1, 2, 3};
        int[] b = {4, 5, 6};
        KthSmallestElementArray ob = new KthSmallestElementArray();
        System.out.println(ob.findKthSmallestSortedArray(a, b, 1));
    }
}