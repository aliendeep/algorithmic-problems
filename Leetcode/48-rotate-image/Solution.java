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