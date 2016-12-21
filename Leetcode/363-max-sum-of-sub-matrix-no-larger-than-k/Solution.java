/*
Given a non-empty 2D matrix matrix and an integer k, find the max sum of a rectangle 
in the matrix such that its sum is no larger than k.

Example:
Given matrix = [
  [1,  0, 1],
  [0, -2, 3]
]
k = 2
The answer is 2. Because the sum of rectangle [[0, 1], [-2, 3]] is 2 and 2 is 
the max number no larger than k (k = 2).

Note:
The rectangle inside the matrix must have an area > 0.
What if the number of rows is much larger than the number of columns?

Sample Input:
[[2,2,-1]]
0
[[2,2,-1]]
3
[[5,-4,-3,4],[-3,-4,4,5],[5,1,5,-4]]
10
[[5,-4,-3,4],[-3,-4,4,5],[5,1,5,-4]]
8

Sample Output:
-1
3
10
8
*/

// Solution 1: Bruteforce O(n^4)
public class Solution {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int row = matrix.length;
        if(row == 0)
            return 0;
        int col = matrix[0].length;
        if(col == 0)
            return 0;
        // Precompute CumSum
        int[][] cumSum = new int[row][col];
        cumSum[0][0] = matrix[0][0];
        for(int r=1; r<row; ++r){
            cumSum[r][0] = cumSum[r-1][0] + matrix[r][0];  
        }
        
        for(int c=1; c<col; ++c){
            cumSum[0][c] = cumSum[0][c-1] + matrix[0][c];  
        }
        
        // Draw Picture
        for(int r=1; r<row; ++r){
            for(int c=1; c<col; ++c){
                cumSum[r][c] = cumSum[r][c-1] + cumSum[r-1][c] - cumSum[r-1][c-1] + matrix[r][c];
            }
        }
        int maxSum = Integer.MIN_VALUE;
        for(int r1=0; r1 < row; ++r1){
            for(int c1 = 0; c1 < col; ++c1){
                for(int r2=r1; r2 < row; ++r2){
                    for(int c2 = c1; c2 < col; ++c2){
                        int area = cumSum[r2][c2] - 
                                   (r1 > 0 ? cumSum[r1-1][c2] : 0) -
                                   (c1 > 0 ? cumSum[r2][c1-1] : 0) +
                                   (r1 > 0 && c1 > 0 ? cumSum[r1-1][c1-1] : 0);
                        // Max sum is no larger than k
                        if(area <= k)
                            maxSum = Math.max(maxSum, area); 
                    }        
                }        
            }
        }
        return maxSum;
    }
}

public class Solution2 {
    // O(c^2 rlogr)
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int row = matrix.length;
        if(row == 0)
            return 0;
        int col = matrix[0].length;
        if(col == 0)
            return 0;
        // Precompute CumSum
        int[][] cumSum = new int[row][col];
        cumSum[0][0] = matrix[0][0];
        for(int r=1; r<row; ++r){
            cumSum[r][0] = cumSum[r-1][0] + matrix[r][0];  
        }
        
        for(int c=1; c<col; ++c){
            cumSum[0][c] = cumSum[0][c-1] + matrix[0][c];  
        }
        
        // Draw Picture
        for(int r=1; r<row; ++r){
            for(int c=1; c<col; ++c){
                cumSum[r][c] = cumSum[r][c-1] + cumSum[r-1][c] - cumSum[r-1][c-1] + matrix[r][c];
            }
        }
        int maxArea = Integer.MIN_VALUE;
        for(int c1 = 0; c1 < col; ++c1){
            for(int c2 = c1; c2 < col; ++c2){
                TreeSet<Integer> set = new TreeSet<>();
                set.add(0);
                for(int r=0; r < row; ++r){
                    int area = cumSum[r][c2] - (c1 > 0 ? cumSum[r][c1-1] : 0);
                    // Ceiling: Smallest number greater than or equal
                    // Find if there is any previous number in the set
                    Integer ceil = set.ceiling(area - k);
                    if(ceil != null){
                        maxArea = Math.max(maxArea, area - ceil);
                    }
                    set.add(area);
                }        
            }        
        }
        return maxArea;
    }
}

public class Solution {
    // O(r^2 clogc)
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int row = matrix.length;
        if(row == 0)
            return 0;
        int col = matrix[0].length;
        if(col == 0)
            return 0;
        // Precompute CumSum
        int[][] cumSum = new int[row][col];
        cumSum[0][0] = matrix[0][0];
        for(int r=1; r<row; ++r){
            cumSum[r][0] = cumSum[r-1][0] + matrix[r][0];  
        }
        
        for(int c=1; c<col; ++c){
            cumSum[0][c] = cumSum[0][c-1] + matrix[0][c];  
        }
        
        // Draw Picture
        for(int r=1; r<row; ++r){
            for(int c=1; c<col; ++c){
                cumSum[r][c] = cumSum[r][c-1] + cumSum[r-1][c] - cumSum[r-1][c-1] + matrix[r][c];
            }
        }
        int maxArea = Integer.MIN_VALUE;
        for(int r1 = 0; r1 < row; ++r1){
            for(int r2 = r1; r2 < row; ++r2){
                TreeSet<Integer> set = new TreeSet<>();
                set.add(0);
                for(int c=0; c < col; ++c){
                    int area = cumSum[r2][c] - (r1 > 0 ? cumSum[r1-1][c] : 0);
                    // Ceiling: Smallest number greater than or equal
                    // Find if there is any previous number in the set
                    Integer ceil = set.ceiling(area - k);
                    if(ceil != null){
                        maxArea = Math.max(maxArea, area - ceil);
                    }
                    set.add(area);
                }        
            }        
        }
        return maxArea;
    }
}

// Solution 3
// Time Complexity: O(c^2 rlogr)
// Space: O(r)
public class Solution4 {
    // https://www.quora.com/Given-an-array-of-integers-A-and-an-integer-k-find-a-subarray-that-contains-the-largest-sum-subject-to-a-constraint-that-the-sum-is-less-than-k
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int nr = matrix.length;
        int nc = matrix[0].length;
        int maxSum = Integer.MIN_VALUE;
    
        for(int c1=0; c1<nc; ++c1){
            int[] sum = new int[nr];
            for(int c2=c1; c2<nc; ++c2){
                for(int r=0; r<nr; r++)
                    sum[r] += matrix[r][c2];
            
                TreeSet<Integer> set = new TreeSet<>();
                // Add 0 to cover the single row case
                set.add(0);
                int run = 0;
                for(int r=0; r<nr; ++r){
                    run += sum[r];
                    Integer sum_i = set.ceiling(run - k);
                    if(sum_i != null){
                        maxSum = Math.max(maxSum, run - sum_i);
                    }
                    set.add(run);
                }
            }
        }
        return maxSum;
    }
}
