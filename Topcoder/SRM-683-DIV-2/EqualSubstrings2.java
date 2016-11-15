/*

Problem Statement
    
You are given a String s. Compute and return the number of ways in which we can choose two identical non-overlapping substrings of s.
(The two substrings must be non-empty. Each substring must be contiguous.)
Definition
    
Class:
EqualSubstrings2
Method:
get
Parameters:
String
Returns:
int
Method signature:
int get(String s)
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
s will consist only of lowercase English letters ('a'-'z').
-
The length of s will be between 1 and 50, inclusive.
Examples
0)

    
"aa"
Returns: 1
There is exactly one way how to choose two non-empty and non-overlapping substrings. In this case they happen to be equal (both are "a"), so the correct return value is 1.
1)

    
"abcd"
Returns: 0
Regardless how we choose two non-overlapping substrings, they will always differ.
2)

    
"aba"
Returns: 1
One pair: ("a", "a").
3)

    
"abab"
Returns: 3
Three pairs: ("a", "a"), ("b", "b"), ("ab", "ab").
4)

    
"aaaab"
Returns: 7
The 7 ways to select the two equal substrings are shown below. Each row represents one way. The characters 1 and 2 denote characters selected to form the first and second substring, respectively.
aaaab
-----
12...
1.2..
1..2.
.12..
.1.2.
..12.
1122.
(In the first six ways, the two selected substrings are "a" and "a". In the last way the selected substrings are "aa" and "aa".)
5)

    
"onetwothreeonetwothree"
Returns: 86

This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
*/
import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;


public class EqualSubstrings2
{
  public int get(String s)
  {
    int n = s.length();
    int cnt = 0;
    for(int len=1; len<=n/2; ++len){
      for(int start = 0; start < n-len; ++start){
        String x = s.substring(start, start+len);
        for(int end = start+len; end<=n-len; ++end){
          String y = s.substring(end, end+len);         
          if(x.equals(y))
            cnt++;
        }
      }
    }
    return cnt;
  }  
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!