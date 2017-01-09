/*
Write an efficient algorithm that searches for a value in an m x n matrix. 
This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous 
row.
For example,

Consider the following matrix:

[
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
Given target = 3, return true.
*/

// O(r + c)
public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
      int row = matrix.length;
      if(row == 0)                
          return false;
      int col = matrix[0].length;
        
        // right most entry in first row
      int r = 0;
      int c = col-1;
    
      while(r < row && c >= 0){
        if(matrix[r][c] == target)          return true;
        else if(matrix[r][c] < target)      r++;
        else                                c--;
      }
      return false;
    }
}

// Alternative: Binary Search
// Treat the whole matrix as a 1D matrix
// O(log(rc))
class Solution2 {
    public boolean searchMatrix(int[][] matrix, int target) {
      int r = matrix.length;
      if(r == 0)                
          return false;
      int c = matrix[0].length;
        int l = 0, h = r*c-1;
        int i, j;
        while(h - l > 3){
            int mid = (l+h)/2;
            i = mid / c;
            j = mid % c;
            if(matrix[i][j] == target){
                return true;
            }
            else if(matrix[i][j] < target){
                l = mid + 1;
            }
            else{
                h = mid - 1;
            }
        }
        for(int x=l; x<=h; ++x){
            i = x / c;
            j = x % c;
            if(matrix[i][j] == target)
                return true;
        }
        return false;
    }
}
