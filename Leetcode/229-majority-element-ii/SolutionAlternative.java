/*
Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times. 
The algorithm should run in linear time and in O(1) space.
*/
public class Solution {
    // Alternative
    public List<Integer> majorityElement(int[] nums) {
        int len = nums.length;
        // no, count
        int k = 3;
        Map<Integer, Integer> hash = new HashMap<>();    
        for(int n : nums){
            hash.put(n, hash.containsKey(n) ? hash.get(n) + 1 : 1);
            if(hash.size() == k){
                List<Integer> del = new ArrayList<>();
                // decrement k unique items
                for(Map.Entry<Integer, Integer> entry : hash.entrySet()){
                    int key = entry.getKey();
                    int cnt = entry.getValue();
                    if(cnt - 1 == 0)
                        del.add(key);
                    else{
                        hash.put(key, cnt-1);
                    }
                }                
                // delete
                for(int key : del){
                    hash.remove(key);
                }
            }
        }
        // Check the candidate whether they occur more than k times
        // reset counter
        for(Map.Entry<Integer, Integer> entry : hash.entrySet()){
            int key = entry.getKey();
            hash.put(key, 0);
        }
        
        for(int n : nums){
            if(hash.containsKey(n))
                hash.put(n, hash.get(n)+1);
        }
        List<Integer> result = new ArrayList<>();
        for(Map.Entry<Integer, Integer> entry : hash.entrySet()){
            if(entry.getValue() > (len * 1.0 / k))
                result.add(entry.getKey());
        }
        return result;        
    }
}