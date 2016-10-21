/*
Implement a data structure supporting the following operations:

Inc(Key) - Inserts a new key with value 1. Or increments an existing key by 1. Key is guaranteed to be a non-empty string.
Dec(Key) - Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. Key is guaranteed to be a non-empty string. If the key does not exist, this function does nothing.
GetMaxKey() - Returns one of the keys with maximal value. If no element exists, return an empty string "".
GetMinKey() - Returns one of the keys with minimal value. If no element exists, return an empty string "".
Challenge: Perform all these in O(1) time complexity.
["AllOne","inc","inc","inc","inc","inc","dec","dec","getMaxKey","getMinKey"]
[[],["a"],["b"],["b"],["b"],["b"],["b"],["b"],[],[]]

*/
import java.util.*;

public class AllOne {
    class DoublyLinkedList{
        public DoublyLinkedList prev, next;
        public String key;
        public int value;

        public DoublyLinkedList(String k, int v){
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
            head = new DoublyLinkedList("", -1);
            tail = new DoublyLinkedList("", -1);
            head.next = tail;
            tail.prev = head;
        }

        public DoublyLinkedList peekFirst(){
            if(head.next == tail)
                return null;
            return head.next;
        }

        public DoublyLinkedList peekLast(){
            if(tail.prev == head)
                return null;
            return tail.prev;
        }

        public DoublyLinkedList peekSecondAfterFirst(){
            // 0 node
            if(head.next == tail)
                return null;
            // 1 node
            if(head.next.next == tail)
                return null;
            return head.next.next;
        }

        // insert node after prev
        public void insertAfter(DoublyLinkedList prev, DoublyLinkedList node){
            DoublyLinkedList next = prev.next;
            node.next = next;
            next.prev = node;
            node.prev = prev;
            prev.next = node;

        }

        // add at the front
        public void addFirst(DoublyLinkedList node){
            insertAfter(head, node);
        }

        // add after head
        public void addAfterHead(DoublyLinkedList node){
            insertAfter(head.next, node);
        }

        // add in the tail
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

    // <Key, node>
    Map<String, DoublyLinkedList> map;  
    DLL list;

    /** Initialize your data structure here. */
    public AllOne() {
        map = new HashMap<>();
        list = new DLL();
    }
    
    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        if(!map.containsKey(key)){
            DoublyLinkedList node = new DoublyLinkedList(key, 1);
            // add to tail
            list.addLast(node);
            map.put(key, node);
        }
        else{
            DoublyLinkedList node = map.get(key);
            node.value = node.value + 1;
            DoublyLinkedList head = list.peekFirst();
            if(head.value <= node.value){
                // remove the node from previpus position
                list.remove(node);
                // add the node at the front
                list.addFirst(node);
            }
        }
    }
    
    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        // invalud decrement key
        if(!map.containsKey(key))
            return;
        
        DoublyLinkedList node = map.get(key);
        node.value = node.value - 1; 
        // remove it
        if(node.value == 0){
            list.remove(node);
            map.remove(key);
        }
        else{
            // if the node is head
            DoublyLinkedList head = list.peekFirst();
            if(key.equals(head.key)){
                // see if it's necessary to update head
                if(list.peekSecondAfterFirst().value > node.value){
                    // remove
                    list.remove(node);
                    // insert after the current head
                    list.addAfterHead(node);
                }
            } 
            else{
                DoublyLinkedList tail = list.peekLast();
                if(tail.value > node.value){
                    // remove the node
                    list.remove(node);
                    // add it after tail
                    list.addLast(node);
                }               
            }                  
        }
    }
    
    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        if(map.size() == 0)
            return "";
        return list.peekFirst().key;
    }
    
    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        if(map.size() == 0)
            return "";
        return list.peekLast().key;        
    }

    public static void test1(){
        AllOne obj = new AllOne();
        obj.inc("hello");
        obj.inc("world");
        obj.inc("hello");
        System.out.println(obj.getMaxKey());        
        obj.dec("world");
        obj.inc("hello");
        obj.inc("leet");
        System.out.println(obj.getMaxKey());        
        System.out.println(obj.getMinKey());        
    }

    public static void test2(){
        AllOne obj = new AllOne();
        obj.inc("hello");
        obj.inc("world");
        obj.inc("hello");
        System.out.println(obj.getMaxKey());        
        obj.dec("world");
        obj.inc("hello");
        System.out.println(obj.getMaxKey());        
        obj.inc("leet");
        obj.dec("hello");
        obj.dec("hello");
        obj.dec("hello");
        System.out.println(obj.getMaxKey());        
        System.out.println(obj.getMinKey());        
    }

    // ["AllOne","inc","inc","inc","inc","inc","dec","dec","getMaxKey","getMinKey"]
    // [[],["a"],["b"],["b"],["b"],["b"],["b"],["b"],[],[]]
    public static void test3(){
        AllOne obj = new AllOne();
        obj.inc("a");
        obj.inc("b");
        obj.inc("b");
        obj.inc("b");
        obj.inc("b");
        System.out.println(obj.getMaxKey());        
        obj.dec("b");
        obj.dec("b");
        System.out.println(obj.getMaxKey());        
        System.out.println(obj.getMinKey());        
    }


    public static void main(String[] args){
        //test1();
        //test2();
        test3();
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