/*
Problem Statement
    
You are given n points (i.e., values on the real axis) and n segments (i.e., intervals on the real axis). You are given their description: int[]s p, l, and r with n elements each. For each valid i, there is a point at p[i]. For each valid i, there is a segment that starts at l[i] and ends at r[i]. Each segments contains both its endpoints and all points between them.
Note that multiple points may share the same location. Also note that some segments may have zero length: if l[i] = r[i], the segment consists of a single point.
You want to pair the points to the segments in such a way that each segment will contain the point it's paired to. Return "Possible" (quotes for clarity) if the given points can be paired to the given segments. Otherwise, return "Impossible".
Definition
    
Class:
SegmentsAndPoints
Method:
isPossible
Parameters:
int[], int[], int[]
Returns:
String
Method signature:
String isPossible(int[] p, int[] l, int[] r)
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
p, l and r will contain the same number of elements.
-
p will contain between 1 and 100 elements, inclusive.
-
l[i] will be not greater that r[i] for each valid i.
-
Each element of p, l and r will be between -500 and 500, inclusive.
Examples
0)

    
{1, 2}
{0, 0}
{1, 3}
Returns: "Possible"
There are two points: one at 1 and the other at 2. There are two segments: [0,1] and [0,3]. We can pair the points to the segments in the given order: 1 lies in [0,1] and 2 lies in [0,3].
1)

    
{0}
{2}
{3}
Returns: "Impossible"
The point 0 does not lie in [2,3].
2)

    
{0, 1, 2}
{0, 0, 1}
{1, 2, 1}
Returns: "Possible"
Here, note that the point that lies at 1 must be assigned to the segment [1,1].
3)

    
{0, 1}
{-1, 0}
{0, 0}
Returns: "Impossible"

4)

    
{434, 63, 241, 418, -380, -46, 397, -205, -262, -282, 260, -106, -389, -286, 422, -75, 127, 382, 52, -383}
{-447, -226, -411, 287, -83, -228, -390, 358, 422, 395, -461, -112, 49, 75, -160, -152, 372, -447, -337, -362}
{-102, 348, -70, 466, 168, -61, -389, 469, 433, 471, -75, -41, 52, 236, 299, -48, 383, -353, 346, -217}
Returns: "Possible"

This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
*/

import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;

public class SegmentsAndPoints
{
  public String isPossible(int[] p, int[] l, int[] r){
    Arrays.sort(p);

    int n = p.length;
    boolean[] flag = new boolean[n+1];
    for(int i=0; i<n; ++i){
      int index = -1;
      for(int j=0; j<n; ++j){
        if(!flag[j] && p[i] >= l[j] && p[i] <= r[j]){
          if(index == -1 || r[index] > r[j])
            index = j;
        }
      }
      if(index == -1)
        return "Impossible";
      flag[index] = true;
    } 
    return "Possible";
  }
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!