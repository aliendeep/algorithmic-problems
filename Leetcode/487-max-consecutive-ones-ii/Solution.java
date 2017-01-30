/*
Given a binary array, find the maximum number of consecutive 1s in this array if 
you can flip at most one 0.

Example 1:
Input: [1,0,1,1,0]
Output: 4
Explanation: Flip the first zero will get the the maximum number of consecutive 1s.
    After flipping, the maximum number of consecutive 1s is 4.
Note:

The input array will only contain 0 and 1.
The length of input array is a positive integer and will not exceed 10,000

Follow up:
What if the input numbers come in one by one as an infinite stream? In other words, 
you can't store all numbers coming from the stream as it's too large to hold in memory. 
Could you solve it efficiently?
*/
// Left and right pointer
public class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int n = nums.length;
        if(n == 0)  return 0;
        int[] cl = new int[n];
        cl[0] = nums[0];
        int max = cl[0];
        for(int i=1; i<n; ++i){
            if(nums[i] == 0){
                cl[i] = 0;
            }
            else{
                cl[i] = cl[i-1] + 1;    
            }
            max = Math.max(max, cl[i]);            
        }
        int[] cr = new int[n];
        cr[n-1] = nums[n-1];
        max = Math.max(max, cr[n-1]);
        for(int i=n-2; i>=0; --i){
            if(nums[i] == 0){
                cr[i] = 0;
            }
            else{
                cr[i] = cr[i+1] + 1;    
            }
            max = Math.max(max, cr[i]);            
        }
        for(int i=0; i<n; ++i){
            int l = 0;
            if(i-1 >= 0)
                l = cl[i-1];
            int r = 0;
            if(i+1 < n)
                r = cr[i+1];
            if(nums[i] == 0){
                max = Math.max(max, l + 1 + r);
            }
            else
                max = Math.max(max, l + r);            
        }
        return max;
    }
}

// Dynamic Programming
class Solution2 {
    public int findMaxConsecutiveOnes(int[] nums) {
        int n = nums.length;
        // dp[i][0]: Maximum left length of consecutive ones including ith element using 0 flip 
        // dp[i][0]: Maximum left length of consecutive ones including ith element using 1 flip 
        int[][] dp = new int[n+1][2];
        // 1 indexing
        dp[0][0] = 0;
        dp[0][1] = 0;
        int maxLength = 0;
        for(int i=1; i<=n; ++i){
            int bit = nums[i-1];
            if(bit == 1){
                // 0 flip
                dp[i][0] = 1 + dp[i-1][0];
                dp[i][1]= 1 + dp[i-1][1];
            }
            else{
                // Using 0 flip
                dp[i][0] = 0; // as the current number is 0
                // flip the current bit
                dp[i][1] = 1 + dp[i-1][0];
            }
            maxLength = Math.max(maxLength, dp[i][0]);
            maxLength = Math.max(maxLength, dp[i][1]);
       }
        return maxLength;
    }
}

// Follow up: if you're allowed to flip k bits
class Solution3 {
    public int findKFlipMaxConsecutiveOnes(int[] nums, int k) {
        int n = nums.length;
        int[][] dp = new int[n+1][k+1];
        for(int j=0; j<=k; ++j){
            dp[0][j] = 0; 
        }
        int maxLength = 0;
        for(int i=1; i<=n; ++i){
            int bit = nums[i-1];
            for(int j=0; j<=k; ++j){
                if(bit == 1){
                    dp[i][j] = 1 + dp[i-1][j];
                }
                else{
                    // current bit is 0
                    // need flips
                    // no flips allowed
                    if(j == 0){
                        dp[i][j] = 0;                    
                    }
                    else{
                        // flip current bit
                        dp[i][j] = 1 + dp[i-1][j-1];
                    }
                }
                maxLength = Math.max(maxLength, dp[i][j]);
            }
        }
        return maxLength;
    }
    
    public int findMaxConsecutiveOnes(int[] nums) {
        return findKFlipMaxConsecutiveOnes(nums, 1);
    }
}

// Memoization
class Solution4 {
    int[][] dp;
    public int getMaxLength(int[] nums, int i, int state){
        if(i == 0)  return 0;
        if(dp[i][state] != -1)
            return dp[i][state];
        
        if(nums[i-1] == 1){
            dp[i][state] = 1 + getMaxLength(nums, i-1, state);
        }
        else{
            // current bit is 0       no flip : flip
            dp[i][state] = (state == 0) ? 0 : 1 + getMaxLength(nums, i-1, 0);
        }
        return dp[i][state];
    }
    
    public int findMaxConsecutiveOnes(int[] nums) {
        int n = nums.length;
        dp = new int[n+1][2];
        for(int[] t : dp)
            Arrays.fill(t, -1);
            
        int maxLength = 0;
        for(int i=1; i<=n; i++){
            maxLength = Math.max(maxLength, getMaxLength(nums, i, 0));
            maxLength = Math.max(maxLength, getMaxLength(nums, i, 1));
        }
        return maxLength;
    }
}

// Two pointer approach
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int maxLen = 0, k = 1;
        int start = 0, end;
        int zero = 0;
        for(end=0; end<nums.length; ++end){
            if(nums[end] == 0)
                zero++;
            while(zero > k){
                if(nums[start] == 0){
                    zero--;
                }
                start++;
            }
            maxLen = Math.max(maxLen, end - start + 1);
        }
        return maxLen;
    }
}

public class Solution {
    // Followup : Two pointer
    public int findMaxConsecutiveOnes(int[] nums) {
        int maxLen = 0, k = 1;
        int start = 0, end;
        Queue<Integer> qIndices = new LinkedList<>();
        // Store indices of zero within window [l, h]
        for(end=0; end<nums.length; ++end){
            if(nums[end] == 0)
                qIndices.add(end);
            while(qIndices.size() > k){
                start = qIndices.remove() + 1;
            }
            maxLen = Math.max(maxLen, end - start + 1);
        }
        return maxLen;
    }
}
