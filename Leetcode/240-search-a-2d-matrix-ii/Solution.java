/*
Write an efficient algorithm that searches for a value in an m x n matrix. 
This matrix has the following properties:

Integers in each row are sorted in ascending from left to right.
Integers in each column are sorted in ascending from top to bottom.
For example,

Consider the following matrix:

[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
Given target = 5, return true.

Given target = 20, return false.
*/
// O(r + c) Solution
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

// CtCi
// #TODO : Debug
public class Solution {
    class Coord{
        int r, c;
        public Coord(int r1, int c1){
            r = r1;
            c = c1;
        }
        
        public boolean equalOrGreaterThan(Coord t){
            return t.r <= r && t.c <= c;
        }
        
        public boolean greaterThan(Coord t){
            return t.r < r && t.c < c;
        }        
    }

    public Coord getMid(Coord l, Coord h){
        return new Coord((l.r + h.r)/2, (l.c + h.c)/2);
    }
    
    int[][] matrix;
    int row, col;
    
    public boolean isValid(Coord c){
        return (c.r >= 0 && c.r < row && c.c >= 0 && c.c < col);        
    }

    public Coord findElement(int[][] matrix, Coord start, Coord end, int target) {
        if(!isValid(start) || !isValid(end))         return null;
        
        if(matrix[start.r][start.c] == target)       return start;
        else if(!end.equalOrGreaterThan(start))      return null;
        
        // Set s to the diagonal of the
        Coord s = new Coord(start.r, start.c);
        int d = Math.min(end.r - start.r, end.c - start.c);
        Coord e = new Coord(start.r + d, start.c + d);
        
        // Find first element that is greater than target
        while(end.greaterThan(start)){
            System.out.println("!loop");
            Coord mid = getMid(s, e);
            if(matrix[mid.r][mid.c] < target){
                s.r = mid.r + 1;
                s.c = mid.c + 1;
            }
            // matrix[mid.r][mid.c] >= target
            else{
                e.r = mid.r - 1;
                e.c = mid.c - 1;
            }
        }
        
        while(isValid(s) && matrix[s.r][s.c] <= target){
            s.r = s.r + 1;            
            s.c = s.c + 1;            
        }
        // Split the grid into quadrants. Search the bottom left and top right
        return partitionSearch(matrix, start, end, s, target);
    }
    
    public Coord partitionSearch(int[][] matrix, Coord origin, Coord dest, Coord pivot, int target){
        Coord lowerLeftO = new Coord(pivot.r, origin.c);    
        Coord lowerLeftD = new Coord(dest.r, pivot.c - 1);    
        Coord upperRightO = new Coord(origin.r, pivot.c);    
        Coord upperRightD = new Coord(pivot.r - 1, dest.c);    
        
        Coord lowerLeft = findElement(matrix, lowerLeftO, lowerLeftD, target);
        if(lowerLeft != null)       return lowerLeft;
        return findElement(matrix, upperRightO, upperRightD, target);
    }
    
    // diagonal binary search
    public boolean searchMatrix(int[][] matrix, int target) {
        this.matrix = matrix;
        row = matrix.length;
        if(row == 0)        return false;
        col = matrix[0].length;
        Coord origin = new Coord(0, 0);
        Coord dest = new Coord(row-1, col-1);
        return findElement(matrix, origin, dest, target) == null ? false : true;
    }
}
