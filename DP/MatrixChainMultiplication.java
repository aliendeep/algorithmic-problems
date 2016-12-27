import java.util.*;

// Cormen 336
// http://www.geeksforgeeks.org/dynamic-programming-set-8-matrix-chain-multiplication/
/* m[i,j] = Minimum number of scalar multiplications needed
       to compute the matrix A[i]A[i+1]...A[j] = A[i..j] where
       dimension of A[i] is p[i-1] x p[i] 
*/

// O(n^3)
public class MatrixChainMultiplication{
  public void printOptimalParen(int[][] s, int i, int j){
    if(i == j)
      System.out.print("A_"+i);
    else{
      System.out.print("(");
      printOptimalParen(s, i, s[i][j]);
      printOptimalParen(s, s[i][j]+1, j);
      System.out.print(")");      
    }
  }

  // Dimension of all matrices are given in the p 
  public int matrixChainOrder(int[] p){
    int n = p.length;
    int[][] m = new int[n][n];
    int[][] s = new int[n][n];

    // Cost of multiplying one matrix is 0
    for(int i=1; i<n; ++i){
      m[i][i] = 0;
    }

    // for all chain length
    for(int l=2; l<n; ++l){
      for(int i=1; i<n-l+1; ++i){
        int j = i + l - 1;
        m[i][j] = Integer.MAX_VALUE;
        // intermediate
        for(int k=i; k<j; ++k){
          int cost = m[i][k] + m[k+1][j] + p[i-1]*p[k]*p[j];          
          if(cost < m[i][j]){
            m[i][j] = cost;
            s[i][j] = k;
          }
        }
      }
    } 
    printOptimalParen(s, 1, n-1);
    System.out.println();
    return m[1][n-1];
  } 

  // Memoization
  // Dimension of all matrices are given in the p 
  public int matrixChainCost(int[] p, int i, int j, int[][] dp, int[][] s){
    if(i == j)
        return 0;

    if(dp[i][j] != -1)
      return dp[i][j];

    int min = Integer.MAX_VALUE;
    int div = -1;
    for(int k=i; k<j; ++k){
      int cost = matrixChainCost(p, i, k, dp, s) +  
                 matrixChainCost(p, k+1, j, dp, s) +
                 p[i-1]*p[k]*p[j];
      if(cost < min){
        min = cost;
        div = k;
      }
    }
    dp[i][j] = min;
    s[i][j] = div;
    return min;
  } 

  public int matrixChainCostMemoization(int[] p){
    int n = p.length;
    int[][] m = new int[n][n];
    for(int[] t : m)
      Arrays.fill(t, -1);

    int[][] s = new int[n][n];
    matrixChainCost(p, 1, n-1, m, s);
    printOptimalParen(s, 1, n-1);
    System.out.println();
    return m[1][n-1];
  }

  public static void main(String[] args){
    int a[] = {30, 35, 15, 5, 10, 20, 25};
    MatrixChainMultiplication ob = new MatrixChainMultiplication();
    System.out.println(ob.matrixChainOrder(a));
    System.out.println(ob.matrixChainCostMemoization(a));
  }
}
