// Alternative: union find (weighted rank)
public class Solution {
    int[] parent;
    int[] rank;
    
    public int findSet(int node){
        while(node != parent[node]){
            node = parent[node];
        }
        return node;
    }
    public void link(int u, int v){
        if(rank[u] > rank[v]){
            parent[v] = u;
        }
        else{
            parent[u] = v;
            if(rank[u] == rank[v])
                rank[u] += 1;
        }
    }
    public void union(int x, int y){
        link(findSet(x), findSet(y));
    }
    public int countComponents(int n, int[][] edges) {
        parent = new int[n];
        for(int i=0; i<n; ++i){
            parent[i] = i;
        }
        rank = new int[n];
        Arrays.fill(rank, 0);
        
        int ncomp = n;
        for(int[] edge : edges){
            int u = findSet(edge[0]);
            int v = findSet(edge[1]);
            if(u != v){
                union(u, v);
                ncomp--;
            }
        }
        return ncomp;
    }
}