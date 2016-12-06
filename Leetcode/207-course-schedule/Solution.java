/*
There are a total of n courses you have to take, labeled from 0 to n - 1.

Some courses may have prerequisites, for example to take course 0 you have to 
first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible 
for you to finish all courses?

For example:

2, [[1,0]]
There are a total of 2 courses to take. To take course 1 you should have finished 
course 0. So it is possible.

2, [[1,0],[0,1]]
There are a total of 2 courses to take. To take course 1 you should have finished 
course 0, and to take course 0 you should also have finished course 1. So it is 
impossible.

Note:
The input prerequisites is a graph represented by a list of edges, not adjacency 
matrices. Read more about how a graph is represented.


Hints:
- This problem is equivalent to finding if a cycle exists in a directed graph. 
If a cycle exists, no topological ordering exists and therefore it will be 
impossible to take all courses.
- Topological Sort via DFS - A great video tutorial (21 minutes) on Coursera 
explaining the basic concepts of Topological Sort.
- Topological sort could also be done via BFS.
*/

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

// Alternative: BFS
class Solution2 {
    public boolean canFinish(int numCourses, int[][] prerequisites){
        if(numCourses <= 1)
            return true;
        List<List<Integer>> graph = new ArrayList<>();
        int[] color = new int[numCourses];        
        // Add all nodes
        int[] indegree = new int[numCourses];
        for(int i=0; i<numCourses; i++){
            graph.add(new ArrayList<>());
        }
        
        for(int i=0; i<prerequisites.length; i++){
            int x = prerequisites[i][0];
            int y = prerequisites[i][1];
            graph.get(y).add(x);
            indegree[x]++;
        }
        
        boolean[] visited = new boolean[numCourses];
        Queue<Integer> Q = new LinkedList<>();
        for(int i=0; i<numCourses; ++i){
            if(indegree[i] == 0){
                Q.add(i);
            }
        }
        int cnt = 0;
        while(!Q.isEmpty()){
            int t = Q.remove();
            indegree[t]--;
            cnt++;
            List<Integer> adj = graph.get(t);
            for(int neighbor : adj){
                indegree[neighbor]--;
                if(indegree[neighbor] == 0){
                    Q.add(neighbor);
                }
            }
        }
        return cnt == numCourses;
    }
}