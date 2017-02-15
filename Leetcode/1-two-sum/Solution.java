/*
Given an array of integers, return indices of the two numbers such that they add 
up to a specific target.

You may assume that each input would have exactly one solution.

Example:
Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].
*/
// O(n) Solution
public class Solution {
    public int[] twoSum(int[] nums, int target) {
        // (value, index) mapping
        HashMap<Integer, Integer> hmap = new HashMap<Integer, Integer>();
        for(int i=0; i<nums.length; i++){
            hmap.put(nums[i], i);
        }
        int[] r = new int[2];
        for(int i=0; i<nums.length; i++){
            int diff = target - nums[i];
            if(hmap.containsKey(diff)){
                int index = hmap.get(diff);
                if(index != i){
                    r[0] = i;
                    r[1] = index;
                    break;
                }
            }
        }
        return r;
    }
}