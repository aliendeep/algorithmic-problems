/*
Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.

Note: The solution set must not contain duplicate quadruplets.

For example, given array S = [1, 0, -1, 0, -2, 2], and target = 0.

A solution set is:
[
  [-1,  0, 0, 1],
  [-2, -1, 1, 2],
  [-2,  0, 0, 2]
]
*/
public class Solution {
    class Pair{
        int x;
        int y;
        public Pair(int x1, int y1){
            x = x1;
            y = y1;
        }
    }
    public List<List<Integer>> fourSum(int[] nums, int target) {
        int n = nums.length;
        
        List<List<Integer>> result = new ArrayList<>();
        if(n < 4)   return result;
        Map<Integer, Set<Pair>> map = new HashMap<>();
        for(int i=0; i<n; ++i){
            for(int j=i+1; j<n; ++j){
                int sum = nums[i] + nums[j];
                if(!map.containsKey(sum))
                    map.put(sum, new HashSet<>());

                map.get(sum).add(new Pair(i, j));
            }
        }   
        
        Set<List<Integer>> resultSet = new HashSet<>();
        for(int i=0; i<n-1; ++i){
            for(int j=i+1; j<n; ++j){
                int sum = nums[i] + nums[j];
                if(map.containsKey(target - sum)){
                    Set<Pair> set = map.get(target - sum);
                    for(Pair t : set){
                        if(t.x != i && t.y != i && t.x != j && t.y != j){
                            List<Integer> list = new ArrayList<>();
                            list.add(nums[i]);
                            list.add(nums[j]);
                            list.add(nums[t.x]);
                            list.add(nums[t.y]);
                            Collections.sort(list);
                            if(!resultSet.contains(list)){
                                resultSet.add(list);
                                result.add(list);
                            }
                        }
                    }
                }     
            }
        }
        return result;
    }
}