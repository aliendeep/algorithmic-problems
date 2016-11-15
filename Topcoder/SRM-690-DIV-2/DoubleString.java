/*
Problem Statement
    
A string is called a square if it can be created by concatenating two copies of the same string. For example, "CANCAN" is a square because it consists of two copies of the string "CAN". Other squares include "AA", "ZZZZ", and "BERIBERI". The strings "AAAAA" and "HAHAHA" are not squares.  You are given a String S. Return "square" (quotes for clarity) if there is a string T such that S = T + T. Otherwise, return "not square". Note that the return value is case-sensitive.
Definition
    
Class:
DoubleString
Method:
check
Parameters:
String
Returns:
String
Method signature:
String check(String S)
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
The length of S will be between 1 and 50, inclusive.
-
Each character in S will be an uppercase letter ('A'-'Z').
Examples
0)

    
"MAZAIMAZAI"
Returns: "square"
If T is "MAZAI", T+T will be "MAZAIMAZAI".
1)

    
"MAMAZAIZAI"
Returns: "not square"
In this case, there is no string T for which T+T will be S.
2)

    
"IOI"
Returns: "not square"
The length of S is odd, so it's impossible to make S by concatenating the same string twice.
3)

    
"AA"
Returns: "square"
T will be "A".
4)

    
"ABCCBA"
Returns: "not square"

5)

    
"Y"
Returns: "not square"

This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
*/
import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;


public class DoubleString
{
  public String check(String S)
  {
    int n = S.length();
    String x = S.substring(0, n/2);
    String y = S.substring(n/2);
    if(x.equals(y))
      return "square";
    return "not square";
  }
  
<%:testing-code%>
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!