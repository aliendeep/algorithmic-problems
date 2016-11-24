/*

Problem Statement
    
You have two robots. Currently, the robots are standing on two different points on an infinite two-dimensional plane. You are given the ints x1, y1, x2, and y2. These are the initial coordinates of both robots: one is located at (x1,y1), the other one is at (x2,y2).
The robots know four instructions: U, D, L, and R. These represent moving 1 unit up, down, left, and right. More formally, U increases the robot's y coordinate by 1, D decreases the y coordinate by 1, L decreases the x coordinate by 1, and R increases the x coordinate by 1.
You are going to send both robots the same sequence of instructions at the same time. This sequence of instructions is given in the String instructions.
Both robots are going to execute this sequence of instructions. They will spend exactly one second executing each instruction. Unfortunately, your robots are a bit buggy. It is possible that they will ignore some instructions and spend the corresponding seconds just standing in place. Each robot has its own bugs and therefore the two robots may ignore different subsets of instructions. (It is possible that a robot will ignore all instructions, and it is also possible that a robot will execute all instructions it was given.)
The robots will explode if they ever occupy the same point at the same time.
Return the String "Explosion" if it is possible that your two robots will explode. Otherwise, return "Safe".
Definition
    
Class:
ExplodingRobots
Method:
canExplode
Parameters:
int, int, int, int, String
Returns:
String
Method signature:
String canExplode(int x1, int y1, int x2, int y2, String instructions)
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
x1,y1,x2,y2 will be between -25 and 25, inclusive.
-
(x1,y1) will be different from (x2,y2).
-
instructions will have between 1 and 50 characters, inclusive.
-
Each character of instructions will be 'U', 'R', 'L', or 'D'.
Examples
0)

    
1
0
2
0
"L"
Returns: "Explosion"
We have two robots. One starts at coordinates (1,0), the other at (2,0). We send them a command to move left. If the first robot ignores it and the second robot moves left, they will collide and explode.
1)

    
1
0
2
0
"U"
Returns: "Safe"
We have the same starting positions as in Example 0, but this time we send a command to go up. Regardless of whether they ignore it or perform it, they cannot collide.
2)

    
1
0
2
0
"UL"
Returns: "Explosion"
In this case, one possible scenario where the robots collide is when the first robot performs the instructions "U", and the second robot performs the instructions "UL".
3)

    
3
3
5
5
"LURLL"
Returns: "Safe"

4)

    
10
5
-9
-10
"LULULULLLUULRULULULULULULLULULLULD"
Returns: "Explosion"

This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
*/
import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;


public class ExplodingRobots
{ 
  public String canExplode(int x1, int y1, int x2, int y2, String instructions){
    int x = Math.abs(x1 - x2);
    int y = Math.abs(y1 - y2);
    for(int i=0; i<instructions.length(); ++i){
      char c = instructions.charAt(i);
      if(c == 'L' || c == 'R')  x--;
      else            y--;
    }
    return (x <= 0 && y <=0) ? "Explosion" : "Safe";
  }  
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!