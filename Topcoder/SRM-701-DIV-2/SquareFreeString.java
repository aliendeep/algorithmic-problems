/*
Problem Statement
    
We say that a string S is a square if it has the form TT, where T is some non-empty string. In other words, a square is a string that is a concatenation of two copies of the same non-empty string. For example, the strings "aa", "bbbb", and "beriberi" are squares.

A string is called square-free if none of its substrings is a square. For example, the string "abca" is square-free. (The substrings of this string are the strings "a", "b", "c", "a", "ab", "bc", "ca", "abc", "bca", and "abca". None of these strings is a square.)

You are given a String s. Return "square-free" if s is square-free. Otherwise, return "not square-free". Note that the return value is case-sensitive.
Definition
    
Class:
SquareFreeString
Method:
isSquareFree
Parameters:
String
Returns:
String
Method signature:
String isSquareFree(String s)
(be sure your method is public)
Limits
    
Time limit (s):
2.000
Memory limit (MB):
256
Stack limit (MB):
512
Constraints
-
s will consist only of lowercase English letters ('a'-'z').
-
The length of s will be between 1 and 50, inclusive.
Examples
0)

    
"bobo"
Returns: "not square-free"
"bobo" = T + T, where T = "bo", so it is not square-free.
1)

    
"apple"
Returns: "not square-free"
Substring "pp" is a square.
2)

    
"pen"
Returns: "square-free"
"pen" does not contain any substrings that are squares.
3)

    
"aydyamrbnauhftmphyrooyq"
Returns: "not square-free"

4)

    
"qwertyuiopasdfghjklzxcvbnm"
Returns: "square-free"

This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
*/
import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;


public class SquareFreeString
{
  public String isSquareFree(String s)
  {
    int n = s.length();
    for(int len=1; len<=n/2; len++){
      int start  = 0;
      while(start+2*len<=n){
        String x = s.substring(start, start+len);
        String y = s.substring(start+len, start+2*len);
        if(x.equals(y))
          return "not square-free";
        start++;  
      }
    } 
    return "square-free";   
  }
  
  public static void main(String[] args){
      SquareFreeString ob = new SquareFreeString();
      // "not square-free"
      System.out.println(ob.isSquareFree("bobo"));
      // "not square-free"
      System.out.println(ob.isSquareFree("apple"));
      // "square-free"
      System.out.println(ob.isSquareFree("pen"));
      // not square-free"
      System.out.println(ob.isSquareFree("aydyamrbnauhftmphyrooyq"));
      // square-free"
      System.out.println(ob.isSquareFree("qwertyuiopasdfghjklzxcvbnm"));
      System.out.println(ob.isSquareFree("ahellohelloc"));
      System.out.println(ob.isSquareFree("abcabc"));
  }
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!