class Solution {
public:
    void bktk(const vector<int>& a, int lev, vector<int>& cur, vector<vector<int>>& r){
        if(lev == a.size()){
            r.push_back(cur);
            return;
        }
        // without
        bktk(a, lev+1, cur, r);
        // with
        cur.push_back(a[lev]);
        bktk(a, lev+1, cur, r);
        cur.pop_back();
    }
    vector<vector<int>> subsets(vector<int>& nums) {
        vector<vector<int>> r;
        vector<int> cur;
        bktk(nums, 0, cur, r);
        return r;
    }
};