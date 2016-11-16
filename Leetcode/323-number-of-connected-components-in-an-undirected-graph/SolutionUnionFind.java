// https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/
public class Solution {
    int[] parent;
    public int findSet(int node){
        while(node != parent[node]){
            node = parent[node];
        }
        return node;
    }

    public void union(int x, int y){
        parent[y] = x;
    }
    
    // Alternative: union find
    public int countComponents(int n, int[][] edges) {
        parent = new int[n];
        for(int i=0; i<n; ++i){
            parent[i] = i;
        }
        
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