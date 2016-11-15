/*
Problem Statement
    
In one organization they have n different committees. The organization has a very large number of employees. Each employee is a member of each committee.
Each committee has a quorum: the smallest number of members that have to be present to have an official meeting. You are given a int[] arr with n elements. Each element of arr is the quorum of one committee.
You are also given an int k. Yesterday, k different committees had an official meeting, all at the same time. Obviously, each person attended at most one of those meetings. Compute and return the smallest possible number of people who attended a meeting yesterday.
Definition
    
Class:
Quorum
Method:
count
Parameters:
int[], int
Returns:
int
Method signature:
int count(int[] arr, int k)
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
The value of n is not given explicitly. Instead, you can determine it as the number of elements in arr.
Constraints
-
arr will contain between 1 and 50 elements, inclusive.
-
Each element of arr will be between 1 and 50.
-
k will be between 1 and the number of elements of arr, inclusive.
Examples
0)

    
{5,2,3}
1
Returns: 2
There are three committees. The first committee requires 5 members to start a meeting, the second requires 2, and the third requires 3 members. As k=1, there was one meeting yesterday. The smallest possible solution is that it was a meeting of the second committee and that exactly 2 employees attended that meeting.
1)

    
{1,1,1,1,1}
5
Returns: 5
All five committees had a meeting yesterday. We need at least one person per meeting. No person can attend more than one meeting. Hence, there must have been at least 5 different people.
2)

    
{50,2,9,49,38}
3
Returns: 49

3)

    
{20,19,18,17,16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1}
14
Returns: 105

This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
*/
import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;


public class Quorum{
  public int count(int[] arr, int k){
    int n = arr.length;
    Arrays.sort(arr);
    int cnt = 0;
    for(int i=0; i<k; ++i){
      cnt += arr[i];
    } 
    return cnt;
  }  
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!