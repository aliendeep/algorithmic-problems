class Solution {
public:
    void bktk(int k, int target, int lev, int cur_sum, vector<int> cur, vector<vector<int>>& r){
        if(cur.size() == k){
            if(cur_sum == target)
                r.push_back(cur);
            return;
        }
        
        for(int i=lev;i<=9; i++){
            cur_sum += i;
            cur.push_back(i);
            bktk(k, target, i+1, cur_sum, cur, r);
            cur_sum -= i;
            cur.pop_back();
        }
    }
    vector<vector<int>> combinationSum3(int k, int n) {
        vector<vector<int>> r;
        vector<int> cur;
        bktk(k, n, 1, 0, cur, r);
        return r;
    }
};