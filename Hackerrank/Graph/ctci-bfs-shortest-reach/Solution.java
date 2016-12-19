import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static class Graph {
        List<List<Integer>> adj; 
        int n;
        
        public Graph(int size) {
            n = size;
            adj = new ArrayList<>();
            for(int i=0; i<n; ++i)
                adj.add(new ArrayList<>());
        }
        
        // undirected
        public void addEdge(int first, int second) {
            adj.get(first).add(second);
            adj.get(second).add(first);
        }
        
        public int[] shortestReach(int startId) { 
            int start = startId;
            
            int[] dist = new int[n];
            boolean[] visited = new boolean[n];
            
            Arrays.fill(dist, Integer.MAX_VALUE);
            Arrays.fill(visited, false);
            
            Queue<Integer> Q = new LinkedList<>();
            Q.add(start);
            dist[start] = 0;
            visited[start] = true;
            
            while(!Q.isEmpty()){
                int t = Q.remove();
                List<Integer> neighbors = adj.get(t);
                for(int neigh : neighbors){
                    if(visited[neigh])
                        continue;
                    dist[neigh] = dist[t] + 1;
                    visited[neigh] = true;
                    Q.add(neigh);
                }
            }            
            for(int i=0; i<n; ++i){
                if(dist[i] == Integer.MAX_VALUE)
                    dist[i] = -1;
                else
                    dist[i] *= 6;
            }            
            return dist;
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
      
        int queries = scanner.nextInt();
        
        for (int t = 0; t < queries; t++) {
            
            // Create a graph of size n where each edge weight is 6:
            Graph graph = new Graph(scanner.nextInt());
            int m = scanner.nextInt();
            
            // read and set edges
            for (int i = 0; i < m; i++) {
                int u = scanner.nextInt() - 1;
                int v = scanner.nextInt() - 1;
                
                // add each edge to the graph
                graph.addEdge(u, v);
            }
            
            // Find shortest reach from node s
            int startId = scanner.nextInt() - 1;
            int[] distances = graph.shortestReach(startId);
 
            for (int i = 0; i < distances.length; i++) {
                if (i != startId) {
                    System.out.print(distances[i]);
                    System.out.print(" ");
                }
            }
            System.out.println();            
        }
        
        scanner.close();
    }
}
