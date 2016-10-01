class Solution {
public:
    bool isValid(int r, int c, int nrow, int ncol){
        if(r >= 0 && r < nrow &&  c >= 0  && c < ncol)
            return true;
        return false;
    }

    int longestIncreasingPath(vector<vector<int>>& matrix) {
        int r = matrix.size();
        if(r == 0)
            return 0;
        int c = matrix[0].size();
    
        int move[4][2] = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        // Construct the graph, key is the number
        map<int, vector<pair<int, int>>> M;
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                M[matrix[i][j]].push_back({i, j});
            }
        }
        
        vector<vector<int>> dp(r, vector<int>(c, 0));
        int max_length = 0;
        int cnt = 0;
        // sorted
        for(auto it=M.cbegin(); it != M.cend(); it++){
            auto v = it->second;
            for(auto& p : v){
                int x = p.first;
                int y = p.second;
                // initialize
                if(cnt == 0){
                    dp[x][y] = 1;
                }
                else{
                    int max_value = 0;
                    for(int i=0; i<4; i++){
                        int x1 = x + move[i][0]; 
                        int y1 = y + move[i][1]; 
                        if(!isValid(x1, y1, r, c))
                            continue;
                        if(max_value < dp[x1][y1] && matrix[x][y] != matrix[x1][y1])
                            max_value = dp[x1][y1];
                    }
                    dp[x][y] = max_value + 1;
                }
                if(max_length < dp[x][y])
                    max_length = dp[x][y];
            }
            cnt++;
        }   
        
      for(int i=0; i<r; i++){
        for(int j=0; j<c; j++){
            cout << dp[i][j] << " ";
        }
        cout << endl;
      }
      return max_length;
    }
};