import java.util.*;

class Main{
  public static void main(String[] args){
    Scanner in = new Scanner(System.in);
    int t = 1;
    int INF = 10000;
    while(in.hasNext()) {
      int n = 1;
      int u = in.nextInt();
      int v = in.nextInt();
      if(u == 0 && v == 0)
        break;

      int[][] dist = new int[205][205];
      for(int[] f : dist)
        Arrays.fill(f, INF);

      for(int i=1; i<201; ++i) {
        dist[i][i] = 0;
      }

      while(!(u == 0 && v == 0)) {
        dist[u][v] = 1;
        n = Math.max(n, u);
        n = Math.max(n, v);
        u = in.nextInt();
        v = in.nextInt();
      }

      for(int k=1; k<=n; ++k) {
        for(int i=1; i<=n; ++i) {
          for(int j=1; j<=n; ++j) {
            if(dist[i][k] != INF && dist[k][j] != INF) {
              dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
            }
          }
        }
      }
      int total = 0;
      int ways = 0;
      for(int i=1; i<=n; ++i) {
        for(int j=1; j<=n; ++j) {
          if(dist[i][j] == INF || i == j)
            continue; 
          total += dist[i][j];         
          ways++;
        }
      }
      double avg = 1.0*total / ways;
      System.out.print("Case " + t +": average length between pages = ");
      System.out.printf("%.3f", avg);
      System.out.println(" clicks");
      t++;
    }
  }  
}
