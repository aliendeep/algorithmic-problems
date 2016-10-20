/*
Given a non-empty 2D matrix matrix and an integer k, find the max sum of a rectangle in the matrix such that its sum is no larger than k.

Example:
Given matrix = [
  [1,  0, 1],
  [0, -2, 3]
]
k = 2
The answer is 2. Because the sum of rectangle [[0, 1], [-2, 3]] is 2 and 2 is the max number no larger than k (k = 2).

Note:
The rectangle inside the matrix must have an area > 0.
What if the number of rows is much larger than the number of columns?
*/

// Time Complexity: O(c^2 rlogr)
// Space: O(r)
public class Solution {
    // https://www.quora.com/Given-an-array-of-integers-A-and-an-integer-k-find-a-subarray-that-contains-the-largest-sum-subject-to-a-constraint-that-the-sum-is-less-than-k
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int nr = matrix.length;
        int nc = matrix[0].length;
        int maxSum = Integer.MIN_VALUE;
    
        for(int c1=0; c1<nc; ++c1){
            int[] sum = new int[nr];
            for(int c2=c1;c2<nc; ++c2){
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