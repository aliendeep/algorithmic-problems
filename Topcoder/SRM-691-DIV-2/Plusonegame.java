/*

Problem Statement
    
Hero plays a game with a deck of cards and a counter. Initially, the counter is set to zero. During the game Hero must play each card in the deck exactly once. He gets to choose the order in which he plays the cards. You are given the description of the deck in the String s. Each character of s is either '+' or a digit ('0'-'9'). Each character represents one card, as described below.
Whenever Hero plays a card with a '+', the counter is incremented. (I.e., its value is increased by 1.)
Whenever Hero plays a card with a digit, he gets some (possibly zero) penalty points. The number of penalty points is calculated as abs(C - D), where C is the current value of the counter and D is the digit on the card.
Hero wants to minimize the sum of penalty points he receives during the game. Find an order in which he should play the cards, and return that order in a String. If there are multiple optimal orders of cards, return the lexicographically smallest among them.
Definition
    
Class:
Plusonegame
Method:
getorder
Parameters:
String
Returns:
String
Method signature:
String getorder(String s)
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
Let S and T be two different but equally long strings, and let i be the smallest index such that S[i] and T[i] differ. We say that S is lexicographically smaller than T if the character S[i] has a smaller ASCII value than the character T[i].
-
The ASCII value of the '+' character is smaller than the ASCII values of digits.
Constraints
-
s will contain between 1 and 50 characters, inclusive.
-
Each character in s will be either '+' or digit('0' - '9').
Examples
0)

    
"1++"
Returns: "+1+"
Hero should increment the counter to 1, then play the card '1' for 0 penalty points, and then increment the counter again.
1)

    
"549"
Returns: "459"
Each of the six possible permutations of these cards leads to the same result: Hero will receive 4+5+9 = 18 penalty points. The string "459" is the lexicographically smallest of the six possible strings.
2)

    
"++++++"
Returns: "++++++"

3)

    
"+++++2+"
Returns: "++2++++"

4)

    
"++++4++++200++2++1+6++++++"
Returns: "00+1+22++4++6+++++++++++++"

5)

    
"++11199999"
Returns: "+111+99999"

This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
*/
import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;


public class Plusonegame
{
  public String getorder(String s)
  {
    int n = s.length();
      int[] map = new int[10];
      int inc = 0;
      for(int i=0; i<n; ++i){
        char c = s.charAt(i);
        if(c == '+'){
          inc++;
        }
        else{
          map[c - '0']++;
        } 
      } 
      
      int cnt = 0;
      StringBuilder r = new StringBuilder();
      for(int i=cnt; i<10; ++i){
        while(r.length() < n && cnt < 10 && map[cnt] > 0){
          r.append(cnt);
          map[cnt]--;
        }
      if(r.length() < n && inc > 0){
          r.append('+');
          inc--;
        }
        cnt++;
    }
    while(inc > 0){
      r.append('+');
      inc--;
    }
        
      return r.toString();
  }
  
<%:testing-code%>
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!