public class Solution {
    private static final int WHITE = 0;
    private static final int GRAY = 1;
    private static final int BLACK = 2;
    
    public boolean dfs(int node, Map<Integer, Set<Integer>> graph, int[] color){
        if(graph.get(node) == null)
            return false;
        color[node] = GRAY;
        
        Set<Integer> edges = graph.get(node);
        for(int edge : edges){
            if(color[edge] == GRAY)
                return true;
            if(color[edge] == WHITE)
                if(dfs(edge, graph, color))
                    return true;
        }
        color[node] = BLACK;
        return false;
    }    
    
    // Alternative: Use Hashmap to build the graph
    public boolean canFinish(int numCourses, int[][] prerequisites){
        if(numCourses <= 1)
            return true;
        Map<Integer, Set<Integer>> graph = new HashMap<Integer, Set<Integer>>();
        int[] color = new int[numCourses];        
        
        // Add all nodes
        for(int i=0; i<numCourses; i++){
            color[i] = WHITE;
        }
        
        for(int i=0; i<prerequisites.length; i++){
            int x = prerequisites[i][0];
            int y = prerequisites[i][1];
            Set<Integer> edges = graph.get(x);
            if(edges == null){
                edges = new HashSet<>();
            }
            edges.add(y);
            graph.put(x, edges);
        }
        
        for(int i=0; i<numCourses; i++){
            if(color[i] == WHITE && dfs(i, graph, color) == true)
                return false;
        }
        
        return true;
    }
}