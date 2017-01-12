/*

Problem Statement
    
Fox Ciel just used the command "sudo" (super-user do) to gain administrative privileges in the UNIX-based operating system on her computer. She now wants to install several new programs. Each program has some dependencies: in addition to the program, the package manager has to install some libraries used by the program. The package repository contains exactly 1000 libraries. For simplicity, we will number them from 1 to 1000, inclusive. You are given the information about the dependencies of the programs Fox Ciel wants to install. More precisely, you are given the int[]s A and B, both containing the same number of elements. For each valid i, one of the programs needs all libraries that have numbers between A[i] and B[i], inclusive. Note that the programs may have overlapping dependences: multiple programs may require the same library to be installed. Of course, in such cases it is sufficient to install such a library once. Calculate and return the total number of libraries that need to be installed.
Definition
    
Class:
SuperUserDo
Method:
install
Parameters:
int[], int[]
Returns:
int
Method signature:
int install(int[] A, int[] B)
(be sure your method is public)
Limits
    
Time limit (s):
2.000
Memory limit (MB):
256
Constraints
-
A will contain between 1 and 100 elements inclusive.
-
B will contain the same number of elements as A.
-
Each element of A will be between 1 and 1000 inclusive.
-
The i-th element of B will be between A[i] and 1000 inclusive.
Examples
0)

    
{1}
{10}
Returns: 10
Only libraries 1 to 10 must be installed, so the answer is 10.
1)

    
{1,101}
{10,110}
Returns: 20

2)

    
{1}
{1000}
Returns: 1000

3)

    
{1,2,3,4,5}
{6,7,8,9,10}
Returns: 10
In this test case the dependencies have non-empty intersections. One program 
needs libraries from 1 to 6, another program needs libraries from 2 to 7, and 
so on. In order to satisfy all dependencies, the package manager will install 
libraries numbered from 1 to 10, inclusive. Hence, the total number of installed 
libraries is 10.
4)

    
{1,1}
{1,1}
Returns: 1

This problem statement is the exclusive and proprietary property of TopCoder, Inc. 
Any unauthorized use or reproduction of this information without the prior written 
consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. 
All rights reserved.
*/

import java.util.*;

public class SuperUserDo
{
  public int install(int[] A, int[] B)
  {
    int n = A.length;
    Set<Integer> set = new HashSet<>();
    for(int i=0; i<n; ++i){
      int start = A[i];
      int end = B[i];
      for(int j=start; j<=end; ++j)
        set.add(j);
    } 
    return set.size();
  }
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!
