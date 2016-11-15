/*

Problem Statement
    
Hero came up with an interesting way to calculate the value of any string. It works as follows:
Find all distinct characters that appear in the string.
For each of those characters, count the number of occurrences.
Square each of those counts.
Sum all those squares to get the value of the string.
For example, suppose Hero has the string "abacaba". This string contains 4 'a's, 2 'b's, and 1 'c'. Thus, its value is 4*4 + 2*2 + 1*1 = 21.
You are given a String s and an int k. You are allowed to remove at most k characters from s. Your goal is to produce a string with the smallest possible value. Compute and return that value.
Definition
    
Class:
Istr
Method:
count
Parameters:
String, int
Returns:
int
Method signature:
int count(String s, int k)
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
Each character in s will be a lowercase letter ('a'-'z').
-
k will be between 0 and the length of s, inclusive.
Examples
0)

    
"aba"
1
Returns: 2
The optimal strategy is to erase one of the two 'a's. This produces a string with value 1*1 + 1*1 = 2.
1)

    
"abacaba"
0
Returns: 21

2)

    
"abacaba"
1
Returns: 14

3)

    
"abacaba"
3
Returns: 6

4)

    
"abc"
3
Returns: 0

5)

    
"wersrsresesrsesrawsdsw"
11
Returns: 23

This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
*/
import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;

public class Istr
{

  public int count(String s, int k)
  {
    int n = s.length();
    int[] cnt = new int[26];
    for(int i=0; i<n; ++i){
      cnt[s.charAt(i) - 'a']++;
    }
  
    PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(1, Collections.reverseOrder());
    for(int i=0; i<26; ++i){
      if(cnt[i] == 0)
        continue;      
      maxHeap.add(cnt[i]);
    } 
    
    while(k > 0 && !maxHeap.isEmpty()){
      int top = maxHeap.poll();
      top--;
      k--;
      if(top > 0){
        maxHeap.add(top);
      }           
    }
    
    int sum = 0;
    while(!maxHeap.isEmpty()){
      int top = maxHeap.poll();
      sum += top*top;
    }
    return sum;      
  }
  public static void main(String[] args){
    Istr ob = new Istr();
    // 6
    System.out.println(ob.count("abacaba", 3));
  }
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!