/*

Problem Statement
    
There is a rectangular board that is divided into n rows by m columns of cells. 
Each cell is either empty or it contains an obstacle. You are given the description 
of the board in the String[] board. Each character in board represents one cell. 
More precisely, the character board[i][j] represents the cell at coordinates (row i, column j). 
The character '#' represents an obstacle, the character '.' is an empty cell.  
You would like to travel from the top left corner to the bottom right corner of 
the board in exactly k steps. More precisely, you want to perform the following 
sequence of actions:  
Enter the board by stepping onto the cell at coordinates (0, 0): the top left corner.
Make exactly k steps. In each step, you'll move from your current cell to one of 
the cells that share a side with your current cell. In other words, in each step 
you'll go one cell up, down, left, or right.
After the k-th step, you must be in the bottom right corner: at coordinates (n-1, m-1).
Note that you can only step onto empty cells. You have to move in each step, it 
is not allowed to stay in the same cell. You are allowed to visit each empty cell 
arbitrarily many times. You are not allowed to leave the board while making the 
k steps.  You are given the String[] board and the int k. Determine whether it is 
possible to reach the bottom right corner in the way described above. If there is 
no solution, return an empty String. If there are some solutions, choose any one 
of them and return a String containing its description.  If a solution exists, 
the returned String should consist of k characters, each describing one step. 
Each character should be one of 'U' (up), 'D' (down), 'L' (left), and 'R' (right).
Definition
    
Class:
StepsConstruct
Method:
construct
Parameters:
String[], int
Returns:
String
Method signature:
String construct(String[] board, int k)
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
The character 'U' represents a step that decreases your row number. The character 'L' represents a step that decreases your column number.
Constraints
-
n, m will be between 2 and 50, inclusive.
-
board will contain exactly n elements.
-
Each element in board will contain exactly m characters.
-
Each character in board will be either '#' or '.'.
-
k will be between 1 and 3,000, inclusive.
Examples
0)

    
{"...",
 ".#.",
 "..."}
4
Returns: "DDRR"
You need to get from (0,0) to (2,2) in exactly 4 steps.
1)

    
{"...",
 ".#.",
 "..."}
12
Returns: "DDRRUULLDDRR"
This time we want to get there in exactly 12 steps, one possible solution is to 
go around that obstacle once.
2)

    
{"...#.",
 "..#..",
 ".#..."}
100
Returns: ""
There is no way to get to (n-1, m-1) from (0,0).
3)

    
{"..#",
 "#.#",
 "..#",
 ".#.",
 "..."}
6
Returns: ""

4)

    
{".#...",
 ".#.#.",
 ".#.#.",
 ".#.#.",
 "...#."}
16
Returns: "DDDDRRUUUURRDDDD"

5)

    
{"#.", 
 ".."}
2
Returns: ""
There is no solution because you are not even able to enter the top left cell.
This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
*/
import java.util.*;

public class StepsConstruct
{
  String[] board;
  int[][][] dp;
  int[][][] par;
    int[][] move = {{-1, 0},  {1, 0}, {0, -1}, {0, 1}};
    int m, n;
    String direction(int dir){
        if(dir == 0)    return "U";        
        if(dir == 1)    return "D";        
        if(dir == 2)    return "L";        
        if(dir == 3)    return "R"; 
        return "";
    }
  
  int k;
  public int isReachable(int r, int c, int step){
    if(r == m-1 && c == n-1 && step == k && board[r].charAt(c) != '#')    return 1;
    if(r < 0 || c < 0 || r >= m || c >= n || step > k)            return 0;
    if(board[r].charAt(c) == '#')                       return 0;
    if(dp[r][c][step] != -1)                        return dp[r][c][step];
    
    int reachable = 0;
    for(int i=0; i<4; ++i){
      int r1 = r + move[i][0];
      int c1 = c + move[i][1];
      if(isReachable(r1, c1, step + 1) == 1){
        reachable = 1;
        // save the direction
        par[r][c][step] = i;
        break;
      }
    } 
    dp[r][c][step] = reachable;
    return reachable;
  }

  public void constructPath(int i, int j, int step, StringBuilder result){
    if(step == k || par[i][j][step] == -1)  return;
    int dir = par[i][j][step];
    result.append(direction(dir));
    constructPath(i + move[dir][0], j + move[dir][1], step+1, result);
  }
  
  public String construct(String[] board, int k)
  {
    this.board = board;
    this.k = k;
    m = board.length;
    n = board[0].length();
    
    dp = new int[m][n][k+1];
    for(int[][] x : dp){
      for(int[] y : x)
        Arrays.fill(y, -1);
    }

    par = new int[m][n][k];
    for(int[][] x : par){
      for(int[] y : x)
        Arrays.fill(y, -1);
    }
    
    int result = isReachable(0, 0, 0);
    if(result == 0)     return "";
    StringBuilder path = new StringBuilder();
    constructPath(0, 0, 0, path);
    return path.toString();
  }
<%:testing-code%>
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!
