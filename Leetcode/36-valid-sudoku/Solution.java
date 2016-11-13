/*
Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.

The Sudoku board could be partially filled, where empty cells are filled with the character '.'.


A partially filled sudoku which is valid.

Note:
A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated.
*/
public class Solution {
    boolean isValid(int[] cnt){
        for(int c=1; c<=9; c++)
            if(cnt[c] > 1)
                return false;
        return true;
    }
    
    boolean isValidRow(char[][] board, int row){
        int[] cnt = new int[10];
        for(int c=0; c<9; c++){
            if(board[row][c] != '.')
                cnt[board[row][c] - '0']++;
        }
        return isValid(cnt);
    }
    
    boolean isValidCol(char[][] board, int col){
        int[] cnt = new int[10];
        for(int r=0; r<9; r++){
            if(board[r][col] != '.')
                cnt[board[r][col] - '0']++;
        }
        return isValid(cnt);
    }    
    
    boolean isValidCell(char[][] board, int startRow, int startCol){
        int[] cnt = new int[10];
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(board[startRow+i][startCol+j] != '.'){
                    cnt[board[startRow+i][startCol+j] - '0']++;
                }
            }
        }
        return isValid(cnt);
    }
    
    public boolean isValidSudoku(char[][] board) {
        int size = board.length;
        for(int i=0; i<size; i++){
            if(!isValidRow(board, i))
                return false;
            if(!isValidCol(board, i))
                return false;
        }        

        for(int i=0; i<9; i+=3){
            for(int j=0; j<9; j+=3){
                if(!isValidCell(board, i, j))
                    return false;
            }
        }
        return true;
    }
}