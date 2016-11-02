public class Solution {
    public static final int FIXED_NUM = 0;
    Random randomNum;
    int[] a;
    
    public Solution(int[] nums) {
        randomNum = new Random();
        a = nums;
    }
    
    public int pick(int target) {
        int resultIndex = -1;
        int cntTarget = 1;
        for(int i=0; i<a.length; i++){
            if(a[i] == target){
                // Change Index with fixed probability (first element is chosen with probability 1 because cntTarget = 1 intiially)
                if(randomNum.nextInt(cntTarget) == FIXED_NUM)
                    resultIndex = i;
                cntTarget++;
            }
        }
        return resultIndex;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */