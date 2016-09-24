/*
Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.

Note: The solution set must not contain duplicate quadruplets.

For example, given array S = [1, 0, -1, 0, -2, 2], and target = 0.

A solution set is:
[
  [-1,  0, 0, 1],
  [-2, -1, 1, 2],
  [-2,  0, 0, 2]
]
*/
public class Solution {
    public List<List<Integer>> threeSum(int[] nums, int startIndex, int target){
        List<List<Integer>> r = new ArrayList<>();
        for(int i=startIndex; i<nums.length-2; i++){
            // skip searching for the same target
            if(i > startIndex && nums[i] == nums[i-1])
                continue;
            int start = i+1;
            int end =  nums.length - 1;
            while(start < end){
                int curSum = nums[start] + nums[end] + nums[i];
                if(curSum == target){
                    List<Integer>  t = new ArrayList<>();
                    t.add(nums[i]);
                    t.add(nums[start]);
                    t.add(nums[end]);
                    r.add(t);
                    start++;
                    end--;
                    while(start < end && nums[end] == nums[end+1])
                        end--;
                    while(start < end && nums[start] == nums[start-1])
                        start++;
                }
                else if(curSum < target)
                    start++;
                else
                    end--;
            }
        }
        return r;
    }
    
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>>  r = new ArrayList<>();
        int n = nums.length;
        if(n <= 3)
            return r;
        Arrays.sort(nums);
        for(int i=0; i<n-3; i++){
            if(i > 0 && nums[i-1] == nums[i])
                continue;
            // find sum of three numbers of target - nums[i]
            List<List<Integer>> t = threeSum(nums, i+1, target - nums[i]);
            for(int j=0; j<t.size(); j++){
                t.get(j).add(0, nums[i]);
                r.add(t.get(j));
            }
        }
        return r;
    }
}