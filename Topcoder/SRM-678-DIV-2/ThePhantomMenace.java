/*

Problem Statement
    
Obi Wan Kenobi and Qui-Gon Jinn are trying to escape from the Federation to find Queen Amidala. They are currently trapped inside a building and they have to escape through one of the doors.  All doors are on the same side of the building. You are given the coordinate of each door in the int[] doors. The doors are protected by some droids. Currently, all those droids are standing next to the wall with the doors. You are given the coordinates of the droids in the int[] droids.  Our two heroes prefer a door that is as far as possible from all the droids. For example, suppose that the doors are at positions { 2, 5, 8 } and that there are two droids: one at position 1 and the other at position 10. The optimal door for the escape would be the door at position 5. For this door, the two droids are 4 and 5 units of distance away from the door. Hence, the closest droid is 4 units of distance away from the door. Each of the other two doors is closer than that to one of the droids.  Formally, the safety level of a door is equal to the distance between the door and the droid that is closest to that door. Compute the safety levels of all doors and return the maximum of those safety levels.
Definition
    
Class:
ThePhantomMenace
Method:
find
Parameters:
int[], int[]
Returns:
int
Method signature:
int find(int[] doors, int[] droids)
(be sure your method is public)
Limits
    
Time limit (s):
2.000
Memory limit (MB):
256
Notes
-
The distance between a door at coordinate x and a droid at coordinate y is |x-y|, that is, the absolute value of the difference between x and y.
Constraints
-
doors will contain between 1 and 10 elements.
-
droids will contain between 1 and 10 elements.
-
Each element of doors will be between 0 and 100 inclusive.
-
Each element of droids will be between 0 and 100 inclusive.
-
No number will appear more than once in the concatenation of doors and droids
Examples
0)

    
{1,2,3,4}
{5,6,7,8}
Returns: 4
There are four doors located at {1,2,3,4} and four droids located at {5,6,7,8}. The safety level of the door at position 1 can be computed as follows:
Droid at position 5 is |5-1| = 4 units of distance away from this door.
Droid at position 6 is |6-1| = 5 units of distance away from this door.
Droid at position 7 is |7-1| = 6 units of distance away from this door.
Droid at position 8 is |8-1| = 7 units of distance away from this door.
Thus, the closest droid to our door is the one at position 5, and the safety level of our door is 4.
The other three doors have safety levels equal to 3, 2, and 1, respectively. Thus, the best choice for the escape is the door at position 1. The correct return value (i.e., the largest of all safety levels) is 4.
1)

    
{1}
{10}
Returns: 9
When there's only one candidate, there's only one choice.
2)

    
{2,3,5,7,11}
{1,8,13}
Returns: 3
If you use door at position 2, then you are at distance 1 from droid 1. If you use door at position 3, then you are at distance 2 from droid 1. If you use door at position 5, then you are at distance 3 from droid 2. If you use door at position 7, then you are at distance 1 from droid 2. If you use door at position 11, then you are at distance 2 from droid 3.  So all doors have droids at 3 or less units of distance. If you choose door at position 5 you have droids at distances 4, 3 and 8 so that's the best choice.
3)

    
{1,3,5,7,9,11,13,15,17,19}
{2,4,6,8,10,12,14,16,18,20}
Returns: 1
Every door is a possible choice for this test case.
4)

    
{2,1}
{4,3}
Returns: 2
Be careful! The input isn't always sorted.
This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
*/
import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;

public class ThePhantomMenace
{
  public int find(int[] doors, int[] droids){
    int n = doors.length;
    int[] safety = new int[n];
    Arrays.fill(safety, 101);
    for(int i=0; i<n; ++i){
      for(int j=0; j<droids.length; ++j){
        safety[i] = Math.min(safety[i], Math.abs(droids[j] - doors[i])); 
      } 
    } 
    
    int max = safety[0];
    for(int i=1; i<n; ++i){
      max = Math.max(max, safety[i]);
    }
    return max;
  }
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!