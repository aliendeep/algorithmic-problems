class Solution {
public:
    // Alternative
    // http://stackoverflow.com/questions/15179536/kth-smallest-element-in-sorted-matrix
    // min heap
    struct Compare{
        bool operator()(const pair<int, pair<int, int>>& a, const pair<int, pair<int, int>>& b){
            return a.first > b.first;
        }
    };
    
    int kthSmallest(vector<vector<int>>& matrix, int k) {
        int n = matrix.size();
        if(n == 0)      return -1;
        vector<vector<int>> visited(n, vector<int>(n, 0));
        // (value, (row_index, col_index))
        priority_queue<pair<int, pair<int, int>>, vector<pair<int, pair<int, int>>>, Compare> min_heap;
        min_heap.push({matrix[0][0], {0, 0}});
        int r = matrix[0][0];
        int i = 0;
        visited[0][0] = 1;
        while(i < k){
            auto t = min_heap.top();
            min_heap.pop();
            r = t.first;
            int x = t.second.first;
            int y = t.second.second;
            
            if(x + 1 < n && !visited[x+1][y]){
                min_heap.push({matrix[x+1][y], {x+1, y}});
                visited[x+1][y] = 1;
            }
            if(y + 1 < n && !visited[x][y+1]){
                min_heap.push({matrix[x][y+1], {x, y+1}});
                visited[x][y+1] = 1;
            }
            i++;
        }
        return r;
    }
};