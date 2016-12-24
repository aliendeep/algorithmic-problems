/*
Given a non-empty integer array of size n, find the minimum number of moves 
required to make all array elements equal, where a move is incrementing 
n - 1 elements by 1.

Example:

Input:
[1,2,3]

Output:
3

Explanation:
Only three moves are needed (remember each move increments two elements):

[1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
*/

// Add 1 to n - 1 elements is the same = Subtract 1 from one element
public class Solution {
    public int minMoves(int[] nums) {
        int minValue = nums[0];
        // Find the min
        for(int i=0; i<nums.length; ++i){
            if(nums[i] < minValue){
                minValue = nums[i];
            }
        }
        
        int cnt = 0;
        for(int i=0; i<nums.length; ++i){
            cnt += nums[i] - minValue;
        }
        return cnt;
    }
}
