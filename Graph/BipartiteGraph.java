// Check if a graph is bipartite
// BFS d
// It is possible to color a cycle graph with even cycle using two colors.
import java.util.*;

public class BipartiteGraph{
  class Node{
    int vertex;
    int color;
    List<Node> adjacent;

    public Node(int v, int c){
      vertex = v;
      color = c;
      adjacent = new ArrayList<>();
    }

    public void addNeighbor(Node v){
      adjacent.add(v);
    }
  }

  public boolean isBipartiteGraph(int n, int[][] edges){
    // init 
    List<Node> nodes = new ArrayList<>();
    for(int i=0; i<n; ++i){
      // inti color
      nodes.add(new Node(i, -1));
    }

    // directed
    for(int[] edge : edges){
      Node u = nodes.get(edge[0]);
      Node v = nodes.get(edge[1]);
      // undirected
      nodes.get(u.vertex).addNeighbor(v);
      nodes.get(v.vertex).addNeighbor(u);
    }

    for(int i=0; i<n; ++i){
      Node u = nodes.get(i);
      if(u.color == -1){
        if(!BFS(u, nodes))
          return false;
      }      
    }
    return true;
  }  

  public boolean BFS(Node source, List<Node> nodes){
    Queue<Node> Q = new LinkedList<>();
    source.color = 0;    
    while(!Q.isEmpty()){
      Node u = Q.remove();
      List<Node> neighbors = u.adjacent;
      if(neighbors != null){
        for(Node v : neighbors){
          // unvisited node
          if(v.color == -1){
            // Assign the opposite color
            v.color = 1 - u.color;
            Q.add(v);
          }
          // If the neighbor has same color, then return false
          else if(v.color == u.color)
            return false;
        }
      }
    }
    return true;
  }

  public static void main(String[] args){
    int[][] edges = {{0, 1}, {0, 3}, {1, 2}, {2, 3}};
    int n = 4;
    BipartiteGraph ob = new BipartiteGraph();
    System.out.println(ob.isBipartiteGraph(n, edges));
  }  
}
