/*
Design a data structure that supports all following operations in average O(1) time.

Note: Duplicate elements are allowed.
insert(val): Inserts an item val to the collection.
remove(val): Removes an item val from the collection if present.
getRandom: Returns a random element from current collection of elements. 
The probability of each element being returned is 
linearly related to the number of same value the collection contains.
Example:

// Init an empty collection.
RandomizedCollection collection = new RandomizedCollection();

// Inserts 1 to the collection. Returns true as the collection did not contain 1.
collection.insert(1);

// Inserts another 1 to the collection. Returns false as the collection contained 1. Collection now contains [1,1].
collection.insert(1);

// Inserts 2 to the collection, returns true. Collection now contains [1,1,2].
collection.insert(2);

// getRandom should return 1 with the probability 2/3, and returns 2 with the probability 1/3.
collection.getRandom();

// Removes 1 from the collection, returns true. Collection now contains [1,2].
collection.remove(1);

// getRandom should return 1 and 2 both equally likely.
collection.getRandom();

Sample Input:
["RandomizedCollection","insert","insert","insert","getRandom","remove","getRandom"]
[[],[1],[1],[2],[],[1],[]]

["RandomizedCollection","insert","remove","insert"]
[[],[1],[1],[1]]

["RandomizedCollection","insert","insert","insert","insert","insert","insert","remove","remove","remove","remove","getRandom","getRandom","getRandom","getRandom","getRandom","getRandom","getRandom","getRandom","getRandom","getRandom"]
[[],[10],[10],[20],[20],[30],[30],[10],[10],[30],[30],[],[],[],[],[],[],[],[],[],[]]
*/
// Allow duplicates
// Design a data structure that supports all following operations in average O(1) time.
public class RandomizedCollection {
    // Set to keep indices of all duplicates number
    Map<Integer, Set<Integer>> location;
    List<Integer> A;
    Random rand;

    /** Initialize your data structure here. */
    public RandomizedCollection() {
        A = new ArrayList<>();
        location = new HashMap<>();  
        rand = new Random();
    }
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        boolean returnVal = location.containsKey(val);
        Set<Integer> locSet = location.get(val);
        if(locSet == null)
            locSet = new HashSet<Integer>();

        A.add(val);
        locSet.add(A.size()-1);
        location.put(val, locSet);
        return !returnVal;
    }
    
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if(location.containsKey(val) == false)
            return false;
        // Get the first element from the set
        Set<Integer> locSet = location.get(val);
        // Get the first index and remove it from the hashtable
        int index = locSet.iterator().next();
        // Delete the index
        location.get(val).remove(index);
        // if the location set becomes empty remove the val
        if(location.get(val).isEmpty())
            location.remove(val);
            
        // Swap this number with the last one in the original array
        if(index < A.size()-1){
            int lastNum = A.get(A.size()-1);
            // Replace A[index] value by last num
            A.set(index, lastNum);
            // update location for the last number
            location.get(lastNum).remove(A.size()-1);
            location.get(lastNum).add(index);
        }
        // delete the last num
        A.remove(A.size()-1);
        return true;
    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
        return A.get(rand.nextInt(A.size()));
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */