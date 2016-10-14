public class PermutationsII{   
    public void permute(int[] nums, int lev, boolean[] taken, List<Integer> cur, List<List<Integer>> result){
        if(lev == nums.length){
            result.add(new ArrayList<>(cur));
            return;
        }
        for(int i=0; i<nums.length; i++){
            if(taken[i] || (i > 0  && nums[i-1] == nums[i] && taken[i-1] == false))
                continue;

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
        if(n == 0)      return result;

        Arrays.sort(nums);

        // initialize
        List<Integer> cur = new ArrayList<>();
        
        boolean[] taken = new boolean[nums.length];
        Arrays.fill(taken, false);
        
        permute(nums, 0, taken, cur, result);
        return result;
    }
}