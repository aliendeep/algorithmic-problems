public class SudukuSolver {
    public final static int N = 10;
    
    public boolean bktk(int r, int c, ArrayList<ArrayList<Character>> board, 
                    boolean[][] row, boolean[][] col, boolean[][][] box){
                        
        if(c == 9){
            r++;
            c = 0;
        }

        if(r == 9){
            return true;
        }
        
        if(board.get(r).get(c) != '.'){
            return bktk(r, c+1, board, row, col, box);
        }
        else{
            for(int num=1; num<=9; num++){
                if(row[r][num] || col[c][num] || box[r/3][c/3][num])
                    continue;
                
                board.get(r).set(c, (char)(num + '0'));
                row[r][num] = true;
                col[c][num] = true;
                box[r/3][c/3][num] = true;
    
                if(bktk(r, c+1, board, row, col, box))
                    return true;
    
                board.get(r).set(c, '.');
                row[r][num] = false;
                col[c][num] = false;
                box[r/3][c/3][num] = false;
            }
            return false;
        }
    }

  public void solveSudoku(ArrayList<ArrayList<Character>> board) {
      boolean[][] row = new boolean[N][10];
      boolean[][] col = new boolean[N][10];
      boolean[][][] box = new boolean[N/3][N/3][10];
      
        // initialization
        for(int r=0; r<9; r++){
            for(int c=0; c<9; c++){
                if(board.get(r).get(c) != '.'){
                    int val = board.get(r).get(c) - '0';
                    row[r][val] = true; 
                    col[c][val] = true; 
                    box[r/3][c/3][val] = true;
                }
            }
        }
        bktk(0, 0, board, row, col, box);
  }
}
