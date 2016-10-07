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

// https://docs.oracle.com/javase/7/docs/api/java/util/BitSet.html
public class PhoneDirectory {
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

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */