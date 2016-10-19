/*
Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

For example, given the following matrix:

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0
Return 6.
*/

/*
left(i,j) = max(left(i-1,j), cur_left), cur_left can be determined from the current row
right(i,j) = min(right(i-1,j), cur_right), cur_right can be determined from the current row
height(i,j) = height(i-1,j) + 1, if matrix[i][j]=='1';
height(i,j) = 0, if matrix[i][j]=='0'
*/

public class Solution {
    // height -  number of '1's above including the current one. 
    // The value of left & right means the boundaries of the rectangle which contains the current point with a height of value height.
    // https://discuss.leetcode.com/topic/6650/share-my-dp-solution    
    // Alternative: DP
    public int maximalRectangle(char[][] matrix) {
        int r = matrix.length;
        if(r == 0)
            return 0;
        int c = matrix[0].length;
        int[] height = new int[c];
        Arrays.fill(height, 0);
        
        int[] left = new int[c];
        Arrays.fill(left, 0);
        
        int[] right = new int[c];
        Arrays.fill(right, c);
        
        int maxArea = 0;
        for(int i=0; i<r; i++){
            // Compute height
            for(int j=0; j<c; j++){
                height[j] = matrix[i][j] == '1' ? height[j] + 1 : 0;
            }
            
            // left boundary
            int curLeft = 0;
            for(int j=0; j<c; j++){
                if(matrix[i][j] == '1')
                    left[j] =  Math.max(left[j] /* prev */, curLeft);
                else{
                    left[j] = 0;
                    curLeft = j + 1;
                }
            }
            // right boundary
            int curRight = c;
            for(int j=c-1; j>=0; j--){
                if(matrix[i][j] == '1')
                    right[j] =  Math.min(right[j] /* prev */, curRight);
                else{
                    right[j] = c;
                    curRight = j;
                }
            } 
            
            for(int j = 0; j<c; j++){
                maxArea = Math.max(maxArea, (right[j] - left[j])*height[j]);
            }
        }
        return maxArea;
    }
}