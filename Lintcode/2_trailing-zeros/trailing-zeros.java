/*
@Copyright:LintCode
@Author:   mts
@Problem:  http://www.lintcode.com/problem/trailing-zeros
@Language: Java
@Datetime: 16-11-08 23:18
*/

class Solution {
    /*
     * param n: As desciption
     * return: An integer, denote the number of trailing zeros in n!
     */
    public long trailingZeros(long n) {
        // 5*2 = 10
        // Count number of 5
        long cnt = 0;
        for(long i=5; i<=n; i*=5){
            cnt += n/i;
        }
        return cnt;
    }
};
