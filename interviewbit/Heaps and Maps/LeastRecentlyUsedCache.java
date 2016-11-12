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
public class LeastRecentlyUsedCache {
    // Key, DLL
    Map<Integer, Node> cache;
    DLL list;
    int capacity;

    public LeastRecentlyUsedCache(int capacity) {
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
    public static void test1(){
        LeastRecentlyUsedCache ob = new LeastRecentlyUsedCache(1);
        ob.set(2, 1);
        System.out.println(ob.get(2));
        ob.set(2, 2);
        System.out.println(ob.get(2));
        ob.set(1, 1);
        System.out.println(ob.get(2));        
    }

    public static void test2(){
        LeastRecentlyUsedCache ob = new LeastRecentlyUsedCache(1);
        ob.set(2, 1);
        System.out.println(ob.get(2));
        ob.set(2, 2);
        System.out.println(ob.get(2));
        ob.set(1, 1);
        ob.set(4, 1);
        System.out.println(ob.get(2));        
    }

    public static void test3(){
        LeastRecentlyUsedCache ob = new LeastRecentlyUsedCache(1);
        ob.set(2, 1);
        System.out.println(ob.get(2));
        ob.set(3, 2);
        System.out.println(ob.get(2));
        System.out.println(ob.get(3));        
    }

    public static void main(String[] args){
        //test1();
        //test2();
        test3();
    }

}


// LinkedHashMap Solution
class Solution1 {
    LinkedHashMap<Integer, Integer> cache;
    public Solution1(int capacity) {
        cache = new LinkedHashMap<Integer, Integer>(capacity, 1.0f, true){
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> entry){
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
