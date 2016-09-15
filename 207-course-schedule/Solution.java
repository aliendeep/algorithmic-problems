public class Solution {
    private static final int WHITE = 0;
    private static final int GRAY = 1;
    private static final int BLACK = 2;
    
    public boolean dfs(int node, List<List<Integer>> adjacency, int[] color){
        color[node] = GRAY;
        
        List<Integer> neighbors = adjacency.get(node);
        for(int neigh : neighbors){
            if(color[neigh] == GRAY)
                return true;
            if(color[neigh] == WHITE)
                if(dfs(neigh, adjacency, color))
                    return true;
        }
        color[node] = BLACK;
        return false;
    }    
    
    public boolean canFinish(int numCourses, int[][] prerequisites){
        if(numCourses <= 1)
            return true;
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
            List<Integer> t = adjacency.get(x);
            t.add(y);
            adjacency.set(x, t);
        }
        
        for(int i=0; i<numCourses; i++){
            if(color[i] == WHITE && dfs(i, adjacency, color) == true)
                return false;
        }
        
        return true;
    }
}