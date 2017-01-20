/*
You are given an n x n 2D matrix representing an image.

Rotate the image by 90 degrees (clockwise).

Follow up:
Could you do this in-place?
*/

public class Solution {
    // Draw a sample example
    // Row i to row n-1-i, col i to n-1-i
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        int x = (n%2 == 0) ? n/2 : (n/2+1);
        for(int i=0; i<n/2; i++){
            for(int j=0; j<x; j++){
                int t = matrix[i][j];
                matrix[i][j] = matrix[n-1-j][i];
                matrix[n-1-j][i] = matrix[n-1-i][n-1-j];
                matrix[n-1-i][n-1-j] = matrix[j][n-1-i];
                matrix[j][n-1-i] = t;
            }
        }
    }
}

class Solution {
    // https://discuss.leetcode.com/topic/6796/a-common-method-to-rotate-the-image
    // Clockwise
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // reverse 
        for(int r=0; r<n/2; ++r){
            // rth row
            int[] t = matrix[r];
            // n - 1 - r th row
            matrix[r] = matrix[n-1-r];
            matrix[n-1-r] = t;
        }
        
        for(int i=0; i<n; ++i){
            for(int j=0; j<i; ++j){
                int t = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = t;
            }
        }
    }
}

class Solution {
    // https://discuss.leetcode.com/topic/6796/a-common-method-to-rotate-the-image
    // Clockwise
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // reverse 
        for(int i=0; i<n/2; ++i){
            for(int j=0; j<n; ++j){
                int t = matrix[i][j];
                //column fixed
                matrix[i][j] = matrix[n-1-i][j];
                matrix[n-1-i][j] = t;
            }
        }
        
        for(int i=0; i<n; ++i){
            for(int j=0; j<i; ++j){
                int t = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = t;
            }
        }
    }
}
