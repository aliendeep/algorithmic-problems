/*
Given an Android 3x3 key lock screen and two integers m and n, where 1 ≤ m ≤ n ≤ 9, 
count the total number of unlock patterns of the Android lock screen, which consist 
of minimum of m keys and maximum n keys.

Rules for a valid pattern:
Each pattern must connect at least m keys and at most n keys.
All the keys must be distinct.
If the line connecting two consecutive keys in the pattern passes through any other keys, 
the other keys must have previously selected in the pattern. No jumps through non 
selected key is allowed.
The order of keys used matters.

Explanation:
| 1 | 2 | 3 |
| 4 | 5 | 6 |
| 7 | 8 | 9 |
Invalid move: 4 - 1 - 3 - 6 
Line 1 - 3 passes through key 2 which had not been selected in the pattern.

Invalid move: 4 - 1 - 9 - 2
Line 1 - 9 passes through key 5 which had not been selected in the pattern.

Valid move: 2 - 4 - 1 - 3 - 6
Line 1 - 3 is valid because it passes through key 2, which had been selected in the pattern

Valid move: 6 - 5 - 4 - 1 - 9 - 2
Line 1 - 9 is valid because it passes through key 5, which had been selected in the pattern.

Example:
Given m = 1, n = 1, return 9.
*/

public class Solution {
    // DFS
    public int dfs(int cur, int[][] skip, boolean[] visited, int remaining){
        if(remaining < 0)           return 0;
        if(remaining == 0)          return 1;
        
        int cnt = 0;        
        visited[cur] = true;
        for(int i=1; i<=9; i++){
            // adjacent number or the number between them is already visited
            if(!visited[i] && (skip[cur][i] == 0 || visited[skip[cur][i]]))
                cnt += dfs(i, skip, visited, remaining-1);
        }
        visited[cur] = false;
        return cnt;
    }
    public int numberOfPatterns(int m, int n) {
        // 1, 3, 7, 9 Symmetric
        // 2, 4, 6, 8 Symmetric
        int[][] skip = new int[10][10];
        skip[1][3] = skip[3][1] = 2;
        skip[1][7] = skip[7][1] = 4;
        skip[9][3] = skip[3][9] = 6;
        skip[7][9] = skip[9][7] = 8;

        skip[1][9] = skip[9][1] = 5;
        skip[3][7] = skip[7][3] = 5;
        skip[4][6] = skip[6][4] = 5;
        skip[2][8] = skip[8][2] = 5;
        
        boolean[] visited = new boolean[10];
        int cnt = 0;
        // minimum of m keys and maximum n keys
        for(int i=m; i<=n; i++){
            // starting from 1
            cnt += dfs(1, skip, visited, i-1)*4;            
            cnt += dfs(2, skip, visited, i-1)*4;            
            cnt += dfs(5, skip, visited, i-1);            
        }
        return cnt;
    }
    public static void main(String[] args){
        Solution ob = new Solution();
        System.out.println(ob.numberOfPatterns(4, 8));
        System.out.println(ob.numberOfPatterns(3, 5));
        System.out.println(ob.numberOfPatterns(3, 3));
    }
}