// https://www.hackerearth.com/practice/algorithms/graphs/strongly-connected-components/practice-problems/algorithm/a-walk-to-remember-qualifier2/

import java.util.*;
import java.io.*;

class TestClass {    
    static List<List<Integer>> graph;
    static boolean[] visited;
    static int n;
    // dfs using stack
    public static void dfs(int start, List<Integer> r){
        Deque<Integer> stk = new LinkedList<>();
        stk.push(start);

        while(!stk.isEmpty()){
            int t = stk.pop();
            if(!visited[t]){
              visited[t] = true;
              r.add(t);
            }
            List<Integer> adj = graph.get(t);
            for(int neighbor : adj){
              if(visited[neighbor])
                  continue;
              stk.push(neighbor);
            }
        }
    }

    public static void constructGraph(int n, List<List<Integer>> graph, int[][] edges){
      // Construct the graph
      for(int i=1; i<=n; i++){
        graph.add(new ArrayList<>());
      }

      for(int[] edge : edges){
        graph.get(edge[0]).add(edge[1]);
      }
    }    

    public static int[] stronglyConnectedComponents(int[][] edges){
        graph = new ArrayList<>();
        constructGraph(n, graph, edges);

        List<Integer> stk = new ArrayList<>();
        visited = new boolean[n+1];
        for(int i=1; i<=n; ++i){
            if(!visited[i]){
                visited[i] = true;
                dfs(i, stk);
            }
        }
        // Reverse the edges
        for(int i=0; i<edges.length; ++i){
            int t = edges[i][0];
            edges[i][0] = edges[i][1];
            edges[i][1] = t;
        }
        graph.clear();
        constructGraph(n, graph, edges);

        // Call DFS decreasing order
        Arrays.fill(visited, false);
        List<List<Integer>> components = new ArrayList<>();
        for(int i=n-1; i>=0; --i){
            int node = stk.get(i);
            if(!visited[node]){
                visited[node] = true;
                List<Integer> r = new ArrayList<>();
                dfs(node, r);
                components.add(r);
            }
        }
        // All init to 0
        int[] result = new int[n+1];
        for(List<Integer> comp : components){
            if(comp.size() > 1){
                for(int c : comp){
                    result[c] = 1;
                }
            }
        }
        return result;
    }

    public static void main(String args[] ) throws Exception {
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        int m = input.nextInt();
        int[][] edges = new int[m][2];

        for(int i=0; i<m; ++i){
            edges[i][0] = input.nextInt();
            edges[i][1] = input.nextInt();
        }

        int[] result = stronglyConnectedComponents(edges);
        for(int i=1; i<=n; ++i)
            System.out.print(result[i] + " ");
        System.out.println();
    }
}
