/*
Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

For example,
X X X X
X O O X
X X O X
X O X X
After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X
*/
// test cases: 
// ["OO","OO"]
// ["OOXX","XXXX", "XOOX", "XXXX"]
public class Solution {
    class Pair{
        int x, y;
        Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
        
    }
    
    void convert(char[][] board){
        // convert all O to X
        int r = board.length;
        int c = board[0].length;

        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                if(board[i][j] == 'O')
                    board[i][j] = 'X';                
            }
        }
        
        // Convert all A's to O
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                if(board[i][j] == 'A')
                    board[i][j] = 'O';                
            }
        }
    }    
    
    void bfs(char[][] board, int row, int col, int[][] visited){
        int r = board.length;
        int c = board[0].length;
        
        int move[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(row, col));
        visited[row][col] = 1;
        while(!q.isEmpty()){
            Pair p = q.remove();
            board[p.x][p.y] = 'A';
            
            for(int i=0; i<4; i++){
                int x1 = p.x + move[i][0];
                int y1 = p.y + move[i][1];
                if(x1 >= 0 && x1 < r && y1 >= 0 && y1 < c){
                    if(board[x1][y1] == 'O' && visited[x1][y1] == 0){
                        visited[x1][y1] = 1;
                        q.add(new Pair(x1, y1));            
                    }
                }
            }
        }
    }
    
    public void solve(char[][] board) {
        int r = board.length;
        if(r <= 1)
            return;
        int c = board[0].length;
        
        int[][] visited = new int[r][c];
        // first row & last row
        for(int j=0; j<c; j++){
            if(board[0][j] == 'O' && visited[0][j] == 0){
                bfs(board, 0, j, visited);
            }
            if(board[r-1][j] == 'O' && visited[r-1][j] == 0){
                bfs(board, r-1, j, visited);
            }
        }
        
        // first col & last col
        for(int i=0; i<r; i++){
            if(board[i][0] == 'O' && visited[i][0] == 0){
                bfs(board, i, 0, visited);
            }
            if(board[i][c-1] == 'O' && visited[i][c-1] == 0){
                bfs(board, i, c-1, visited);
            }
        }
        convert(board);
    }
}