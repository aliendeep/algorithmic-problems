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

public class NumMatrix {
    int[][] binaryIndexedTree;
    int[][] matrix;

    public NumMatrix(int[][] matrix) {
        this.matrix = matrix;
        int r = matrix.length;
        if(r == 0)  
            return;
        int c = matrix[0].length;
        binaryIndexedTree = new int[r+1][c+1];
        // Create binary indexed tree
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                updateBinaryIndexedTree(i, j, matrix[i][j]);
            }
        }
    }

    public int getNext(int index){
        return index + (index & -index);    
    }

    public int getParent(int index){
        return index - (index & -index);    
    }
    
    public void updateBinaryIndexedTree(int row, int col, int val){
        for(int i=row+1; i<binaryIndexedTree.length; i = getNext(i)){
            for(int j=col+1; j<binaryIndexedTree[0].length; j = getNext(j)){
                binaryIndexedTree[i][j] += val;
            }            
        }
    }
    public int getSum(int row, int col){
        int sum = 0;
        for(int i=row; i>0; i = getParent(i)){
            for(int j=col; j>0; j = getParent(j)){
                sum += binaryIndexedTree[i][j];
            }            
        }
        return sum;
    }
    
    public void update(int row, int col, int val) {
        int diff = val - matrix[row][col];
        matrix[row][col] = val;
        updateBinaryIndexedTree(row, col, diff);
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return getSum(row2+1, col2+1) + getSum(row1, col1) - getSum(row2+1, col1) - getSum(row1, col2+1);  
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
        System.out.println(numMatrix.getSum(2, 1));
        System.out.println(numMatrix.sumRegion(2, 1, 4, 3));
        numMatrix.update(3, 2, 2);
        System.out.println(numMatrix.sumRegion(2, 1, 4, 3));
    }
}

// Your NumMatrix object will be instantiated and called as such:
// NumMatrix numMatrix = new NumMatrix(matrix);
// numMatrix.sumRegion(0, 1, 2, 3);
// numMatrix.update(1, 1, 10);
// numMatrix.sumRegion(1, 2, 3, 4);