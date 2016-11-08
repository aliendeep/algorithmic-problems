
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

  public static void main(String[] args){
    int[][] matrix = {
                  {3, 0, 1, 4, 2},
                  {5, 6, 3, 2, 1},
                  {1, 2, 0, 1, 5},
                  {4, 1, 0, 1, 7},
                  {1, 0, 3, 0, 5}
                 };    
    BinaryIndexedTree2D bit = new BinaryIndexedTree2D(matrix);
    System.out.println(bit.getSum(1, 3)); // 24
  }
} 