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

// Solution 1
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
            graph.get(x).add(y);
            graph.get(y).add(x);
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

// Solution 2
/* How to find longest path in a tree
- BFS 1 to find a node y that is further away from a arbitrary node p
- BFS 2 from node y to get another longest distance node x
- Result is the middle of the path from x to y
*/
class Solution2 {
    // Find the longest path in the tree
    // The root should be the middle element/s of the the path
    // https://apps.topcoder.com/forums/?module=Thread&threadID=794604&start=0&mc=3#1758019
    public void bfs(int n, int start, int[] parent, int[] dist, List<List<Integer>> graph){
        boolean[] visited = new boolean[n];
        // distance
        Queue<Integer> Q = new LinkedList<>();
        Q.add(start);

        parent[start] = start;
        visited[start] = true;
        dist[start] = 0;
        
        while(!Q.isEmpty()){
            int front = Q.remove();
            List<Integer> adj = graph.get(front);
            for(int neigh : adj){
                if(visited[neigh])
                    continue;
                visited[neigh] = true;                    
                dist[neigh] = dist[front] + 1;
                parent[neigh] = front;
                Q.add(neigh);
            }                
        }
    }
    
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        // Create graph
        List<List<Integer>> graph = new ArrayList<>();
        for(int i=0; i<n; i++)
            graph.add(new ArrayList<>());
            
        for(int[] edge : edges){
            // undirected
            int x = edge[0];
            int y = edge[1];
            graph.get(x).add(y);
            graph.get(y).add(x);
        }
        
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);

        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        
        // Do a bfs from any arbitrary node
        bfs(n, 0, parent, dist, graph);
        
        // Find the node that is furthest away from the source
        int u = 0;
        for(int i=0; i<n; ++i){
            if(dist[u] < dist[i]){
               u = i;
            }
        }
        // reset parent and distance array        
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);

        // Call another bfs from the node u
        bfs(n, u, parent, dist, graph);
        
        int v = 0;
        for(int i=0; i<n; ++i){
            if(dist[v] < dist[i]){
               v = i;
            }
        }
        
        // Construct the longest path
        List<Integer> longest = new ArrayList<>();
        int node = v;
        while(parent[node] != node){
            longest.add(node);
            node = parent[node];
        }
        // Add the source
        longest.add(u);
        
        System.out.println(longest);
        List<Integer> r = new ArrayList<>();
        int size = longest.size();
        System.out.print(size);
        // if length of the longest path is odd, then result is only one node
        if(size % 2 == 1){
            r.add(longest.get(size/2));
        }
        else{
            r.add(longest.get((size/2)-1));
            r.add(longest.get(size/2));
        }
        return r;
    }
}
