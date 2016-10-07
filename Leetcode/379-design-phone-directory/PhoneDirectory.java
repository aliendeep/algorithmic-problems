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