/*
Given two sparse matrices A and B, return the result of AB.

You may assume that A's column number is equal to B's row number.

Example:

A = [
  [ 1, 0, 0],
  [-1, 0, 3]
]

B = [
  [ 7, 0, 0 ],
  [ 0, 0, 0 ],
  [ 0, 0, 1 ]
]


     |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
                  | 0 0 1 |
*/

/* Test case:
[[1,0,0],[-1,0,3]]
[[7,0,0],[0,0,0],[0,0,1]]
[[1,-5]]
[[12],[-1]]
*/

public class Solution {
    // Straightforward solution
    public int[][] multiply(int[][] A, int[][] B) {
        int m = A.length;
        int aCol = A[0].length;
        int bCol = B[0].length;
        
        int[][] r = new int[m][bCol];
        // for all rows
        for(int i=0; i<m; i++){
            for(int k=0; k<aCol; k++){
                if(A[i][k] != 0){
                    // You may assume that A's column number is equal to B's row number.
                    for(int j=0; j<bCol; j++){
                        if(B[k][j] != 0){
                            r[i][j] += A[i][k]*B[k][j];
                        }
                    }
                }
            }
        }
        return r;
    }
}