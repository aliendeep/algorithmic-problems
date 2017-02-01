/*
Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where 
"adjacent" cells are those horizontally or vertically neighboring. The same letter 
cell may not be used more than once.

For example,
Given board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]
word = "ABCCED", -> returns true,
word = "SEE", -> returns true,
word = "ABCB", -> returns false.
*/
// DFS
// visited array
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
            
        visited[r][c] = true;
        for(int i=0; i<4; i++){
            int r1 = r + move[i][0];
            int c1 = c + move[i][1];
            if(!isValid(r1, c1, board.length, board[0].length) || visited[r1][c1] == true)
                continue;
            
            if(word.charAt(0) == board[r1][c1]){
                if(dfs(board, r1, c1, visited, word.substring(1)))
                    return true;
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
                    if(dfs(board, i, j, visited, word.substring(1)))
                        return true;
                }
            }
        }
        return false;
    }
}

// Without using substring and visited array
class Solution2 {
    int[][] move = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    public boolean isPatternFound(char[][] board, String word, int r, int c, int offset){
        if(offset == word.length())
            return true;
            
        if(r >= board.length || r < 0 || c >= board[0].length || c < 0 || word.charAt(offset) != board[r][c])
            return false;

        // One character can be used at most one
        board[r][c] = '#';
        for(int i=0; i<4; i++){
            int r1 = r + move[i][0];
            int c1 = c + move[i][1];
            if(isPatternFound(board, word, r1, c1, offset + 1))
                return true;
        }
        board[r][c] = word.charAt(offset);
        return false;
    }

    public boolean exist(char[][] board, String word) {
        int r = board.length;
        if(r == 0)  return false;
        int c = board[0].length;
        
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                if(word.charAt(0) == board[i][j]){
                    if(isPatternFound(board, word, i, j, 0))
                        return true;
                }                
            }
        }
        return false;
    }
}
