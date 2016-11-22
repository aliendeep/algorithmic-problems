/*
Given a non-empty array of integers, return the k most frequent elements.

For example,
Given [1,1,1,2,2,3] and k = 2, return [1,2].

Note: 
You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
*/
public class Solution {
    // Solution 3
    public List<Integer> topKFrequent(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> cntMap = new HashMap<Integer, Integer>();
        for(int t : nums){
            cntMap.put(t, cntMap.getOrDefault(t, 0) + 1);
        }        
        // freq, key
        TreeMap<Integer, List<Integer>> map = new TreeMap<>();
        for(Map.Entry<Integer, Integer> entry : cntMap.entrySet()){
            int key = entry.getKey();
            int val = entry.getValue();
            if(!map.containsKey(val)){
                map.put(val, new ArrayList<Integer>());
            }
            map.get(val).add(key);
        }
        List<Integer> r = new ArrayList<>();
        while(k > 0){
            Map.Entry<Integer, List<Integer>> entry = map.pollLastEntry();
            List<Integer> list = entry.getValue();
            if(list.size() <= k) 
                r.addAll(list);
            else
                r.addAll(list.subList(0, k));
            k -= list.size();
        }
        return r;
   }
}