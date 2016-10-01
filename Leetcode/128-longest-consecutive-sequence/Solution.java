public class Solution {
    public int longestConsecutive(int[] nums) {
        int n = nums.length;
        if(n <= 1)
            return n;
            
        Set<Integer> numSet = new HashSet<Integer>();
        for(int i : nums)
            numSet.add(i);
            
        int maxLen = 1;
        int len = 1;
        for(int i=0; i<n; i++){
            int num = nums[i];
            len = 1;
            // left side boundary
            int left = num - 1;
            while(numSet.contains(left)){
                numSet.remove(left--);
                len++;
            }
            // right side boundary
            int right = num + 1;
            while(numSet.contains(right)){
                numSet.remove(right++);
                len++;
            }
            maxLen = Math.max(maxLen, len);
        }
        return maxLen;
    }
}