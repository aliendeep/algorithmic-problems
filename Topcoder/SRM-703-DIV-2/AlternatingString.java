/*

Problem Statement
    
You are given four ints: n, k, x, and y.  The ints n and k describe a simple undirected graph. The graph has n nodes, numbered 1 through n. Two distinct vertices i and j are connected by an edge if and only if gcd(i, j) > k. Here, gcd(i, j) denotes the greatest common divisor of i and j.  The ints x and y are the numbers of two (not necessarily distinct) vertices in our graph. Return "Possible" if it is possible to travel from x to y by following the edges of our graph. Otherwise, return "Impossible".  In other words, return "Possible" if our graph contains a path that connects the nodes x and y, and "Impossible" if there is no such path.
Definition
    
Class:
GCDGraph
Method:
possible
Parameters:
int, int, int, int
Returns:
String
Method signature:
String possible(int n, int k, int x, int y)
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
n will be between 2 and 1,000,000, inclusive.
-
k will be between 0 and n, inclusive.
-
x and y will be between 1 and n, inclusive.
Examples
0)

    
12
2
8
9
Returns: "Possible"
We have a graph with n = 12 nodes. As k = 2, vertices i and j are connected by an edge if and only if gcd(i, j) is strictly greater than 2. In this graph it is possible to travel from node 8 to node 9. One possible path: 8 -> 4 -> 12 -> 9.
1)

    
12
2
11
12
Returns: "Impossible"
This is the same graph as in Example 0, but now we are interested in another pair of nodes. It is not possible to travel from node 11 to node 12. In particular, in our graph node 11 is an isolated node because for any other node x we have gcd(11, x) = 1.
2)

    
12
2
11
11
Returns: "Possible"
A node is always reachable from itself.
3)

    
10
2
8
9
Returns: "Impossible"

4)

    
1000000
1000
12345
54321
Returns: "Possible"

5)

    
1000000
2000
12345
54321
Returns: "Impossible"

6)

    
2
0
1
2
Returns: "Possible"

This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
*/

import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;


public class AlternatingString
{
  public int maxLength(String s)
  {
    int n = s.length();
    if(n <= 1)    return n;
    int maxLen = 1;
    for(int i=0; i<n; ++i){
      char prev = s.charAt(i);
      int len = 1;
      for(int j=i+1; j<n; ++j){
        char c = s.charAt(j);
        if(prev == c)
          break;
        len++;
        prev = c;
      }     
      maxLen = Math.max(maxLen, len);
    }
    return maxLen;
  }
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!