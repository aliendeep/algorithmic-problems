/*
Design a data structure that supports all following operations in average O(1) time.

insert(val): Inserts an item val to the set if not already present.
remove(val): Removes an item val from the set if present.
getRandom: Returns a random element from current set of elements. Each element must 
have the same probability of being returned.
Example:

// Init an empty set.
RandomizedSet randomSet = new RandomizedSet();

// Inserts 1 to the set. Returns true as 1 was inserted successfully.
randomSet.insert(1);

// Returns false as 2 does not exist in the set.
randomSet.remove(2);

// Inserts 2 to the set, returns true. Set now contains [1,2].
randomSet.insert(2);

// getRandom should return either 1 or 2 randomly.
randomSet.getRandom();

// Removes 1 from the set, returns true. Set now contains [2].
randomSet.remove(1);

// 2 was already in the set, so return false.
randomSet.insert(2);

// Since 1 is the only number in the set, getRandom always return 1.
randomSet.getRandom();
*/
// Two HashMap
public class RandomizedSet {
    // num to index
    Map<Integer, Integer> valueMap;
    // index to num
    Map<Integer, Integer> indexMap;
    Random rand;
    
    /** Initialize your data structure here. */
    public RandomizedSet() {
        valueMap = new HashMap<>();
        indexMap = new HashMap<>();
        rand = new Random();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(valueMap.containsKey(val))
            return false;
        
        int index = valueMap.size();
        // notice the index value
        valueMap.put(val, index);
        indexMap.put(index, val);
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(!valueMap.containsKey(val))
            return false; 
        int size = valueMap.size();
        int index = valueMap.get(val);
        if(index != size-1){
            // swap
            int lastElement = indexMap.get(size-1);
            // update the index of last element
            valueMap.put(lastElement, index);
            indexMap.put(index, lastElement);
            
            // not necessary though    
            valueMap.put(val, size);
            indexMap.put(size-1, val);
        }
        // remove the last entry
        valueMap.remove(val);
        indexMap.remove(size-1);
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        int size = valueMap.size();
        // Generate a random number between 0 to size
        return indexMap.get(rand.nextInt(size));
    }
}

// Solution 2
class RandomizedSet {
    // O(1) time
    Map<Integer,Integer> location;
    List<Integer> A;
    Random rand;

    /** Initialize your data structure here. */
    public RandomizedSet() {
        A = new ArrayList<>();
        location = new HashMap<>();  
        rand = new Random();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(location.containsKey(val))
            return false;
        A.add(val);
        location.put(val, A.size()-1);
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(!location.containsKey(val))
            return false;
        int index = location.get(val); 
        // Swap this value with the last number
        if(index < A.size()-1){
            int lastNum = A.get(A.size()-1);
            // replace A[index] value by last number
            A.set(index, lastNum);
            // update location of last num
            location.put(lastNum, index);
        }
        location.remove(val);
        A.remove(A.size()-1);
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        return A.get(rand.nextInt(A.size()));
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
