class Solution {
public:
    // O(n) Solution
    vector<int> twoSum(vector<int>& n, int target) {
        // (value, index) mapping
        unordered_map<int, int> M;
        for(int i=0; i<n.size(); i++){
            M[n[i]] = i;
        }
        
        vector<int> r;
        for(int i=0; i<n.size(); i++){
            auto it = M.find(target - n[i]);
            if(it != M.end() && it->second != i){
                r.push_back(i);                
                r.push_back(it->second);
                break;
            }
        }  
        return r;
    }
};