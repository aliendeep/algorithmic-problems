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