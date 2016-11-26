/*
@Copyright:LintCode
@Author:   mts
@Problem:  http://www.lintcode.com/problem/median-of-two-sorted-arrays
@Language: Java
@Datetime: 16-11-25 04:14
*/

class Solution {
    /**
     * @param A: An integer array.
     * @param B: An integer array.
     * @return: a double whose format is *.5 or *.0
     */
    // Binary Search
    // [1,2,3,4,5,6] 
    // [2,3,4,5]
    /*
    Left part                                |  Right Part
    { A[0], A[1], … , A[i - 1] } |  { A[i], A[i + 1], … , A[m - 1] }
    { B[0], B[1], … , B[j - 1] } |  { B[j], B[j + 1], … , B[n - 1] }    
        Length of left part = Length of right part or 
        Length of left part = Length of right part + 1    
        Search for i in [0...m] such that
            i + j = (m-i) + (n-j) + 1
        => i+j = (m+n+1) - i - j
        => 2i + 2j = m+n+1
        => j = (m+n+1 - 2i)/2;
        => j = (m+n+1)/2 - i
    */
    public double findMedianSortedArrays(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        if(m > n)
            return findMedianSortedArrays(B, A);
        int i, j;
        int il = 0, ih = m;
        while(il <= ih){
            // cut point
            i = (il+ih)/2;
            j = (m+n+1)/2 - i;
            if(i > 0 && j < n && A[i-1] > B[j]){
                ih = i - 1;
            }
            else if(i < m && j > 0 && B[j-1] > A[i]){
                il = i + 1;
            }
            // found the mid point
            else{
                // Max of A[i-1] and B[j-1]
                double median1 = Math.max(i == 0 ? Integer.MIN_VALUE : A[i-1],
                                          j == 0 ? Integer.MIN_VALUE : B[j-1]);

                // odd
                if((m + n) % 2 == 1)    return median1;    
                // Min of A[i] and B[j]
                double median2 = Math.min(i == m ? Integer.MAX_VALUE: A[i], 
                                          j == n ? Integer.MAX_VALUE: B[j]);
                return (median1 + median2)/2.0;                
            }
        }
        return 0;
    }
}
