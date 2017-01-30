/*
Design a Phone Directory which supports the following operations:

get: Provide a number which is not assigned to anyone.
check: Check if a number is available or not.
release: Recycle or release a number.
Example:

// Init a phone directory containing a total of 3 numbers: 0, 1, and 2.
PhoneDirectory directory = new PhoneDirectory(3);

// It can return any available phone number. Here we assume it returns 0.
directory.get();

// Assume it returns 1.
directory.get();

// The number 2 is available, so return true.
directory.check(2);

// It returns 2, the only number that is left.
directory.get();

// The number 2 is no longer available, so return false.
directory.check(2);

// Release number 2 back to the pool.
directory.release(2);

// Number 2 is available again, return true.
directory.check(2);
*/

public class PhoneDirectory {
    // Queue
    Queue<Integer> Q;
    Set<Integer> used;
    int maxNum; 
    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    public PhoneDirectory(int maxNumbers) {
        maxNum = maxNumbers;
        used = new HashSet<>();
        Q = new LinkedList<>();
        for(int i=0; i<maxNumbers; i++){
            Q.add(i);
        }
    }
    
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    public int get() {
        if(Q.isEmpty())     return -1;
        int n = Q.poll();
        used.add(n);
        return n;
    }
    
    /** Check if a number is available or not. */
    public boolean check(int number) {
        if(number < 0 || number >= maxNum)
            return false;
        return !used.contains(number);
    }
    
    /** Recycle or release a number. */
    public void release(int number) {
        // if the number is already in use, then we can remove it from the used list and add it to the available number queue
        if(used.remove(number)){
            Q.add(number);
        }
    }

    public static void main(String[] args){
      PhoneDirectory obj = new PhoneDirectory(3);
      obj.release(2);
      System.out.println(obj.get());
      obj.release(2);
      obj.release(0);
      System.out.println(obj.get());
      System.out.println(obj.get());
      System.out.println(obj.check(1));
      System.out.println(obj.get());
      obj.release(0);
      System.out.println(obj.get());
      obj.release(0);
      obj.release(0);
      System.out.println(obj.get());
      System.out.println(obj.check(1));
      obj.release(1);
    }
}
/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */

// https://docs.oracle.com/javase/7/docs/api/java/util/BitSet.html
class PhoneDirectory {
    // Use Bitset to return smallest free index
    // Set the bit if it's not available
    BitSet bitset; 
    int smallestFreeIndex;
    int maxLimit;
    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    public PhoneDirectory(int maxNumbers) {
        maxLimit = maxNumbers;
        bitset = new BitSet(maxNumbers);
        smallestFreeIndex = 0;
    }
    
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    // Needs to return smallest available number
    // Returning any available number will not pass the OJ
    public int get() {
        if(smallestFreeIndex == maxLimit)
            return -1;
        int available = smallestFreeIndex;
        // Mark the smallestFreeIndex as not available
        bitset.set(smallestFreeIndex);
        // Get next smallest clear bit index starting from previous smallestFreeIndex
        smallestFreeIndex = bitset.nextClearBit(smallestFreeIndex);
        return available;
    }
    
    /** Check if a number is available or not. */
    public boolean check(int number) {
        if(number < 0 || number >= maxLimit)    
            return false;
        // if the index is not set yet, then the number is free.
        return bitset.get(number) == false;
    }
    
    /** Recycle or release a number. */
    public void release(int number) {
        if(number < 0 || number >= maxLimit)    return;
        // If the number is not set yet
        if(bitset.get(number) == false)         return;
        bitset.clear(number);
        if(number < smallestFreeIndex)
            smallestFreeIndex = number;
    }
}

// Solution 3 : DLL Solution
// Doubly linked list node
class Node{
    int val;
    public Node prev, next;
    public Node(int v){
        val = v;
        prev = null;
        next = null;
    }
}

class DLL{
    Node dummyHead, dummyTail;
    public DLL(){
        dummyHead = new Node(-1);
        dummyTail = new Node(-1);
        //dummyHead.next = dummyHead.prev = dummyTail;
        //dummyTail.prev = dummyTail.next = dummyHead;
        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;
    }
    
    public void addLast(Node t){
        t.next = dummyTail;
        t.prev = dummyTail.prev;
        dummyTail.prev.next = t;
        dummyTail.prev = t;
    }

    public Node removeFirst(){
        Node first = dummyHead.next;
        dummyHead.next = first.next;
        first.next.prev = dummyHead;
        return first;
    }
}

public class PhoneDirectory {
    DLL list;
    Map<Integer, Node> map;
    
    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    public PhoneDirectory(int maxNumbers) {
        list = new DLL();
        map = new HashMap<>();
        for(int i=0; i<maxNumbers; ++i){
            Node n = new Node(i);
            list.addLast(n);
            map.put(i, n);
        }
    }
    
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    // O(1)
    public int get() {
        if(map.size() == 0)
            return -1;
        Node first = list.removeFirst();
        map.remove(first.val);
        
        return first.val;
    }
    
    // O(1)
    /** Check if a number is available or not. */
    public boolean check(int number) {
        if(!map.containsKey(number))    return false;
        return true;
    }
    
    // O(1)
    /** Recycle or release a number. */
    public void release(int number) {
        if(map.containsKey(number))     return;
        Node n = new Node(number);
        list.addLast(n);
        map.put(number, n);
    }
}
