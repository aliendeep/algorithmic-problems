public class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if(nums.length <= 1 || k == 0)
            return false;        
        Set<Integer> occurrence = new HashSet<Integer>();
        int start = 0;
        for(int n : nums){
            if(!occurrence.add(n))
                return true;
            if(occurrence.size() > k){
                occurrence.remove(nums[start++]);
            } 
        }
        return false;
    }
}