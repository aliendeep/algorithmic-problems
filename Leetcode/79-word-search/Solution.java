public class Solution {
    boolean isValid(int x, int y, int r, int c){
        if(x >= 0 && x < r && y >= 0 && y < c )
            return true;
        return false;
    }
    int[][] move = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    boolean dfs(char[][] board, int r, int c, boolean[][] visited, String word){
        if(word.length() == 0)
            return true;
            
        for(int i=0; i<4; i++){
            int r1 = r + move[i][0];
            int c1 = c + move[i][1];
            if(!isValid(r1, c1, board.length, board[0].length) || visited[r1][c1] == true)
                continue;
            
            if(word.charAt(0) == board[r1][c1]){
                visited[r1][c1] = true;
                if(dfs(board, r1, c1, visited, word.substring(1)))
                    return true;
                visited[r1][c1] = false;
            }
        }
        visited[r][c] = false;
        return false;
    }
    
    // DFS
    public boolean exist(char[][] board, String word) {
        int r = board.length;
        if(r == 0)  return false;
        int c = board[0].length;
        
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                if(word.charAt(0) == board[i][j]){
                    boolean[][] visited = new boolean[r][c];
                    visited[i][j] = true;
                    if(dfs(board, i, j, visited, word.substring(1)))
                        return true;
                }
            }
        }
        return false;
    }
}