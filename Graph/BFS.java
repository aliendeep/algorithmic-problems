/*

Consider an undirected graph consisting of n nodes where each node is labeled from  1 to n 
and the edge between any two nodes is always of length 1. We define node s  to be the starting position for a BFS.

Given  queries in the form of a graph and some starting node, s, perform each query by calculating the shortest distance from 
starting node s to all the other nodes in the graph. Then print a single line of n-1 space-separated integers listing node s's 
shortest distance to each of the n-1 other nodes (ordered sequentially by node number); 
if s is disconnected from a node, print -1  as the distance to that node.

The first line contains two space-separated integers describing the respective values of  n(the number of nodes) and  m(the number of edges) 
in the graph.
Each line i of the m subsequent lines contains two space-separated integers, u and v, describing an edge connecting node u to node v.
The last line contains a single integer, s, denoting the index of the starting node.

Sample Input

2
4 2
1 2
1 3
1
3 1
2 3
2
Sample Output

1 1 -1
-1 1
*/
// Classic BFS
import java.util.*;

// Time Complexity: O(V+E)
public class BFS {    
    public static void bfs(int start, Map<Integer, List<Integer>> graph, boolean[] visited,  int[] parent, int[] distance){
      Queue<Integer> Q = new LinkedList<>();
      
      Q.add(start);
      distance[start] = 0;
      visited[start] = true;

      while(!Q.isEmpty()){
        int u = Q.remove();
        List<Integer> adj = graph.get(u);
        for(int v : adj){
          if(visited[v] == false){
            visited[v] = true;
            distance[v] = distance[u] + 1;
            parent[v] = u;
            Q.add(v);
          }
        } 
      }

      for(int i=1; i<distance.length; i++){
        if(i == start)
          continue;
        if(distance[i] != Integer.MAX_VALUE){
          System.out.print(distance[i] + " ");
        }
        else
          System.out.print(-1 + " ");
      }
      System.out.println();
    }

    public static void main(String[] args) {
      Scanner scan = new Scanner(System.in);
      String line = scan.nextLine().trim();
      int q = Integer.parseInt(line);

      for(int t=0; t<q; t++){       
        Map<Integer, List<Integer>> graph = new HashMap<>();
        line = scan.nextLine();
        String[] token = line.split(" ");
        // no of nodes
        int n = Integer.parseInt(token[0]);
        // no of edges
        int m = Integer.parseInt(token[1]);
        // initialization
        // for all nodes 1 to n        
        for(int i=0; i<n; i++){
          graph.put(i+1, new ArrayList<>());
        }

        boolean[] visited = new boolean[n+1];
        Arrays.fill(visited, false);

        // to print the path
        int[] parent = new int[n+1];
        Arrays.fill(parent, -1);

        // distance
        int[] distance = new int[n+1];
        Arrays.fill(distance, Integer.MAX_VALUE);

        // Next m lines
        for(int j=0; j<m; j++){
          line = scan.nextLine();
          token = line.split(" ");
          int u = Integer.parseInt(token[0]);
          int v = Integer.parseInt(token[1]);
          // undirected
          List<Integer> adjU = graph.get(u);
          adjU.add(v);
          graph.put(u, adjU);

          List<Integer> adjV = graph.get(v);
          adjV.add(u);
          graph.put(v, adjV);
        }

        line = scan.nextLine().trim();
        int start = Integer.parseInt(line);       
        bfs(start, graph, visited, parent, distance);
      }
    }
}