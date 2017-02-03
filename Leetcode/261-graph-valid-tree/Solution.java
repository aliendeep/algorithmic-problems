/*
Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge 
is a pair of nodes), write a function to check whether these edges make up a valid tree.

For example:

Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.

Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.

Hint:

Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], what should your return? 
Is this case a valid tree?
According to the definition of tree on Wikipedia: “a tree is an undirected graph 
in which any two vertices are connected by exactly one path. In other words, 
any connected graph without simple cycles is a tree.”
Note: you can assume that no duplicate edges will appear in edges. Since all 
edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
*/

public class Solution {
    // Undirected graph. Need parent info
    public final static int White = 0;
    public final static int Gray = 1;
    public final static int Black = 2;
    
    public boolean cycleExists(int node, Map<Integer, List<Integer>> graph, int[] color, int parent){
        color[node] = Gray;
        for(int adj : graph.get(node)){
            if(adj == parent)
                continue;
            if(color[adj] == Gray)
                return true;
            if(color[adj] == Black)
                continue;
            // not visited before
            if(cycleExists(adj, graph, color, node))
                return true;
        }
        color[node] = Black;
        return false;
    }
        
    public boolean validTree(int n, int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        
        // Add n nodes
        for(int i=0; i<n; i++)
            graph.put(i, new ArrayList<>());
            
        for(int[] edge : edges){
            // undirected
            graph.get(edge[0]).add(edge[1]);            
            graph.get(edge[1]).add(edge[0]);            
        }
        
        int[] color = new int[n];
        Arrays.fill(color, White);
        // If there is cycle or number of components > 1 
        // then it's not a valid tree
        int cnt = 0;
        for(int i=0; i<n; i++){
            if(color[i] == White){
                // Disconnected
                if(cnt > 0)
                    return false;
                if(cycleExists(i, graph, color, -1))  
                    return false;
                cnt++;
            }
        }
        return true;
    }
}

// Alternative: if the graph is a valid tree, then number of edges should be = n-1
public class Solution {
    // Undirected graph. Need parent info
    public final static int White = 0;
    public final static int Gray = 1;
    public final static int Black = 2;
    
    public boolean cycleExists(int node, Map<Integer, List<Integer>> graph, int[] color, int parent){
        color[node] = Gray;
        for(int adj : graph.get(node)){
            if(adj == parent)
                continue;
            if(color[adj] == Gray)
                return true;
            if(color[adj] == Black)
                continue;
            // not visited before
            if(cycleExists(adj, graph, color, node))
                return true;
        }
        color[node] = Black;
        return false;
    }
        
    public boolean validTree(int n, int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        if(edges.length != n-1) return false;

        // Construct graph : Add n nodes
        for(int i=0; i<n; i++)
            graph.put(i, new ArrayList<>());
        for(int[] edge : edges){
            // undirected
            graph.get(edge[0]).add(edge[1]);            
            graph.get(edge[1]).add(edge[0]);            
        }
                
        int[] color = new int[n];
        // If there is cycle
        for(int i=0; i<n; i++){
            if(color[i] == White){
                if(cycleExists(i, graph, color, -1))  
                    return false;
            }
        }
        return true;
    }
}

// Alternative: Union find
public class Solution {
    int[] parent;
    int[] rank;

    public int findSet(int x){
        if(parent[x] != x)
            parent[x] = findSet(parent[x]);
        return parent[x];
    }
    
    public void link(int x, int y){
        if(rank[x] > rank[y]){
            parent[y] = x;
        }        
        else{
            parent[x] = y;
            if(rank[x] == rank[y])
                rank[y] = rank[y] + 1; 
        }
    }

    public void union(int x, int y){
        link(findSet(x), findSet(y));
    }
    
    public boolean validTree(int n, int[][] edges) {
        if(edges.length != n-1)     return false;

        parent = new int[n];
        for(int i=0; i<n; ++i)
            parent[i] = i;
        rank = new int[n];
        
        for(int[] edge : edges){
            int x = findSet(edge[0]);
            int y = findSet(edge[1]);
            
            // cycle
            if(x == y)      return false;
            union(x, y);
        }
        return true;
    }
}
