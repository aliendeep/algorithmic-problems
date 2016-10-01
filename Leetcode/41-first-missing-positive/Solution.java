public class Solution {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        int i = 0;
        while(i < n){
            if(nums[i] > 0 && nums[i] <= n && nums[i] != nums[nums[i]-1]){
                int t = nums[i];
                nums[i] = nums[nums[i]-1];
                nums[t-1] = t;
            }
            else{
                i++;
            }
        }
        for(i=0; i<n; i++){
            if(nums[i] != i+1)
                return i+1;
        }
        return n+1;
    }
}