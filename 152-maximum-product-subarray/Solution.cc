class Solution {
public:
    int maxProduct(vector<int>& nums) {
        int r = INT_MIN;
        int max_product = 1;
        int min_product = 1;
        for(const auto& n : nums){
            int x = max_product;
            int y = min_product;
            max_product = max(max(x*n, y*n), n); 
            min_product = min(min(x*n, y*n), n); 
            r = max(r, max_product);
        }
        return r;
    }
};