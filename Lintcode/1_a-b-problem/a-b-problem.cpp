/*
@Copyright:LintCode
@Author:   mts
@Problem:  http://www.lintcode.com/problem/a-b-problem
@Language: C++
@Datetime: 15-10-09 23:48
*/

class Solution {
public:
    /*
     * @param a: The first integer
     * @param b: The second integer
     * @return: The sum of a and b
     */
     
    int aplusb(int a, int b) {
        // write your code here, try to do it without arithmetic operators.
        while(b != 0){
            int carry = a & b;
            a = a^b;
            b = carry << 1;
        }
        return a;
    }
};
