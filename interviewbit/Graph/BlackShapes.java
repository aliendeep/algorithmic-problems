/*
https://www.interviewbit.com/problems/black-shapes/
Given N * M field of O's and X's, where O=white, X=black
Return the number of black shapes. A black shape consists of one or more adjacent X's (diagonals not included)

Example:

OOOXOOO
OOXXOXO
OXOOOXO

answer is 3 shapes are  :
(i)    X
     X X
(ii)
      X
 (iii)
      X
      X
Note that we are looking for connected shapes here.

For example,

XXX
XXX
XXX
is just one single connected black shape.
*/

public class Solution {
    int n, m;
    int[][] move = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
    // dfs
    public void dfs(ArrayList<String> a, int r, int c, boolean[][] visited){
        for(int i=0; i<4; ++i){
            int r1 = r + move[i][0];
            int c1 = c + move[i][1];
            if(r1 < 0 || r1 >= n || c1 < 0 || c1 >= m || visited[r1][c1] == true || a.get(r1).charAt(c1) == 'O')
                continue;
            visited[r1][c1] = true;
            dfs(a, r1, c1, visited);
        }
    }
    
    public int black(ArrayList<String> a) {
        // row
        n = a.size();
        if(n == 0)      return 0;
        // col
        m = a.get(0).length();
        
        boolean[][] visited = new boolean[n][m];
        int cnt = 0;
        for(int i=0; i<n; ++i){
            for(int j=0; j<m; ++j){
                if(a.get(i).charAt(j) == 'X' && !visited[i][j]){    
                    visited[i][j] = true;
                    dfs(a, i, j, visited);
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
