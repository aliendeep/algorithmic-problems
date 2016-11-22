public class Solution {
    // Solution 2
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> cntMap = new HashMap<Integer, Integer>();
        for(int n : nums){
            cntMap.put(n, cntMap.getOrDefault(n, 0) + 1);
        }        

        PriorityQueue<Map.Entry<Integer, Integer>> maxHeap = new PriorityQueue<>(k, new Comparator<Map.Entry<Integer, Integer>>(){
            @Override
            public int compare(Map.Entry<Integer, Integer> a, Map.Entry<Integer, Integer> b){
                return Integer.compare(b.getValue(), a.getValue());  
            }  
        });  
        
        for(Map.Entry<Integer, Integer> entry : cntMap.entrySet()){
            maxHeap.add(entry);
        }
        
        List<Integer> r = new ArrayList<>();
        while(k-- > 0){
            r.add(maxHeap.poll().getKey());
        }
        return r;
    }
}

public class Solution2 {
    // Solution 2
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> cntMap = new HashMap<Integer, Integer>();
        for(int n : nums){
            cntMap.put(n, cntMap.getOrDefault(n, 0) + 1);
        }        

        PriorityQueue<Map.Entry<Integer, Integer>> maxHeap = new PriorityQueue<>((a, b) -> 
                                                            Integer.compare(b.getValue(), a.getValue()));
        
        for(Map.Entry<Integer, Integer> entry : cntMap.entrySet()){
            maxHeap.add(entry);
        }
        
        List<Integer> r = new ArrayList<>();
        while(k-- > 0){
            r.add(maxHeap.poll().getKey());
        }
        return r;
    }
}