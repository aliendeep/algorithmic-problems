class Solution {
public:
    bool isValid(int* cnt){
        for(int c=1; c<=9; c++)
            if(cnt[c] > 1)
                return false;
        return true;
    }
    
    bool isValidRow(vector<vector<char>>& board, int row){
        int cnt[10] = {0};
        for(int c=0; c<9; c++){
            if(board[row][c] != '.')
                cnt[board[row][c] - '0']++;
        }
        return isValid(cnt);
    }
    
    bool isValidCol(vector<vector<char>>& board, int col){
        int cnt[10] = {0};
        for(int r=0; r<9; r++){
            if(board[r][col] != '.')
                cnt[board[r][col] - '0']++;
        }
        return isValid(cnt);
    }    
    
    bool isValidCell(vector<vector<char>>& board, int startRow, int startCol){
        int cnt[10] = {0};
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(board[startRow+i][startCol+j] != '.'){
                    cnt[board[startRow+i][startCol+j] - '0']++;
                }
            }
        }
        return isValid(cnt);
    }
    
    bool isValidSudoku(vector<vector<char>>& board) {
        int size = board.size();
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
};