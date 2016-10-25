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
import java.util.*;

public class AllOne {
    class DoublyLinkedList<T>{
        public DoublyLinkedList<T> prev, next;
        public T data;

        public DoublyLinkedList(T data){
            this.data = data;
            this.prev = null;
            this.next = null;
        }
        public DoublyLinkedList(){
            this.data = null;
            this.prev = null;
            this.next = null;
        }
    }   
    class DLL<T>{
        DoublyLinkedList<T> dummyHead, dummyTail;       
        int listSize;

        public DLL(){
            // dummy head and dummyTail
            dummyHead = new DoublyLinkedList();
            dummyTail = new DoublyLinkedList();
            dummyHead.next = dummyTail;
            dummyTail.prev = dummyHead;
            listSize = 0;
        }

        public int size(){
            return listSize;
        }

        public DoublyLinkedList<T> peekFirst(){
            if(dummyHead.next == dummyTail)
                return null;
            return dummyHead.next;
        }

        public DoublyLinkedList<T> peekLast(){
            if(dummyTail.prev == dummyHead)
                return null;
            return dummyTail.prev;
        }

        // insert node after prev
        public void insertAfter(DoublyLinkedList<T> prev, DoublyLinkedList<T> node){
            DoublyLinkedList<T> next = prev.next;
            node.next = next;
            next.prev = node;
            node.prev = prev;
            prev.next = node;
            listSize++;
        }

       // insert node before cur
        public void insertBefore(DoublyLinkedList<T> cur, DoublyLinkedList<T> node){
            node.next = cur;
            node.prev = cur.prev;
            cur.prev.next = node;
            cur.prev = node;
            listSize++;
        }
        // add at the front
        public void addFirst(DoublyLinkedList<T> node){
            insertAfter(dummyHead, node);
        }

        // add in the dummyTail
        public void addLast(DoublyLinkedList<T> node){
            DoublyLinkedList<T> prev = dummyTail.prev;
            node.prev = prev;
            prev.next = node;
            node.next = dummyTail;            
            dummyTail.prev = node;
            listSize++;
        }

        // Remove this particular node
        public void remove(DoublyLinkedList<T> node){
            DoublyLinkedList<T> prev = node.prev;
            DoublyLinkedList<T> next = node.next;
            prev.next = next;
            next.prev = prev;
            listSize--;
        }
    }

    // Nested Doubly Linked List
    class Pair{
        public String key;
        public int val;
        public Pair(String k, int v){
            key = k;
            val = v;
        }
    }

    class Bucket{
        public DLL<Pair> nestedList;
        public int val;
        public Bucket(DLL<Pair> l, int val){
            nestedList = l;
            this.val  = val;
        }

        void print(){
            if(nestedList.size() == 0)
                return;
            DoublyLinkedList<Pair> cur = nestedList.dummyHead.next;
            while(cur != nestedList.dummyTail){
                System.out.print("<"+cur.data.key + " " + cur.data.val + "> ");
                cur = cur.next;
            }
            System.out.println();
        }
    }

    class Entry{
        public int val;
        public DoublyLinkedList<Bucket> bucket;
        public DoublyLinkedList<Pair> node;        
        public Entry(DoublyLinkedList<Bucket> b, DoublyLinkedList<Pair> n, int v){
            this.bucket = b;
            node = n;
            this.val = v;
        }

        public void print(){
            Bucket c = bucket.data;
            if(c != null)
                c.print();
            else
             System.out.println("here");
        }
    }

    public void print(DLL<Pair> l){
        DoublyLinkedList<Pair> cur = l.dummyHead.next;
        while(cur != l.dummyTail){
            System.out.print("<"+cur.data.key + " " + cur.data.val + "> ");
            cur = cur.next;
        }
        System.out.println();
    }

    public void printBucket(){
        System.out.println("Bucket Content");
        DoublyLinkedList<Bucket> cur = bucketList.dummyHead.next;
        while(cur != bucketList.dummyTail){
            cur.data.print();
            cur = cur.next;
        }
        System.out.println();
    }

    // <Key, <bucket, node>>
    Map<String, Entry> map;
    // val, bucket
    Map<Integer, DoublyLinkedList<Bucket>> bucketMap;
    DLL<Bucket> bucketList;

    /** Initialize your data structure here. */
    public AllOne() {
        map = new HashMap<>();        
        bucketMap = new HashMap<>();        
        bucketList = new DLL<Bucket>();
    }

    public void operation(String key, int prevValue, boolean incrementFlag){
        Entry previousEntry = map.get(key); 
        //if(previousEntry != null)
            //System.out.println("Previous Value of " + key + " "+previousEntry.val);

        int newValue = 0;
        if(incrementFlag)
            newValue = prevValue + 1;
        else
            newValue = prevValue - 1;

        //System.out.println("key: "+ key + " " +newValue);
        if(newValue > 0){
            // Get the bucket
            DoublyLinkedList<Bucket> buck = bucketMap.get(newValue);

            // Create a new node
            DoublyLinkedList node = new DoublyLinkedList(new Pair(key, newValue));
            if(buck == null){
                DLL<Pair> nestedList = new DLL();
                nestedList.addLast(node); 

                // Create new bucket
                DoublyLinkedList<Bucket> newBucket = new DoublyLinkedList<Bucket>(new Bucket(nestedList, newValue));
                // if the new value is 1
                if(incrementFlag){
                    if(previousEntry == null){
                        //System.out.println("Insert First time this val - key " + key + " val: " + newValue);
                        bucketList.addFirst(newBucket);
                    }
                    else{
                        //System.out.println("Inserting After value "+previousEntry.bucket.data.val +  " key " + key + " val: " + newValue);
                        bucketList.insertAfter(previousEntry.bucket, newBucket);
                    }
                }
                // decrement
                else{
                    //System.out.println("Inserting Before: "+previousEntry.bucket.data.val + " newval " + newValue);
                    bucketList.insertBefore(previousEntry.bucket, newBucket);
                }

                // update bucket map
                bucketMap.put(newValue, newBucket);                
                map.put(key, new Entry(newBucket, node, newValue)); 
                //newBucket.data.print();           
            }
            else{
                //System.out.println("Adding at the tail " + key + " val: " + newValue);                        
                Bucket data = buck.data;
                // always append at the tail
                data.nestedList.addLast(node);
                //data.print();
                map.put(key, new Entry(buck, node, newValue)); 
            }
            //System.out.println("Updating key: " + key +  " nv: " +newValue);
            //buck.data.print();                
        }

        // Remove the previous entry
        if(previousEntry != null){
            DoublyLinkedList<Bucket> bucketData = previousEntry.bucket;            
            bucketData.data.nestedList.remove(previousEntry.node);

            //System.out.println("#Size: " + bucketData.data.nestedList.size());
            if(bucketData.data.nestedList.size() == 0){
                //System.out.println("Remove " + key + " " + previousEntry.bucket.data.val);
                bucketList.remove(previousEntry.bucket);
                bucketMap.remove(previousEntry.bucket.data.val);
            }
        }

        if(newValue == 0){
            map.remove(key);
        }
        //printBucket();
    }

    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        if(!map.containsKey(key))
            operation(key, 0, true);
        else
            operation(key, map.get(key).val, true);
    }
    
    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        if(!map.containsKey(key))
            return;
        operation(key, map.get(key).val, false);
    }
    
    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        if(map.size() == 0)         return "";   
        DLL<Pair> nestedList = bucketList.peekLast().data.nestedList;
        //System.out.print("Maximum: ");
        //print(nestedList);
        return nestedList.peekFirst().data.key;
    }
    
    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        if(map.size() == 0)         return "";           
        DLL<Pair> nestedList = bucketList.peekFirst().data.nestedList;
        //System.out.print("Min: ");
        //print(nestedList);

        return nestedList.peekFirst().data.key;
    }

    public static void test1(){
        AllOne obj = new AllOne();
        obj.inc("hello");
        obj.inc("world");
        obj.inc("hello");
        obj.inc("hello");
        obj.inc("world");
        obj.inc("world");
        System.out.println(obj.getMaxKey());        
        obj.inc("leet");
        obj.inc("leet");
        obj.inc("leet");
        System.out.println(obj.getMaxKey());        
        obj.dec("leet");
        System.out.println(obj.getMaxKey());        
        obj.dec("world");
        System.out.println(obj.getMaxKey());        
        obj.dec("leet");
        System.out.println(obj.getMaxKey());        
        System.out.println(obj.getMaxKey());        
        System.out.println(obj.getMinKey());        
    }

    public static void test2(){
        AllOne obj = new AllOne();
        obj.inc("hello");
        obj.inc("world");
        obj.inc("hello");
        obj.dec("world");
        obj.inc("hello");
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
        System.out.println(obj.getMinKey());        
        obj.dec("b");
        obj.dec("b");
        obj.dec("b");
        obj.inc("a");
        System.out.println(obj.getMaxKey());        
        System.out.println(obj.getMinKey());        
        obj.dec("b");
    }


    public static void main(String[] args){
        test1();
        test2();
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