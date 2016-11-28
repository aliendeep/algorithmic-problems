/*
@Copyright:LintCode
@Author:   mts
@Problem:  http://www.lintcode.com/problem/rotate-string
@Language: C++
@Datetime: 15-10-02 06:37
*/

class Solution {
public:
    /**
     * @param str: a string
     * @param offset: an integer
     * @return: nothing
     */
    void rotateString(string &str,int offset){
        if(str.size() == 0 || str.size() == 1) 
            return;
        offset = offset % str.size(); 
        string s = str;
        s.append(str);
        str = s.substr(str.size() - offset, str.size());
    }
};

