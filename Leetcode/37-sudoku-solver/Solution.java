public class Solution {
    public static final int GRID_SIZE = 9;
    
    void print(char[][] board){
        for(int r=0; r<GRID_SIZE; r++){
            for(int c=0; c<GRID_SIZE; c++){
                System.out.print(board[r][c]);                
            }        
            System.out.println();
        }
    }
    boolean bktk(char[][] board, int r, int c, boolean[][] row, boolean[][] col, boolean[][][] box){
        // if column is out of range, go to the next col
        if(c == GRID_SIZE){
            c = 0;
            r++;
        }
        // Finished processing all
        if(r == GRID_SIZE){
            return true;
        }
        
        if(board[r][c] != '.')
            return bktk(board, r, c+1, row, col, box);
        else{
            // try with all possible number
            for(int number=1; number<=GRID_SIZE; number++){
                // if already taken
                if(row[r][number] == true || col[c][number] == true || box[r/3][c/3][number] == true)
                    continue;
                
                board[r][c] = (char)(number + '0');
                row[r][number] = true;
                col[c][number] = true; 
                box[r/3][c/3][number] = true;
                // if found a solution
                if(bktk(board, r, c+1, row, col, box) == true){
                    return true;
                }
                // undo
                board[r][c] = '.';
                row[r][number] = false;
                col[c][number] = false; 
                box[r/3][c/3][number] = false;
            }
        }   
        return false;
    }
    
    public void solveSudoku(char[][] board) {
        boolean[][] row = new boolean[GRID_SIZE][10];
        boolean[][] col = new boolean[GRID_SIZE][10];
        boolean[][][] box = new boolean[GRID_SIZE/3][GRID_SIZE/3][10];
        
        // initialization
        for(int r=0; r<GRID_SIZE; r++){
            for(int c=0; c<GRID_SIZE; c++){
                if(board[r][c] != '.'){
                    int val = board[r][c] - '0';
                    row[r][val] = true; 
                    col[c][val] = true; 
                    box[r/3][c/3][val] = true;
                }
            }
        }
        bktk(board, 0, 0, row, col, box);
    }
}

class Solution2 {
    public void solveSudoku(char[][] board) {
        solve(board);
    }
    
    public boolean isValid(char[][] board, int i, int j, char num){
        for(int r=0; r<9; ++r){
            if(board[r][j] == num)
                return false;
        }
        for(int c=0; c<9; ++c){
            if(board[i][c] == num)
                return false;
        }
        
        int sr = 3*(i/3);
        int sc = 3*(j/3);
        for(int r=0; r<3; ++r){
            for(int c=0; c<3; ++c){
                if(board[sr+r][sc+c] == num)
                    return false;
            }
        }        
        return true;
    }
    
    public boolean solve(char[][] board){
        for(int i=0; i<board.length; ++i){
            for(int j=0; j<board.length; ++j){
                if(board[i][j] == '.'){
                    // Try all possible num
                    for(int num=1; num<=9; ++num){
                        char c = (char)(num + '0');
                        if(isValid(board, i, j, c)){
                            board[i][j] = c;
                            // Rest of the board is solvable
                            if(solve(board))
                                return true;
                            // revert
                            board[i][j] = '.';
                        }
                    }
                    // No solution
                    return false;
                }
            }    
        }    
        return true;
    }
}
