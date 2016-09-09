class Solution {
public:
    int calculateMinimumHP(vector<vector<int>>& dungeon) {
        int r = dungeon.size();
        if(r == 0)
            return 0;
        int c = dungeon[0].size();

        vector<vector<int>> dp(r, vector<int>(c, 0));
        dp[r-1][c-1] = max(1-dungeon[r-1][c-1], 1);

        // last row
        for(int j=c-2; j>=0; j--){
            dp[r-1][j] = max(dp[r-1][j+1] - dungeon[r-1][j], 1);
        }

        // last col
        for(int i=r-2; i>=0; i--){
            dp[i][c-1] = max(dp[i+1][c-1] - dungeon[i][c-1], 1);
        }

        for(int i=r-2; i>=0; i--){
            for(int j=c-2; j>=0; j--){
                int down = max(dp[i+1][j] - dungeon[i][j], 1);
                int right = max(dp[i][j+1] - dungeon[i][j], 1);
                dp[i][j] = min(down, right);
            }
        }
        return dp[0][0];
    }
};