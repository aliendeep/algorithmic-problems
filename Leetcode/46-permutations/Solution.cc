class Solution {
public:
    void bktk(const vector<int>& nums,  vector<int>& taken, int lev, vector<int> cur, vector<vector<int>>& r){
        if(lev == nums.size()){
            r.emplace_back(cur);
            return;
        }    
        for(int i=0; i<nums.size(); i++){
            if(taken[i])    continue;
            cur[lev] = nums[i];
            taken[i] = 1;
            bktk(nums, taken, lev+1, cur, r);
            taken[i] = 0;
            cur[lev] = -1;
        }
    }
    
    vector<vector<int>> permute(vector<int>& nums) {
        vector<vector<int>> r;
        vector<int> cur(nums.size(), -1);
        vector<int> taken(nums.size(), 0);
        bktk(nums, taken, 0, cur, r);   
        return r;
    }
};