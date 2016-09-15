public class Solution {
    private static final int WHITE = 0;
    private static final int GRAY = 1;
    private static final int BLACK = 2;
    
    public boolean dfs(int node, List<List<Integer>> adjacency, int[] color, Stack<Integer> stk){
        color[node] = GRAY;
        
        List<Integer> neighbors = adjacency.get(node);
        for(int neigh : neighbors){
            if(color[neigh] == GRAY)
                return true;
            if(color[neigh] == WHITE)
                if(dfs(neigh, adjacency, color, stk))
                    return true;
        }
        color[node] = BLACK;
        stk.push(node);
        return false;
    }    
    
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if(numCourses == 0)
            return new int[0];
        List<List<Integer>> adjacency = new ArrayList<>();
        int[] color = new int[numCourses];        
        
        // Add all nodes
        for(int i=0; i<numCourses; i++){
            color[i] = WHITE;
            List<Integer> t = new ArrayList<>();
            adjacency.add(t);
        }
        
        for(int i=0; i<prerequisites.length; i++){
            int x = prerequisites[i][0];
            int y = prerequisites[i][1];
            List<Integer> t = adjacency.get(y);
            t.add(x);
            adjacency.set(y, t);
        }
        
        Stack<Integer> stk = new Stack<Integer>();
        for(int i=0; i<numCourses; i++){
            if(color[i] == WHITE && dfs(i, adjacency, color, stk) == true)
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