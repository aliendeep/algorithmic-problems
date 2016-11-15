/*

Problem Statement
    
Limak is going to spend N days in the mountains. The days will be numbered 1 through N. In the morning of day 1 Limak starts his hike in the base camp at altitude 0. In the evening of day N Limak must return back to altitude 0. Limak carries a tent and a sleeping bag, so during the hike he can sleep at any altitude.  During each day of his hike, Limak either ascends or descends. (Each day he has to choose one or the other, he cannot both ascend and descend on the same day.) Additionally, there are two constraints:
He cannot ascend too quickly, to make acclimatization to higher altitudes easier. More precisely, each day spent ascending can increase his altitude by at most A.
He cannot descend too quickly, otherwise his knees hurt. More precisely, each day spent descending can decrease his altitude by at most B.
For example, suppose that Limak's altitude in the morning is 470, and suppose that A=100 and B=200. In the evening of the same day Limak can be anywhere between the altitudes 270 and 570, inclusive.  You are given the ints N, A, and B. Return the largest altitude Limak can reach.
Definition
    
Class:
UpDownHiking
Method:
maxHeight
Parameters:
int, int, int
Returns:
int
Method signature:
int maxHeight(int N, int A, int B)
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
N will be between 2 and 50, inclusive.
-
A and B will be between 1 and 50, inclusive.
Examples
0)

    
3
7
10
Returns: 10
The highest altitude Limak can reach is 10. Here is one way to do so:
On day 1, Limak will start at altitude 0 and he will ascend to altitude 7.
On day 2, Limak will start at altitude 7 and he will ascend to altitude 10.
On day 3, Limak will start at altitude 10 and he will descend back to altitude 0.
1)

    
5
40
30
Returns: 80
In the five days of this hike Limak can change his altitude by +40, +40, -27, -27, and -26. The highest altitude reached is 80.
2)

    
2
50
1
Returns: 1
Even though Limak can climb quickly, moving up even by 50 in one day, he can move down by at most 1 in the second day. So, in the first day he should climb by 1 only.
3)

    
3
42
42
Returns: 42

4)

    
20
7
9
Returns: 77

This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
*/
import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;


public class UpDownHiking
{
  public int maxHeight(int N, int A, int B){
    int result = 0;
    for(int a=1; a<N-1; ++a){
      int d = N - a;
      result = Math.max(result, Math.min(a*A, d*B)); 
    }
    return result;
  }
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!