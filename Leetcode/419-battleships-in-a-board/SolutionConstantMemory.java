public class Solution {
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