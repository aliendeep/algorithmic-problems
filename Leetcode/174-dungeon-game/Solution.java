public class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int row = dungeon.length;
        if(row == 0)
            return 0;
        int col = dungeon[0].length;
        
        int[][] dp = new int[row][col];
        // Init
        dp[row-1][col-1] = Math.max(1 - dungeon[row-1][col-1], 1);

        // last col
        for(int i=row-2; i>=0; i--){
            dp[i][col-1] = Math.max(dp[i+1][col-1] - dungeon[i][col-1], 1);
        }
        
        // last row
        for(int j=col-2; j>=0; j--){
            dp[row-1][j] = Math.max(dp[row-1][j+1] - dungeon[row-1][j], 1);
            
        }
        
        for(int i=row-2; i>=0; i--){
            for(int j=col-2; j>=0; j--){
                int x = Math.max(dp[i+1][j] - dungeon[i][j], 1);
                int y = Math.max(dp[i][j+1] - dungeon[i][j], 1);
                dp[i][j] = Math.min(x, y);
            }
        }
        
        return dp[0][0];
    }
}