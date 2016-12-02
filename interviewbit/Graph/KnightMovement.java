/*
Knight movement on a chess board

Given any source point and destination point on a chess board, we need to find whether Knight can move to the destination or not.

Knight's movements

The above figure details the movements for a knight ( 8 possibilities ). Note that a knight cannot go out of the board.

If yes, then what would be the minimum number of steps for the knight to move to the said point.
If knight can not move from the source point to the destination point, then return -1

Input:

N, M, x1, y1, x2, y2
where N and M are size of chess board
x1, y1  coordinates of source point
x2, y2  coordinates of destination point
Output:

return Minimum moves or -1
Example

Input : 8 8 1 1 8 8
Output :  6
*/

public class Solution {
    class Pair{
        int r, c;
        public Pair(int x, int y){
            this.r = x;
            this.c = y;
        }
        
    }    
    // BFS
    // 1 indexing
  public int knight(int N, int M, int x1, int y1, int x2, int y2) {
      if(x1 == x2 && y1 == y2)
          return 0;
      int[][] move = {{2, 1}, {-2, 1}, {2, -1}, {-2, -1}, {1, 2}, {1, -2}, {-1, 2}, {-1, -2}};
      boolean[][] visited = new boolean[N+1][M+1];
      Queue<Pair> Q = new LinkedList<>();
      Q.add(new Pair(x1, y1));
      visited[x1][y1] = true;
        
        int levCnt = Q.size();
        int minStep = 0;
      while(!Q.isEmpty()){
          Pair t = Q.remove();
          levCnt--;

          for(int i=0; i<8; ++i){
              int r1 = t.r + move[i][0];
              int c1 = t.c + move[i][1];
              if(r1 <= 0 || r1 > N || c1 <= 0 || c1 > M || visited[r1][c1])
                  continue;
              
              // reached destination
              if(r1  == x2 && c1 == y2)
                  return minStep + 1;
                  
              visited[r1][c1] = true;
              Q.add(new Pair(r1, c1));    
          }
          
          if(levCnt == 0){
              levCnt = Q.size();
              minStep++;
          }
      }
      return -1;
  }
}
