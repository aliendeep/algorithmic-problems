public class FloydWarshallAlgorithm{
  public static final int INF = 1000000;

  public int[][] allPairsShortestPath(int[][] distance){
    int n = distance.length;
    for(int k=0; k<n; ++k){
      for(int i=0; i<n; ++i){
        for(int j=0; j<n; ++j){
          distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
        }
      }
    }
    // Check if negative cycle exists
    // after the algorithm, distance(i,i) will be negative if there exists a negative-length path from i back to i.
    for(int i=0; i<n; ++i){
      if(distance[i][i] < 0){
        System.out.println("Negative Cycle Exists!");
      }
    }
    return distance;
  }

  public static void main(String args[]){
    int[][] distance = {
            {0,   3,   8,   INF, -4},
            {INF, 0,   INF,   1,  7},
            {INF, 4,   0,   INF, INF},
            {2,   INF, -5,  0,   INF},
            {INF, INF, INF, 6,   0}
        };

    FloydWarshallAlgorithm fa = new FloydWarshallAlgorithm();
    int[][] d = fa.allPairsShortestPath(distance);
    int n = distance.length;
    for(int i=0; i<n; ++i){
      for(int j=0; j<n; ++j){
        System.out.print(d[i][j] + " ");        
      }
      System.out.println();
    }
  }
}