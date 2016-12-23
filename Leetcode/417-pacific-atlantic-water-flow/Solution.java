/*
Given an m x n matrix of non-negative integers representing the height of each 
unit cell in a continent, the "Pacific ocean" touches the left and top edges of 
the matrix and the "Atlantic ocean" touches the right and bottom edges.

Water can only flow in four directions (up, down, left, or right) from a cell 
to another one with height equal or lower.

Find the list of grid coordinates where water can flow to both the Pacific and 
Atlantic ocean.

Note:
The order of returned grid coordinates does not matter.
Both m and n are less than 150.
Example:

Given the following 5x5 matrix:

  Pacific ~   ~   ~   ~   ~ 
       ~  1   2   2   3  (5) *
       ~  3   2   3  (4) (4) *
       ~  2   4  (5)  3   1  *
       ~ (6) (7)  1   4   5  *
       ~ (5)  1   1   2   4  *
          *   *   *   *   * Atlantic

Return:

[[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with 
parentheses in above matrix).
*/
/*
Sample Input:
[[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
Output:
[[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
*/

import java.util.*;

// O(NM)
public class Solution {
    int r, c;
    int[][] matrix;
    int[][] move = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    void dfs(int x, int y, boolean[][] reachable){
        reachable[x][y] = true;

        for(int i=0; i<4; i++){
            int x1 = x + move[i][0];    
            int y1 = y + move[i][1];
            if(x1 < 0 || x1 >= r || y1 < 0 || y1 >= c || reachable[x1][y1])
                continue;
            // Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.
            if(matrix[x1][y1] < matrix[x][y])
                continue;
            dfs(x1, y1, reachable);
        }
    }

    public List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> result = new ArrayList<>();
        r = matrix.length;
        if(r == 0)
            return result;
        c = matrix[0].length;
        this.matrix = matrix;

        // Pacific 
        boolean[][] pacificReachable = new boolean[r][c];
        for(int i=0; i<r; i++){
            if(!pacificReachable[i][0])
              dfs(i, 0, pacificReachable); 
        }

        for(int j=0; j<c; j++){
            if(!pacificReachable[0][j])
              dfs(0, j, pacificReachable); 
        }

        // Atlantic
        boolean[][] atlanticReachable = new boolean[r][c];
        for(int i=0; i<r; i++){
            if(!atlanticReachable[i][c-1])
              dfs(i, c-1, atlanticReachable); 
        }

        for(int j=0; j<c; j++){
            if(!atlanticReachable[r-1][j])
              dfs(r-1, j, atlanticReachable); 
        }

        for(int i=0; i<r; i++){
          for(int j=0; j<c; j++){
            if(pacificReachable[i][j] && atlanticReachable[i][j]){
              int[] t = new int[2];
              t[0] = i;
              t[1] = j;
              result.add(t);
            }
          }
        }
        return result;
    }
}

// BFS
class Solution2{
    int r, c;
    int[][] matrix;
    int[][] move = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    void bfs(Queue<int[]> Q, boolean[][] visited){
        while(!Q.isEmpty()){
            int[] front = Q.remove();
            int x = front[0];
            int y = front[1];
            for(int i=0; i<4; ++i){
                int r1 = x + move[i][0];
                int c1 = y + move[i][1];
                // cell to another one with height equal or lower. (beware about the direction)
                if(r1 < 0 || r1 >= r || c1 < 0 || c1 >= c || visited[r1][c1] || matrix[r1][c1] < matrix[x][y])
                    continue;     
                visited[r1][c1] = true;
                int[] t = {r1, c1};
                Q.add(t);
            }
        }
    }

    public List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> result = new ArrayList<>();
        r = matrix.length;
        if(r == 0)
            return result;
        c = matrix[0].length;
        this.matrix = matrix;

        // Pacific 
        boolean[][] pacificVisited = new boolean[r][c];
        Queue<int[]> pacificQueue = new LinkedList<>();
        for(int i=0; i<r; i++){
            int[] t = {i, 0};
            pacificQueue.add(t);
            pacificVisited[i][0] = true;
        }

        for(int j=0; j<c; j++){
            int[] t = {0, j};
            pacificQueue.add(t);
            pacificVisited[0][j] = true;
        }

        bfs(pacificQueue, pacificVisited);

        // Atlantic
        boolean[][] atlanticVisited = new boolean[r][c];
        Queue<int[]> atlanticQueue = new LinkedList<>();
        for(int i=0; i<r; i++){
            int[] t = {i, c-1};
            atlanticQueue.add(t);
            atlanticVisited[i][c-1] = true;
        }

        for(int j=0; j<c; j++){
            int[] t = {r-1, j};
            atlanticQueue.add(t);
            atlanticVisited[r-1][j] = true;
        }
        bfs(atlanticQueue, atlanticVisited);

        for(int i=0; i<r; i++){
          for(int j=0; j<c; j++){
            if(pacificVisited[i][j] && atlanticVisited[i][j]){
              int[] t = new int[2];
              t[0] = i;
              t[1] = j;
              result.add(t);
            }
          }
        }
        return result;
    }
}
