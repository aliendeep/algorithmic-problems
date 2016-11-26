/*
@Copyright:LintCode
@Author:   mts
@Problem:  http://www.lintcode.com/problem/the-smallest-difference
@Language: Java
@Datetime: 16-11-25 02:46
*/

public class Solution {
    /**
     * @param A, B: Two integer arrays.
     * @return: Their smallest difference.
     */
    public int smallestDifference(int[] A, int[] B) {
        int n = A.length;
        int m = B.length;
        Arrays.sort(A);
        Arrays.sort(B);
        int i = 0, j = 0;
        int minDiff = Integer.MAX_VALUE;
        while(i < n && j < m){
            int a = A[i];
            int b = B[j];
            if(a < b && i<n){
                ++i;
            }
            else if(a > b && j < m){
                ++j;
            }
            else{
                ++i;
                ++j;
            }
            minDiff = Math.min(minDiff, Math.abs(a-b));
        }    
        return minDiff;
    }
}
