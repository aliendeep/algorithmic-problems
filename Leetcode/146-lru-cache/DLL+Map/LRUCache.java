/*
Design and implement a data structure for Least Recently Used (LRU) cache. It 
should support the following operations: get and set.

get(key) - Get the value (will always be positive) of the key if the key exists 
in the cache, otherwise return -1.
set(key, value) - Set or insert the value if the key is not already present. 
When the cache reached its capacity, it should invalidate the least recently 
used item before inserting a new item.
*/

import java.util.*;
// Doubly linked list node
class Node{
    int key;
    int val;
    public Node prev, next;
    public Node(int k, int v){
        key = k;
        val = v;
        prev = null;
        next = null;
    }
}

class DLL{
    Node dummyHead, dummyTail;
    public DLL(){
        dummyHead = new Node(-1, -1);
        dummyTail = new Node(-1, -1);
        //dummyHead.next = dummyHead.prev = dummyTail;
        //dummyTail.prev = dummyTail.next = dummyHead;
        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;
    }
    
    public void addFirst(Node t){
        Node next = dummyHead.next;
        dummyHead.next = t;
        t.prev = dummyHead;
        t.next = next;
        next.prev = t;
    }
    public Node removeLast(){
        Node last = dummyTail.prev;
        Node prev = last.prev;
        dummyTail.prev = prev;
        prev.next = dummyTail;
        return last;
    }
    
    public void remove(Node t){
        Node next = t.next;
        Node prev = t.prev;
        prev.next = next;
        next.prev = prev;
    }
    
    public void moveFirst(Node t){
        remove(t);
        addFirst(t);
    }
}
public class LRUCache {
    // Key, DLL
    Map<Integer, Node> cache;
    DLL list;
    int capacity;

    public LRUCache(int capacity) {
        cache = new HashMap<>();
        list = new DLL();
        this.capacity = capacity;
    }
    
    public int get(int key) {
        if(!cache.containsKey(key))
            return -1;
        
        // Accessed so move to the front of the list
        Node t = cache.get(key);
        list.moveFirst(t);
        return t.val;
    }
    
    public void set(int key, int value) {
        if(cache.containsKey(key)){
            Node t = cache.get(key);
            t.val = value;
            // Accessed so move to the front of the list
            list.moveFirst(t);
        }    
        else{
            // Add at the front of the list
            Node t = new Node(key, value);
            list.addFirst(t);
            cache.put(key, t);
            if(cache.size() > capacity){
                // remove the oldest entry
                Node last = list.removeLast();
                cache.remove(last.key);
            }
        }
    }
}
