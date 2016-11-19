/*

Problem Statement
    
Bear Limak loves algorithms, especially the ones with words and strings.
 
Limak's friend recently entered a programming competition and wrote a program. The program contains a string constant s. Limak would now like to challenge the program by making it exceed the time limit. To do that, he must find two different characters in s that are as far apart as possible.
 
Formally, Limak must find two integers i and j with the following properties:
Both i and j must be valid indices into s. That is, both numbers must be between 0 and n-1, inclusive, where n is the length of s.
The characters s[i] and s[j] must be different.
The difference between i and j must be as large as possible.
 
You are given the String s. If Limak cannot choose any integers with the required properties, return -1. Otherwise, return the largest possible difference between i and j.
Definition
    
Class:
BearPair
Method:
bigDistance
Parameters:
String
Returns:
int
Method signature:
int bigDistance(String s)
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
s will have between 2 and 50 characters, inclusive.
-
Each character in s will be a lowercase English letter ('a' - 'z').
Examples
0)

    
"bear"
Returns: 3
Limak can choose the (0-based) indices 0 and 3. We have s[0]='b' and s[3]='r', which are indeed two different letters. The difference between the two indices is 3-0 = 3.
1)

    
"abcba"
Returns: 3
Here, one optimal solution is for Limak to choose the indices 1 and 4 (corresponding to 'b' and 'a', respectively). Another optimal solution is to choose indices 0 and 3 (letters 'a' and 'b'). In both cases the difference is 3.
2)

    
"oooohyeahpotato"
Returns: 13

3)

    
"zzzzzzzzzzzzzzzzzzzzz"
Returns: -1
Here, Limak can't choose two indices with different letters.
4)

    
"qw"
Returns: 1

5)

    
"xxyyxyxyyyyyyxxxyxyxxxyxyxyyyyyxxxxxxyyyyyyyyxxxxx"
Returns: 47

This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
*/
import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;

public class BearPair
{
  public int bigDistance(String s)
  {
    int n = s.length();
    int max = -1;
    for(int i=0; i<n; ++i){
      for(int j=0; j<n; ++j){
        if(s.charAt(i) != s.charAt(j))
          max = Math.max(max, j - i); 
      } 
    } 
    return max;
  }
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!