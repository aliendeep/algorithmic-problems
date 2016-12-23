/*
Given a list of numbers, a[0], a[1], a[2], … , a[N-1], where 0 <= a[i] < 2^32. 
Find the maximum result of a[i] XOR a[j].

Could you do this in O(n) runtime?

Input: [3, 10, 5, 25, 2, 8]
Output: 28
*/

// Try to select two number whose highest msb bits differ
public class Solution {
    public int findMaximumXOR(int[] nums) {
        int maxXor = 0;
        int mask = 0;
        for(int i=31; i>=0; i--){
            mask = mask | (1<<i);
            Set<Integer> set = new HashSet<>();
            for(int n : nums){
                // Ignore right bits (i+1 .. 0)
                set.add(n & mask);
            }

            // A ^ B = C, then A ^ C = B, B ^ C = A
            int t = maxXor | (1<<i);
            for(int prefix : set){
                if(set.contains(t ^ prefix)){
                    maxXor = t;
                    break;
                }
            }
        }
        return maxXor;
    }
}
