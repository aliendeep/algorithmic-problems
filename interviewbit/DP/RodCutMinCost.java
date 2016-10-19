/*
There is a rod of length N lying on x-axis with its left end at x = 0 and right end at x = N. Now, there are M weak points on this rod denoted by positive integer values(all less than N) A1, A2, …, AM. You have to cut rod at all these weak points. You can perform these cuts in any order. After a cut, rod gets divided into two smaller sub-rods. Cost of making a cut is the length of the sub-rod in which you are making a cut.

Your aim is to minimise this cost. Return an array denoting the sequence in which you will make cuts. If two different sequences of cuts give same cost, return the lexicographically smallest.

Notes: 
- Sequence a1, a2 ,…, an is lexicographically smaller than b1, b2 ,…, bm, if and only if at the first i where ai and bi differ, ai < bi, or if no such i found, then n < m.
- N can be upto 109.

For example,

N = 6
A = [1, 2, 5]

If we make cuts in order [1, 2, 5], let us see what total cost would be.
For first cut, the length of rod is 6.
For second cut, the length of sub-rod in which we are making cut is 5(since we already have made a cut at 1).
For third cut, the length of sub-rod in which we are making cut is 4(since we already have made a cut at 2).
So, total cost is 6 + 5 + 4.

Cut order          | Sum of cost
(lexicographically | of each cut
 sorted)           |
___________________|_______________
[1, 2, 5]          | 6 + 5 + 4 = 15
[1, 5, 2]          | 6 + 5 + 4 = 15
[2, 1, 5]          | 6 + 2 + 4 = 12
[2, 5, 1]          | 6 + 4 + 2 = 12
[5, 1, 2]          | 6 + 5 + 4 = 15
[5, 2, 1]          | 6 + 5 + 2 = 13


So, we return [2, 1, 5].
*/

public class Solution {
    // dp[l][r] = B[r] - B[l] +  dp[l][i] +  dp[i][r] for all i
    public long getCost(int left, int right, ArrayList<Integer> B, long[][] dp, int[][] taken){
        if(right - left <= 1){
            return 0;
        }
            
        if(dp[left][right] != -1)
            return dp[left][right];
            
        long minCost = Long.MAX_VALUE;
        int minIndex = left + 1;
        for(int cutPoint=left+1; cutPoint<right; cutPoint++){
            long cost = B.get(right) - B.get(left) + 
                        getCost(left, cutPoint, B, dp, taken) +
                        getCost(cutPoint, right, B, dp, taken);
            if(cost < minCost){
                minCost = cost;
                minIndex = cutPoint;
            }
        }
        
        dp[left][right] = minCost;
        taken[left][right] = minIndex;
        return minCost;
    }
    
    public void getSolution(ArrayList<Integer> r, ArrayList<Integer> B, int[][] taken, int left, int right){
        if(right - left <= 1)
            return;
            
        int cutIndex = taken[left][right];
        r.add(B.get(cutIndex));
        
        getSolution(r, B, taken, left, cutIndex);
        getSolution(r, B, taken, cutIndex, right);
    }
    
    public ArrayList<Integer> rodCut(int A, ArrayList<Integer> B) {    
        ArrayList<Integer> r = new ArrayList<>();
        
        ArrayList<Integer> point = new ArrayList<>();
        point.add(0);
        for(int p : B){
            point.add(p);
        }
        point.add(A);
        
        int n = point.size();
        long[][] dp = new long[n+1][n+1];
        for(long[] t : dp)
            Arrays.fill(t, -1);
            
        int[][] taken = new int[n+1][n+1];
        
        getCost(0, n-1, point, dp, taken); 
        getSolution(r, point, taken, 0, n-1);
        return r;
    }
}
