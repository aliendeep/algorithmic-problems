class Solution {
    void bktk(int[] nums, int lev, ArrayList<Integer> cur, List<List<Integer>> result){
        if(lev == nums.length){
            result.add(new ArrayList<Integer>(cur));
            return;
        }
            
        // without
        bktk(nums, lev+1, cur, result);
        // with
        cur.add(nums[lev]);
        bktk(nums, lev+1, cur, result);
        cur.remove(cur.size()-1);
    }
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        ArrayList<Integer> cur =  new ArrayList<Integer>();
        bktk(nums, 0, cur, result);
        return result;
    }
}