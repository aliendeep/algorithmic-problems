/*
Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and set.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
set(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
*/
import java.util.*;

// Alternate : Implement doubly linked list
class LRUCache {
    class DoublyLinkedList{
        public DoublyLinkedList prev, next;
        public int key, value;

        public DoublyLinkedList(int k, int v){
            this.key = k;
            this.value = v;
            this.prev = null;
            this.next = null;
        }
    }   
    class DLL{
        DoublyLinkedList head, tail;        
        public DLL(){
            // dummy head and tail
            head = new DoublyLinkedList(-1, -1);
            tail = new DoublyLinkedList(-1, -1);
            head.next = tail;
            tail.prev = head;
        }

        // add at the tail
        public void addLast(DoublyLinkedList node){
            DoublyLinkedList prev = tail.prev;
            node.prev = prev;
            prev.next = node;
            node.next = tail;            
            tail.prev = node;
        }

        // remove the first node and return it
        public DoublyLinkedList remove(){
            DoublyLinkedList n = head.next;
            DoublyLinkedList next = head.next.next;
            head.next = next;
            next.prev = head;
            return n;
        }

        // Remove this particular node
        public void remove(DoublyLinkedList node){
            DoublyLinkedList prev = node.prev;
            DoublyLinkedList next = node.next;
            prev.next = next;
            next.prev = prev;
        }
    }

    // Hash + doubly linked list
    public DLL list;
    public Map<Integer, DoublyLinkedList> cache;
    public int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();
        list = new DLL();
    }
    
    public void moveNode(DoublyLinkedList node, int value){
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
        DoublyLinkedList node = cache.get(key);
        moveNode(node, node.value);
        return node.value;
    }
    
    public void set(int key, int value) {
        if(!cache.containsKey(key)){
            // evict if needed
            if(cache.size() >= capacity){
                // remove the head from the linked list and cache
                DoublyLinkedList head = list.remove();
                cache.remove(head.key);
            }
            // add at the end of the list
            DoublyLinkedList node = new DoublyLinkedList(key, value);
            list.addLast(node);
            cache.put(key, node);
        }
        else{
            DoublyLinkedList node = cache.get(key);
            moveNode(node, value);
        }
    }
}

public class LRUCacheAlternate{
    public static void test1(){
        LRUCache ob = new LRUCache(1);
        ob.set(2, 1);
        System.out.println(ob.get(2));
        ob.set(2, 2);
        System.out.println(ob.get(2));
        ob.set(1, 1);
        System.out.println(ob.get(2));        
    }

    public static void test2(){
        LRUCache ob = new LRUCache(1);
        ob.set(2, 1);
        System.out.println(ob.get(2));
        ob.set(2, 2);
        System.out.println(ob.get(2));
        ob.set(1, 1);
        ob.set(4, 1);
        System.out.println(ob.get(2));        
    }

    public static void main(String[] args){
        //test1();
        test2();
    }
}