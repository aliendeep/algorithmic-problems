public class Solution {
    // Simulation
    // Write an example
    // Get next sum from previous sum
    public int maxRotateFunction(int[] A) {
        int n = A.length;
        if(n == 0)
            return 0;
            
        int totSum = 0;
        int prevSum = 0;
        for(int i=0; i<n; i++){
            totSum += A[i];
            prevSum += A[i]*i;
        }
        
        int maxSum = prevSum;
        int curSum = 0;
        for(int i=n-1; i>0; i--){
            // A[i] is shifted off
            curSum = prevSum - A[i]*n + totSum;
            maxSum = Math.max(maxSum, curSum);
            prevSum = curSum;
        }
        return maxSum;
    }
}