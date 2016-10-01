class Solution {
public:
    void bktk(const vector<int>& a, int target, int prev_i, int& sum, vector<int>& cur, vector<vector<int>>& r){
        if(sum == target){
            r.push_back(cur);
            return;
        }
        if(sum > target)
            return;
        int n = a.size();
        for(int i=prev_i; i<n; i++){
            sum += a[i];
            cur.push_back(a[i]);
            // Same number can be added multiple times, hence i
            bktk(a, target, i, sum, cur, r);
            // undo
            sum -= a[i];
            cur.pop_back();
        }
    }
    vector<vector<int>> combinationSum(vector<int>& candidates, int target) {
        vector<vector<int>> r;
        vector<int> cur;
        // Sort the numbers
        sort(candidates.begin(), candidates.end());

        int sum = 0;
        bktk(candidates, target, 0, sum, cur, r);
        return r;
    }
};