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
            if (i > prev_i && a[i] == a[i-1]) continue;

            sum += a[i];
            cur.push_back(a[i]);

            // Each number in the array may only be used once in the combination, hence i+1
            bktk(a, target, i+1, sum, cur, r);

            sum -= a[i];
            cur.pop_back();
        }
    }
    
    vector<vector<int>> combinationSum2(vector<int>& candidates, int target) {
        vector<vector<int>> r;
        vector<int> cur;
        sort(candidates.begin(), candidates.end());
        int sum = 0;
        bktk(candidates, target, 0, sum, cur, r);
        return r;
    }
};