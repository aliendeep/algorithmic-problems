/*

Problem Statement
    
We have a simple undirected graph G with n nodes, labeled 0 through n-1. Each edge of this graph has two weights. You are given the description of G encoded into String[]s weight1 and weight2. If nodes i and j are connected by an edge, both weight1[i][j] and weight2[i][j] are nonzero digits ('1'-'9'), and these represent the two weights of this edge. Otherwise, both weight1[i][j] and weight2[i][j] are periods ('.').  Your task is to find the cheapest path from node 0 to node 1. The cost of a path is calculated as (W1 * W2), where W1 is the sum of all first weights and W2 is the sum of all second weights of the edges on your path. Return the smallest possible cost of a path from node 0 to node 1. If there is no such path, return -1 instead.
Definition
    
Class:
DoubleWeights
Method:
minimalCost
Parameters:
String[], String[]
Returns:
int
Method signature:
int minimalCost(String[] weight1, String[] weight2)
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
n will be between 2 and 20, inclusive.
-
weight1 and weight2 will contain exactly n elements, each.
-
Each element in weight1 and weight2 will contain exactly n characters.
-
Each character in weight1 and weight2 will be '.' or '1' - '9'.
-
For each i, weight1[i][i] = weight2[i][i] = '.'.
-
For each i and j, weight1[i][j] = weight1[j][i].
-
For each i and j, weight2[i][j] = weight2[j][i].
-
weight1[i][j] = '.' if and only if weight2[i][j] = '.'.
Examples
0)

    
{"..14",
 "..94",
 "19..",
 "44.."}
{"..94",
 "..14",
 "91..",
 "44.."}
Returns: 64
The best path is 0 -> 3 -> 1. The cost of this path is (4+4) * (4+4) = 64.  Note that the other possible path (0 -> 2 -> 1) is more expensive. Its cost is (1+9) * (9+1) = 100.
1)

    
{"..14",
 "..14",
 "11..",
 "44.."}
{"..94",
 "..94",
 "99..",
 "44.."}
Returns: 36
This time the best path is 0->2->1, the cost is (1+1) * (9+9) = 36.
2)

    
{"..",
 ".."}
{"..",
 ".."}
Returns: -1
There is no path between node 0 and node 1, so you should return -1.
3)

    
{".....9",
 "..9...",
 ".9.9..",
 "..9.9.",
 "...9.9",
 "9...9."}
{".....9",
 "..9...",
 ".9.9..",
 "..9.9.",
 "...9.9",
 "9...9."}
Returns: 2025

4)

    
{".4...1",
 "4.1...",
 ".1.1..",
 "..1.1.",
 "...1.1",
 "1...1."}
{".4...1",
 "4.1...",
 ".1.1..",
 "..1.1.",
 "...1.1",
 "1...1."}
Returns: 16

This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
*/
import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;

public class DoubleWeights
{
  List<List<Edge>> graph;
  int[] d1;
  int[] d2;
  int[] d;

  class Node{
    int n;
    int distance;
    public Node(int i, int d){
      n = i;
      distance = d;
    }
  }

  class Edge{
    int v;
    int w1;   
    int w2;   
    public Edge(int i, int p, int q){
      v = i;
      w1 = p;
      w2 = q;
    }
  }
  
  public void addEdge(int u, int v, int w1, int w2){
      graph.get(u).add(new Edge(v, w1, w2));
    }
    
  public void dijkstra(int start){
    PriorityQueue<Node> minHeap = new PriorityQueue<Node>(new Comparator<Node>(){
      @Override
      public int compare(Node u, Node v){
        return Integer.compare(u.distance, v.distance);
      }
    });   
      
      Set<Integer> visited = new HashSet<>();
      Node s = new Node(start, 0);
      d[start] = 0;
      d1[start] = 0;
      d2[start] = 0;

      minHeap.add(s);
      while(!minHeap.isEmpty()){
          Node node = minHeap.poll();
          int u = node.n;   
          if(visited.contains(u))
              continue;
          visited.add(u);
          // for all adjancent neighbors
          List<Edge> adj = graph.get(u);
          List<Node> candidate = new ArrayList<>();
          // for all adjacent nodes, relax
          for(int i=0; i<adj.size(); i++){
            Edge e = adj.get(i);
            // If there are edges between the same pair of nodes with different weights, they are to be considered as is, 
            // like multiple edges.
            if(visited.contains(e.v))
                continue;

            d1[e.v] = Math.min(d1[e.v], d1[u] + e.w1);
            d2[e.v] = Math.min(d2[e.v], d2[u] + e.w2);
            d[e.v] = Math.min(d[e.v], (d1[u] + e.w1)*(d2[u] + e.w2));

            minHeap.add(new Node(e.v, d[e.v]));
          }
        }      
  }
  
  public int minimalCost(String[] weight1, String[] weight2){
    int n = weight1.length;
    graph = new ArrayList<>();
    d = new int[n];
    Arrays.fill(d, Integer.MAX_VALUE);

    d1 = new int[n];
    Arrays.fill(d1, Integer.MAX_VALUE);
    
    d2 = new int[n];
    Arrays.fill(d2, Integer.MAX_VALUE);

    for(int u=0; u<n; ++u){
      graph.add(new ArrayList<>());
    }
    
    for(int u=0; u<n; ++u){
      for(int v=0; v<n; ++v){
        if(weight1[u].charAt(v) == '.')
          continue;
        // undirected
        int w1 = weight1[u].charAt(v) - '0';
        int w2 = weight2[u].charAt(v) - '0';
        addEdge(u, v, w1, w2);
        addEdge(v, u, w1, w2);
      }   
    } 
    
    dijkstra(0);
    return d[1] == Integer.MAX_VALUE ? -1 : d[1];  
  }

  public static void main(String[] args){
    DoubleWeights ob = new DoubleWeights();
    String[] a = {".....9","..9...",".9.9..","..9.9.","...9.9","9...9."};
    String[] b  = {".....9","..9...",".9.9..","..9.9.","...9.9","9...9."};
    // 2025
    System.out.println(ob.minimalCost(a, b));
    String[] p0 = new String[]{"..14","..94","19..","44.."};
    String[] p1 = new String[]{"..94","..14","91..","44.."};
    // 64
    System.out.println(ob.minimalCost(p0, p1));

  }  
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!