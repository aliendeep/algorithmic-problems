/*
Given an array of non-negative integers, you are initially positioned at the 
first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

For example:
A = [2,3,1,1,4], return true.

A = [3,2,1,0,4], return false.
*/

// Greedy
public class Solution {
    public boolean canJump(int[] nums) {
        int i;
        int n = nums.length;
        int reach = 0;
        for(i=0; i<n && i<=reach; ++i){
            reach = Math.max(reach, nums[i] + i);
        }
        return i == n;
    }
}

class Solution {
    // Alternative: See if we can reach the starting position
    public boolean canJump(int[] nums) {
        int n = nums.length;
        int startPos = n-1;
        for(int i=n-1; i>=0; i--){
            if(i + nums[i] >= startPos){
                startPos = i;
            }
        }
        return startPos == 0;
    }
}

class Solution2 {
    public boolean canJump(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        
        dp[0] = nums[0];
        for(int i=1; i<n; i++){
            // if this index is reachable
            if(dp[i-1] >= i){
                dp[i] = Math.max(i + nums[i], dp[i-1]);
            }
        }

        for(int i=0; i<n; i++)
            if(dp[i] >= n-1)
                return true;
        return false;
    }
}


// Stack overflow
class Solution {
    // Memoization
    public int jump(int i, int n, int[] nums, int[] dp){
        if(i >= n-1){
            return 1;
        }
        if(dp[i] != -1)
            return dp[i];
        
        int reach = i + nums[i];
        if(reach >= n-1){
            return 1;
        }

        int ans = 0;
        for(int t=i+1; t<=reach; ++t){
            ans = jump(t, n, nums, dp);
            if(ans == 1)
                break;
        }
        dp[i] = ans;
        return dp[i];
    }
    
    public boolean canJump(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        int r = jump(0, n, nums, dp);  
        return r == 1 ? true : false;
    }
}
