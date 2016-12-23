/*
Implement a data structure supporting the following operations:

Inc(Key) - Inserts a new key with value 1. Or increments an existing key by 1. Key is guaranteed to be a non-empty string.
Dec(Key) - Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. Key is guaranteed to be a non-empty string. If the key does not exist, this function does nothing.
GetMaxKey() - Returns one of the keys with maximal value. If no element exists, return an empty string "".
GetMinKey() - Returns one of the keys with minimal value. If no element exists, return an empty string "".
Challenge: Perform all these in O(1) time complexity.
["AllOne","inc","inc","inc","inc","inc","dec","dec","getMaxKey","getMinKey"]
[[],["a"],["b"],["b"],["b"],["b"],["b"],["b"],[],[]]

Sample Input:
["AllOne","inc","inc","inc","inc","inc","dec","dec","getMaxKey","getMinKey"]
[[],["a"],["b"],["b"],["b"],["b"],["b"],["b"],[],[]]
["AllOne","inc","inc","inc","dec","inc","inc","getMaxKey"]
[[],["hello"],["world"],["hello"],["world"],["hello"],["leet"],[]]
["AllOne","dec","getMaxKey"]
[[],["hello"],[]]

Sample Output:
[null,null,null,null,null,null,null,null,"b","a"]
[null,null,null,null,null,null,null,"hello"]
[null,null,""]
*/
public class AllOne {
    Map<String, Integer> map;
    List<Set<String>> cntKey;
    int min, max;

    /** Initialize your data structure here. */
    public AllOne() {
        map = new HashMap<>();
        cntKey = new ArrayList<>();
        cntKey.add(new HashSet<>());
        min = 0;
        max = 0;
    }
    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        Integer prevCnt = map.get(key);
        int cnt = (prevCnt == null) ? 1 : prevCnt + 1;
        // update map
        map.put(key, cnt);
        if(prevCnt != null){
            cntKey.get(prevCnt).remove(key);
        }
        if(cntKey.size() <= cnt){
            cntKey.add(new HashSet<>()); 
        }
        cntKey.get(cnt).add(key);
        if(cnt > max){
            max = cnt;
        }
        if(min == 0){
            min++;
        }
    }
    
    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        if(!map.containsKey(key))
            return;
            
        Integer prevCnt = map.get(key);
        cntKey.get(prevCnt).remove(key);
        
        int cnt = (prevCnt == null) ? 1 : prevCnt - 1;
        // update map
        if(cnt == 0){
            map.remove(key);
        }
        else{
            map.put(key, cnt);
            cntKey.get(cnt).add(key);
        }
        if(cntKey.get(max).isEmpty()){
            max--;
        }    
        if(cnt < min){
            min = cnt;
        }
        if(cntKey.get(min).isEmpty()){
            min++;
        }    
    }
    
    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        if(map.size() == 0)
            return "";
        return cntKey.get(max).iterator().next();
    }
    
    /** Returns one of the keys with Minimal value. */
    // Amortized O(1)
    public String getMinKey() {
        if(map.size() == 0)
            return "";
            
        while (cntKey.get(min).isEmpty()) 
            min++;
        return cntKey.get(min).iterator().next();        
    }
}

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */
/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */