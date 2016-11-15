/*

Problem Statement
    
Hero has a collection of ropes. You are given the String s that contains an ASCII art depicting this collection of ropes. Each character of s is either '-' (dash, ASCII 45) or '.' (period, ASCII 46). Each dash is a rope segment, each period is an empty space. Each contiguous segment of dashes represents one rope; the number of dashes is the length of the rope. For example, s = "..-..----." means that Hero has two ropes: one of length 1 and one of length 4. Hero has decided to rearrange his collection of ropes, using the following rules:
Each rope that has an even length must be placed to the left of all ropes that have odd lengths.
There must be at least one empty space between any two ropes.
If there are multiple arrangements that satisfy the previous two rules, Hero will choose the one that corresponds to the lexicographically smallest string. (This is explained below.)
Once Hero rearranges his ropes, we can encode their new placement into a new string that looks similar to s. (The string will have the same length as s and it will contain the same collection of ropes, but possibly in different places.) Given two strings of the same length, the lexicographically smaller one is the one that has a character with a smaller ASCII value on the first position on which they differ. For example, the string X = "---.-" is lexicographically smaller than the string Y = "-.---" because X[0] = Y[0] and X[1] < Y[1]. (Note that a dash has a smaller ASCII value than a period.) Construct the arrangement of ropes chosen by Hero and return the String that encodes it.
Definition
    
Class:
Ropestring
Method:
makerope
Parameters:
String
Returns:
String
Method signature:
String makerope(String s)
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
s will contain between 1 and 50 characters, inclusive.
-
Each character in s will be either '-' or '.'.
Examples
0)

    
"..-..-"
Returns: "-.-..."

1)

    
"-.-"
Returns: "-.-"

2)

    
"--..-.---..--"
Returns: "--.--.---.-.."

3)

    
"--..-.---..--..-----.--."
Returns: "--.--.--.-----.---.-...."

4)

    
"..."
Returns: "..."

This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
*/
import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;


public class Ropestring
{
  int dot;
  public void add(List<Integer> a, StringBuilder result){
    for(int i=a.size()-1; i>=0; --i){
      char[] repeat = new char[a.get(i)];
      Arrays.fill(repeat, '-');
      result.append(new String(repeat));
      if(dot > 0){
        result.append('.');
        dot--;
      }
    } 
  }
  public String makerope(String s){
    dot = 0;
    List<Integer> even = new ArrayList<>();
    List<Integer> odd = new ArrayList<>();
    int cnt = 0;
    for(int i=0; i<s.length(); ++i){
      if(s.charAt(i) == '.'){
        if(cnt > 0){
          if(cnt % 2 == 0)
            even.add(cnt);
          else
            odd.add(cnt);
        }
        dot++;
        cnt = 0;
      }
      else{
        cnt++;
      }
    } 
    if(cnt > 0){
      if(cnt % 2 == 0)
        even.add(cnt);
      else
        odd.add(cnt);
    }
    Collections.sort(even); 
    Collections.sort(odd);
    StringBuilder result = new StringBuilder();
    add(even, result);
    add(odd, result);
    
    while(dot > 0){
      result.append(".");
      dot--;
    }
    return result.toString();
  }
  
<%:testing-code%>
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!