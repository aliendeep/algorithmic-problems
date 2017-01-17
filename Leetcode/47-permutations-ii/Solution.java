/*
Given a collection of numbers that might contain duplicates, return all possible unique permutations.

For example,
[1,1,2] have the following unique permutations:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]
*/

public class Solution {
    public void print(int t){
        while(t-- > 0)
            System.out.print(" ");
    } 
    
    public void permute(int[] nums, int lev, boolean[] taken, List<Integer> cur, List<List<Integer>> result){
        if(lev == nums.length){
            System.out.println(cur);
            result.add(new ArrayList<>(cur));
            return;
        }
        for(int i=0; i<nums.length; i++){
            if(taken[i] || (i > 0  && nums[i-1] == nums[i] && taken[i-1] == false))
                continue;
            print(lev);
            System.out.println("Lev " + lev + " i: " + i + " taken " + nums[i]);
            cur.add(nums[i]);
            taken[i] = true;
            permute(nums, lev+1, taken, cur, result);
            cur.remove(cur.size()-1);
            taken[i] = false;
        }
    }
    public List<List<Integer>> permuteUnique(int[] nums) {
        int n = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        if(n == 0)
            return result;

        Arrays.sort(nums);

        // initialize
        List<Integer> cur = new ArrayList<>();
        
        boolean[] taken = new boolean[nums.length];
        permute(nums, 0, taken, cur, result);
        return result;
    }
}

class Solution2 {
    public void permute(int[] nums, int lev, boolean[] taken, List<Integer> cur, List<List<Integer>> result){
        if(lev == nums.length){
            result.add(new ArrayList<>(cur));
            return;
        }
        // Keep indices of non-taken unique elements
        List<Integer> candidateIndex = new ArrayList<>();
        for(int i=0; i<nums.length; i++){
            if(taken[i])    continue;
            if(candidateIndex.size() > 0 && nums[candidateIndex.get(candidateIndex.size()-1)] == nums[i])
                continue;
            candidateIndex.add(i);
        }
        
        for(int i : candidateIndex) {
            cur.add(nums[i]);
            taken[i] = true;

            permute(nums, lev+1, taken, cur, result);

            cur.remove(cur.size()-1);
            taken[i] = false;
        }
    }
    public List<List<Integer>> permuteUnique(int[] nums) {
        int n = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        if(n == 0)
            return result;

        Arrays.sort(nums);

        boolean[] taken = new boolean[nums.length];
        permute(nums, 0, taken, new ArrayList<>(), result);
        return result;
    }
}
