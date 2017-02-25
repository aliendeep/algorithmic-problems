/*
Given a collection of distinct numbers, return all possible permutations.

For example,
[1,2,3] have the following permutations:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
*/
public class Solution {
    void bktk(Integer[] cur, int[] nums, int lev, boolean[] taken, List<List<Integer>> r){
        if(lev == nums.length){
            List<Integer> l = new ArrayList<Integer>(Arrays.asList(cur));
            r.add(l);
            return;
        }
        for(int i=0; i<nums.length; i++){
            if(taken[i] == true)    continue;
            cur[lev] = nums[i];
            taken[i] = true;

            bktk(cur, nums, lev+1, taken, r);

            cur[lev] = -1;
            taken[i] = false;
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> r = new ArrayList<List<Integer>>();
        int n = nums.length;
        boolean[] taken = new boolean[n];
        
        Integer[] cur = new Integer[n];        
        for(int i=0; i<n; i++){
            taken[i] = false;
        }
        bktk(cur, nums, 0, taken, r);
        return r;
    }
}
