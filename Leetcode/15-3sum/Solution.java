/*
Given an array S of n integers, are there elements a, b, c in S such that 
a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Note: The solution set must not contain duplicate triplets.

For example, given array S = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]
*/

public class Solution {
    // Sorted Array
    // Use the two sum function
    public void twoSum(int[] nums, int target, int start, int end, List<List<Integer>> r){
        int i = start, j = end;
        while(i < j){
            int sum = nums[i] + nums[j];
            if(sum == target){
                List<Integer> t = new ArrayList<>();
                t.add(-target);
                t.add(nums[i]);
                t.add(nums[j]);
                r.add(t);
                i++;
                j--;
                // make sure all triplets are unique
                while(i < j && nums[j] == nums[j+1])
                    j--;
                while(i < j && nums[i] == nums[i-1])
                    i++;
            }
            else if(sum < target){
                i++;
            }
            else{
                j--;
            }
        }
    }
    
    public List<List<Integer>> threeSum(int[] nums){
        Arrays.sort(nums);
        List<List<Integer>> r = new ArrayList<>();
        for(int i=0; i<nums.length-2; i++){
            // skip searching for the same target
            if(i > 0 && nums[i] == nums[i-1])
                continue;
            int target = -nums[i];
            int start = i + 1;
            int end = nums.length-1;
            twoSum(nums, target, start, end, r);
        }
        return r;
    }
}
