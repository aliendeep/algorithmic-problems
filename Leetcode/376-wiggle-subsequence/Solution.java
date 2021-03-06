/*
A sequence of numbers is called a wiggle sequence if the differences between 
successive numbers strictly alternate between positive and negative. 
The first difference (if one exists) may be either positive or negative. 
A sequence with fewer than two elements is trivially a wiggle sequence.

For example, [1,7,4,9,2,5] is a wiggle sequence because the differences (6,-3,5,-7,3) 
are alternately positive and negative. In contrast, [1,4,7,2,5] and [1,7,4,5,5] are 
not wiggle sequences, the first because its first two differences are positive 
and the second because its last difference is zero.

Given a sequence of integers, return the length of the longest subsequence that is 
a wiggle sequence. A subsequence is obtained by deleting some number of elements 
(eventually, also zero) from the original sequence, leaving the remaining elements 
in their original order.

Examples:
Input: [1,7,4,9,2,5]
Output: 6
The entire sequence is a wiggle sequence.

Input: [1,17,5,10,13,15,10,5,16,8]
Output: 7
There are several subsequences that achieve this length. One is [1,17,10,13,10,16,8].

Input: [1,2,3,4,5,6,7,8,9]
Output: 2

Sample Input:
[1,7,4,9,2,5]
[3,3,3,2,5]
[1,17,5,10,13,15,10,5,16,8]
Sample Output:
6
3
7
*/
// dp
// O(n^2)
public class Solution {
    public int signCheck(int n){
      if(n == 0)
          return 0;
      return (n > 0) ? 1 : -1;
    }
    
    public int wiggleMaxLength(int[] nums) {
        int n = nums.length;
        if(n == 0)
            return 0;

        int[] x = new int[n];
        int[] y = new int[n];
        Arrays.fill(x, 1);
        Arrays.fill(y, 1);
        
        for(int i=1; i<n; i++){
            for(int j=0; j<i; j++){
                if(nums[j] < nums[i]){
                    x[i] = Math.max(x[i], y[j] + 1);
                }
                else if(nums[j] > nums[i]){
                    y[i] = Math.max(y[i], x[j] + 1);
                }
            }
        }
        return Math.max(x[n-1], y[n-1]);
    }
}

// O(n) space
class Solution {
    public int wiggleMaxLength(int[] nums) {
        int n = nums.length;
        if(n == 0)      return 0;
        
        int[] up = new int[n];
        int[] down = new int[n];
        Arrays.fill(up, 1);
        Arrays.fill(down, 1);

        for(int i=1; i<n; ++i){
            // increasing
            if(nums[i] > nums[i-1]){
                up[i] = down[i-1] + 1;
                down[i] = down[i-1];
            }
            // decreasing
            else if(nums[i] < nums[i-1]){
                down[i] = up[i-1] + 1;
                up[i] = up[i-1];
            }
            // Equal number
            else{
                up[i] = up[i-1];
                down[i] = down[i-1];
            }
        }
        return Math.max(up[n-1], down[n-1]);
    }
}
// Cleaner version O(1) space
class Solution2 {
    // DP:  O(n) (Space Optimized)
    // https://leetcode.com/articles/wiggle-subsequence/
    public int wiggleMaxLength(int[] nums) {
        int n = nums.length;
        if(n == 0)      return 0;
        
        // values
        int up = 1;
        int down = 1;
        for(int i=1; i<n; ++i){
            // increasing
            if(nums[i] > nums[i-1]){
                up = down + 1;
            }
            // decreasing
            else if(nums[i] < nums[i-1]){
                down = up + 1;
            }
            // equal, same as the before
        }
        return Math.max(up, down);
    }
}

// Greedy
class Solution2 {
    public int wiggleMaxLength(int[] nums) {
        int n = nums.length;
        if(n <= 1)      return n;
        
        int prevDiff = (nums[1] - nums[0]);
        int len = (prevDiff == 0) ? 1 : 2;
        for(int i=2; i<n; ++i){
            int diff = nums[i] - nums[i-1];
            if((diff > 0 && prevDiff <= 0) || (diff < 0 && prevDiff >= 0)){
                len++;
                prevDiff = diff;
            }
        }
        return len;
    }
}
