/*
https://www.interviewbit.com/problems/maximum-absolute-difference/
You are given an array of N integers, A1, A2 ,…, AN. Return maximum value of f(i, j) for all 1 ≤ i, j ≤ N.
f(i, j) is defined as |A[i] - A[j]| + |i - j|, where |x| denotes absolute value of x.

For example,

A=[1, 3, -1]

f(1, 1) = f(2, 2) = f(3, 3) = 0
f(1, 2) = f(2, 1) = |1 - 3| + |1 - 2| = 3
f(1, 3) = f(3, 1) = |1 - (-1)| + |1 - 3| = 4
f(2, 3) = f(3, 2) = |3 - (-1)| + |2 - 3| = 5

So, we return 5.
*/
/*
Solution Approach:
f(i, j) = |A[i] - A[j]| + |i - j| can be written in 4 ways 
Case 1: (A[i] + i) - (A[j] + j)  (A[i] and A[j] positive)
Case 2: -(A[i] - i) + (A[j] - j)  (-A[i] and -A[j] negative)
Case 3: (A[i] - i) - (A[j] - j)   ( i negative and j positive)
Case 4: (-A[i] - i) + (A[j] + j) = -(A[i] + i) + (A[j] + j)    (i negative and A[i] negative)
Note that case 1 and 4 are equivalent and so are case 2 and 3.

We can construct two arrays with values: A[i] + i and A[i] - i. Then, for above 2 cases, we find the maximum value possible. 
For that, we just have to store minimum and maximum values of expressions A[i] + i and A[i] - i for all i.
*/
class MaximumAbsoluteDifference{
    public int maxArr(ArrayList<Integer> A) {
        int n = A.size();
        int max1 = Integer.MIN_VALUE, max2 =Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        for(int i=0; i<n; ++i){
            int v = A.get(i);
            max1 = Math.max(max1, v + i);
            max2 = Math.max(max2, v - i);
            min1 = Math.min(min1, v + i);
            min2 = Math.min(min2, v - i);
        }
        return Math.max(max1 - min1, max2 - min2);
    }
}