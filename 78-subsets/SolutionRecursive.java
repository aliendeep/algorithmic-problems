public class Solution {
    void bktk(int[] nums, int lev, ArrayList<Integer> cur, List<List<Integer>> result){
        result.add(new ArrayList<Integer>(cur));
        for(int i=lev; i<nums.length; i++){
            cur.add(nums[i]);
            bktk(nums, i+1, cur, result);
            cur.remove(cur.size()-1);
        }
    }
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        ArrayList<Integer> cur =  new ArrayList<Integer>();
        bktk(nums, 0, cur, result);
        return result;
    }
}