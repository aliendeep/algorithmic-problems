/*
Write a program to find the n-th ugly number.

Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. 
For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.

Note that 1 is typically treated as an ugly number.
Hint:

- The naive approach is to call isUgly for every number until you reach the nth one. 
- Most numbers are not ugly. Try to focus your effort on generating only the ugly ones.
- An ugly number must be multiplied by either 2, 3, or 5 from a smaller ugly number.
- The key is how to maintain the order of the ugly numbers. Try a similar approach of 
merging from three sorted lists: L1, L2, and L3.
- Assume you have Uk, the kth ugly number. Then Uk+1 must be Min(L1 * 2, L2 * 3, L3 * 5).
*/

public class Solution {
    public int nthUglyNumber(int n) {
        if(n <= 1)   return 1;
        Queue<Long> q2 = new LinkedList<Long>();
        Queue<Long> q3 = new LinkedList<Long>();
        Queue<Long> q5 = new LinkedList<Long>();
        q2.add(1L);
        long val = 1;
        for(int i=1; i<=n; i++){
            long v2 = q2.size() > 0 ? q2.peek() : Integer.MAX_VALUE;
            long v3 = q3.size() > 0 ? q3.peek() : Integer.MAX_VALUE;
            long v5 = q5.size() > 0 ? q5.peek() : Integer.MAX_VALUE;
            val = Math.min(v2, Math.min(v3, v5));
            if(val == v2){
                // remove : Retrieves and removes the head (first element) of this list.
                q2.remove();
                q2.add(val*2);
                q3.add(val*3);
            }
            else if(val == v3){
                q3.remove();
                q3.add(val*3);
            }
            else{
                q5.remove();
            }
            q5.add(val*5);
        }
        return (int)val;
    }
}

// Alternative
// O(n) Solution
class Solution2 {
    public int nthUglyNumber(int n) {
        List<Integer> r = new ArrayList<>();
        r.add(1);
        int i = 0, j = 0, k = 0;
        while(r.size() < n){
            int y = Math.min(r.get(i)*2, r.get(j)*3);
            y = Math.min(y, r.get(k)*5);
            r.add(y);
            if(y == r.get(i)*2) ++i; 
            if(y == r.get(j)*3) ++j; 
            if(y == r.get(k)*5) ++k; 
        }
        return r.get(n-1);
    }
}

class Solution3 {
    public int nthUglyNumber(int n) {
        int[] ugly = new int[n];
        int t2 = 0, t3 = 0, t5 = 0;
        ugly[0] = 1;
        for(int i=1; i<n; ++i){
            int x = ugly[t2]*2;
            int y = ugly[t3]*3;
            int z = ugly[t5]*5;
            ugly[i] = Math.min(x, Math.min(y, z));
            if(ugly[i] == x)
                t2++;   
            if(ugly[i] == y)
                t3++;   
            if(ugly[i] == z)
                t5++;   
        }
        return ugly[n-1];
    }
}