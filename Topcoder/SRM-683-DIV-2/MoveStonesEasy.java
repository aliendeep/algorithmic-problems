/*

Problem Statement
    
There are n piles of stones arranged in a line. The piles are numbered 0 through n-1, in order. In other words, for each valid i, piles i and i+1 are adjacent.
You are given two int[]s a and b, each with n elements. For each i, a[i] is the current number of stones in pile i, and b[i] is the desired number of stones for this pile. You want to move some stones to create the desired configuration. In each step you can take any single stone from any pile and move the stone to any adjacent pile. Find and return the smallest number of steps needed to create the desired configuration, or -1 if the desired distribution of stones cannot be reached.
Definition
    
Class:
MoveStonesEasy
Method:
get
Parameters:
int[], int[]
Returns:
int
Method signature:
int get(int[] a, int[] b)
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
At any moment during the game some piles may be empty. Empty piles still remain in place. For example, if pile 5 is empty, you are not allowed to move a stone from pile 4 directly to pile 6 in a single step. Instead, you must place the stone onto the empty pile 5 first.
Constraints
-
n will be between 1 and 50, inclusive.
-
a will have exactly n elements.
-
b will have exactly n elements.
-
Each element of a and b will be between 0 and 1,000,000 (10^6), inclusive.
Examples
0)

    
{1, 2}
{2, 1}
Returns: 1
We need to move one stone from pile 1 to pile 0.
1)

    
{10, 0}
{0, 10}
Returns: 10

2)

    
{0, 0, 1}
{1, 0, 0}
Returns: 2
Note that in a single step we can only move a stone between adjacent piles. Hence, we need two steps to move a stone from pile 2 to pile 0.
3)

    
{12, 12}
{12, 12}
Returns: 0
The desired configuration of stones is the same as the current configuration. No steps necessary.
4)

    
{5}
{6}
Returns: -1
We cannot add or remove stones, we can only move them between piles.
5)

    
{3,10,0,4,0,0,0,1,0}
{5,5,0,7,0,0,0,0,1}
Returns: 9
Move 2 stones from pile 1 to pile 0.
Move 3 stones from pile 1 to pile 2.
Move 3 stones from pile 2 to pile 3.
Move 1 stone between two last piles.
The total number of steps is 2 + 3 + 3 + 1 = 9.
This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
*/
import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;


public class MoveStonesEasy
{
  public int get(int[] a, int[] b)
  {
    int n = a.length;
    int[] diff = new int[n];  
    int diffSum = 0;
    for(int i=0; i<n; ++i){
      diff[i] = b[i] - a[i];
      diffSum += diff[i];  
    }
    System.out.println("diff " + diffSum);
    if(diffSum != 0)
      return -1;
    
    int moves = 0;
    for(int i=0; i<n; ++i){
      if(diff[i] < 0){
        for(int j=0; j<n; ++j){
          if(diff[j] > 0){
            int d = Math.min(diff[j], -diff[i]);
            moves += Math.abs((j - i))*d;
            diff[i] += d;
            diff[j] -= d;
          }
        }
      }
    } 
    return moves;
  }
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!