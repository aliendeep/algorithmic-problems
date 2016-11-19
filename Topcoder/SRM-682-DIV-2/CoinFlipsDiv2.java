/*

Problem Statement
    
You have n coins, labeled 0 through n-1. Each of the coins shows either heads or tails. You are given the states of all coins in the String state with n characters. For each i, state[i] is 'H' if coin i shows heads, or 'T' if it shows tails.
The coins are laid out in a row, ordered from coin 0 to coin n-1. A coin is called interesting if it differs from at least one of its neighbors. (For example, a coin that shows heads is interesting if at least one of its neighbors shows tails.) Return the number of interesting coins.
Definition
    
Class:
CoinFlipsDiv2
Method:
countCoins
Parameters:
String
Returns:
int
Method signature:
int countCoins(String state)
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
The value of n is not given explicitly. Instead, you can determine it as the number of characters in state.
Constraints
-
state will have between 1 and 50 elements, inclusive.
-
Each character of state will be either 'H' or 'T'.
Examples
0)

    
"HT"
Returns: 2
Coin 0 is interesting because it shows heads and its only neighbor shows tails. Similarly, coin 1 is interesting because it shows tails and its only neighbor shows heads. Thus, the answer is 2.
1)

    
"T"
Returns: 0
In this test case there is a single coin. It has no neighbors, and therefore it has no different neighbors. This means that the coin is not interesting.
2)

    
"HHH"
Returns: 0
None of these coins are interesting, because each of them is only adjacent to coins that show the same side.
3)

    
"HHTHHH"
Returns: 3
Here, the three interesting coins are coins 1, 2, and 3. (Remember that the indices are 0-based.)
4)

    
"HHHTTTHHHTTTHTHTH"
Returns: 12

This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
*/
import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;


public class CoinFlipsDiv2
{
  public int countCoins(String state)
  {
    int n = state.length();
    boolean[] cnt = new boolean[n];
    for(int i=0; i<n; i++){
      char c = state.charAt(i);
      if(i < n-1 && state.charAt(i) != state.charAt(i+1)){
        cnt[i] = true;
        cnt[i+1] = true;  
      } 
    }
    int sum = 0;
    for(int i=0; i<n; ++i)
      if(cnt[i])
        sum++;
    return sum;
  }
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!