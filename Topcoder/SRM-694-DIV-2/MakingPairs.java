/*

Problem Statement
    
You have a collection of cards. Each card contains an integer between 0 and N-1, inclusive.

You are given a int[] card with N elements. For each valid i, you have exactly card[i] cards that contain the integer i.

You want to create pairs of cards that have the same number. Each card can only be used at most once. Return the largest number of pairs you can create.
Definition
    
Class:
MakingPairs
Method:
get
Parameters:
int[]
Returns:
int
Method signature:
int get(int[] card)
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
The value of N is not given explicitly. You can determine its value by looking at the number of elements in card.
Constraints
-
N will be between 1 and 50, inclusive.
-
card will have exactly N elements.
-
Each element of card will be between 0 and 50, inclusive.
Examples
0)

    
{2,2,2}
Returns: 3
You have two cards with the number 0, two cards with the number 1, and two cards with the number 2.
You can use these cards to form three pairs of identical cards: 0 with 0, 1 with 1, and 2 with 2.
1)

    
{1,1,1}
Returns: 0
Here, you have three cards that have the numbers 0, 1, and 2, respectively. As these cards are all distinct, you cannot make any pairs. Thus, the correct return value is 0.
2)

    
{5}
Returns: 2
You have five cards. Each of them contains the number 0. Using these cards, you can form at most two pairs of identical cards. (Note that you cannot use the same card in multiple pairs.)
3)

    
{43,23,10,39,39,22,22,0,3,4,3,2}
Returns: 102

4)

    
{0}
Returns: 0

This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
*/
import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;


public class MakingPairs
{
  public int get(int[] card)
  {
    int same = 0;
    for(int n : card){
      same += n/2;
    } 
    return same;
  }  
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!