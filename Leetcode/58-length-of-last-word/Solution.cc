class Solution {
public:
    int lengthOfLastWord(string s) {
        if(s.size() == 0)       return 0;
        int i = s.size() - 1;
        // skip the spaces
        while(i >= 0 && s[i] == ' ')    i--;
        int end = i;
        while(i >= 0 && s[i] != ' '){
            i--;
        }
        return end - i;
    }
};