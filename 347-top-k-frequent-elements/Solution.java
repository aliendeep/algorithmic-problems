public class Solution {
    class Pair{
        int value;
        // Number of times the value appeared in the array
        int cnt;
        public Pair(int value, int cnt){
            this.value = value;
            this.cnt = cnt;
        }
    };
    
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> cntMap = new HashMap<Integer, Integer>();
        for(int n : nums){
            if(cntMap.containsKey(n) == false)
                cntMap.put(n, 1);
            else{
                cntMap.put(n, cntMap.get(n)+1);
            }
        }
        // Min Heap of the k-most frequent entries
        // insert the first k entries into the min heap
        PriorityQueue<Pair> minHeap = new PriorityQueue<Pair>(k, new Comparator<Pair>(){
            @Override
            public int compare(Pair a, Pair b){
                if(a.cnt > b.cnt)
                    return 1;
                if(a.cnt == b.cnt)
                    return 0;
                return -1;
            }  
        }); 
        
        int i = 0;
        for(Map.Entry<Integer, Integer> entry : cntMap.entrySet()){
            int key = entry.getKey();
            int val = entry.getValue();
            if(i < k){
                minHeap.add(new Pair(key, val));                
            }
            else{
                Pair top = minHeap.peek();
                if(top.cnt < cntMap.get(key)){
                    minHeap.remove();
                    minHeap.add(new Pair(key, val));
                }
            }
            i++;
        }
        
        // Items in the min heap are the top frequent elements 
        List<Integer> r = new ArrayList<>();
        while(!minHeap.isEmpty()){
            r.add(minHeap.peek().value);
            minHeap.remove();
        }
        return r;
    }
}