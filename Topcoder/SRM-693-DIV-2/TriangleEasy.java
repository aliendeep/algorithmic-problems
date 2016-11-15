/*

Problem Statement
    
You are given an undirected graph with n vertices numbered 0 through n-1. For each valid i, there is an undirected edge connecting two different vertices x[i] and y[i]. No two edges connect the same pair of vertices. A triangle is a set of three distinct vertices such that each pair of those vertices is connected by an edge. Formally, three distinct vertices u,v,w are a triangle if the graph contains the edges (u,v), (v,w), and (w,u). You are given the description of the graph: the int n and the int[]s x and y. You are allowed to add edges to this graph. You may add as many edges as you want, and each of them may connect any two vertices. Your goal is to produce a graph that contains at least one triangle. Compute and return the smallest number of edges you need to add. 
Definition
    
Class:
TriangleEasy
Method:
find
Parameters:
int, int[], int[]
Returns:
int
Method signature:
int find(int n, int[] x, int[] y)
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
n will be between 3 and 50, inclusive.
-
x will have between 0 and n*(n-1)/2 elements, inclusive.
-
y will have the same number of elements as x.
-
Each element of x and y will be between 0 and n-1, inclusive.
-
For each valid i, x[i] != y[i].
-
No two edges will connect the same pair of vertices.
Examples
0)

    
3
{}
{}
Returns: 3
The graph has three vertices but no edges. You need to add edges (0,1), (1,2), and (2,0) to make it a triangle.
1)

    
4
{0,2,1,2}
{3,0,2,3}
Returns: 0
Note that the edges are undirected. The graph already has a triangle: (2,0),(0,3),(2,3), so we don't have to add anything.
2)

    
6
{0,0,2}
{3,1,4}
Returns: 1

3)

    
4
{0,2}
{1,3}
Returns: 2

4)

    
20
{16,4,15,6,1,0,10,12,7,15,2,4,8,1,10,15,13,10,1,16,3,19,8,7,13,1,15,15,15,5,16,7,5,6,4,18,3,8,6,2,16,8,19,14,17,16,4,6,9,17,4,10,8,12,2,3,18,9,13,17,4,7,10,0,13,11,15,17,11,15,11,19,19,4,10,14,16,6,3,17,1,4,14,9,7,18,10,11,5,0,5,9,9,7,16,12,4,10,17,3}
{17,18,6,16,18,6,11,2,15,10,1,15,17,8,5,9,7,0,0,4,16,1,9,0,9,5,17,14,1,12,14,11,9,18,0,12,11,3,19,14,7,6,3,19,0,1,19,5,11,19,2,13,12,0,6,2,14,16,14,18,5,5,19,3,6,14,12,5,17,3,1,12,7,11,8,8,10,11,13,2,13,13,0,18,2,7,2,12,14,9,3,19,2,8,12,13,8,18,13,18}
Returns: 1

This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
*/
import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;


public class TriangleEasy
{
  public int find(int n, int[] x, int[] y){
    int[][] adj = new int[n][n];
    for(int [] t : adj){
      Arrays.fill(t, 0);
    }
    int length = x.length;
    for(int i=0; i<length; ++i){
      for(int j=0; j<length; ++j){
        int p = x[i];
        int q = y[i];       
        adj[p][q] = 1;
        adj[q][p] = 1;
      }
    }   
    int cnt = length > 0 ? 2 : 3;
    for(int i=0; i<n; ++i){
      for(int j=i+1; j<n; ++j){
        for(int k=j+1; k<n; ++k){
          // triangle
          if(adj[i][j] == 1 && adj[j][k] == 1 && adj[k][i] == 1)
            return 0; 
          if((adj[i][j] == 1 && adj[j][k] == 1) ||
            (adj[i][j] == 1 && adj[i][k] == 1) || 
            (adj[i][k] == 1 && adj[j][k] == 1) 
            )   
            cnt = 1;      
        }
      }
    }
    return cnt;
  }
  
<%:testing-code%>
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!