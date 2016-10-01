/*
Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.


Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].

The largest rectangle is shown in the shaded area, which has area = 10 unit.

For example,
Given heights = [2,1,5,6,2,3],
return 10.

*/
public class Solution {
    // Stack
    // If any previous height is larger than current height, then we can't use the previous height
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        if(n == 0)
            return 0;
        int maxArea = 0;
        // stack contains indices
        Deque<Integer> stk = new LinkedList<>();
        for(int i=0; i<=n; i++){
            // end of the process or height of heights[i] < height of top of the stack
            while(!stk.isEmpty() && (i == n || (heights[stk.peekFirst()] > heights[i]))){
                int topIndex = stk.removeFirst();
                // if stack is empty then the rectangle can extend to 0
                // Notice the pop: i-s.peekFirst() - 1 extend the rectangle to the earlier height as
                // s.peekFirst() contains the previous index
                int width = stk.isEmpty() ? i : (i - stk.peekFirst() - 1);
                int area = heights[topIndex] * width;
                maxArea = Math.max(maxArea, area);
            }
            stk.addFirst(i);
        }
        
        return maxArea;
    }
}