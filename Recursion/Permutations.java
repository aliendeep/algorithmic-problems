// Given a collection of distinct numbers, return all possible permutations.

public class Permutations {
    void bktk(List<Integer> cur, int[] nums, int lev, boolean[] taken, List<List<Integer>> r){
        if(lev == nums.length){
            List<Integer> l = new ArrayList<Integer>(cur);
            r.add(l);
            return;
        }
        for(int i=0; i<nums.length; i++){
            if(taken[i] == true)    continue;
            cur.add(nums[i]);
            taken[i] = true;
            bktk(cur, nums, lev+1, taken, r);
            cur.remove(cur.size()-1);
            taken[i] = false;
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> r = new ArrayList<List<Integer>>();
        int n = nums.length;
        boolean[] taken = new boolean[n];
        List<Integer> cur = new ArrayList<>();        
        bktk(cur, nums, 0, taken, r);
        return r;
    }
}