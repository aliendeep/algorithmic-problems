/*
Design and implement a data structure for Least Frequently Used (LFU) cache. 
It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists 
in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. 
When the cache reaches its capacity, it should invalidate the least frequently 
used item before inserting a new item. For the purpose of this problem, when 
there is a tie (i.e., two or more keys that have the same frequency), the least 
recently used key would be evicted.

Follow up:
Could you do both operations in O(1) time complexity?

Example:

LFUCache cache = new LFUCache( 2);

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.get(3);       // returns 3.
cache.put(4, 4);    // evicts key 1.
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4
*/
// Solution 1
public class LFUCache {
    class Node {
        public Node prev, next;
        public final int freq;
        public LinkedHashSet<Integer> keys = new LinkedHashSet<>();
    
        public Node(int key, int f) {
            this.freq = f;
            keys.add(key);
            this.prev = null;
            this.next = null;
        }
    }
    int capacity;
    // Key, value
    Map<Integer, Integer> map;
    // Key, Node
    Map<Integer, Node> cache;
    Node dummyHead, dummyTail;

    public LFUCache (int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        cache = new HashMap<>();
        dummyHead = new Node(-1, -10);
        dummyTail = new Node(-1, -10);
        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;
    }

    public int get(int key) {
        // increase Frequency
        if(cache.containsKey(key)) 
            incrementFrequncy(key);
        return map.getOrDefault(key, -1);
    }
    
    public void put(int key, int value) {
        if(capacity == 0)   return;
        if(map.containsKey(key))
            incrementFrequncy(key);
        else{
            if(cache.size() == capacity) 
                removeLFUItem();
            // Add this new item to the head
            addFirst(key);
        }
        // update key, value
        map.put(key, value);
    }
    
    void incrementFrequncy(int key) {
        Node node = cache.get(key);
        // Remove the node from the keyset
        node.keys.remove(key);
        
        // No need to create new node. 
        if(node.next.freq == node.freq + 1)
            node.next.keys.add(key);
        else{
            // insert this node before next node
            Node newNode = new Node(key, node.freq + 1);
            node.next.prev = newNode; 
            newNode.next = node.next;
            newNode.prev = node;
            node.next = newNode;
        }
        cache.put(key, node.next);
        
        // Delete the node, if the keyset of this node contains only this key
        if(node.keys.isEmpty()) 
            removeNode(node);
    }
    
    void addFirst(int key) {
        Node next = dummyHead.next;
        if(next.freq == 1){
            // No need to create a new node. Just add the key to the keyset of the current head
            next.keys.add(key);
        }
        else{
            // insert at the head
            Node node = new Node(key, 1);
            node.next = dummyHead.next;
            dummyHead.next.prev = node;
            dummyHead.next = node;
            node.prev = dummyHead;
        }
        // Update cache
        cache.put(key, dummyHead.next);
    }
    
    void removeNode(Node node) {
        // delete node
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // Head contains the least frequently used item
    void removeLFUItem() {
        if(dummyHead.next == dummyTail) 
            return;
        Node head = dummyHead.next;
        int leastFrequencyItem = head.keys.iterator().next();
        head.keys.remove(leastFrequencyItem);

        if(head.keys.isEmpty()) 
            removeNode(head);
        // Remove from map
        map.remove(leastFrequencyItem);
        // Remove from cache
        cache.remove(leastFrequencyItem);
    }
}

// Alternative
class LFUCache {
    class Node {
        public Node prev, next;
        public final int freq;
        public LinkedHashSet<Integer> keys = new LinkedHashSet<>();
    
        public Node(int key, int f, Node prev, Node next) {
            this.freq = f;
            this.prev = prev;
            this.next = next;
            keys.add(key);
        }
    }
    int capacity;
    // Key, value
    Map<Integer, Integer> map;
    // Key, Node
    Map<Integer, Node> cache;
    Node head;

    public LFUCache (int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        cache = new HashMap<>();
        head = null;
    }

    public int get(int key) {
        // increase Frequency
        if(cache.containsKey(key)) 
            incrementFrequncy(key);
        return map.getOrDefault(key, -1);
    }
    
    public void put(int key, int value) {
        if(capacity == 0)   return;
        if(map.containsKey(key))
            incrementFrequncy(key);
        else{
            if(cache.size() == capacity) 
                removeLFUItem();
            // Add this new item to the head
            addFirst(key);
        }
        // update key, value
        map.put(key, value);
    }
    
    void incrementFrequncy(int key) {
        Node node = cache.get(key);
        // Remove the node from the keyset
        node.keys.remove(key);
        
        // Move the item to the next
        if(node.next == null)
            node.next = new Node(key, node.freq + 1, node, null);
        else{
            // No need to create new node. 
            if(node.next.freq == node.freq + 1)
                node.next.keys.add(key);
            else{
                // insert this node before next node
                Node newNode = new Node(key, node.freq + 1, node, node.next);
                node.next.prev = newNode; 
                node.next = newNode;
            }
        }
        cache.put(key, node.next);
        
        // Delete the node, if the keyset of this node contains only this key
        if(node.keys.isEmpty()) 
            removeNode(node);
    }
    
    void addFirst(int key) {
        if(head == null){
            // Create a new node
            head = new Node(key, 1, null, null);
        }
        else if(head.freq == 1){
            // No need to create a new node. Just add the key to the keyset of the current head
            head.keys.add(key);
        }
        else{
            // insert before current head
            Node node = new Node(key, 1, null, null);
            head.prev = node;
            node.next = head;
            // update head
            head = node;
        }
        // Update cache
        cache.put(key, head);
    }
    
    void removeNode(Node node) {
        if(head == node){
            head = node.next;
        }
        else{
            if(node.next != null)
                node.next.prev = node.prev;
            node.prev.next = node.next;
        } 
    }
    // Head contains the least frequently used item
    void removeLFUItem() {
        if(head == null) 
            return;
        int leastFrequencyItem = head.keys.iterator().next();
        head.keys.remove(leastFrequencyItem);
        if(head.keys.isEmpty()) 
            removeNode(head);
        // Remove from map
        map.remove(leastFrequencyItem);
        // Remove from cache
        cache.remove(leastFrequencyItem);
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
