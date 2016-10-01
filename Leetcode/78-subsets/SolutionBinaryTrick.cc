class Solution {
public:
    // No of subsets = 2^n
    // Get the lowest set bit, s = n & ~(n-1)
    // Say, s = 2^4 = 16 = (Binary 10000)
    // Get the index of the lowest set bit = log2(s) = log2(16) = log2(2^4) = 4
    vector<vector<int>> subsets(vector<int>& nums) {
        vector<vector<int>> r;
        for(int i=0; i<(1<<nums.size()); i++){
            int n = i;
            vector<int> t;
            while(n){
                // get the lowest set bit
                t.emplace_back(nums[log2(n & ~(n-1))]);
                // clear the last set bit to 0
                n = n & (n-1);
            }
            r.emplace_back(t);
        }
        return r;
    }
};