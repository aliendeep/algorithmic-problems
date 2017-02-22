/*
Given a graph consisting n nodes (labelled 1 to )n where a specific given node s 
represents the starting position s and 
an edge between two nodes is of a given length, which may or may not be equal to 
other lengths in the graph.

It is required to calculate the shortest distance from the start position (Node s) 
to all of the other nodes in the graph.

Note: If a node is unreachable, the distance is assumed as -1.
If there are edges between the same pair of nodes with different weights, 
they are to be considered as is, like multiple edges.

Sample Input
1
4 4
1 2 24
1 4 20
3 1 3
4 3 12
1
Sample Output
24 3 15
*/

// Dijkstra's Algorithm
// Single Source shortest path on a weighted graph
import java.util.*;
// for faster input
import java.io.*;

public class Dijkstra{
  Map<Integer, List<Edge>> graph;
  int[] d;

  public Dijkstra(){
    graph  = new HashMap<>();
  }

  public void init(int n){
    // for all nodes 1 to n        
    for(int i=0; i<n; i++){
      graph.put(i+1, new ArrayList<>());
    }
    // distance
    d = new int[n+1];
    Arrays.fill(d, Integer.MAX_VALUE);
  }

  public void dijkstraAlgorithm(int start){
    PriorityQueue<Node> minHeap = new PriorityQueue<Node>(new Comparator<Node>(){
      @Override
      public int compare(Node u, Node v){
        return Integer.compare(u.distance, v.distance);
      }
    });
    // init
    Node s = new Node(start, 0);
    d[start] = 0;
    minHeap.add(s);
    Set<Integer> visited = new HashSet<>();

    while(!minHeap.isEmpty()){
      Node node = minHeap.poll();
      int u = node.n;
      // 
      if(visited.contains(u))
          continue;
      visited.add(u);
      // for all adjancent neighbors
      List<Edge> adj = graph.get(u);
      // for all adjacent nodes, relax
      for(int i=0; i<adj.size(); i++){
        Edge e = adj.get(i);
        // If there are edges between the same pair of nodes with different weights, they are to be considered as is, like multiple edges.
        if(visited.contains(e.v))
            continue;

        if(d[e.v] > d[u] + e.weight){
            d[e.v] = d[u] + e.weight;
            minHeap.add(new Node(e.v, d[e.v]));
        }
      }      
    }
    // print
    for(int i=1; i<d.length; i++){
      if(i == start)
        continue;
      if(d[i] != Integer.MAX_VALUE){
        System.out.print(d[i] + " ");
      }
      else
        System.out.print(-1 + " ");
    }
    System.out.println();  
  }
  
  class Node{
    int n;
    int distance;
    public Node(int i, int d){
      n = i;
      distance = d;
    }
  }

  class Edge{
      int v;
      int weight;
      public Edge(int i, int w){
        v = i;
        weight = w;
      }
  }

  public void addEdge(int u, int v, int w){
      List<Edge> adj = graph.get(u);
      adj.add(new Edge(v, w));
      graph.put(u, adj);
   }

   public static void main(String[] args) throws IOException {
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));    
      String line = reader.readLine().trim();
      int q = Integer.parseInt(line);

      for(int t=0; t<q; t++){       
        line = reader.readLine();
        String[] token = line.split(" ");
        // no of nodes
        int n = Integer.parseInt(token[0]);
        // no of edges
        int m = Integer.parseInt(token[1]);        
        // initialization
        Dijkstra dijk = new Dijkstra();
        dijk.init(n);

        // Next m lines
        for(int j=0; j<m; j++){
          line = reader.readLine();
          token = line.split(" ");
          int u = Integer.parseInt(token[0]);
          int v = Integer.parseInt(token[1]);
          int w = Integer.parseInt(token[2]);
          // undirected
          dijk.addEdge(u, v, w);
          dijk.addEdge(v, u, w);
        }
        line = reader.readLine().trim();
        int start = Integer.parseInt(line);       
        dijk.dijkstraAlgorithm(start);
      }
    }
}
