public class Solution {
    // Solution 3
    public List<Integer> topKFrequent(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int t : nums){
            map.put(t, map.getOrDefault(t, 0) + 1);
        }        
    
        List<Integer>[] bucket = new List[n+1];
        for(int key : map.keySet()){
            int frequency = map.get(key);
            if(bucket[frequency] == null){
                bucket[frequency] = new LinkedList<>();
            }
            bucket[frequency].add(key);
        }
        
        List<Integer> r = new ArrayList<>();
        for(int i=bucket.length-1; i>=0 && k > 0; --i){
            if(bucket[i] != null){
                List<Integer> list = bucket[i];
                if(list.size() <= k) 
                    r.addAll(list);
                else
                    r.addAll(list.subList(0, k));
                k -= list.size();
            }
        } 
        return r;
    }
}