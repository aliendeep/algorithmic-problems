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

// Shortest path solution
// Generate all substrings
// Edge from i to j+1 if s[i..j] is a palindrome
public class Solution {
    public int minCut(String s) {
        int n = s.length();
        // Palindrome lookup table
        boolean[][] isPalindrome = new boolean[n][n];
        for(int i=0; i<n; ++i)
            isPalindrome[i][i] = true;
        
        for(int l=1; l<n; ++l){
            int i = 0, j = l;
            while(j < n){
                if(s.charAt(i) == s.charAt(j) && (i + 1 > j - 1 || isPalindrome[i+1][j-1])){
                    isPalindrome[i][j] = true;
                }
                ++i;
                ++j;
            }
        }
        
        // Construct graph (directed edge)       
        List<List<Integer>> graph = new ArrayList<>();
        for(int i=0; i<n; ++i){
            graph.add(new ArrayList<>());
            for(int j=i; j<n; ++j){
                if(isPalindrome[i][j]){
                    graph.get(i).add(j+1);
                }
            }
        }
        
        int[] d = new int[n+1];
        Arrays.fill(d, -1);
        
        // BFS (find shortest path from 0 to n-1)
        Queue<Integer> Q = new LinkedList<>();
        Q.add(0);
        d[0] = 0;
        while(!Q.isEmpty()){
            int size = Q.size();
            for(int i=0; i<size; ++i){
                int t = Q.remove();
                if(t == n)
                    return d[n] - 1;
                List<Integer> neighbor = graph.get(t);
                if(neighbor == null)
                    continue;
                for(int x=neighbor.size()-1; x>=0; --x){
                    int adj = neighbor.get(x);
                    if(d[adj] != -1)
                        continue;
                    d[adj] = d[t] + 1;
                    if(adj == n)
                        return d[n] - 1;
                    Q.add(adj);
                }
            }
        }
        return -1;
    }
}

class Solution3 {
    public int minCut(String s) {
        int n = s.length();
        // Palindrome lookup table
        boolean[][] isPalindrome = new boolean[n][n];
        for(int i=0; i<n; ++i)
            isPalindrome[i][i] = true;

        for(int l=2; l<=n; ++l){
            for(int i=0; i<=n-l; ++i){
                int j = i + l - 1;
                if(s.charAt(i) == s.charAt(j)){
                    if(l == 2)  isPalindrome[i][j] = true;
                    else        isPalindrome[i][j] = isPalindrome[i+1][j-1];
                }
            }
        }
        
        // Construct graph (directed edge)       
        List<List<Integer>> graph = new ArrayList<>();
        for(int i=0; i<n; ++i){
            graph.add(new ArrayList<>());
            for(int j=i; j<n; ++j){
                if(isPalindrome[i][j]){
                    graph.get(i).add(j+1);
                }
            }
        }
        
        int[] d = new int[n+1];
        Arrays.fill(d, -1);
        
        // BFS (find shortest path from 0 to n-1)
        Queue<Integer> Q = new LinkedList<>();
        Q.add(0);
        d[0] = 0;
        while(!Q.isEmpty()){
            int size = Q.size();
            for(int i=0; i<size; ++i){
                int t = Q.remove();
                if(t == n)
                    return d[n] - 1;
                List<Integer> neighbor = graph.get(t);
                if(neighbor == null)
                    continue;
                for(int x=neighbor.size()-1; x>=0; --x){
                    int adj = neighbor.get(x);
                    if(d[adj] != -1)
                        continue;
                    d[adj] = d[t] + 1;
                    if(adj == n)
                        return d[n] - 1;
                    Q.add(adj);
                }
            }
        }
        return -1;
    }
}