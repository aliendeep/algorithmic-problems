/*
By now, you are given a secret signature consisting of character 'D' and 'I'. 'D' 
represents a decreasing relationship between two numbers, 'I' represents an 
increasing relationship between two numbers. And our secret signature was 
constructed by a special integer array, which contains uniquely all the different 
number from 1 to n (n is the length of the secret signature plus 1). For example, 
the secret signature "DI" can be constructed by array [2,1,3] or [3,1,2], but won't 
be constructed by array [3,2,4] or [2,1,3,4], which are both illegal constructing 
special string that can't represent the "DI" secret signature.

On the other hand, now your job is to find the lexicographically smallest 
permutation of [1, 2, ... n] could refer to the given secret signature in the input.

Example 1:
Input: "I"
Output: [1,2]
Explanation: [1,2] is the only legal initial spectial string can construct secret 
signature "I", where the number 1 and 2 construct an increasing relationship.
Example 2:
Input: "DI"
Output: [2,1,3]
Explanation: Both [2,1,3] and [3,1,2] can construct the secret signature "DI", 
but since we want to find the one with the smallest lexicographical permutation, 
you need to output [2,1,3]
Note:
- The input string will only contain the character 'D' and 'I'.
- The length of input string is a positive integer and will not exceed 10,000

Sample Input:
"DDIIDI"
"DDIIDI"
"DI"
"I"
"DDIIDIIIDDIIDI"
""
"D"
"DDD"
"IIII"
"II"
"IDI"
"DID"
Sample Output:
[3,2,1,4,6,5,7]
[3,2,1,4,6,5,7]
[2,1,3]
[1,2]
[3,2,1,4,6,5,7,8,11,10,9,12,14,13,15]
[1]
[2,1]
[4,3,2,1]
[1,2,3,4,5]
[1,2,3]
[1,3,2,4]
[2,1,4,3]
*/

// O(n^2) Solution
public class Solution {
    public void swap(int[] A, int i, int j){
        int t = A[i];
        A[i] = A[j];
        A[j] = t;
    }
    public int[] findPermutation(String s) {
        int n = s.length();
        int[] A = new int[n+1];
        for(int i=0; i<n+1; ++i){
            A[i] = i+1;
        }
        
        for(int i=0; i<n; ++i){
            if(s.charAt(i) == 'D'){
                swap(A, i, i+1);
                int j = i-1;
                while(j >= 0){
                    if(s.charAt(j) == 'D' && A[j] > A[j+1]){
                        break;
                    }
                    if(s.charAt(j) == 'I' && A[j] < A[j+1]){
                        break;
                    }
                    swap(A, j, j+1);
                    j--;
                }
            }
        }
        return A;
    }
}