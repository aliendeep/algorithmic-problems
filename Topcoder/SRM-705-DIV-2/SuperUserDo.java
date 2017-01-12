/*
Problem Statement
    
The ancient civilization of Nlogonia used the same 26 letters as modern English: 
'a'-'z'. However, we do not know the order in which these letters appeared in 
the Nlogonian alphabet. One particular custom in Nlogonia was that in a good word 
the letters appear in non-decreasing order. For example, in English the word "ciel" 
is not a good word because in the alphabet 'i' is after 'e'. The word "ceil" is 
a good word because 'c' <= 'e' <= 'i' <= 'l'. You are given the String[] words. 
Each element of words is a nonempty string of lowercase English letters. Return 
"Possible" if it is possible that all elements of words were good words in Nlogonian, 
and "Impossible" otherwise. In other words, return "Possible" if and only if there 
is at least one possible Nlogonian alphabet such that the letters of each word in 
words are in non-decreasing alphabetical order.
Definition
    
Class:
AlphabetOrderDiv2
Method:
isOrdered
Parameters:
String[]
Returns:
String
Method signature:
String isOrdered(String[] words)
(be sure your method is public)
Limits
    
Time limit (s):
2.000
Memory limit (MB):
256
Constraints
-
words has between 2 and 100 elements inclusive.
-
The size of each element of words will be between 1 and 100 inclusive.
-
Elements of words contains only English lowercase letters from 'a' to 'z'.
Examples
0)

    
{"single","round","match"}
Returns: "Possible"
One possible Nlogonian alphabet is "bfjkmapqrositchundglevwxyz".
1)

    
{"topcoder","topcoder"}
Returns: "Impossible"
The word "topcoder" can never be a good word. The character 'o' cannot be both 
before 'p' and after 'p' in the alphabet.
2)

    
{"algorithm", "contest"}
Returns: "Impossible"

3)

    
{"pink","floyd"}
Returns: "Possible"

4)

    
{"gimnasia","y","esgrima","la","plata"}
Returns: "Impossible"

5)

    
{"hello","hello"}
Returns: "Possible"
This is a good word for any alphabet in which the letters 'h', 'e', 'l', and 'o' 
appear in this order.
6)

    
{"abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"}
Returns: "Possible"
In this case the English alphabet is one of the valid permutations.
7)

    
{"abc","bca"}
Returns: "Impossible"
'a' must come before 'c' (because of the first name) and after 'c' (because of 
the second name) and that is a contradiction. Thus, there is no valid solution.
8)

    
{"aaaaa","eeeee","iiiii","ooooo","uuuuu"} 
Returns: "Possible"
Any order is valid in this case
9)

    
{"ab","bc","ca"}
Returns: "Impossible"

This problem statement is the exclusive and proprietary property of TopCoder, Inc. 
Any unauthorized use or reproduction of this information without the prior 
written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. 
All rights reserved.
*/

import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;

// Topological sort - cycle
public class AlphabetOrderDiv2
{ 
  public boolean hasCycle(int node, int[] color, Map<Integer, Set<Integer>> graph){
      // gray
    color[node] = 1;
    if(graph.containsKey(node)){
      Set<Integer> adj = graph.get(node);
      for(int neighbor : adj){
        if(color[neighbor] == 2)
          continue;
        if(color[neighbor] == 1)
          return true;
        if(hasCycle(neighbor, color, graph))
                    return true;  
      }
    }
    color[node] = 2;
    return false;
  } 
  
  public String isOrdered(String[] words){
    Map<Integer, Set<Integer>> graph = new HashMap<>(); 
    for(String word : words){
      for(int i=0; i<word.length()-1; ++i){
        int x = word.charAt(i) - 'a';
        int y = word.charAt(i+1) - 'a';
        if(x == y) 
          continue;
        if(!graph.containsKey(x)){
          graph.put(x, new HashSet<>());
        }
        graph.get(x).add(y);
      }
    }
    
    int[] color = new int[26];
    for(int i=0; i<26; ++i){
      if(color[i] == 0){
        if(hasCycle(i, color, graph))
          return "Impossible";
      }
    }
    return "Possible";    
  }
}
