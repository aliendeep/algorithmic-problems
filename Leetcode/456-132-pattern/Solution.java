/*
Given a sequence of n integers a1, a2, ..., an, a 132 pattern is a subsequence ai, aj, ak such that i < j < k and ai < ak < aj. Design an algorithm that takes a list of n numbers as input and checks whether there is a 132 pattern in the list.

Note: n will be less than 15,000.

Example 1:
Input: [1, 2, 3, 4]

Output: False

Explanation: There is no 132 pattern in the sequence.
Example 2:
Input: [3, 1, 4, 2]

Output: True

Explanation: There is a 132 pattern in the sequence: [1, 4, 2].
Example 3:
Input: [-1, 3, 2, 0]

Output: True

Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].
*/
public class Solution {
    public boolean find132pattern(int[] nums) {
        int n = nums.length;
        if(n <= 2)   return false;
        int[] min = new int[n];
        min[0] = nums[0];
        for(int i=1; i<n; ++i){
            min[i] = Math.min(min[i-1], nums[i]);
        }
    
        TreeSet<Integer> set = new TreeSet<>();
        set.add(nums[n-1]);
    
        for(int i=n-2; i>0; --i){
            if(min[i-1] < nums[i]){
                Set<Integer> rightSubSet = set.subSet(min[i-1] + 1, nums[i]);
                if(rightSubSet.size() > 0)
                    return true;
            }
            set.add(nums[i]);
        }
        return false;
    }
}