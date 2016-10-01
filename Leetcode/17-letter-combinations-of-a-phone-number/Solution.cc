class Solution {
public:
    unordered_map<char, string> map = {{'2', "abc"},{'3', "def"}, {'4', "ghi"}, {'5', "jkl"}, {'6', "mno"}, {'7', "pqrs"}, {'8', "tuv"}, {'9', "wxyz"}};
  
    void bktk(const string& digits, string& cur, int lev, vector<string>& r){
        if(lev == digits.size()){
            if(cur.size() > 0) 
                r.emplace_back(cur);
            return;
        }        
 
        string s = map[digits[lev]];
        for(int i=0; i<s.size(); i++){
            cur[lev] = s[i];
            bktk(digits, cur, lev+1, r);
            cur[lev] = -1;        
        }
    }
    
    vector<string> letterCombinations(string digits) {
        string cur(digits);
        vector<string> r;
        bktk(digits, cur, 0, r);
        return r;
    }
};