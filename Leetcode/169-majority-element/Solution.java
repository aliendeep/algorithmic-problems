public class Solution {
    public int majorityElement(int[] nums) {
        int n = nums.length;
        if(n == 0)
            return 0;
        int cnt = 1;
        int x = nums[0];
        int i = 1;
        while(i < n){
            if(nums[i] == x){
                cnt++;
            }
            else{
                cnt--;
                if(cnt == 0){
                    x = nums[i];
                    cnt = 1;
                }
            }
            i++;
        }
        return x;
    }
}