import java.util.*;

class Main {
  static void bfs(Map<Integer, Set<Integer>> graph, int n, int src) {
    if(!graph.containsKey(src)) {
      System.out.println(0);     
      return;
    } 
    Queue<Integer> Q = new LinkedList<>();
    Set<Integer> visited = new HashSet<>();
    Q.add(src);
    visited.add(src);

    int lev = 0;
    int max = 0;
    int day = 0;

    while(!Q.isEmpty()) {
      int size = Q.size();
      if(max < size || (max == size && day == 0)) {
        max = size;
        day = lev;
      }
      for(int i=0; i<size; ++i) {
        int f = Q.remove();
        if(!graph.containsKey(f))
          continue;

        Set<Integer> adj = graph.get(f);
        for(int neighbor : adj) {
          if(visited.contains(neighbor))
              continue;

          visited.add(neighbor);
          Q.add(neighbor);
        }
      }
      lev++;
    }
    System.out.println(max + " " + day);
  }

  public static void main(String[] args){
    Scanner in = new Scanner(System.in);
    int e = in.nextInt();
    Map<Integer, Set<Integer>> graph = new HashMap<>();
    for(int f=0; f<e; ++f) {
      int n = in.nextInt();
      int x = f;
      for(int i=0; i<n; ++i) {
        int y = in.nextInt();
        if(!graph.containsKey(x))
          graph.put(x, new HashSet<>());
        graph.get(x).add(y);
      }
    }

    int t = in.nextInt();
    while(t-- > 0) {
      int src = in.nextInt();
      //System.out.println("Src " + src);
      bfs(graph, e, src);
    }
  }  
}
