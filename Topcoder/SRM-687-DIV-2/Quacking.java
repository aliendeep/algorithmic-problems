/*

Problem Statement
    
Ducks have started mysteriously appearing in your room. All ducks make the same sound: "quack". Each duck makes the sound one or more times, one after another. For example, valid sounds for a single duck are "quack", "quackquackquackquack", "quackquack", etc.
You have lost count of how many ducks are in your room. The ducks are quacking, and the sounds of their quacks are getting mixed up. You have recorded the sounds, obtaining a String of characters. When you later listened to the recording, you realized that the quacking of each individual duck forms a (not necessarily contiguous) subsequence of the recording. You also noticed that each letter in the recording belongs to exactly one duck. For example, if there were two ducks, you may have recorded "quqacukqauackck".
You are given a String s that contains an arbitrary recording. Find and return the smallest number of ducks that could have produced the recording. Note that it is possible that the given recording is not a valid recording of quacking ducks. In such case, return -1.
Definition
    
Class:
Quacking
Method:
quack
Parameters:
String
Returns:
int
Method signature:
int quack(String s)
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
s will have between 5 and 2,500 characters, inclusive.
-
Each character of s will be 'q', 'u', 'a', 'c', or 'k'.
Examples
0)

    
"quqacukqauackck"
Returns: 2
This is the same as the one from the problem statement.
1)

    
"kcauq"
Returns: -1
A backward duck is not a real duck.
2)

    
"quackquackquackquackquackquackquackquackquackquack"
Returns: 1
A single duck can make arbitrarily many quack sounds.
3)

    
"qqqqqqqqqquuuuuuuuuuaaaaaaaaaacccccccccckkkkkkkkkk"
Returns: 10

4)

    
"quqaquuacakcqckkuaquckqauckack"
Returns: 3
This is one possible solution with 3 ducks.
Mixed: quqaquuacakcqckkuaquckqauckack
Duck1: ____q_u__a___ck_______________
Duck2: __q__u_ac_k_q___ua__ckq_u__ack
Duck3: qu_a_______c___k__qu___a_ck___
Here, we can verify that each letter comes from exactly one duck.
5)

    
"quackqauckquack"
Returns: -1
Note that the second quack is misspelled.
This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
*/
import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;

public class Quacking
{
  public int quack(String s)
  {
    int max = 0;
    int cnt = 0;
    // cnt of q, u, a, c, k
    Map<Character, Integer> prev = new HashMap<>();
    prev.put('u', 0);
    prev.put('a', 1);
    prev.put('c', 2);
    prev.put('k', 3);   
    int[] sound = new int[5];
    for(int i=0; i<s.length(); ++i){
      char c = s.charAt(i);
      if(c == 'q'){
        cnt++;        
        sound[0]++;
      }
      else{
        if(cnt == 0)  return -1;
        int previ = prev.get(c);
        sound[previ+1]++;
        if(sound[previ] < sound[previ+1])
          return -1;
        if(c == 'k')
          cnt--;
      }
        
      max = Math.max(max, cnt);
    }
    return cnt == 0 ? max : -1;
  }
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!