class Solution {
public:
    void convert(vector<vector<char>>& board){
        // convert all O to X
        int r = board.size();
        int c = board[0].size();
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                if(board[i][j] == 'O')
                    board[i][j] = 'X';                
            }
        }
        
        // Convert all A's to O
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                if(board[i][j] == 'A')
                    board[i][j] = 'O';                
            }
        }
    }
    
    void BFS(vector<vector<char>>& board, vector<vector<int>>& visited, int row, int col){
        int move[4][2] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        queue<pair<int, int>> Q;
        Q.push({row, col});
        
        int r = board.size();
        int c = board[0].size();
        while(!Q.empty()){
            pair<int, int> t = Q.front();
            Q.pop();
            int x1 = t.first;
            int y1 = t.second;
            if(visited[x1][y1])  
                continue;
            board[x1][y1] = 'A';
            visited[x1][y1] = 1;
            
            for(int i=0; i<4; i++){
                int x = x1 + move[i][0];
                int y = y1 + move[i][1];
                if(x >= 0 && x < r && y >= 0 && y < c && !visited[x][y] && board[x][y] == 'O')
                    Q.push({x, y});
            }
        }
    }
    
    void solve(vector<vector<char>>& board) {
        if(board.size() == 0)   
            return;
        int r = board.size();
        int c = board[0].size();
        if(r <= 1 || c <= 1)    
            return;
        vector<vector<int>> visited(r, vector<int>(c, 0));
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                // start only from the border
                if(i==0 || j==0 || i==r-1 || j==c-1){
                    if(board[i][j] == 'O')
                        BFS(board, visited, i, j);
                }
            }
        }
        convert(board);
    }
};