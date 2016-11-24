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
    public int[] findOrder(int n, int[][] prerequisites) {
        if(n == 0)     return new int[0];
        // indegree of all nodes
        int[] indegree = new int[n];
        // Construct the graph
        List<Set<Integer>> graph = new ArrayList<>();
        for(int i=0; i<n; ++i)
            graph.add(new HashSet<>());
            
        for(int i=0; i<prerequisites.length; i++){
            int x = prerequisites[i][0];
            int y = prerequisites[i][1];
            // To take course 0 you have to first take course 1, which is expressed as a pair: [0, 1]            
            // Same edge can occur multiple times in the input
            if(graph.get(y).add(x)){
                indegree[x]++;
            }
        }
        Queue<Integer> Q = new LinkedList<>();
        for(int i=0; i<n; ++i){
            if(indegree[i] == 0){
                Q.add(i);
            }
        }
        int[] r = new int[n];
        int i = 0;
        while(!Q.isEmpty()){
            int t = Q.remove();
            r[i++] = t;
            Set<Integer> edges = graph.get(t);
            for(int neigh : edges){
                indegree[neigh]--;
                if(indegree[neigh] == 0){
                    Q.add(neigh);
                }
            }
        }
        // Cycle
        if(i < n)
            return new int[0];
        return r;
    }
}