// Write a program to solve a Sudoku puzzle by filling the empty cells.

public class SudokuSolver{
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