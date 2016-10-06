/*
Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to find the number of connected components in an undirected graph.

Example 1:
     0          3
     |          |
     1 --- 2    4
Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], return 2.

Example 2:
     0           4
     |           |
     1 --- 2 --- 3
Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]], return 1.

Note:
You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
*/

public class Solution {
    // DFS
    public void dfs(int node, Map<Integer, List<Integer>> graph, boolean[] visited){
        visited[node] = true;
        for(int adj : graph.get(node)){
            if(!visited[adj])
                dfs(adj, graph, visited);
        }
    }
    
    public int countComponents(int n, int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        
        // Add n nodes
        for(int i=0; i<n; i++)
            graph.put(i, new ArrayList<>());
            
        for(int[] edge : edges){
            // undirected
            graph.get(edge[0]).add(edge[1]);            
            graph.get(edge[1]).add(edge[0]);            
        }
        
        boolean[] visited = new boolean[n];
        int cnt = 0;
        for(int i=0; i<n; i++){
            if(!visited[i]){
                dfs(i, graph, visited);
                cnt++;
            }
        }
        return cnt;
        
    }
}