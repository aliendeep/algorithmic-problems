/*
Given four lists A, B, C, D of integer values, compute how many tuples (i, j, k, l) 
there are such that A[i] + B[j] + C[k] + D[l] is zero.

To make problem a bit easier, all A, B, C, D have same length of N where 0 ≤ N ≤ 500. 
All integers are in the range of -228 to 228 - 1 and the result is guaranteed to 
be at most 231 - 1.

Example:

Input:
A = [ 1, 2]
B = [-2,-1]
C = [-1, 2]
D = [ 0, 2]

Output:
2

Explanation:
The two tuples are:
1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
*/
// O(n^2) Solution
public class Solution {
    // O(n^2) Solution
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        // (sum, count)
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int k=0; k<C.length; ++k){
            for(int l=0; l<D.length; ++l){
                int sum = C[k] + D[l];
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }    
        int cnt = 0;
        for(int i=0; i<A.length; ++i){
            for(int j=0; j<B.length; ++j){
                int sum = A[i] + B[j];
                if(map.containsKey(-sum)){
                    cnt += map.get(-sum);
                }
            }
        }
        return cnt;
    }
}

