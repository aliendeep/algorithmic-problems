/*
Given an 2D board, count how many different battleships are in it. The battleships 
are represented with 'X's, empty slots are represented with '.'s. You may assume the following rules:

You receive a valid board, made of only battleships or empty slots.
Battleships can only be placed horizontally or vertically. In other words, they 
can only be made of the shape 1xN (1 row, N columns) or Nx1 (N rows, 1 column).
At least one horizontal or vertical cell separates between two battleships - 
there are no adjacent battleships.
Example:
X..X
...X
...X
In the above board there are 2 battleships.
Your algorithm should not modify the value of the board.
*/

public class Solution {
    // DFS
    int[][] move = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    void dfs(char[][] board, int r, int c, boolean[][] visited){
        visited[r][c] = true;

        for(int i=0; i<4; i++){
            int r1 = r + move[i][0];
            int c1 = c + move[i][1];
            
            if(r1 < 0 || r1 >= board.length || c1 < 0 || c1 >= board[0].length || visited[r1][c1] || board[r1][c1] != 'X')
                continue;
            dfs(board, r1, c1, visited);
        }
    }
    
    public int countBattleships(char[][] board) {
        if (board.length == 0 || board[0].length == 0) 
            return 0;
        
        int r = board.length;
        int c = board[0].length;

        boolean[][] visited = new boolean[r][c];
        
        int cnt = 0;
        for(int i=0; i<r; ++i){
            for(int j=0; j<c; ++j){
                if(board[i][j] == 'X' && !visited[i][j]){
                    dfs(board, i, j, visited);
                    cnt++;
                }
            }
        }
        return cnt;
    }
}

class Solution2 {
    // Follow up: O(1) extra memory
    // Count only top leftmost corner of a battleship
    public int countBattleships(char[][] board) {
        if (board.length == 0 || board[0].length == 0) 
            return 0;
        
        int r = board.length;
        int c = board[0].length;
        int cnt = 0;
        for(int i=0; i<r; ++i){
            for(int j=0; j<c; ++j){
                if(board[i][j] == '.')              continue;
                if(i > 0 && board[i-1][j] == 'X')   continue;
                if(j > 0 && board[i][j-1] == 'X')   continue;
                cnt++;
            }
        }
        return cnt;
    }
}
