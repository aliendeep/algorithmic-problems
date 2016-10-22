public class Solution {
    public void permute(ArrayList<Integer> nums, int lev, boolean[] taken, ArrayList<Integer> cur, 
                    ArrayList<ArrayList<Integer>> result){
        if(lev == nums.size()){
            result.add(new ArrayList<>(cur));
            return;
        }
        for(int i=0; i<nums.size(); i++){
            if(taken[i] || (i > 0  && nums.get(i-1) == nums.get(i) && taken[i-1] == false))
                continue;

            cur.add(nums.get(i));
            taken[i] = true;
            permute(nums, lev+1, taken, cur, result);
            cur.remove(cur.size()-1);
            taken[i] = false;
        }
    }
  public ArrayList<ArrayList<Integer>> permute(ArrayList<Integer> nums) {
        int n = nums.size();
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if(n == 0)  return result;

        Collections.sort(nums);

        // initialize
        ArrayList<Integer> cur = new ArrayList<>();
        
        boolean[] taken = new boolean[n];
        Arrays.fill(taken, false);
        
        permute(nums, 0, taken, cur, result);
        return result;
  }
}
