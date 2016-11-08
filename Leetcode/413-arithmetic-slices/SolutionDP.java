public class Solution {
    public int numberOfArithmeticSlices(int[] A) {
        int n = A.length;
        int[] dp = new int[n];
        
        for(int i=2; i<n; ++i){
            // If we can extend the arithmetric sequence by appending A[i]
            // Number of arithmetic sequences ending at A[i] == Number of arithmetic sequences endihn at A[i-1] + 1
            if(A[i] -  A[i-1] == A[i-1] - A[i-2]){
                dp[i] = dp[i-1] + 1;
            }
        }
        int r = 0;
        for(int i=2; i<n; ++i){
            r += dp[i];
        }
        return r;
    }
}