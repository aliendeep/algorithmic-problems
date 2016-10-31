/*
http://www.geeksforgeeks.org/how-to-print-maximum-number-of-a-using-given-four-keys/

Imagine you have a special keyboard with the following keys: 
Key 1:  Prints 'A' on screen
Key 2: (Ctrl-A): Select screen
Key 3: (Ctrl-C): Copy selection to buffer
Key 4: (Ctrl-V): Print buffer on screen appending it
                 after what has already been printed. 

If you can only press the keyboard for N times (with the above four
keys), write a program to produce maximum numbers of A's. That is to
say, the input parameter is N (No. of keys that you can press), the 
output is M (No. of As that you can produce).
Examples:

Input:  N = 3
Output: 3
We can at most get 3 A's on screen by pressing 
following key sequence.
A, A, A

Input:  N = 7
Output: 9
We can at most get 9 A's on screen by pressing 
following key sequence.
A, A, A, Ctrl A, Ctrl C, Ctrl V, Ctrl V

Input:  N = 11
Output: 27
We can at most get 27 A's on screen by pressing 
following key sequence.
A, A, A, Ctrl A, Ctrl C, Ctrl V, Ctrl V, Ctrl A, 
Ctrl C, Ctrl V, Ctrl V
*/

// Observation: The optimal sequence ends with last three keystrokes - Ctrl C, Ctrl V, Ctrl V
import java.util.*;

class MaximumNumberAFourKeys{
  // Memoization
  public int maxNumberOfARecur(int n, int[] dp){
    if(n <= 6)        return n;
    if(dp[n] != -1)   return dp[n];

    int maxLen= 0;
    for(int b=n-3; b>=1; b--){
      // If the breakpoint is at b, then the length of the string n - b - 1
      maxLen = Math.max(maxLen, (n - b - 1)*maxNumberOfARecur(b, dp));
    }
    dp[n] = maxLen;
    return dp[n];
  }

  public int maxNumberOfA(int n){
    int[] dp = new int[n+1];
    Arrays.fill(dp, -1);
    return maxNumberOfARecur(n, dp);
  }

  // Table
  public int maxNumberOfaDP(int N){
    if(N <= 6)    return N;
    int[] dp = new int[N+1];

    // init
    for(int n=1; n<=6; ++n){
      dp[n] = n;
    }

    for(int n=7; n<=N; ++n){
      for(int b=n-3; b>=1; b--){
        dp[n] = Math.max(dp[n], (n-b-1)*dp[b]);
      }
    }
    return dp[N];    
  }

  public static void main(String[] args){
    MaximumNumberAFourKeys s = new MaximumNumberAFourKeys();
    System.out.println(s.maxNumberOfaDP(20));
  }
}