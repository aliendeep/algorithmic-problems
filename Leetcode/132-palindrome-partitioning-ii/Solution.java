/*
Given a string s, partition s such that every substring of the partition is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.

For example, given s = "aab",
Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.
*/

public class Solution {
    // DP
    // Time: O(n^2)
    // http://www.geeksforgeeks.org/dynamic-programming-set-17-palindrome-partitioning/
    public int minCut(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int[] cut = new int[n];
        Arrays.fill(cut, n+1);
        
        // 1 length palindrome
        for(int i=0; i<n; i++)
            dp[i][i] = true;
        
        // for all possible length
        for(int l=2; l<=n; l++){
            // starting points
            for(int i=0; i<n-l+1; i++){
                int j = i + l - 1;
                // if first and last char is same
                if(s.charAt(i) == s.charAt(j)){
                    if(l ==  2)     dp[i][j] = true;
                    else            dp[i][j] = dp[i+1][j-1];
                }
            }
        }    
        
        // find min cut
        for(int i=0; i<n; i++){
            // string 0..i is palindrome, so no cut needed
            if(dp[0][i])
                cut[i] = 0;
            else{
                // see if it's possible to reduce number of cut
                for(int j=0; j<i; j++){
                    // if the right half is palindrome 
                    if(dp[j+1][i] && cut[i] > cut[j] + 1)
                        cut[i] = cut[j] + 1;
                }
            }
        }
        return cut[n-1];
    }
}