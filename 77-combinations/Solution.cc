class Solution {
public:
    void bktk(int n, int k, int prev_i, int lev, vector<int>& cur, vector<vector<int>>& r){
        if(lev == k){
            r.push_back(cur);
            return;
        }
        for(int i=prev_i+1; i<=n; i++){
            cur[lev] = i;
            bktk(n, k, i, lev+1, cur, r);
            cur[lev] = -1;
        }
    }
    vector<vector<int>> combine(int n, int k) {
        vector<vector<int>> r;
        vector<int> cur(k, 0);
        bktk(n, k, 0, 0, cur, r);
        return r;
    }
};