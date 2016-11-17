/*

Problem Statement
    
A positive integer is called a prime if it has exactly two distinct positive integer divisors: 1 and itself. The first few primes are 2, 3, 5, 7, 11, 13, ...  A positive integer is called a palindrome if its base-10 representation reads the same forwards and backwards. Some palindromes: 2, 77, 101, 33333, 12344321.  A positive integer is called a palindromic prime if it is both a palindrome and a prime.  You are given two ints: L and R. Compute and return the number of palindromic primes between L and R, inclusive.
Definition
    
Class:
PalindromePrime
Method:
count
Parameters:
int, int
Returns:
int
Method signature:
int count(int L, int R)
(be sure your method is public)
Limits
    
Time limit (s):
2.000
Memory limit (MB):
256
Notes
-
The number 1 is not a prime number.
Constraints
-
L will be between 1 and 1,000, inclusive.
-
R will be between L and 1,000, inclusive.
Examples
0)

    
100
150
Returns: 2
This range contains only two palindromic primes: 101 and 131.
1)

    
1
9
Returns: 4
The palindromic primes in this range are 2, 3, 5, and 7.
2)

    
929
929
Returns: 1

3)

    
1
1
Returns: 0

4)

    
1
1000
Returns: 20

This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
*/
import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;

public class PalindromePrime
{
  boolean isPalindrome(int num){
    int nDigits = (int)Math.floor(Math.log10(num)) + 1;
    int div = (int)Math.pow(10, nDigits-1);
    for(int i=0; i<nDigits/2; ++i){
      if(num /div != num % 10)
        return false;
      
      num = num % div;
      num = num/10;
      div = div/100;
    }
    return true;
  }

  public int count(int L, int R)
  {
    boolean[] flag = new boolean[R+1];
    flag[1] = true;
    for(int i=3; i<=R; i+=2){
      if(flag[i] == false){
        for(int j=i*i; j<=R; j+=2*i){
          flag[j] = true;
        }
      }
    } 
    for(int i=4; i<=R; i+=2)
      flag[i] = true;
      
    List<Integer> primes = new ArrayList<>();   
    for(int i=L; i<=R; ++i){
      if(flag[i] == false)
        primes.add(i);
    }       
    
    int cnt = 0;
    for(int i=0; i<primes.size(); ++i){
      if(isPalindrome(primes.get(i))){
        //System.out.println(primes.get(i));
        cnt++;
      }
    }
    return cnt;
  }
  
<%:testing-code%>
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!