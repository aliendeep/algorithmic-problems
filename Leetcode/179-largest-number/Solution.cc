// "1211" + "12" = "121112" 
// "12" + "1211" = "121211", 
bool compare(const string& a, const string& b){
    string x = a + b;
    string y = b + a;
    return x > y;
}

class Solution {
public:
    string largestNumber(vector<int>& nums) {
        if(nums.size() == 0)
            return "";
        vector<string> r;
        for(int i=0; i<nums.size(); i++)
            r.push_back(to_string(nums[i]));
        sort(r.begin(), r.end(), compare);

        string t;
        for(int i=0; i<r.size(); i++)
            t += r[i];
        if(t[0] == '0')
            return "0";
        return t;
    }
};