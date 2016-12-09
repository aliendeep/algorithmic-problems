/*
Given a positive integer n, find the least number of perfect square numbers 
(for example, 1, 4, 9, 16, ...) which sum to n.

For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, 
return 2 because 13 = 4 + 9.
*/
public class Solution {
    public int numSquares(int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp, n+1);
        
        // dp[i] = the least number of perfect 
        // square numbers which sum to i
        dp[0] = 0;
        for(int i=1; i<=n; i++){
            // i must be sum of sum number i-j*j + j*j
            for(int j=1; j*j<=i; j++){
                // j*j is a square
                dp[i] = Math.min(dp[i], dp[i-j*j] + 1);
            }
        }
        return dp[n];
    }
}

// BFS
class Solution2 {
    // Alternative: BFS
    public int numSquares(int n) {
        Queue<Integer> Q = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        Q.add(0);
        visited.add(0);
        
        int distance = 0;
        while(!Q.isEmpty()){
            int size = Q.size();
            distance++;
            // for all nodes in the same level
            while(size-- > 0){
                int u = Q.remove();
                for(int i=1; i*i<=n; ++i){
                    int v = u + i*i;
                    // reached n
                    if(v == n){
                        return distance;
                    }
                    if(v > n)
                        break;
                    if(!visited.contains(v)){
                        Q.add(v);
                        visited.add(v);
                    }
                }
            }
        }
        return distance;
    }
}