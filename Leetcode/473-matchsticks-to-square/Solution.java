/*
Remember the story of Little Match Girl? By now, you know exactly what matchsticks 
the little match girl has, please find out a way you can make one square by using 
up all those matchsticks. You should not break any stick, but you can link them up, 
and each matchstick must be used exactly one time.

Your input will be several matchsticks the girl has, represented with their stick 
length. Your output will either be true or false, to represent whether you can 
save this little girl or not.

Example 1:
Input: [1,1,2,2,2]
Output: true

Explanation: You can form a square with length 2, one side of the square came 
two sticks with length 1.
Example 2:
Input: [3,3,3,3,4]
Output: false

Explanation: You cannot find a way to form a square with all the matchsticks.
Note:
The length sum of the given matchsticks is in the range of 0 to 10^9.
The length of the given matchstick array will not exceed 15.
*/

// Bit Mask Dynamic Programming
public class Solution {
    int sideLength; 
    int[] sum;
    int[][] dp;
    
    // return 1 if it's possible to create nSides from the state that sums to sideLength 
    public int isPossibleSquare(int state, int nSides){
        if(state == 0)     return nSides == 0 ? 1 : 0;                        
        if(dp[state][nSides] != -1)
            return dp[state][nSides];
        
        for(int subMask = 0; subMask<=state; ++subMask){
            // Check if the subMask contains 1 other than the positions of set bits of state
            if((subMask & ~state) != 0)     continue;    
            
            // if the sum of this submask is not equal to the desired side length, then continue
            if(sum[subMask] != sideLength)
                continue;
            
            // if it's possible to create remaining nSides-1 of sidelength, then return true
            if(isPossibleSquare(state ^ subMask, nSides - 1) == 1){
                dp[state][nSides] = 1;
                return 1;
            }
        }
        dp[state][nSides] = 0;
        return 0;
    }
    
    public boolean makesquare(int[] nums) {
        int n = nums.length;
        int tot = 0;
        for(int a : nums){
            tot += a;
        }
        if(tot % 4 != 0)    return false;
        sideLength = tot/4;
        int nstates = (1<<n);
            
        sum = new int[nstates];
        // Precalculate sum of all bit mask state
        for(int mask = 0; mask<nstates; ++mask){
            int t = 0;
            for(int i=0; i<n; ++i){
                if((mask & (1<<i)) == 0)    continue;
                // if ith bit is one, add nums[i] to the sum
                t += nums[i]; 
            }            
            sum[mask] = t;
        }

        dp = new int[nstates][5];
        for(int[] t : dp)
            Arrays.fill(t, -1);

        return isPossibleSquare((1<<n)-1, 4) == 1;
    }
}


// Backtracking
class Solution2 {
    int sideLength;
    public boolean makesquare(int[] nums) {
        if(nums.length == 0)
            return false;
        long sum = 0;
        for(int n : nums){
            sum += n;
        }
        if(sum % 4 != 0)
            return false;
        
        int[] t = new int[4];
        sideLength = (int)sum/4;
        return recur(nums, t, 0);
    }
    
    public boolean recur(int[] nums, int[] cur, int n){
        if(n == nums.length){
            if(cur[0] == sideLength && cur[1] == sideLength && cur[2] == sideLength && cur[3] == sideLength){
                return true;
            }
            return false;
        }
        for(int i=0; i<4; ++i){
            if(cur[i] + nums[n] > sideLength)
                continue;
                
            cur[i] += nums[n];

            if(recur(nums, cur, n+1))
                return true;

            cur[i] -= nums[n];
        }
        return false;
    }
}
