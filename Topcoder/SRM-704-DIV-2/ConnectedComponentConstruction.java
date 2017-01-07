/*

Problem Statement
    
Any undirected graph can be decomposed into connected components. Two vertices 
u and v belong to the same connected component if we can travel from u to v by 
following a sequence of zero or more consecutive edges. The size of a connected 
component is the number of vertices it contains.  You are given a int[] s. 
Construct a simple undirected graph with the following properties:
The number of vertices is n, where n is the number of elements in s.
The vertices are numbered 0 through n-1.
For each i, the size of the connected component that contains vertex i is exactly s[i].
If there is no such graph, return an empty String[]. Otherwise, return a String[] 
ret with n elements, each containing n characters. For each i and j, ret[i][j] 
should be 'Y' if there is an edge between i and j in your graph. Otherwise, 
ret[i][j] should be 'N'. If there are multiple solutions, you may return any of them.
Definition
    
Class:
ConnectedComponentConstruction
Method:
construct
Parameters:
int[]
Returns:
String[]
Method signature:
String[] construct(int[] s)
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
s will contain between 1 and 50 elements, inclusive.
-
Each element in s will be between 1 and |s|, inclusive.
Examples
0)

    
{2,1,1,2,1}
Returns: {"NNNYN", "NNNNN", "NNNNN", "YNNNN", "NNNNN" }
The answer is a graph that contains only one edge. This edge connects the 
vertices 0 and 3. This graph has four connected components: {0, 3}, {1}, {2}, and {4}.
1)

    
{1,1,1,1}
Returns: {"NNNN", "NNNN", "NNNN", "NNNN" }
Here the only correct answer is a graph with four vertices and no edges.
2)

    
{3,3,3}
Returns: {"NYY", "YNY", "YYN" }
This time one correct answer could be the complete graph on three vertices.
3)

    
{4,4,4,4,4}
Returns: { }
There is no solution.
4)

    
{1}
Returns: {"N" }

This problem statement is the exclusive and proprietary property of TopCoder, Inc. 
Any unauthorized use or reproduction of this information without the prior 
written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. 
All rights reserved.
*/
import java.util.*;

public class ConnectedComponentConstruction
{
  public String[] construct(int[] s)
  { 
    int n = s.length;
    // number of components, List of indices
    Map<Integer, List<Integer>> comp = new HashMap<>();
    for(int i=0; i<n; ++i){
      if(s[i] == 1) 
        continue;
      int v = s[i];
      if(!comp.containsKey(v))
        comp.put(v, new ArrayList<>());
      comp.get(v).add(i); 
    }
        
    boolean isPossible = true;
    for(Map.Entry<Integer, List<Integer>> entry : comp.entrySet()){
      int k = entry.getKey();
      int v = entry.getValue().size();
      if(v % k != 0){
        isPossible = false;
        break;
      }
    }
    if(!isPossible){
      // empty String
      return new String[0];
    }
    
    // feasible
    // 0 - no edge
    // 1 - edge
    int[][] matrix = new int[n][n];
    for(Map.Entry<Integer, List<Integer>> entry : comp.entrySet()){
      int ncomp = entry.getKey();
      List<Integer> t = entry.getValue();
      int i = 0;
      while(i < t.size()){
        for(int j = i; j<i+ncomp-1; ++j){
            int a = t.get(j);
            int b = t.get(j+1);
            matrix[a][b] = 1;
            matrix[b][a] = 1;
        }
        i += ncomp;
      }
    }   
    String[] r = new String[n];
    // for all rows
    for(int i=0; i<n; ++i){
      StringBuilder t = new StringBuilder();
      for(int j=0; j<n; ++j){
        t.append(matrix[i][j] == 0 ? 'N' : 'Y');
      }   
      r[i] = t.toString();
    }   
    return r;
  } 
<%:testing-code%>
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!