/*
Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.

For example,
Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
Therefore, return the max sliding window as [3,3,5,5,6,7].

Note: 
You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for non-empty array.

Follow up:
Could you solve it in linear time?

Hint:

How about using a data structure such as deque (double-ended queue)?
The queue size need not be the same as the window’s size.
Remove redundant elements and the queue should store only elements that need to be considered.
*/

// Use double ended queue
// cleaner
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if(n == 0)  return new int[0];
        int[] result = new int[n - k + 1];
        int index = 0;
        // Store indices
        Deque<Integer> Q = new LinkedList<>();
        for(int i=0; i<n; ++i){
            // remove out of window element
            if(!Q.isEmpty() && Q.peek() < i - k + 1)
                Q.remove();
            // Remove smaller or equal numbers from the tail
            while(!Q.isEmpty() && nums[Q.peekLast()] <= nums[i])
                Q.removeLast();

            Q.add(i);
            if(i >= k-1){
                result[index++] = nums[Q.peek()];
            }
        }
        return result;
    }
}

class Solution2 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if(n == 0)
            return new int[0];
        
        int[] result = new int[n-k+1];
        // contains the index
        Deque<Integer> window = new LinkedList<>();
        
        // insert first number into the window
        for(int i=0; i<k; i++){
           // remove all previous elements that are equal or smaller than nums[i]
           while(!window.isEmpty() && nums[window.peekLast()] <= nums[i]){
                window.removeLast();
            }
            window.addLast(i);
        }
        int winIndex = 0;
        for(int i=k; i<n; i++){
            // First element in the window is part of the result
            result[winIndex++] = nums[window.peek()];
            // if the index of front element is out of the window, then remove
            if(!window.isEmpty() && i - window.peek() >= k)
                window.removeFirst();
            // remove all previous elements that are equal or smaller than nums[i]
            while(!window.isEmpty() && nums[window.peekLast()] <= nums[i])
                window.removeLast();
            window.addLast(i);
        }
        // last window
        result[winIndex++] = nums[window.peek()];
        return result;
    }
}
