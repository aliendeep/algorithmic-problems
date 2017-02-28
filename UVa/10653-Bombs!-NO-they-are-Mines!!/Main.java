import java.util.*;

class Main {
  static int r, c;
  static class Pair {
    int r, c;
    public Pair(int x, int y) {
      r = x;
      c = y;
    }
  }

  static int bfs(int[][] grid, int sr, int sc, int dr, int dc) {
    boolean[][] visited = new boolean[r][c];
    Queue<Pair> Q = new LinkedList<>();
    Q.add(new Pair(sr, sc));
    visited[sr][sc] = true;

    int[][] move = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    int lev = 0;
    while(!Q.isEmpty()) {
      int size = Q.size();
      for(int i=0; i<size; ++i) {
        Pair f = Q.remove();
        if(f.r == dr && f.c == dc)
          return lev;
        for(int k=0; k<4; ++k) {
          int r1 = f.r + move[k][0];
          int c1 = f.c + move[k][1];
          if(r1 < 0 || r1 >= r || c1 < 0 || c1 >= c || visited[r1][c1] || grid[r1][c1] == 1)
              continue;

          visited[r1][c1] = true;
          Q.add(new Pair(r1, c1));
        }
      }
      lev++;
    }
    return -1; 
  }

  public static void main(String[] args){
    Scanner in = new Scanner(System.in);
    while(true) {
      r = in.nextInt();
      c = in.nextInt();
      if(r == 0 && c == 0)
        break;
      
      // 1 - obstacle
      int[][] grid = new int[r][c];      
      int bombs = in.nextInt();
      int x, y, t;
      for(int i=0; i<bombs; ++i) {
        x = in.nextInt();
        t = in.nextInt();
        while(t-- > 0) {
          y = in.nextInt();
          grid[x][y] = 1;
        }
      }
      int sr = in.nextInt();
      int sc = in.nextInt();
      int dr = in.nextInt();
      int dc = in.nextInt();

      System.out.println(bfs(grid, sr, sc, dr, dc));
    }
  }  
}
