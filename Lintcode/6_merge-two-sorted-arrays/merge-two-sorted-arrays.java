/*
@Copyright:LintCode
@Author:   mts
@Problem:  http://www.lintcode.com/problem/merge-two-sorted-arrays
@Language: Java
@Datetime: 16-11-08 03:22
*/

class Solution {
    /**
     * @param A and B: sorted integer array A and B.
     * @return: A new sorted integer array
     */
    public int[] mergeSortedArray(int[] A, int[] B) {
        // Write your code here
        int a = A.length;
        int b = B.length;
        int[] c = new int[a + b];
        int i=0, j=0;
        int k = 0;
        while(i < a && j < b){
            c[k++] = (A[i] < B[j]) ? A[i++] : B[j++];
        }
        while(i < a){
            c[k++] = A[i++];
        }
        while(j < b){
            c[k++] = B[j++];
        }
        return c;
    }
}