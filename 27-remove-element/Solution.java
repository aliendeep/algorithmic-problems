public class Solution {
    public int removeElement(int[] nums, int val) {
        int w = 0;
        for(int n : nums){
            if(n != val)
                nums[w++] = n;
        }
        return w;
    }
}