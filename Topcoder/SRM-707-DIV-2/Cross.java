/*

Problem Statement
    
There is a rectangular board that is divided into n rows by m columns of cells. 
Each cell is either black or white. You are given the description of the board in 
the String[] board. Each character in board represents one cell. More precisely, 
the character board[i][j] represents the cell at coordinates (row i, column j). 
The character '#' represents a black cell, the character '.' is a white cell.  
You want to find a cross on this board. A cross is a configuration of 5 black cells placed like this:  
 #
###
 #
  Formally, there is a cross centered at (x, y) if the following five cells are 
  all black: (x, y), (x+1, y), (x-1, y), (x, y-1), and (x, y+1). Note that other 
  cells adjacent to the cross may also be black, it is still a valid cross.  
  Return "Exists" if there is at least one cross on the given board. Otherwise, 
  return "Does not exist". Note that the return value is case-sensitive.
Definition
    
Class:
Cross
Method:
exist
Parameters:
String[]
Returns:
String
Method signature:
String exist(String[] board)
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
n, m will be between 3 and 50, inclusive.
-
board will contain exactly n elements.
-
Each element in board will contain exactly m characters.
-
Each character in board will be either '#' or '.'.
Examples
0)

    
{".##",
 "###",
 "##."}
Returns: "Exist"
There is a cross centered at (1,1).
1)

    
{".##",
 "###",
 "#.."}
Returns: "Does not exist"
This time there is no cross.
2)

    
{"######",
 "######",
 "######",
 "######",
 "######",
 "######",
 "######"}
Returns: "Exist"

3)

    
{".#.#",
 "#.#.",
 ".#.#",
 "#.#."}
Returns: "Does not exist"

4)

    
{".#.#",
 "###.",
 ".###",
 "#.#."}
Returns: "Exist"

This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
*/
import java.util.*;

public class Cross
{
  int m, n;
  String[] board;
  
  boolean isValid(int i, int j){
    if(i < 0 || i >= m || j < 0 || j >= n)  return false;
    return board[i].charAt(j) == '#';
  }
  
  public String exist(String[] board)
  {
    this.board = board;
    m = board.length;
    n = board[0].length();  
    
    for(int i=0; i<m; ++i){
      for(int j=0; j<n; ++j){
        if(board[i].charAt(j) != '#')
          continue;
        if(isValid(i+1, j) && isValid(i-1, j) && isValid(i, j-1) && isValid(i, j+1))
          return "Exist";  
      }
    }
    return "Does not exist";  
  }
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!
