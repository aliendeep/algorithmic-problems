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

public class Solution {
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
        return recur(nums, t, 0, (int)sum/4);
    }
    
    public boolean recur(int[] nums, int[] cur, int n, int target){
        if(n == nums.length){
            if(cur[0] == target && cur[1] == target && cur[2] == target && cur[3] == target){
                return true;
            }
        }
        for(int i=0; i<4; ++i){
            if(cur[i] + nums[n] > target)
                continue;
            cur[i] += nums[n];
            if(recur(nums, cur, n+1, target))
                return true;
            cur[i] -= nums[n];
        }
        return false;
    }
}

