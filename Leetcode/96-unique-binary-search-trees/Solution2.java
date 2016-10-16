public class Solution {
    // http://www.geeksforgeeks.org/program-nth-catalan-number/
    // Catalan Number
    public int numTrees(int n) {
        if(n <= 1)  
            return 1;
        int[] cnt = new int[n+1];
        cnt[0] = 1;
        cnt[1] = 1;
        for(int i=2;i<=n; i++){
            for(int j=0; j<i; j++)
                cnt[i] += cnt[j] * cnt[i-j-1];
        }
        return cnt[n];
    }
}