/*

Problem Statement
    
Weiwei is studying math. Unfortunately, math is very hard for him. Today, the teacher asked Weiwei to determine if a relation is a bijection or not.
Formally, a relation is a set of ordered pairs of elements. The teacher gave Weiwei one such relation. You are also given a description of this relation: int[]s domain and range. For each valid i, the relation contains the ordered pair (domain[i], range[i]).
Let X be the set of elements that appear at least once in domain. Similarly, let Y be the set of elements that appear at least once in range. We say that an element x of X is paired to an element y of Y if the relation contains the ordered pair (x, y).
We will say that our relation is a bijection if each element of X is paired to exactly one element of Y, and each element of Y is paired to exactly one element of X.
If Weiwei's relation is a bijection, return "Bijection" (quotes for clarity). Otherwise, return "Not". Note that the return value is case-sensitive.
Definition
    
Class:
RelationClassifier
Method:
isBijection
Parameters:
int[], int[]
Returns:
String
Method signature:
String isBijection(int[] domain, int[] range)
(be sure your method is public)
Limits
    
Time limit (s):
2.000
Memory limit (MB):
256
Constraints
-
domain will contain between 1 and 10 elements, inclusive.
-
range will contain the same number of elements as domain.
-
Each element of domain and range will be between 1 and 100, inclusive.
-
No two pairs (domain[i], range[i]) will be identical.
Examples
0)

    
{1, 1}
{2, 3}
Returns: "Not"
Since 1 in X is paired with both 2 and 3 in Y, the given relation is not a bijection.
1)

    
{4, 5}
{2, 2}
Returns: "Not"
Since both 4 and 5 in X are paired with 2 in Y, the given relation is not a bijection.
2)

    
{1}
{2}
Returns: "Bijection"
A single ordered pair is always a bijection.
3)

    
{1, 2, 3, 4, 5}
{1, 2, 3, 4, 5}
Returns: "Bijection"

4)

    
{14, 12, 10, 13, 20, 18, 9, 17, 14, 9}
{18, 6, 8, 15, 2, 14, 10, 13, 13, 15}
Returns: "Not"

This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
*/
import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;

public class RelationClassifier
{
  public String isBijection(int[] domain, int[] range)
  {
    int n = domain.length;
    Map<Integer, Integer> map = new HashMap<>();
    for(int i=0; i<n; ++i){
      // put returns previously mapped value or null
      // Any multiplier
      if(map.put(domain[i], i) != map.put(range[i]*10, i))
        return "Not";
    }
    return "Bijection";
  }
  
<%:testing-code%>
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!