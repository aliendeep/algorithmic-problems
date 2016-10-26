/*
There are a total of n courses you have to take, labeled from 0 to n - 1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.

There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.

For example:

2, [[1,0]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1]

4, [[1,0],[2,0],[3,1],[3,2]]
There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0. So one correct course order is [0,1,2,3]. Another correct ordering is[0,2,1,3].

Note:
The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.

Hints:
- This problem is equivalent to finding the topological order in a directed graph. If a cycle exists, no topological ordering exists and therefore it will be impossible to take all courses.
- Topological Sort via DFS - A great video tutorial (21 minutes) on Coursera explaining the basic concepts of Topological Sort.
- Topological sort could also be done via BFS.
*/

public class Solution {
    // Topological sort via BFS
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if(numCourses == 0)     return new int[0];
        
        // indegree of all nodes
        int[] indegree = new int[numCourses];
        // Construct the graph
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for(int i=0; i<prerequisites.length; i++){
            int x = prerequisites[i][0];
            int y = prerequisites[i][1];
            Set<Integer> edges = graph.get(y);
            if(edges == null){
                edges = new HashSet<>();
            }
            // To take course 0 you have to first take course 1, which is expressed as a pair: [0, 1]            
            // Same edge can occur multiple times in the input
            if(edges.add(x)){
                indegree[x]++;
            }
            graph.put(y, edges);
        }
        
        Queue<Integer> Q = new LinkedList();
        // Add all node with indegree 0 to the queue
        for(int i=0; i<numCourses; ++i){
            if(indegree[i] == 0) 
                Q.add(i);
        }
        
        List<Integer> result = new ArrayList<>();
        while(!Q.isEmpty()){
            int front = Q.remove();
            result.add(front);
            
            Set<Integer> adj = graph.get(front);
            // reduce indegree of all adjacent nodes by 1
            if(adj != null){
                for(int neighbor : adj){
                    indegree[neighbor]--;
                    if(indegree[neighbor] == 0){
                        Q.add(neighbor);
                    }
                }
            }
        }
        
        // Cycle
        if(result.size() != numCourses)
            return new int[0];
        
        int[] r = new int[result.size()];
        int i = 0;
        for(int t : result){
            r[i++] = t;
        }
        return r;
    }
}