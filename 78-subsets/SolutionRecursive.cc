class Solution {
public:
    void bktk(const vector<int>& a, int lev, vector<int>& cur, vector<vector<int>>& r){
        r.push_back(cur);
        for(int i=lev; i<a.size(); i++){
            cur.push_back(a[i]);
            bktk(a, i+1, cur, r);
            cur.pop_back();
        }
    }
    vector<vector<int>> subsets(vector<int>& nums) {
        vector<vector<int>> r;
        vector<int> cur;
        bktk(nums, 0, cur, r);
        return r;
    }
};