/*
Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and set.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
set(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
*/

import java.util.*;

class LRUCache {
    // Alternate 1 : use the LinkedList<>
    // Hash + doubly linked list
    LinkedList<Pair> list;
    Map<Integer, Pair> cache;
    int capacity;
    
    class Pair{
        int key;
        int value;
        public Pair(int k, int v){
            key = k;
            value = v;
        }
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();
        list = new LinkedList<>();
    }
    
    public void moveNode(Pair node, int value){
        // Remove the node
        list.remove(node);
        // update value of the node
        node.value = value;
        // Move the node at the end
        list.addLast(node);        
    }

    public int get(int key) {
        if(!cache.containsKey(key)){
            return -1;
        }        
        // Accessed recently        
        Pair node = cache.get(key);
        moveNode(node, node.value);
        return node.value;
    }
    
    public void set(int key, int value) {
        if(!cache.containsKey(key)){
            // evict if needed
            if(cache.size() >= capacity){
                // remove the head from the linked list and cache
                Pair head = list.remove();
                cache.remove(head.key);
            }
            // add at the end of the list
            Pair node = new Pair(key, value);
            list.addLast(node);
            cache.put(key, node);
        }
        else{
            Pair node = cache.get(key);
            moveNode(node, value);
        }
    }
}

public class LRUCacheSolution2{
    public static void main(String[] args){
        LRUCache ob = new LRUCache(1);
        ob.set(2, 1);
        System.out.println(ob.get(2));
        ob.set(2, 2);
        System.out.println(ob.get(2));
        ob.set(1, 1);
        System.out.println(ob.get(2));
    }
}