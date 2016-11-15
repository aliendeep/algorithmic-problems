/*
Problem Statement
    
Bear Limak is going to walk to the store. On his way to the store he likes to visit his friends and stop at various other interesting places. On his way back he is carrying heavy bags and therefore he walks straight home.
 
Limak's walk from his home to the store consists of M parts. In the i-th part (0-based index) Limak will walk a[i] meters in the direction indicated by the character dir[i]. That character is one of 'N', 'S', 'W', 'E', denoting North, South, West, and East, respectively.
 
On his way back home, Limak follows a straight line from the store to his home.
 
You are given the description of Limak's way to the store: the int[] a with M integers and the String dir with M characters. Compute and return the total distance (in meters) Limak will walk on his way to the store and back. Note that the correct answer may be non-integer (see the first example below).
Definition
    
Class:
BearNSWE
Method:
totalDistance
Parameters:
int[], String
Returns:
double
Method signature:
double totalDistance(int[] a, String dir)
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
Your return value must have relative error smaller than 1e-6. In other words, your returned value x will be accepted only if abs(x - ans) ≤ ans * 10-6, where ans denotes the exact correct answer.
Constraints
-
M will be between 1 and 50, inclusive.
-
a will have exactly M elements.
-
dir will have exactly M characters.
-
Each element in a will be between 1 and 50, inclusive.
-
Each character in dir will be one of 'N', 'S', 'W', 'E'.
Examples
0)

    
{1,3,3}
"NES"
Returns: 10.60555127546399
  
In total, Limak's walk consists of four parts:
He goes 1 meter North.
3 meters East.
3 meters South.
He goes straight to his home. The distance is sqrt(2*2+3*3) = sqrt(13) = 3.6055512755.
The total distance is 1 + 3 + 3 + 3.6055512755 = 10.6055512755.
1)

    
{10,10,10,10}
"NWSE"
Returns: 40.0
In this test case the store is located at the same place as Limak's home. Thus, the length of Limak's walk from the store back home is 0 meters.
2)

    
{10,10,10,15,8,20,5}
"NEWEWWE"
Returns: 90.8062484748657
It's possible that Limak visits some places more than once.
3)

    
{42}
"E"
Returns: 84.0

4)

    
{10,40,40}
"NSE"
Returns: 140.0

This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
*/
import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;


public class BearNSWE
{
  class Pair{
    int x, y;
    public Pair(int x, int y){
      this.x = x;
      this.y = y;
    }
  }
  public double totalDistance(int[] a, String dir){
    int n = a.length;
    if(n == 0)  return 0;
    Pair cur = new Pair(0, 0);
    double distance = 0;
    for(int i=0; i<n; ++i){
      distance += a[i];
      if(dir.charAt(i) == 'E'){
        cur = new Pair(cur.x + a[i], cur.y);
      }
      else if(dir.charAt(i) == 'W'){
        cur = new Pair(cur.x - a[i], cur.y);
      }     
      else if(dir.charAt(i) == 'N'){
        cur = new Pair(cur.x, cur.y + a[i]);
      }     
      // South
      else{
        cur = new Pair(cur.x, cur.y - a[i]);
      }     
    }
    distance += Math.sqrt(cur.x * cur.x + cur.y * cur.y);
    return distance;
  }
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!