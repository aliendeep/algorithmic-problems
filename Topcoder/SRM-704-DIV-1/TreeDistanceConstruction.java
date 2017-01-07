/*

Problem Statement
    
In a tree, the distance d(u,v) between vertices u and v is the smallest number of edges you need to traverse in order to get from u to v.  The eccentricity of a vertex u is the maximum of all d(u,v). In other words, the eccentricity of u is the distance between u and the vertex that is the farthest away from u.  You are given a int[] d with n elements. Construct any tree with the following properties:
The tree has n vertices, numbered 0 through n-1.
For each i, the eccentricity of vertex i is exactly d[i].
If there is no such tree, return an empty int[]. If there are multiple such trees, you may output any of them. If your tree contains the edges a[0] - b[0], a[1] - b[1], ..., a[n-2] - b[n-2], return the following int[]: {a[0], b[0], a[1], b[1], ..., a[n-2], b[n-2]}. Note that the return value should contain exactly 2*(n-1) elements.
Definition
    
Class:
TreeDistanceConstruction
Method:
construct
Parameters:
int[]
Returns:
int[]
Method signature:
int[] construct(int[] d)
(be sure your method is public)
Limits
    
Time limit (s):
2.000
Memory limit (MB):
256
Stack limit (MB):
256
Constraints
-
d will contain between 2 and 50 elements, inclusive.
-
Each element in d will be between 1 and |d|-1, inclusive.
Examples
0)

    
{3,2,2,3}
Returns: {1, 2, 1, 0, 2, 3 }
The return value shown in this example describes the chain 0 - 1 - 2 - 3. This 
is one of multiple correct trees for this test case.
1)

    
{1,2,2,2}
Returns: {0, 1, 0, 2, 0, 3 }
In this case the only correct tree is a star with vertex 0 in the middle.
2)

    
{1,1,1,1}
Returns: { }

3)

    
{1,1,1}
Returns: { }

4)

    
{1,1}
Returns: {0, 1 }

This problem statement is the exclusive and proprietary property of TopCoder, Inc. 
Any unauthorized use or reproduction of this information without the prior written 
consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
*/
import java.util.*;

public class TreeDistanceConstruction
{     
  public int maxDistance(int n, int node, List<List<Integer>> graph){
    boolean[] visited = new boolean[n];
    Queue<Integer> Q = new LinkedList<>();
    Q.add(node);
    visited[node] = true;
    int max = 0;
    int d[] = new int[n];
    d[node] = 0;
    while(!Q.isEmpty()){
      int t = Q.remove();
      max = Math.max(max, d[t]);
      List<Integer> adj = graph.get(t);
      if(adj == null)
        continue;
      for(int ne : adj){
        if(visited[ne])
          continue;
        visited[ne] = true;
        d[ne] = d[t] + 1;
        Q.add(ne);
      }
    }
    return max;
  }

  public int[] construct(int[] d)
  {
      int n = d.length;
    int[] result = new int[2*n-2];
      TreeMap<Integer, List<Integer>> map = new TreeMap<>();
      for(int i=0; i<n; ++i){
        int v = d[i];
        if(!map.containsKey(v))
          map.put(v, new ArrayList<>());
        map.get(v).add(i);        
    }
    int index = 0;
    List<Integer> prev = new ArrayList<>();   
    int prevDistance = -1;
        
    // Sorted
    Map.Entry<Integer, List<Integer>> rootEntry = map.firstEntry();
    // one root
    // There can be at most two root
    if(rootEntry.getValue().size() == 1){
      int k = rootEntry.getKey();
      prevDistance = k;
        prev = rootEntry.getValue();
      map.remove(k);
    }
    else if(rootEntry.getValue().size() == 2){
      int k = rootEntry.getKey();
        prev = rootEntry.getValue();
      map.remove(k);
      prevDistance = k;
      result[index++] = prev.get(0);
      result[index++] = prev.get(1);
    }
    else{
      // impossible
      return new int[0];
    }
    int cnt = map.size();
    while(cnt-- > 0){
      if(map.size() == 0)
        return new int[0];

      // Get the latest key
      Map.Entry<Integer, List<Integer>> entry = map.firstEntry();
      int k = entry.getKey();
      List<Integer> cur = entry.getValue();     
      map.remove(k);
      int j = 0;
      for(int i=0; i<cur.size(); ++i){
        result[index++] = prev.get(j);
        result[index++] = cur.get(i);
        j = (j+1) % prev.size();
      }
      // update 
      prev = cur;
      prevDistance = k;
    }
    
    // Validate result
    // Longest path DP
    List<List<Integer>> graph = new ArrayList<>();
    for(int i=0; i<n; ++i){
      graph.add(new ArrayList<>());
    }
    // Construct graph
    for(int i=0; i<result.length; i+=2){
      int a = result[i];
      int b = result[i+1];      
      graph.get(a).add(b);
      graph.get(b).add(a);
    }

    for(int i=0; i<n; ++i){
      if(maxDistance(n, i, graph) != d[i])
        return new int[0];
    }   
    return result;
  }
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!
