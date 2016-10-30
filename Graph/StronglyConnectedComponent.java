// Cormen
import java.util.*;

public class StronglyConnectedComponent{
  public void dfs(int node, boolean[] visited, Map<Integer, List<Integer>> graph, List<Integer> r){
    visited[node] = true;
    List<Integer> adjacent = graph.get(node);
    for(int neighbor : adjacent){
      if(visited[neighbor])
          continue;

      dfs(neighbor, visited, graph, r);
    }
    r.add(node);    
  }

  public void constructGraph(int n, int[][] edges, Map<Integer, List<Integer>> graph){
    // Construct the graph
    for(int i=0; i<n; i++){
      graph.put(i, new ArrayList<>());
    }

    for(int[] edge : edges){
      int x = edge[0];
      int y = edge[1];
      List<Integer> adjacent = graph.get(x);
      adjacent.add(y);
      graph.put(x, adjacent);
    }
  }

  public List<List<Integer>> computeStronglyConnectedComponent(int n, int[][] edges){
    Map<Integer, List<Integer>> graph = new HashMap<>();
    constructGraph(n, edges, graph);

    List<Integer> stk = new ArrayList<>();
    boolean[] visited = new boolean[n];
    for(int i=0; i<n; ++i){
      if(visited[i])
          continue;
      dfs(i, visited, graph, stk);
    }

    // Reverse the edges
    for(int[] edge : edges){
      int t = edge[0];
      edge[0] = edge[1];
      edge[1] = t;
    }

    // Contruct the graph with reverse edges
    Map<Integer, List<Integer>> reversedGraph = new HashMap<>();
    constructGraph(n, edges, reversedGraph);

    // Call the dfs 
    Arrays.fill(visited, false);
    List<List<Integer>> result = new ArrayList<>();
    for(int i=stk.size()-1; i>=0; i--){
      int source = stk.get(i);
      if(visited[source] == false){      
        List<Integer> component = new ArrayList<>();
        dfs(source, visited, reversedGraph, component);
        result.add(component);
      }
    }
    return result;
  }

  public void print(List<Integer> t){
    for(int i : t)
      System.out.print(i + " ");
    System.out.println();    
  }

  public void printResult(List<List<Integer>> r){
    System.out.println("Strongly connected components in the given graph");

    for(List<Integer> t : r){
      System.out.print("[ ");
      for(int i : t)
        System.out.print(i + " ");
      System.out.println("]");
    }
  }

  public static void main(String[] args){
    int[][] edges = {{1, 0}, {0, 2}, {2, 1}, {0, 3}, {3, 4}};
    StronglyConnectedComponent sc = new StronglyConnectedComponent();
    List<List<Integer>> r =  sc.computeStronglyConnectedComponent(5, edges);
    sc.printResult(r);
  } 
}