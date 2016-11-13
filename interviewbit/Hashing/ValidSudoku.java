public class ValidSudoku {
    boolean isValid(int[] cnt){
        for(int c=1; c<=9; c++)
            if(cnt[c] > 1)
                return false;
        return true;
    }
    
    boolean isValidRow(final List<String> board, int row){
        int[] cnt = new int[10];
        
        for(int c=0; c<9; c++){
            if(board.get(row).charAt(c) != '.')
                cnt[board.get(row).charAt(c) - '0']++;
        }
        return isValid(cnt);
    }

    boolean isValidCol(final List<String> board, int col){
        int[] cnt = new int[10];
        for(int r=0; r<9; r++){
            if(board.get(r).charAt(col) != '.')
                cnt[board.get(r).charAt(col) - '0']++;
        }
        return isValid(cnt);
    }    
    
    boolean isValidCell(final List<String> board, int startRow, int startCol){
        int[] cnt = new int[10];
        
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(board.get(startRow+i).charAt(startCol+j) != '.'){
                    cnt[board.get(startRow+i).charAt(startCol+j) - '0']++;
                }
            }
        }
        return isValid(cnt);
    }    
    public int isValidSudoku(final List<String> board) {
        int size = board.size();
        for(int i=0; i<size; i++){
            if(!isValidRow(board, i))
                return 0;
            if(!isValidCol(board, i))
                return 0;
        }        
        
        // cell check
        for(int i=0; i<9; i+=3){
            for(int j=0; j<9; j+=3){
                if(!isValidCell(board, i, j))
                    return 0;
            }
        }
        return 1;
        
    }
}
