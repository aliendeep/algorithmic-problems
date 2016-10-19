public class Solution {
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
    //  convert the 2D matrix. if current row-col is '0', simple treat this row-col in histogram as 0.
    public int maximalRectangle(char[][] matrix) {
        int r = matrix.length;
        if(matrix == null || r == 0 || matrix[0].length == 0)
            return 0;
        int c = matrix[0].length;

        int[] hist = new int[c];
        int maxArea = 0;
        for(int i=0; i<r; i++){
          for(int j=0; j<c; j++){
              if(i == 0){
                  hist[j] = matrix[i][j] - '0';
                  continue;
              }
              if(matrix[i][j] == '0')
                  hist[j] = 0;
              else
                  hist[j] += 1; 
          }
          maxArea = Math.max(maxArea, largestRectangleArea(hist));
        }
        return maxArea;
    }
}