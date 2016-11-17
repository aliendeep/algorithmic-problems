/*

Problem Statement
    
Limak is a little polar bear. Today he found two things in the snow: a bucket of blue paint and a white rectangular grid with W times H square cells.
 
Limak is going to paint some (possibly even all) cells blue. He wants to do it in such a way that the blue cells will form a completely filled blue rectangle. He has enough paint for M cells. What is the largest possible area of a blue rectangle he can paint?
Definition
    
Class:
BearPaints
Method:
maxArea
Parameters:
int, int, long
Returns:
long
Method signature:
long maxArea(int W, int H, long M)
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
W and H will be between 1 and 10^6, inclusive.
-
M will be between 1 and 10^12, inclusive.
Examples
0)

    
3
5
14
Returns: 12
Limak has a grid that is W = 3 cells wide and H = 5 cells tall. He doesn't have enough paint to color all 15 cells. He also cannot color just 14 or 13 cells in a way that would produce a blue rectangle. The best he can do is to color four consecutive rows blue. This will produce a blue rectangle. Its area is 12 squares.
1)

    
4
4
10
Returns: 9
Here the best solution is to paint a rectangle of size 3 times 3 blue. (A square is a valid rectangle.)
2)

    
1000000
12345
1000000000000
Returns: 12345000000
Limak has more than enough paint to make whole grid blue.
3)

    
1000000
1000000
720000000007
Returns: 720000000000
Limak's grid is a square with side 10^6. Limak can paint a rectangle of size 800,000 times 900,000.
4)

    
1000000
1000000
999999999999
Returns: 999999000000

This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
*/
import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;

// O(nlogn) Solution
public class BearPaints
{
  public long maxArea(int W, int H, long M)
  {
    if(M == 0)
      return 0;
    long area = 0;
    area = (long)W*H;
    if(area <= M)
      return area;
    
    long result = 0;
    for(int i=W; i>0; --i){
      int low = 1; 
      int high = H;
      while(high - low > 3){
        int mid = (high - low )/2 + low;
        long calArea = (long)mid*i;
        if(calArea > M){
          high = mid - 1;
        }
        // calArea <= M
        else{
          low = mid;
        }
      }     
      for(int j=high; j>=low; --j){
        long calArea = (long)j*i ;
        if(calArea <= M){
          result = Math.max(result, calArea);
          break;
        }
      }
    }
    return result;
  }
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!


// O(n) Solution
class BearPaints1
{
  public long maxArea(int W, int H, long M)
  {
    if(M == 0)
      return 0;
    long area = 0;
    area = (long)W*H;
    if(area <= M)
      return area;
    
    long result = 0;
    for(int i=1; i<=W; ++i){
      long h = M/i;
      if(h <= H){
        long calArea = (long)h*i;
        result = Math.max(result, calArea);
      }
    }
    return result;
  } 
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!