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

// Trie Solution
public class Solution {
    class TrieNode{
        TrieNode[] children;
        public TrieNode(){
            children = new TrieNode[2];
        }
    }
    public int findMaximumXOR(int[] nums) {
        int n = nums.length;
        if(n == 0)      return 0;
        TrieNode root = new TrieNode();
        // build tree
        for(int num : nums){
            TrieNode cur = root;
            for(int i=31; i>=0; --i){
                int bit = (num & (1<<i)) != 0 ? 1 :  0;
                if(cur.children[bit] == null){
                    cur.children[bit] = new TrieNode();
                }
                cur = cur.children[bit];
            }
        }
        // find result
        int max = 0;
        for(int num : nums){
            TrieNode cur = root;
            int curSum = 0;
            for(int i=31; i>=0; --i){
                int bit = (num & (1<<i)) != 0 ? 1 :  0;
                if(cur.children[bit^1] != null){
                    curSum += (1<<i);
                    cur = cur.children[bit^1];
                }
                else
                    cur = cur.children[bit];
            }
            max = Math.max(max, curSum);
        }
        return max;
    }
}
