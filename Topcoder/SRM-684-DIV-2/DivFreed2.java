/*

Problem Statement
    
Hero likes some arrays. The arrays he likes are the arrays that have all of the following properties:
The length of the array is n.
Each element is an integer between 1 and k, inclusive.
Whenever A and B are two consecutive elements of the array (in this order), we have (A <= B) or (A mod B != 0).
For example, suppose n=4 and k=7. Hero will like the array {1,7,7,2} because it has the right length, all elements are in the correct range, 1 <= 7, 7 <= 7, and 7 mod 2 != 0. Hero will not like the array {4,4,4,2}.
You are given the ints n and k. Let X be the number of different arrays Hero likes. Compute and return the value (X mod 1,000,000,007).
Definition
    
Class:
DivFreed2
Method:
count
Parameters:
int, int
Returns:
int
Method signature:
int count(int n, int k)
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
n will be between 1 and 10, inclusive.
-
k will be between 1 and 100,000, inclusive.
Examples
0)

    
2
2
Returns: 3
The three arrays Hero likes are {1,1}, {1,2}, and {2,2}.
1)

    
9
1
Returns: 1
The only array Hero likes is {1,1,1,1,1,1,1,1,1}.
2)

    
3
3
Returns: 15

3)

    
1
107
Returns: 107

4)

    
2
10
Returns: 83

5)

    
2
1234
Returns: 1515011

6)

    
3
8
Returns: 326

7)

    
10
100000
Returns: 526882214

This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
*/
import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;


public class DivFreed2
{
  public final static int MOD = 1000000007;
  
  public int count(int n, int k)
  {
    long[][] dp = new long[n+1][k+1];
    for(int i=1; i<=k; ++i){
      dp[1][i] = 1;
    }   
    
    // 1 indexing
    for(int pos=2; pos<=n; ++pos){
      // count all  ways    
      long cnt = 0;
      for(int i=1; i<=k; ++i){
        cnt = (cnt + dp[pos-1][i]) % MOD;
      }
      
      for(int i=1; i<=k; ++i){
        dp[pos][i] = (int)cnt;
        for(int j=2*i; j<=k; j+=i){
          dp[pos][i] = (dp[pos][i] - dp[pos-1][j] + MOD) % MOD; 
        }
      }         
    } 
    long result = 0;
    for(int i=1; i<=k; ++i){
      result = (result + dp[n][i]) % MOD;
    }
    return (int)result;
  }  
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!