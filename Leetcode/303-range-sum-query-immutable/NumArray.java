public class NumArray {
    int[] dp;
    public NumArray(int[] nums) {
        dp = new int[nums.length];
        // test case: []
        if(nums.length > 0){
            dp[0] = nums[0];

            for(int i=1; i<nums.length; i++){
                dp[i] = dp[i-1] + nums[i];
            }
        }
    }

    public int sumRange(int i, int j) {
        if(i < 0 || i >= dp.length || j < 0 || j >= dp.length)
            return 0;
        if(i == 0)
            return dp[j];
        return dp[j] - dp[i-1];
    }
    public static void main(String[] args){
        int nums[] = {1, 2, 3, 5, 7, 9, 11};
        NumArray numArray = new NumArray(nums);
        System.out.println(numArray.sumRange(0, 1));
        System.out.println(numArray.sumRange(0, 2));
        System.out.println(numArray.sumRange(1, 2));
    }    
}


// Your NumArray object will be instantiated and called as such:
// NumArray numArray = new NumArray(nums);
// numArray.sumRange(0, 1);
// numArray.sumRange(1, 2);