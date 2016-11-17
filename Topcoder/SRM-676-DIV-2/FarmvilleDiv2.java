/*

Problem Statement
    
Farmer John recently found out about a popular online farming game.
There are n types of plants in the game. The types are numbered 0 through n-1. At the beginning of the game, Farmer John is given one seed of each plant type.
There is a single plot of land. At any time the plot can only contain at most one plant. Whenever the plot is empty, Farmer John can plant one of the seeds. Once a seed of type i is planted, it takes time[i] seconds until it grows into a fully developed plant. When that happens, Farmer John will harvest the plant and the plot will become empty again. Planting a seed and harvesting a plant happens instanteously.
Farmer John also has budget coins. He can spend these coins to make his plants grow faster. For each i, Farmer John may pay cost[i] coins to reduce time[i] by 1. Farmer John may pay for the same plant multiple times, each time decreasing its growing time by 1. Obviously, the growing time cannot be reduced below 0.
You are given the int[]s time and cost with n elements each, and the int budget. Determine and return the minimum amount of time in which Farmer John can grow a single plant of each type.
Definition
    
Class:
FarmvilleDiv2
Method:
minTime
Parameters:
int[], int[], int
Returns:
int
Method signature:
int minTime(int[] time, int[] cost, int budget)
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
The value of n is not given as a separate argument. Instead, you can determine it as the number of elements in time.
Constraints
-
n will be between 1 and 50, inclusive.
-
time,cost will have exactly n elements.
-
Each element of time,cost will be between 1 and 100, inclusive.
-
budget will be between 1 and 5,000, inclusive.
Examples
0)

    
{100}
{1}
26
Returns: 74
In this case, we have a single plant that takes 100 seconds to grow. We can reduce the time it takes to grow by 1 second at a cost of 1 coin. Since we have 26 coins, we can use all our coins to reduce the time it takes the plant to grow to only 74 seconds.
1)

    
{100}
{1}
101
Returns: 0

2)

    
{2,1}
{1,1}
3
Returns: 0
Here we have two plants. Without payments, plant 0 will grow in 2 seconds and plant 1 will grow in 1 second. We have a budget of 3 coins. We can pay 1+1 to decrease the growing time of plant 0 from 2 to 0. We can then pay 1 to decrease the growing time of plant 1 from 1 to 0.
3)

    
{1,2,3,4,5}
{5,4,3,2,1}
15
Returns: 6

4)

    
{100,100,100,100,100,100,100,100,100,100}
{2,4,6,8,10,1,3,5,7,9}
5000
Returns: 50

5)

    
{30,40,20,10}
{10,20,30,40}
5
Returns: 100

This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
)
*/
import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;


public class FarmvilleDiv2{
  class Pair{
    int time;
    int cost;
    public Pair(int i, int j){
      time = i;
      cost = j;
    }
  }
  public int minTime(int[] time, int[] cost, int budget)
  {
      int n = time.length;
      Pair[] a = new Pair[n];
      int total = 0;  
      int minCost = Integer.MAX_VALUE;
      for(int i=0; i<n; ++i){
        a[i] = new Pair(time[i], cost[i]);
        minCost = Math.min(minCost, cost[i]);
        total += time[i];
      }   
      if(minCost > budget)
        return total;
      
      // Sort by cost
    Arrays.sort(a, new Comparator<Pair>(){
      @Override
      public int compare(Pair a, Pair b){
        return Integer.compare(a.cost, b.cost);
      }
    });
    
    int rem = budget;
    for(int i=0; i<n; ++i){
      int unit = Math.min(a[i].time, rem/a[i].cost);
      a[i].time -= unit;
      rem -= unit*a[i].cost;
    }
    int sum = 0;
      for(int i=0; i<n; ++i)
        sum += a[i].time;
      return sum;
  }
  
  <%:testing-code%>
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!