/*
Given a non-empty array of integers, return the k most frequent elements.

For example,
Given [1,1,1,2,2,3] and k = 2, return [1,2].

Note: 
You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Your algorithm's time complexity must be better than O(n log n), where n is the 
array's size.
*/
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

// Solution 2
class Solution2 {
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

class Solution2.1 {
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

// Solution 3 : Bucket Sort O(n) Solution
class Solution3 {
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

// Solution 3
class Solution3.1 {
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