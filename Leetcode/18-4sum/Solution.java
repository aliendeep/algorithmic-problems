/*
Given an array S of n integers, are there elements a, b, c, and d in S such that 
a + b + c + d = target? Find all unique quadruplets in the array which gives the 
sum of target.

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

class Solution2 {
    int[] nums;
    List<List<Integer>> result;
    
    public List<List<Integer>> fourSum(int[] a, int target) {
        nums = a;
        result = new ArrayList<>();
        int n = nums.length;
        if(n < 4)   
            return result;
        Arrays.sort(nums);
        int min = nums[0];
        int max = nums[n-1];
        if(4*min > target || 4*max < target)
            return result;
        
        // Choose first number
        for(int i=0; i<n-3; ++i){
            int x = nums[i];
            // avoid duplicate
            if(i > 0 && nums[i-1] == nums[i])
                continue;
            // x is too small
            if(x + 3*max < target)
                continue;
            // x is too large
            if(x*4 > target)
                break;
            // Same number
            if(4*x == target){
                if(nums[i+3] == x)
                    result.add(Arrays.asList(x, x, x, x));
                break;
            }
            threeSum(x, target - x, i+1, n);
        }
        return result;
    }
    
    public void threeSum(int x, int target, int l, int h){
        int min = nums[l];
        int max = nums[h-1];
        if(3*min > target || 3*max < target)
            return;

        // Choose second number
        for(int i=l; i<h-2; ++i){
            int y = nums[i];
            // avoid duplicate
            if(i > l && nums[i-1] == nums[i])
                continue;
            // y too small
            if(y + 2*max < target)
                continue;
            // y too large
            if(3*y > target)
                break;
            if(3*y == target){
                if(nums[i+2] == y)
                    result.add(Arrays.asList(x, y, y, y));
                break;
            }
            twoSum(x, y, target - y, i+1, h);
        }
    }

    public void twoSum(int x, int y, int target, int l, int h){
        int i = l, j = h - 1;
        while(i < j){
            int sum = nums[i] + nums[j];
            if(sum == target){
                result.add(Arrays.asList(x, y, nums[i], nums[j]));
                i++;
                j--;
                // Avoid duplicates
                while(i < j && nums[i] == nums[i-1])
                    ++i;
                while(i < j && nums[j] == nums[j+1])
                    --j;
            }
            else if(sum < target){
                i++;
            }
            else
                j--;
        }
    }    
}
