/*

Problem Statement
    
NOTE: This problem statement contains an image that may not display properly if 
viewed outside of the applet. 
Given a list of two-dimensional rectangles, compute the area of their union. For 
example, the union of the three rectangles shown in the figure below:
 
cover an area of 35 units.
The list of rectangles will be given as a String[], where each element describes 
one rectangle. Each String will be formatted as 4 space-separated integers with 
no leading zeros, giving the coordinates of the left, bottom, right, and top of 
the rectangle (in that order). The three rectangles shown above would be given as:
    { "1 3 5 6",
      "3 1 7 5",
      "4 4 9 7" }
Definition
    
Class:
BoxUnion
Method:
area
Parameters:
String[]
Returns:
int
Method signature:
int area(String[] rectangles)
(be sure your method is public)
Limits
    
Time limit (s):
2.000
Memory limit (MB):
64
Constraints
-
rectangles will contain between 1 and 3 elements, inclusive.
-
Each element of rectangles will be formatted as described in the problem statement.
-
For each rectangle, the left coordinate will be less than the right coordinate and the bottom coordinate will be less than the top coordinate.
-
All coordinates will be between 0 and 20000, inclusive.
Examples
0)

    
{ "200 300 203 304" }
Returns: 12
A single rectangle with area 12.
1)

    
{ "0 0 10 10",
  "20 20 30 30" }
Returns: 200
Two disjoint rectangles, each of area 100.
2)

    
{ "0 500 20000 501",
  "500 0 501 20000" }
Returns: 39999
These two rectangles intersect at a single point.
3)

    
{ "4 6 18 24",
  "7 2 12 19",
  "0 0 100 100" }
Returns: 10000
The third rectangle completely overlaps the first two.
4)

    
{ "1 3 5 6",
  "3 1 7 5",
  "4 4 9 7" }
Returns: 35
This is the example from the problem statement.
5)

    
{ "0 0 20000 20000",
  "0 0 20000 20000",
  "0 0 20000 20000" }
Returns: 400000000

This problem statement is the exclusive and proprietary property of TopCoder, Inc. 
Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
*/
import java.util.*;

class YRange{
  public int y1, y2, tag;
  public YRange(int y1, int y2, int tag){
    this.y1 = y1;
    this.y2 = y2;
    this.tag = tag;    
  }
}

class Events{
  public List<YRange> in;
  public List<YRange> out;
  public Events(){
    in = new ArrayList<>();
    out = new ArrayList<>();
  }
}

public class BoxUnion{
  public int area(String[] rectangles){
    // Sorted. unique x, all events at that particular x
    TreeMap<Integer, Events> events = new TreeMap<>();

    int tag = 0;
    for(String r : rectangles){
      tag++;
      Scanner sc = new Scanner(r);
      int x1 = sc.nextInt();
      int y1 = sc.nextInt();
      int x2 = sc.nextInt();
      int y2 = sc.nextInt();
      if(!events.containsKey(x1))
        events.put(x1, new Events());
      if(!events.containsKey(x2))
        events.put(x2, new Events());

      events.get(x1).in.add(new YRange(y1, y2, tag));
      events.get(x2).out.add(new YRange(y1, y2, tag));
    }

    // Active set of tags for each unique y
    // we need to identify where each each rectangle starts & end
    // positve tag - start 
    // negative tag - end
    // We need all of them sorted
    TreeMap<Integer, TreeSet<Integer>> active = new TreeMap<>();
    int area = 0;
    for(Map.Entry<Integer, Events> entry : events.entrySet()){
      int x = entry.getKey();
      Integer next_x = events.higherKey(x);
      // processed last unique x
      if(next_x == null)    break;
      Events e = entry.getValue();

      // Construct the current active set
      for(YRange y : e.in){
        if(!active.containsKey(y.y1))     active.put(y.y1, new TreeSet<>());
        if(!active.containsKey(y.y2))     active.put(y.y2, new TreeSet<>());

        active.get(y.y1).add(y.tag);
        active.get(y.y2).add(-y.tag);
      }
      for(YRange y : e.out){
        active.get(y.y1).remove(y.tag);
        active.get(y.y2).remove(-y.tag);
      }
      // Compute union of y segments
      Integer lasty = null;
      int ySum = 0;
      int cnt = 0;
      for(Map.Entry<Integer, TreeSet<Integer>> ytags : active.entrySet()){
        int y = ytags.getKey();
        if(cnt == 0){
          lasty = y;
        }
        for(int ytag : ytags.getValue()){
          // closing
          if(ytag < 0)   cnt--;
          else          cnt++;
        }

        if(cnt == 0){
          ySum += y - lasty;
          // reset lasty
          lasty = null;
        }
      }
      area += (next_x - x)*ySum;
    }
    return area;
  }  
<%:testing-code%>
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!
