/*

Problem Statement
    
Recently, Alice had to take a test. The test consisted of a sequence of true/false questions. Alice was completely unprepared for the test, so she just guessed each answer.
You are given the following inputs:
an int questions: the number of questions on the test.
an int guessed: the number of questions for which Alice guessed that the answer is "true".
an int actual: the actual number of questions for which the answer is "true".
In the test, each correct answer was worth 1 point and each incorrect answer was worth 0 points. Compute the largest possible number of points Alice could have received.
Definition
    
Class:
TestTaking
Method:
findMax
Parameters:
int, int, int
Returns:
int
Method signature:
int findMax(int questions, int guessed, int actual)
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
questions will be between 1 and 50, inclusive.
-
guessed, actual will be between 0 and questions, inclusive.
Examples
0)

    
3
1
2
Returns: 2
The test consisted of 3 statements.
Alice guessed that 1 of them is true.
Actually, there were 2 true statements on the test.
There are multiple possible outcomes of the test.
For example, it is possible that Alice wrote down the sequence of answers (false,true,false), but the correct sequence of answers is (true,false,true). In this case Alice would have received 0 points.
One possible best scenario for Alice looks as follows: Alice guessed that the answers are (false,false,true), and the correct answers are (true,false,true). In this scenario Alice would have received 0+1+1 = 2 points.
1)

    
3
2
1
Returns: 2

2)

    
5
5
0
Returns: 0
Alice guessed five times "true", but all five correct answers were "false". Thus, Alice certainly received 0 points.
3)

    
10
8
8
Returns: 10

4)

    
7
0
2
Returns: 5

This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
*/
import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;


public class TestTaking
{
  public int findMax(int q, int g, int a)
  {
    return Math.min(g, a) + Math.min(q - g, q - a);
  }  
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!