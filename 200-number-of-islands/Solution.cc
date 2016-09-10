class Solution {
public:
    int r[4] = {0, 1, -1, 0};
    int c[4] = {1, 0, 0, -1};

    void DFS(vector<vector<char>>& grid, vector<vector<int>>& visited, int row, int col){
        visited[row][col] = 1;
        // change the char to 0
        grid[row][col] = 0;
        for(int i=0; i<4; i++){
            int x = row + r[i];
            int y = col + c[i];
            if(x>=0  && x<grid.size() && y >= 0 && y<grid[0].size() && grid[x][y] == '1' && !visited[x][y])
                DFS(grid, visited, x, y);
        }
    }
    
    int numIslands(vector<vector<char>>& grid) {
        int r = grid.size();
        if(r == 0)    return 0;
        int c = grid[0].size();
        int cnt = 0;
        vector<vector<int>> visited(r+1, vector<int>(c+1, 0));
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                if(grid[i][j] == '1' && !visited[i][j]){
                    DFS(grid, visited, i, j);
                    cnt++;
                }
            }
        }
        return cnt;
    }
};