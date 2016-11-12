// https://www.interviewbit.com/problems/magician-and-chocolates/
/*
Given N bags, each bag contains Ai chocolates. There is a kid and a magician. In one unit of time, kid chooses a random bag i, eats Ai chocolates, then the magician fills the ith bag with floor(Ai/2) chocolates.

Given Ai for 1 <= i <= N, find the maximum number of chocolates kid can eat in K units of time.

For example,

K = 3
N = 2
A = 6 5

Return: 14
At t = 1 kid eats 6 chocolates from bag 0, and the bag gets filled by 3 chocolates
At t = 2 kid eats 5 chocolates from bag 1, and the bag gets filled by 2 chocolates
At t = 3 kid eats 3 chocolates from bag 0, and the bag gets filled by 1 chocolate
so, total number of chocolates eaten: 6 + 5 + 3 = 14

Note: Return your answer modulo 10^9+7
*/
public class Solution {
    // Max heap
    public static final long MOD = 1000000007;
    public int nchoc(int A, ArrayList<Integer> B) {
        if(B.size() == 0)
            return 0;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(A, Collections.reverseOrder());
        for(int c : B)
            maxHeap.add(c);
        
        long cnt = 0;
        for(int i=0; i<A; ++i){
            if(maxHeap.size() == 0)
                break;
            int c = maxHeap.poll();
            cnt  = (cnt + c) % MOD;
            c = c/2;
            if(c > 0)   
                maxHeap.add(c);
        }
        return (int)(cnt % MOD);
    }
}
