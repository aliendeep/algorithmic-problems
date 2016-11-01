/*
https://leetcode.com/problems/shuffle-an-array/

Shuffle a set of numbers without duplicates.

Example:

// Init an array with set 1, 2, and 3.
int[] nums = {1,2,3};
Solution solution = new Solution(nums);

// Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.
solution.shuffle();

// Resets the array back to its original configuration [1,2,3].
solution.reset();

// Returns the random shuffling of array [1,2,3].
solution.shuffle();
*/

// Offline Sampling
public class ShuffleArray {
    int[] original;
    Random gen;

    public Solution(int[] nums) {
        original = nums;
        gen = new Random();
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return original;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        int n = original.length;
        int[] A = new int[n];
        for(int i=0; i<n; i++)
            A[i] = original[i];
            
        for(int i=0; i<n; i++){
            // Generate a random number in [i, A.size()-1]
            int index = i + gen.nextInt(n - i);
            // Swap
            int t = A[i];
            A[i] = A[index];
            A[index] = t;
        }
        return A;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */

public class ShuffleArray2{
    int[] original;
    Random gen;

    public Solution(int[] nums) {
        original = nums;
        gen = new Random();
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return original;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        int n = original.length;
        int[] A = new int[n];
        for(int i=0; i<n; i++)
            A[i] = original[i];
            
        for(int i=0; i<n; i++){
            // Generate a random number between [0..i]
            int index = gen.nextInt(i+1);
            // Swap
            int t = A[i];
            A[i] = A[index];
            A[index] = t;
        }
        return A;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */