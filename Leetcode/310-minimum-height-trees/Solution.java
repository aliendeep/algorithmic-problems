/*
4
[[1,0],[1,2],[1,3]]
6
[[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]
2
[[0,1]]
4
[[1,0],[1,2],[1,3]]
*/
public class Solution {
    // BFS topological sort
    // remove all leaves and decrease indegree
    // Left alone nodes are the result
    // At most two elements can be the roots
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> r = new ArrayList<>();
        int[] inDegree = new int[n];
        // If no edges
        if(edges.length == 0){
            for(int i=0; i<n; i++)
                r.add(i);
            return r;
        }
        
        // Create graph
        List<List<Integer>> graph = new ArrayList<>();
        for(int i=0; i<n; i++)
            graph.add(new ArrayList<>());
            
        for(int[] edge : edges){
            // undirected
            int x = edge[0];
            int y = edge[1];
            List<Integer> tx = graph.get(x);
            tx.add(y);
            graph.set(x, tx);    
            
            List<Integer> ty = graph.get(y);
            ty.add(x);
            graph.set(y, ty);    
            inDegree[x]++;
            inDegree[y]++;
        }
        
        Queue<Integer> Q = new LinkedList<>();
        // Add all leaves
        for(int i=0; i<n; i++){
            if(inDegree[i] == 1){
                Q.add(i);
            }
        }
        
        while(n > 2){
            int levCnt = Q.size();
            // Level count needed to process all nodes that were entered at the same time
            for(int i=0; i<levCnt; i++){
                int t = Q.remove();
                inDegree[t]--;
                n--;
                List<Integer> adj = graph.get(t);
                for(int neighbor : adj){
                    inDegree[neighbor]--;
                    if(inDegree[neighbor] == 1)
                        Q.add(neighbor);
                }
            }
        }
        
        while(!Q.isEmpty())
            r.add(Q.remove());
        return r;
    }
}