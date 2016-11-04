/*
Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).

Range Sum Query 2D
The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.

Example:
Given matrix = [
  [3, 0, 1, 4, 2],
  [5, 6, 3, 2, 1],
  [1, 2, 0, 1, 5],
  [4, 1, 0, 1, 7],
  [1, 0, 3, 0, 5]
]

sumRegion(2, 1, 4, 3) -> 8
update(3, 2, 2)
sumRegion(2, 1, 4, 3) -> 10
Note:
The matrix is only modifiable by the update function.
You may assume the number of calls to update and sumRegion function is distributed evenly.
You may assume that row1 ≤ row2 and col1 ≤ col2.
*/

/*
Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).

Range Sum Query 2D
The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.

Example:
Given matrix = [
  [3, 0, 1, 4, 2],
  [5, 6, 3, 2, 1],
  [1, 2, 0, 1, 5],
  [4, 1, 0, 1, 7],
  [1, 0, 3, 0, 5]
]

sumRegion(2, 1, 4, 3) -> 8
update(3, 2, 2)
sumRegion(2, 1, 4, 3) -> 10
Note:
The matrix is only modifiable by the update function.
You may assume the number of calls to update and sumRegion function is distributed evenly.
You may assume that row1 ≤ row2 and col1 ≤ col2.
*/

class BinaryIndexedTree2D{
  int[][] bit;
  int[][] matrix;

  public BinaryIndexedTree2D(int[][] matrix){
    this.matrix = matrix;
    int r = matrix.length;
    int c = matrix[0].length;

    bit = new int[r+1][c+1];
    
    for(int i=1; i<=r; ++i){
      for(int j=1; j<=c; ++j){
        addDiff(i, j, matrix[i-1][j-1]); 
      }
    }
  }

  public int lsb(int index){
    return index & -index;
  }
  // 1 indexing
  private void addDiff(int row, int col, int diff){
    for(int i=row; i<bit.length; i=i+lsb(i)){
      for(int j=col; j<bit[0].length; j=j+lsb(j)){
        bit[i][j] += diff;
      }
    }    
  }

  // 0 <= row < r, 0 <= col < c 
  public void set(int row, int col, int val){
    int diff = val - matrix[row][col];
    matrix[row][col] = val;
    addDiff(row+1, col+1, diff);
  }
  
  // Returns sum by matrix[0][0] to matrix[row][col]
  public int getSum(int row, int col){
    row++;
    col++;

    int sum = 0;
    for(int i=row; i>0; i=i-lsb(i)){
      for(int j=col; j>0; j=j-lsb(j)){
        sum += bit[i][j];
      }
    } 
    return sum;           
  }
} 

public class NumMatrix {
    BinaryIndexedTree2D bit;
    
    public NumMatrix(int[][] matrix) {
        int r = matrix.length;
        if(r == 0)      return;
        bit = new BinaryIndexedTree2D(matrix);    
    }

    public void update(int row, int col, int val) {
        bit.set(row, col, val);
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return bit.getSum(row2, col2) - bit.getSum(row1-1, col2) - bit.getSum(row2, col1-1) + bit.getSum(row1-1, col1-1);
    }
    public static void main(String[] args){
        int[][] matrix = {
                      {3, 0, 1, 4, 2},
                      {5, 6, 3, 2, 1},
                      {1, 2, 0, 1, 5},
                      {4, 1, 0, 1, 7},
                      {1, 0, 3, 0, 5}
                     };
        NumMatrix numMatrix = new NumMatrix(matrix);
        System.out.println(numMatrix.sumRegion(0, 0, 2, 1)); // 17
        System.out.println(numMatrix.sumRegion(2, 1, 4, 3)); // 8
        numMatrix.update(3, 2, 2);
        System.out.println(numMatrix.sumRegion(2, 1, 4, 3)); // 10
    }
}

// Your NumMatrix object will be instantiated and called as such:
// NumMatrix numMatrix = new NumMatrix(matrix);
// numMatrix.sumRegion(0, 1, 2, 3);
// numMatrix.update(1, 1, 10);
// numMatrix.sumRegion(1, 2, 3, 4);