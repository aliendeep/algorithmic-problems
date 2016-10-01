public class Solution {
    private static final int WHITE = 0;
    private static final int GRAY = 1;
    private static final int BLACK = 2;
    
    // Returns true if there is a cycle in the graph
    public boolean dfs(int node, Map<Integer, Set<Integer>> graph, int[] color, Stack<Integer> stk){
        color[node] = GRAY;
        
        Set<Integer> edges = graph.get(node);
        if(edges != null){
            for(int edge : edges){
                if(color[edge] == GRAY)
                    return true;
                if(color[edge] == WHITE)
                    if(dfs(edge, graph, color, stk))
                        return true;
            }
        }
        color[node] = BLACK;
        stk.push(node);
        return false;
    }    
    
    // Alternative: Use Hashmap to build the graph
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if(numCourses == 0)
            return new int[0];
        
        // Initialize visited array
        int[] color = new int[numCourses];        
        Arrays.fill(color, WHITE);
        
        Map<Integer, Set<Integer>> graph = new HashMap<Integer, Set<Integer>>();
        for(int i=0; i<prerequisites.length; i++){
            int x = prerequisites[i][0];
            int y = prerequisites[i][1];
            Set<Integer> edges = graph.get(y);
            if(edges == null){
                edges = new HashSet<>();
            }
            edges.add(x);
            graph.put(y, edges);
        }
        
        Stack<Integer> stk = new Stack<Integer>();
        for(int i=0; i<numCourses; i++){
            if(color[i] == WHITE && dfs(i, graph, color, stk) == true)
                return new int[0];
        }
        
        if(stk.size() != numCourses)
            return new int[0];
        
        int[] r = new int[numCourses];
        int i = 0;
        while(stk.size() > 0){
            r[i] = stk.pop();
            i++;
        }
        return r;
    }
}