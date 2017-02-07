/*
Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. 
An island is surrounded by water and is formed by connecting adjacent lands 
horizontally or vertically. You may assume all four edges of the grid are all 
surrounded by water.

Example 1:

11110
11010
11000
00000
Answer: 1

Example 2:

11000
11000
00100
00011
Answer: 3

https://leetcode.com/problems/number-of-islands/
*/
// Time: O(V+E) : O(rc)
public class Solution {
    int[][] move = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    void dfs(int r, int c, char[][] grid, boolean[][] visited){
        visited[r][c] = true;   
        for(int i=0; i<4; i++){
            int r1 = r + move[i][0];
            int c1 = c + move[i][1];
            if(r1 >= 0 && r1 < grid.length && c1 >= 0 && c1 < grid[0].length && grid[r1][c1] == '1' && !visited[r1][c1])
                dfs(r1, c1, grid, visited);
        }
    }
    
    public int numIslands(char[][] grid) {
        if(grid.length == 0)
            return 0;
        
        int row = grid.length;
        int col = grid[0].length;

        boolean[][] visited = new boolean[row][col];
        int cnt = 0;
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                if(grid[i][j] == '1' && !visited[i][j]){
                    dfs(i, j, grid, visited);
                    cnt++;
                }
            }
        }
        return cnt;
    }
}

// Union find
class Solution2 {
    // number of columns
    int cols;
    int rows;
    int[] parent;
    int[] rank;

    // Convert 2d index (r, c) to 1d index 
    // n*r + c (n is the number of rows)
    public int getID(int r, int c){
        return r*cols + c;
    }
    
    // Proportional to the height of the tree
    // O(h) Worst case O(n)
    public int findSet(int x){
        if(x != parent[x])
            parent[x] = findSet(parent[x]);
        return parent[x];
    }

    public void union(int x, int y){
        link(findSet(x), findSet(y));
    }
    
    // O(1)
    public void link(int x, int y){
        if(rank[x] > rank[y])
            parent[y] = x;
        else{
            parent[x] = y;
            if(rank[x] == rank[y]){
                rank[y] = rank[x] + 1;
            }
        }
    }
    
    public int numIslands(char[][] grid) {
        rows = grid.length;
        if(rows == 0)   return 0;
        cols = grid[0].length;
        parent = new int[rows*cols];
        rank = new int[rows*cols];
        
        for(int i=0; i<rows*cols; ++i)
            parent[i] = i;
            
        int[][] move = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int cnt = 0;
        for(int r=0; r<rows; ++r){
            for(int c=0; c<cols; ++c){
                if(grid[r][c] == '0')
                    continue;
                // land
                int u = getID(r, c);
                cnt++;
                for(int i=0; i<4; i++){
                    int _r = r + move[i][0];
                    int _c = c + move[i][1];
                    // invalid or water
                    if(_r < 0 || _r >= rows || _c < 0 || _c >= cols || grid[_r][_c] == '0')
                        continue;
                    
                    int v = getID(_r, _c);
                    if(findSet(u) != findSet(v)){
                        union(u, v);
                        cnt--;
                    }
                }
            }
        }
        return cnt;
    }
}

// BFS
public class Solution {
    class Pair{
        int r, c;
        public Pair(int r1, int c1){
            r = r1;
            c = c1;
        }
    }
    int[][] move = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    int rows, cols;
    char[][] grid;
    boolean[][] visited;
    
    void bfs(int r, int c){
        Queue<Pair> Q = new LinkedList<>();
        Q.add(new Pair(r, c));
        visited[r][c] = true;
        
        while(!Q.isEmpty()){
            Pair f = Q.remove();
            
            for(int i=0; i<4; i++){
                int _r = f.r + move[i][0];
                int _c = f.c + move[i][1];
                if(_r < 0 || _r >= rows || _c < 0 || _c >= cols || grid[_r][_c] == '0' || visited[_r][_c])
                    continue;
                    
                visited[_r][_c] = true;
                Q.add(new Pair(_r, _c));
            }
        }
    }
       
    public int numIslands(char[][] grid) {
        rows = grid.length;
        if(rows == 0)   return 0;
        cols = grid[0].length;
        this.grid = grid;
        
        int cnt = 0;
        visited = new boolean[rows][cols];        
        for(int r=0; r<rows; ++r){
            for(int c=0; c<cols; ++c){
                if(grid[r][c] == '0' || visited[r][c])
                    continue;
                
                bfs(r, c);
                cnt++;
            }
       }
        return cnt;
    }
}
