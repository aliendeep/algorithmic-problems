class Solution {
public:
    #define GRID_SIZE 9
    bool bktk(vector<vector<char>>& board, int r, int c, int row[][10], int col[][10], int box[][3][10]){
        // if column is out of range, go to the next col
        if(c == GRID_SIZE){
            c = 0;
            // go to the next row
            r++;
        }
        // done
        if(r == GRID_SIZE){
            // base case
            return true;
        }
        // if (r,c) is not empty
        if(board[r][c] != '.'){
            return bktk(board, r, c+1, row, col, box);
        }
        else{
            // try all possible number
            for(int i=1; i<=9; i++){
                // if already taken
                if(row[r][i] || col[c][i] || box[r/3][c/3][i])
                    continue;
                board[r][c] = i + '0';
                row[r][i] = 1;
                col[c][i] = 1;
                box[r/3][c/3][i] = 1;
                // if found a solution, then return true
                if(bktk(board, r, c+1, row, col, box))
                    return true;
                    
                // Undo Assignments, if no solution has been found
                board[r][c] = '.';
                row[r][i] = 0;
                col[c][i] = 0;
                box[r/3][c/3][i] = 0;        
            }
        }
        return false;
    }
    
    void solveSudoku(vector<vector<char>>& board) {
        // int array, 0 - true. 1 - false
        int row[GRID_SIZE][10] = {0};
        int col[GRID_SIZE][10] = {0};
        int box[GRID_SIZE/3][GRID_SIZE/3][10] = {0};
        int r = board.size();
        int c = board[0].size();
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                if(board[i][j] != '.'){
                    int val = board[i][j] - '0';
                    row[i][val] = 1;
                    col[j][val] = 1;
                    box[i/3][j/3][val] = 1;
                }
            }
        }
        bktk(board, 0, 0, row, col, box);
    }
};