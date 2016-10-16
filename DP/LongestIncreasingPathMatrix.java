// Time complexity: O(rc)
// Space complexity: O(rc)
public class LongestIncreasingPathMatrix {
    int[][] move = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    public int dfs(int[][] matrix, int r, int c, int[][] d, int prev){
        if(matrix[r][c] <= prev)
            return 0;
    
        if(d[r][c] != 0)
            return d[r][c];
        
        int len = 0;
        for(int i=0; i<4; i++){
            int r1 = r+move[i][0];            
            int c1 = c+move[i][1];
            if(r1 < 0 || r1 >= matrix.length || c1 < 0 || c1 >= matrix[0].length)
                continue;
            len = Math.max(len, dfs(matrix, r1, c1, d, matrix[r][c]));
        } 
        d[r][c] = len + 1;
        return d[r][c]; 
    }
    
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) 
            return 0;

        int[][] d = new int[matrix.length][matrix[0].length];
        int maxLength = 1;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                maxLength = Math.max(maxLength, dfs(matrix, i, j, d, Integer.MIN_VALUE));
            }
        }
        return maxLength;
    }
}