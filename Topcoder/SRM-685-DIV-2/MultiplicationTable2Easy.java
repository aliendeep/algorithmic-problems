/*

Problem Statement
    
Fox Ciel is creating a new binary operation.  The operation will be denoted $ and it will be defined on the finite set S = {0, 1, 2, ..., n-1}. I.e., for each ordered pair (i, j) of elements of S the operation (i $ j) will return some element of S.  For example, we can have S = {0, 1}, and we can define that (0 $ 0) = 0, (0 $ 1) = 1, (1 $ 0) = 0, and (1 $ 1) = 0.  Note that Ciel's operation is not necessarily symmetric. In other words, it is possible that for some i and j the operations (i $ j) and (j $ i) return two different values.  A nice concise description of the operation $ is its "multiplication table": a square table where in row i and column j we have the value (i $ j). You are given this "multiplication table" encoded as a int[] table with n^2 elements. For each valid i and j the operation (i $ j) returns the value table[i*n+j].  A subset T of S is called good if it has the following property: for any two elements i and j in T, (i $ j) is also in T.  You are given a int[] t. The elements of t form a subset of the set S. Return "Good" (quotes for clarity) if this subset is good. Otherwise, return "Not Good". Note that the return value is case-sensitive.
Definition
    
Class:
MultiplicationTable2Easy
Method:
isGoodSet
Parameters:
int[], int[]
Returns:
String
Method signature:
String isGoodSet(int[] table, int[] t)
(be sure your method is public)
Limits
    
Time limit (s):
2.000
Memory limit (MB):
256
Stack limit (MB):
256
Notes
-
You are not given the value of n explicitly. Instead, you can determine it as the square root of the number of elements in table.
Constraints
-
n will be between 2 and 50, inclusive.
-
table will contain exactly n*n elements.
-
Each element in table will be between 0 and n-1, inclusive.
-
t will contain between 1 and n elements, inclusive.
-
Each element in t will be between 0 and n-1, inclusive.
-
Elements in t will be distinct.
Examples
0)

    
{1, 1, 2, 3,
 1, 0, 2, 3,
 3, 3, 0, 3,
 2, 2, 2, 0}
{1,0}
Returns: "Good"
We can verify:
0 $ 0 = 1
0 $ 1 = 1
1 $ 0 = 1
1 $ 1 = 0
1)

    
{1, 1, 2, 3,
 1, 0, 2, 3,
 3, 3, 0, 3,
 2, 2, 2, 0}
{2, 3}
Returns: "Not Good"
Note that: 2 $ 2 = 0 but 0 is not in T, so it is not good.
2)

    
{1, 1, 2, 3,
 1, 0, 2, 3,
 3, 3, 0, 3,
 2, 2, 2, 0}
{0,1,2,3}
Returns: "Good"

3)

    
{1, 1, 2, 3,
 1, 0, 2, 3,
 3, 3, 0, 3,
 2, 2, 2, 0}
{1}
Returns: "Not Good"

4)

    
{2,2,2,2,2,2,
 2,2,2,2,2,2,
 2,2,2,2,2,2,
 2,2,2,2,2,2,
 2,2,2,2,2,2,
 2,2,2,2,2,2}
{2,4,5}
Returns: "Good"

5)

    
{2,2,2,2,2,2,
 2,2,2,2,2,2,
 2,2,2,2,2,2,
 2,2,2,2,2,2,
 2,2,2,2,2,2,
 2,2,2,2,2,2}
{0,1,3,4,5}
Returns: "Not Good"

This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
*/
import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;


public class MultiplicationTable2Easy
{
  public String isGoodSet(int[] table, int[] t){
    int size = table.length;
    int m = t.length;
    boolean[] map = new boolean[size];
    for(int i=0; i<m; ++i){
      map[t[i]] = true;
      System.out.println("set: " +map[t[i]]);
    }
    
    System.out.println("length of t "+m);
    int n = (int)Math.sqrt(size+1);
    for(int i=0; i<m; ++i){
      for(int j=0; j<m; ++j){
        int x = t[i];
        int y = t[j];
        int op =  table[x*n + y];
        System.out.println("Value: " +op);
        if(!map[op])
          return "Not Good";
      }
    }
    return "Good";
  }
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!