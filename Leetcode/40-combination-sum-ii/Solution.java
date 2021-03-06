/*
Given a collection of candidate numbers (C) and a target number (T), find all unique 
combinations in C where the candidate numbers sums to T.

Each number in C may only be used once in the combination.

Note:
All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8, 
A solution set is: 
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]
*/
public class Solution {
    public void bktk(int[] candidates, int target, int prev_i, int sum, List<Integer> cur, List<List<Integer>> result ){
        if(sum == target){
            // add to the result set
            result.add(new ArrayList<Integer>(cur));
            return;
        }
        if(sum > target)
            return;
            
        for(int i=prev_i; i<candidates.length; i++){
            // The solution set must not contain duplicate combinations.
            if(i > prev_i && candidates[i] == candidates[i-1])
                continue;
            sum += candidates[i];
            cur.add(candidates[i]);
            // Each number in the array may only be used once in the combination, hence i+1
            bktk(candidates, target, i+1, sum, cur, result);
            
            // undo
            sum -= candidates[i];
            cur.remove(cur.size()-1);
        }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> cur = new ArrayList<Integer>();

        // Sort
        Arrays.sort(candidates);
        bktk(candidates, target, 0, 0, cur, result);
        return result;
    }
}

class Solution2 {
    public void bktk(int[] candidates, int target, int start, int sum, List<Integer> cur, List<List<Integer>> result ){
        if(sum == target){
            // add to the result set
            System.out.println(cur);
            result.add(new ArrayList<Integer>(cur));
            return;
        }
        if(sum > target)
            return;
            
        for(int i=start; i<candidates.length; i++){
            // The solution set must not contain duplicate combinations.
            if(i == start || candidates[i] != candidates[i-1]){
                sum += candidates[i];
                cur.add(candidates[i]);
                // Each number in the array may only be used once in the combination ,hence i+1
                bktk(candidates, target, i+1, sum, cur, result);
                
                // undo
                sum -= candidates[i];
                cur.remove(cur.size()-1);
            }
        }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> cur = new ArrayList<Integer>();

        // Sort
        Arrays.sort(candidates);
        
        bktk(candidates, target, 0, 0, cur, result);
        return result;
    }
}

class Solution3 {
    public void bktk(int[] nums, int target, int start, int sum, List<Integer> cur, List<List<Integer>> result ){
        if(sum == target){
            // add to the result set
            result.add(new ArrayList<Integer>(cur));
            return;
        }
        if(sum > target)
            return;

        // Create a index list by taking the indices of only unique numbers
        List<Integer> candidateIndex = new ArrayList<>();
        for(int i=start; i<nums.length; i++){
            if(candidateIndex.size() > 0 && nums[candidateIndex.get(candidateIndex.size()-1)] == nums[i])
                continue;
            candidateIndex.add(i);
        }
        
        for(int i : candidateIndex){
            sum += nums[i];
            cur.add(nums[i]);
            // Each number in the array may only be used once in the combination ,hence i+1
            bktk(nums, target, i+1, sum, cur, result);
            
            // undo
            sum -= nums[i];
            cur.remove(cur.size()-1);
        }
    }

    public List<List<Integer>> combinationSum2(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> cur = new ArrayList<Integer>();

        // Sort
        Arrays.sort(nums);
        
        bktk(nums, target, 0, 0, cur, result);
        return result;
    }
}
