/*

Problem Statement
    
Cucumber Boy is trying to be elected to the city council. In order to achieve that, he needs to show that he has a lot of support in some part of the city.  The city is divided into N districts. These are numbered from 0 to N-1. For each i, there are A[i] voters in district i, and out of them B[i] support Cucumber Boy.  Cucumber Boy must choose a part of the city: K or more districts with consecutive numbers. Cucumber Boy wants to maximize the proportion of votes in his favor. Formally, Cucumber Boy wants to maximize the value X/Y, where Y is the total number of voters in the chosen districts and X is the number of Cucumber Boy's supporters among them.  You are given the int[]s A and B with N elements each. You are also given the int K. Compute and return a real number: the largest value X/Y that can be achieved.
Definition
    
Class:
GerrymanderEasy
Method:
getmax
Parameters:
int[], int[], int
Returns:
double
Method signature:
double getmax(int[] A, int[] B, int K)
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
The returned value must have an absolute or relative error less than 1e-9.
Constraints
-
A will contain between 1 and 1,000 elements, inclusive.
-
A and B will contain the same number of elements.
-
For each i, A[i] will be between 1 and 10,000, inclusive.
-
For each i, B[i] will be between 0 and A[i], inclusive.
-
K will be between 1 and the number of elements in A, inclusive.
Examples
0)

    
{5,1,2,7}
{4,0,2,2}
2
Returns: 0.75
The optimal solution is to choose districts 0, 1, and 2. The total number of voters will be Y = 5+1+2 = 8. The total number of Cucumber Boy's supporters will be X = 4+0+2 = 6. Hence, the proportion of votes in Cucumber Boy's favor is X/Y = 0.75. Note that the chosen districts must have consecutive numbers. We are not allowed to choose only districts 0 and 2.
1)

    
{12,34,56,78,90}
{1,1,1,1,1}
1
Returns: 0.08333333333333333

2)

    
{10000,10000,10000,10000,10000,10000,10000,10000,10000,10000}
{3,1,4,1,5,9,2,6,5,3}
5
Returns: 5.4E-4

3)

    
{123,4,46,88,22,34,564,87,56,311,886}
{0,0,0,0,0,0,0,0,0,0,0}
1
Returns: 0.0
Sometimes the answer will be 0.
This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
*/
import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;

public class GerrymanderEasy
{
  public double getmax(int[] A, int[] B, int K)
  {
    double result = 0;
    int n = A.length;
    for(int i=0; i+K<=n; ++i){
      double sumA = 0, sumB = 0;
      for(int j=i; j<n; ++j){
        sumA += A[j];
        sumB += B[j];
        if(j - i + 1 < K)
          continue;
        result = Math.max(result, sumB*1.0/sumA);
      }
    }
    return result;
  }
  
<%:testing-code%>
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!