/*

Problem Statement
    
When dealing with long names and phrases, we sometimes use the initial letters of words to form its acronym. For example, we use "JFK" instead of "John Fitzgerald Kennedy", "lgtm" instead of "looks good to me", and so on.  You are given a String name. This String contains a phrase: one or more words separated by single spaces. Please compute and return the acronym of this phrase. (As above, the acronym should consist of the first letter of each word, in order.)
Definition
    
Class:
Initials
Method:
getInitials
Parameters:
String
Returns:
String
Method signature:
String getInitials(String name)
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
name will contain between 1 and 50 characters, inclusive.
-
Each character in s will be a space or a lowercase English letter ('a' - 'z').
-
The first and last character in s will not be a space.
-
No two continuous spaces can appear in s.
Examples
0)

    
"john fitzgerald kennedy"
Returns: "jfk"
There are three words: "john", "fitzgerald" and "kennedy". Their first letters are 'j', 'f' and 'k'. The correct return value is their concatenation: the string "jfk". Note that all letters in our problem are in lowercase.
1)

    
"single"
Returns: "s"
There is only one word: "single". Its acronym has a single letter.
2)

    
"looks good to me"
Returns: "lgtm"

3)

    
"single round match"
Returns: "srm"

4)

    
"a aa aaa aa a bbb bb b bb bbb"
Returns: "aaaaabbbbb"

This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
*/
import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;


public class Initials
{
  public String getInitials(String name)
  {
    String[] arr = name.split(" ");
    StringBuilder r = new StringBuilder();
    for(int i=0; i<arr.length; ++i){
      r.append(arr[i].charAt(0));
    }
    return r.toString();
  }
  
<%:testing-code%>
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!