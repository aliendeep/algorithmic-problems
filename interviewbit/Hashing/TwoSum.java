public class Solution {
  public ArrayList<Integer> twoSum(final List<Integer> nums, int target) {
        int n = nums.size();
        Map<Integer, Integer> hmap = new HashMap<Integer, Integer>();
        ArrayList<Integer> r = new ArrayList<>();
        for(int i=0; i<n; i++){
            int diff = target - nums.get(i);
            if(hmap.containsKey(diff)){
                int index = hmap.get(diff);
                if(index != i){
                    r.add(index+1);
                    r.add(i+1);
                    break;
                }
            }
            if(!hmap.containsKey(nums.get(i))){
                hmap.put(nums.get(i), i);
            }
        }
        return r;
  }
}
