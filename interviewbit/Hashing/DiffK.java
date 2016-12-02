public class Solution {
  public int diffPossible(final List<Integer> nums, int k) {
        int n = nums.size();
        Set<Integer> set = new HashSet<>();
        ArrayList<Integer> r = new ArrayList<>();
        for(int i=0; i<n; i++){
            int num = nums.get(i);
            if(set.contains(num - k) || set.contains(num + k)){
                return 1;
            }
            set.add(num);
        }
        return 0;
  }
}
