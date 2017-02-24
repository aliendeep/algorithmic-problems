/*
Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle 
containing only 1's and return its area.

For example, given the following matrix:

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0
Return 6.

Sample Input:
["10100","10111","11111","10010"]
["11"]
["11111111","11111110","11111110","11111000","01111000"]
Sample Output:
6
2
21
*/
// Less Space and time O(rc)
public class Solution {
    // if any previous height is higher than current height, then we don't need 
    // to remember that previous height 
    public int largestRectangleArea(int[] heights){
        int n = heights.length;
        // stack contains indices
        Deque<Integer> stk = new LinkedList<>();
        int maxArea = 0;
        for(int i=0; i<=n; i++){
            // stack is not empty (i is either past last index or height of A[i] < height of top of the stack)
            while(!stk.isEmpty() && (i == n || heights[i] < heights[stk.peekFirst()])){
                int height = heights[stk.pop()];
                // if stack is empty then the rectangle can extend to 0
                // Notice the pop: i-stk.top()-1 extend the rectangle to the earlier height 
                maxArea = Math.max(maxArea, height * (stk.isEmpty() ? i :  i - stk.peekFirst() - 1));
            }
            stk.push(i);
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

class Solution2 {
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
        int[][] cumSum = new int[r][c];
        
        int maxArea = 0;
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                if(i == 0){
                    cumSum[i][j] = (matrix[i][j] == '0') ? 0 : 1;
                    continue;
                }                
                if(matrix[i][j] == '1')
                    cumSum[i][j] = cumSum[i-1][j] + 1;
                else
                    cumSum[i][j] = 0;
            }
            maxArea = Math.max(maxArea, largestRectangleArea(cumSum[i]));
        }
        return maxArea;
    }
}

// O(r^2c)
class Solution3 {
    class HeightWidth{
        int h, w;
        public HeightWidth(int h1, int w1){
            h = h1;
            w = w1;
        }
    }
    public int maximalRectangle(char[][] matrix) {
        int r = matrix.length;
        if(matrix == null || r == 0 || matrix[0].length == 0)
            return 0;
        int c = matrix[0].length;
        HeightWidth[][] dp = new HeightWidth[r][c];     
        
        // init
        dp[r-1][c-1] = matrix[r-1][c-1] == '0' ? new HeightWidth(0, 0) : new HeightWidth(1, 1);
        // last column
        for(int i=r-2; i>=0; i--){
            int h = 0, w = 0;
            if(matrix[i][c-1] == '1'){
                h = dp[i+1][c-1].h + 1;
                w = 1;
            }
            dp[i][c-1] = new HeightWidth(h, w);
        }
        
        // last row
        for(int j=c-2; j>=0; j--){
            int h = 0, w = 0;
            if(matrix[r-1][j] == '1'){
                h = 1;
                w = dp[r-1][j+1].w + 1;
            }
            dp[r-1][j] = new HeightWidth(h, w);
        }
        
        for(int i=r-2; i>=0; i--){
            for(int j=c-2; j>=0; j--){
                int h = 0, w = 0;
                if(matrix[i][j] == '1'){
                    h = dp[i+1][j].h + 1;
                    w = dp[i][j+1].w + 1;
                }
                dp[i][j] = new HeightWidth(h, w);
            }
        }
        int maxRect = 0;
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                // if it's a candidate
                if(matrix[i][j] == '1' && dp[i][j].w * dp[i][j].h > maxRect){
                    // get the minimum width
                    int width = c;
                    for(int k=0; k<dp[i][j].h; ++k){
                        width = Math.min(width, dp[i+k][j].w);
                        maxRect = Math.max(maxRect, width*(k+1));
                    }
                }
            }
        }
        return maxRect;
    }
}

// DP
class Solution4 {
    // height -  number of '1's above including the current one. 
    // The value of left & right means the boundaries of the rectangle which contains the current point with a height of value height.
    // https://discuss.leetcode.com/topic/6650/share-my-dp-solution    
    // Alternative: DP
    public int maximalRectangle(char[][] matrix) {
        int r = matrix.length;
        if(r == 0)
            return 0;
        int c = matrix[0].length;
        int[] height = new int[c];
        Arrays.fill(height, 0);
        
        int[] left = new int[c];
        Arrays.fill(left, 0);
        
        int[] right = new int[c];
        Arrays.fill(right, c);
        
        int maxArea = 0;
        for(int i=0; i<r; i++){
            // Compute height
            for(int j=0; j<c; j++){
                height[j] = matrix[i][j] == '1' ? height[j] + 1 : 0;
            }
            
            // left boundary
            int curLeft = 0;
            for(int j=0; j<c; j++){
                if(matrix[i][j] == '1')
                    left[j] =  Math.max(left[j] /* prev */, curLeft);
                else{
                    left[j] = 0;
                    curLeft = j + 1;
                }
            }
            // right boundary
            int curRight = c;
            for(int j=c-1; j>=0; j--){
                if(matrix[i][j] == '1')
                    right[j] =  Math.min(right[j] /* prev */, curRight);
                else{
                    right[j] = c;
                    curRight = j;
                }
            } 
            
            for(int j = 0; j<c; j++){
                maxArea = Math.max(maxArea, (right[j] - left[j])*height[j]);
            }
        }
        return maxArea;
    }
}
