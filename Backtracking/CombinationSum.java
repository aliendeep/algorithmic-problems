/*
Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

The same repeated number may be chosen from C unlimited number of times.
https://leetcode.com/problems/combination-sum/
*/

public class CombinationSum {
    public void bktk(int[] candidates, int target, int prev_i, int sum, List<Integer> cur, List<List<Integer>> result ){
        if(sum == target){
            // add to the result set
            result.add(new ArrayList<Integer>(cur));
            return;
        }
        if(sum > target)    return;
            
        for(int i=prev_i; i<candidates.length; i++){
            sum += candidates[i];
            cur.add(candidates[i]);

            bktk(candidates, target, i, sum, cur, result);
            
            // undo
            sum -= candidates[i];
            cur.remove(cur.size()-1);
        }
    }
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> cur = new ArrayList<Integer>();

        // Sort
        Arrays.sort(candidates);
        bktk(candidates, target, 0, 0, cur, result);
        return result;
    }
}