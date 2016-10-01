import java.util.*;

// dp
public class NumMatrix {
    int[][] dp;

    public NumMatrix(int[][] matrix) {
        int r = matrix.length;
        if(r > 0){
            int c = matrix[0].length;
            
            dp = new int[r+1][c+1];
            // dp[i][j] = sum of the element (matrix[0][0] to matrix[i-1][j-1])
            for(int i=1; i<=r; i++){
                for(int j=1; j<=c; j++){
                    dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + matrix[i-1][j-1];        
                }
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return dp[row2+1][col2+1] - dp[row2+1][col1] - dp[row1][col2+1] + dp[row1][col1]; 
    }

    public static void main(String[] args){
        int[][] nums =  {
          {3, 0, 1, 4, 2},
          {5, 6, 3, 2, 1},
          {1, 2, 0, 1, 5},
          {4, 1, 0, 1, 7},
          {1, 0, 3, 0, 5}
        };

        NumMatrix numMatrix = new NumMatrix(nums);
        System.out.println(numMatrix.sumRegion(2, 1, 4, 3));
        System.out.println(numMatrix.sumRegion(1, 1, 2, 2));
        System.out.println(numMatrix.sumRegion(1, 2, 2, 4));
    }
}


// Your NumMatrix object will be instantiated and called as such:
// NumMatrix numMatrix = new NumMatrix(matrix);
// numMatrix.sumRegion(0, 1, 2, 3);
// numMatrix.sumRegion(1, 2, 3, 4);