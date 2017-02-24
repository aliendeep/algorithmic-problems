/*
Given a sequence of n integers a1, a2, ..., an, a 132 pattern is a subsequence ai, 
aj, ak such that i < j < k and ai < ak < aj. Design an algorithm that takes a 
list of n numbers as input and checks whether there is a 132 pattern in the list.

Note: n will be less than 15,000.

Example 1:
Input: [1, 2, 3, 4]

Output: False

Explanation: There is no 132 pattern in the sequence.
Example 2:
Input: [3, 1, 4, 2]

Output: True

Explanation: There is a 132 pattern in the sequence: [1, 4, 2].
Example 3:
Input: [-1, 3, 2, 0]

Output: True

Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0] 
and [-1, 2, 0].
*/
// O(n^2)
public class Solution {
    public boolean find132pattern(int[] nums) {
        int n = nums.length;
        if(n <= 2)   return false;
        int[] min = new int[n];
        min[0] = nums[0];
        for(int i=1; i<n; ++i){
            min[i] = Math.min(min[i-1], nums[i]);
        }
    
        TreeSet<Integer> set = new TreeSet<>();
        set.add(nums[n-1]);
    
        for(int i=n-2; i>0; --i){
            if(min[i-1] < nums[i]){
                Set<Integer> rightSubSet = set.subSet(min[i-1] + 1, nums[i]);
                if(rightSubSet.size() > 0)
                    return true;
            }
            set.add(nums[i]);
        }
        return false;
    }
}


// O(nlogn)
class Solution2 {
    public boolean find132pattern(int[] nums) {
        int n = nums.length;
        if(n <= 2)   return false;
        // Construct so far min
        int[] min = new int[n];
        min[0] = nums[0];
        for(int i=1; i<n; ++i){
            min[i] = Math.min(min[i-1], nums[i]);
        }
    
        TreeSet<Integer> set = new TreeSet<>();
        set.add(nums[n-1]);
    
        for(int i=n-2; i>0; --i){
            if(min[i-1] < nums[i]){
                Integer floor = set.floor(nums[i] - 1);
                if(floor != null && floor > min[i-1])
                    return true;
                Integer ceil = set.ceiling(min[i-1] + 1);
                if(ceil != null && ceil < nums[i])
                    return true;
            }
            set.add(nums[i]);
        }
        return false;
    }
}

// Stack
public class Solution {
    // O(n)
    // Find a1 < a2 > a3
    public boolean find132pattern(int[] nums) {
        int n = nums.length;
        if(n < 3)
            return false;
        // numbers stored in the stack are a2
        Deque<Integer> stk = new LinkedList<>();
        // Keep track of the largest candidate of a3
        int a3 = Integer.MIN_VALUE;
        // Start from the end
        for(int i=n-1; i>=0; i--){
            // when we encounter a number smaller than a3, then we have found a 
            // sequence a1 < a3
            if(nums[i] < a3)
                return true;
            // Candidate a3 if there is any number nums[i] (on the left) is greater than a3
            while(!stk.isEmpty() && nums[i] > stk.peekFirst()){
                a3 = stk.pop();
            }
            stk.push(nums[i]);
        }
        return false;
    }
}
