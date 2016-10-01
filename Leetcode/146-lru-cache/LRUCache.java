public class LRUCache {
    LinkedHashMap<Integer, Integer> cache;
    public LRUCache(int capacity) {
        // (capacity, load factor, access order)
        // accessOrder - the ordering mode - true for access-order, false for insertion-order
        // https://docs.oracle.com/javase/7/docs/api/java/util/HashMap.html
        this.cache = new LinkedHashMap<Integer, Integer>(capacity, 1.0f, true){
            // https://docs.oracle.com/javase/7/docs/api/java/util/LinkedHashMap.html#removeEldestEntry(java.util.Map.Entry)
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer>  e){
                return this.size() > capacity;
            }
        };
    }
    
    public int get(int key) {
        if(!cache.containsKey(key))
            return -1;
        return cache.get(key);
    }
    
    public void set(int key, int value) {
        cache.put(key, value);
    }
}