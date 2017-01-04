import java.util.*;

// Longest path in a DAG
// Alternative Solution: Negate the weight of each edges. Find the shortest weight path
public class LongestPathDAG{
  class Edge{
    Node v;
    int weight;

    public Edge(Node u, int w){
      v = u;
      weight = w;
    }
  }

  class Node{
    int vertex;
    int maxDistance;
    List<Edge> edges;
    Node parent;

    public Node(int v, int d){
      vertex = v;
      maxDistance = d;
      edges = new ArrayList<>();
      parent = null;
    }

    public void addEdge(Edge v){
      edges.add(v);
    }
  }

  public void dfs(int node, List<Node> nodes, boolean[] visited, Deque<Node> ordered){
    List<Edge> adjacents = nodes.get(node).edges;
    if(adjacents != null){
      for(Edge e : adjacents){
        if(visited[e.v.vertex])
          continue;
        visited[e.v.vertex] = true;
        dfs(e.v.vertex, nodes, visited, ordered);
      }      
    }
    ordered.push(nodes.get(node));
  }

  // Node id: 0 to n-1
  public int longestPathInDAG(int n, int[][] edges){
    // init 
    List<Node> nodes = new ArrayList<>();
    // Max distance 1 because we need to find the longest path
    for(int i=0; i<n; ++i){
      nodes.add(new Node(i, 1));
    }

    // directed
    for(int[] edge : edges){
      Node u = nodes.get(edge[0]);
      Node v = nodes.get(edge[1]);
      int w = edge[2];
      Edge e = new Edge(v, w);
      nodes.get(u.vertex).edges.add(e);
    }

    Deque<Node> ordered = new LinkedList<>();
    boolean[] visited = new boolean[n];
    for(int i=0; i<n; ++i){
      if(!visited[i]){
        visited[i] = true;
        dfs(i, nodes, visited, ordered);
      }
    }

    print(ordered);
    return findLongestPath(ordered, nodes);
  }

  public void printPath(Node node){
    if(node != null){
      printPath(node.parent);
      System.out.print(node.vertex + " ");
    }
  }

  public int findLongestPath(Deque<Node> ordered, List<Node> nodes){
    int maxDistance = 0;    

    while(!ordered.isEmpty()){
      Node u = ordered.pop();
      maxDistance = Math.max(maxDistance, u.maxDistance);

      List<Edge> edges = nodes.get(u.vertex).edges;
      if(edges == null)
        continue;
      for(Edge e : edges){
        Node v = e.v;
        if(v.maxDistance < u.maxDistance + e.weight){
          v.maxDistance = u.maxDistance + e.weight;
          v.parent = u;
        }
      }
    }

    for(int i=0; i<nodes.size(); ++i){
      Node u = nodes.get(i);
      System.out.println("Node "+i + " distance " + u.maxDistance + " Path");
      printPath(u);
      System.out.println();
    }
    return maxDistance;
  }

  public void print(Deque<Node> a){
    for(Node t : a){
      System.out.print(t.vertex + " ");
    }
    System.out.println();
  }

  public static void main(String[] args){
    int[][] edges = {{0, 1, 5}, {0, 2, 3}, {1, 3, 6}, {1, 2, 2}, {2, 4, 4}, {2, 5, 2}, {2, 3, 7}, {3, 5, 1}, {3, 4, -1}, {4, 5, -2}};
    int n = 6;
    LongestPathDAG ob = new LongestPathDAG();
    System.out.println(ob.longestPathInDAG(n, edges));
  }  
}
