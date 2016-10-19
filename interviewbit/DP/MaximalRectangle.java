/*
Given a 2D binary matrix filled with 0’s and 1’s, find the largest rectangle containing all ones and return its area.

Bonus if you can solve it in O(n^2) or less.

Example :

A : [  1 1 1
       0 1 1
       1 0 0 
    ]

Output : 4 

As the max area rectangle is created by the 2x2 rectangle created by (0,1), (0,2), (1,1) and (1,2)

*/

public class MaximalRectangle {
    // if any previous height is higher than current height, then we don't need to remember that previous height 
    public int largestRectangleArea(int[] heights){
        int n = heights.length;
        // stack contains indices
        Deque<Integer> stk = new LinkedList<>();
        int maxArea = 0;
        for(int i=0; i<=n; i++){
            // stack is not empty (i is either past last index or height of A[i] < height of top of the stack)
            while(!stk.isEmpty() && (i == n || heights[i] < heights[stk.peekFirst()])){
                int height = heights[stk.removeFirst()];
                // if stack is empty then the rectangle can extend to 0
                // Notice the pop: i-stk.top()-1 extend the rectangle to the earlier height 
                maxArea = Math.max(maxArea, height * (stk.isEmpty() ? i :  i - stk.peekFirst() - 1));
            }
            stk.addFirst(i);
        }
        return maxArea;
    }
    
  public int maximalRectangle(ArrayList<ArrayList<Integer>> a) {
        int r = a.size();
        if(a == null || r == 0)
            return 0;
            
        int c = a.get(0).size();
        int[] hist = new int[c];
        int maxArea = 0;
        for(int i=0; i<r; i++){
          for(int j=0; j<c; j++){
              if(i == 0){
                  hist[j] = a.get(i).get(j);
                  continue;
              }
              if(a.get(i).get(j) == 0)
                  hist[j] = 0;
              else
                  hist[j] += 1; 
          }
          maxArea = Math.max(maxArea, largestRectangleArea(hist));
        }
        return maxArea;
  }
}
