// Given a collection of integers that might contain duplicates, nums, return all possible subsets.

public class Solution {
    public void genSubsets(int[] nums, int lev, List<Integer> cur, List<List<Integer>> r){
        r.add(new ArrayList<>(cur));
        for(int i=lev; i<nums.length; i++){
            // avoid duplicates
            if(i > lev && nums[i] == nums[i-1])
                continue;
            cur.add(nums[i]);
            genSubsets(nums, i+1, cur, r);
            cur.remove(cur.size()-1);
        }
    }
    
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> r = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        Arrays.sort(nums);
        genSubsets(nums, 0, cur, r);
        return r;
    }
}