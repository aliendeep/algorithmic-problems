/*
A frog is crossing a river. The river is divided into x units and at each unit 
there may or may not exist a stone. The frog can jump on a stone, but it must not 
jump into the water.

Given a list of stones' positions (in units) in sorted ascending order, determine if 
the frog is able to cross the river by landing on the last stone. Initially, 
the frog is on the first stone and assume the first jump must be 1 unit.

If the frog's last jump was k units, then its next jump must be either k - 1, k, 
or k + 1 units. Note that the frog can only jump in the forward direction.

Note:

The number of stones is ≥ 2 and is < 1,100.
Each stone's position will be a non-negative integer < 231.
The first stone's position is always 0.
Example 1:

[0,1,3,5,6,8,12,17]

There are a total of 8 stones.
The first stone at the 0th unit, second stone at the 1st unit,
third stone at the 3rd unit, and so on...
The last stone at the 17th unit.

Return true. The frog can jump to the last stone by jumping 
1 unit to the 2nd stone, then 2 units to the 3rd stone, then 
2 units to the 4th stone, then 3 units to the 6th stone, 
4 units to the 7th stone, and 5 units to the 8th stone.
Example 2:

[0,1,2,3,4,8,9,11]

Return false. There is no way to jump to the last stone as 
the gap between the 5th and 6th stone is too large.
*/
import java.util.*;

public class Solution {
    // Time: O(n^2) Solution
    public static boolean canCross(int[] stones) {
        // The number of stones is ≥ 2 and is < 1,100
        // if second stone is more than 1 hop away, then return false
        if(stones[1] > 1)
            return false;
        int n = stones.length;
        if(n == 2)      return true;

        Set[] prevJumps = new Set[n];
        for(int i=0; i<n; ++i)
            prevJumps[i] = new HashSet<>();
        
        prevJumps[1].add(1);
        for(int i=2; i<n; ++i){
            for(int j=1; j<i; ++j){
                if(prevJumps[j].isEmpty())
                    continue;
                // See if it's possible to reach i from j
                int jump = stones[i] - stones[j];
                if(prevJumps[j].contains(jump) || prevJumps[j].contains(jump + 1) || prevJumps[j].contains(jump - 1)){                
                    prevJumps[i].add(jump);
                    // last stone
                    if(i == n-1)        return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args){
        int[] stones1 = {0,1,3,5,6,8,12,17};
        System.out.println(canCross(stones1));

        int[] stones = {0,1,2,3,4,8,9,11};
        System.out.println(canCross(stones));
    }    
}

// Memoization
class Solution {
    public int canCross(int[] stones, int[][] dp, int cur, int k) {
        if(cur >= stones.length)
            return 0;
            
        if(cur == stones.length-1){
            dp[cur][k] = 1;
            return dp[cur][k];
        }

        if(dp[cur][k] != -1)
            return dp[cur][k];
        
        // the frog can only jump in the forward direction
        int r = 0;
        for(int i=cur + 1; i<stones.length; ++i){
            int distance = stones[i] - stones[cur];
            if(distance < k - 1)
                continue;
            if(distance > k + 1){
                break;
            }
            if(canCross(stones, dp, i, distance) == 1){
                r = 1;
                break;
            }
        }
        dp[cur][k] = r;
        return r;
    }

    public boolean canCross(int[] stones) {
        int n = stones.length;
        int[][] dp = new int[n+1][n+1];
        for(int[] t : dp)
            Arrays.fill(t, -1);
        return canCross(stones, dp, 0, 0) == 1 ? true : false;
    }
}

// O(n^2logn)
public class Solution {
    int n;
    public int canCross(int[] stones, int[][] dp, int index, int k) {
        if(index == n-1)
            return 1;
        if(dp[index][k] != -1)
            return dp[index][k];
        
        // the frog can only jump in the forward direction
        dp[index][k] = 0;
        int pos = Arrays.binarySearch(stones, index+1, n, stones[index] + k);
        if(pos >= 0 && canCross(stones, dp, pos, k) == 1){
            dp[index][k] = 1;
            return 1;
        }
        pos = Arrays.binarySearch(stones, index+1, n, stones[index] + k + 1);
        if(pos >= 0 && canCross(stones, dp, pos, k + 1) == 1){
            dp[index][k] = 1;
            return 1;
        }
        pos = Arrays.binarySearch(stones, index+1, n, stones[index] + k - 1);
        if(pos >= 0 && canCross(stones, dp, pos, k - 1) == 1){
            dp[index][k] = 1;
            return 1;
        }
        return dp[index][k];
    }

    public boolean canCross(int[] stones) {
        n = stones.length;
        int[][] dp = new int[n+1][n+1];
        for(int[] t : dp)
            Arrays.fill(t, -1);
        return canCross(stones, dp, 0, 0) == 1;
    }
}
