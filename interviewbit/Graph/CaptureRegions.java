/*
Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.

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

public class Solution {
    class Pair{
        int r, c;
        public Pair(int x, int y){
            this.r = x;
            this.c = y;
        }
        
    }
    
    public void bfs(ArrayList<ArrayList<Character>> board, int r, int c, boolean[][] visited){
        int row = board.size();
        int col = board.get(0).size();
        
        int move[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        Queue<Pair> q = new LinkedList<>();
        visited[r][c] = true;
        q.add(new Pair(r, c));
        
        while(!q.isEmpty()){
            Pair p = q.remove();
            board.get(p.r).set(p.c, 'A');
            
            for(int i=0; i<4; i++){
                int x1 = p.r + move[i][0];
                int y1 = p.c + move[i][1];
                if(x1 >= 0 && x1 < row && y1 >= 0 && y1 < col){
                    if(board.get(x1).get(y1) == 'O' && visited[x1][y1] == false){
                        visited[x1][y1] = true;
                        q.add(new Pair(x1, y1));            
                    }
                }
            }
        }
    }
    void convert(ArrayList<ArrayList<Character>> board){
        // convert all O to X
        int r = board.size();
        int c = board.get(0).size();

        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                if(board.get(i).get(j) == 'O')
                    board.get(i).set(j, 'X');                
            }
        }
        
        // Convert all A's to O
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                if(board.get(i).get(j)== 'A')
                    board.get(i).set(j, 'O');                
            }
        }
    }     
  public void solve(ArrayList<ArrayList<Character>> board) {
        int r = board.size();
        if(r <= 1)
            return;
        int c = board.get(0).size();
        
        boolean[][] visited = new boolean[r][c];
        // first row & last row
        for(int j=0; j<c; j++){
            if(board.get(0).get(j) == 'O' && visited[0][j] == false){
                bfs(board, 0, j, visited);
            }
            if(board.get(r-1).get(j) == 'O' && visited[r-1][j] == false){
                bfs(board, r-1, j, visited);
            }
        }
        
        // first col & last col
        for(int i=0; i<r; i++){
            if(board.get(i).get(0) == 'O' && visited[i][0] == false){
                bfs(board, i, 0, visited);
            }
            if(board.get(i).get(c-1) == 'O' && visited[i][c-1] == false){
                bfs(board, i, c-1, visited);
            }
        }
        convert(board);
  }
}
