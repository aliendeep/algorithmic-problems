public class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int curSum;
        int r = 0;
        int minDiff = Integer.MAX_VALUE;
        for(int i=0; i<n-2; i++){
            int start = i + 1;
            int end = n-1;
            while(start < end){
                curSum = nums[i] + nums[start] + nums[end];                                  
                int diff = Math.abs(target - curSum);
                if(diff < minDiff){
                    minDiff = diff;
                    r = curSum;
                }
                if(curSum == target)
                    return curSum;
                else if(curSum < target)
                    start++;    
                else
                    end--;
            }
        }
        return r;
    }
}