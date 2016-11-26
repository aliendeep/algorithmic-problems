/*
@Copyright:LintCode
@Author:   mts
@Problem:  http://www.lintcode.com/problem/majority-number
@Language: Java
@Datetime: 16-11-26 03:05
*/

public class Solution {
    /**
     * @param nums: a list of integers
     * @return: find a  majority number
     */
    public int majorityNumber(ArrayList<Integer> nums) {
        int n = nums.size();
        if(n == 0)
            return 0;
        int cnt = 1;
        int x = nums.get(0);
        int i = 1;
        while(i < n){
            if(nums.get(i) == x)
                cnt++;
            else{
                cnt--;
                if(cnt == 0){
                    x = nums.get(i);
                    cnt = 1;
                }
            }
            i++;
        }
        return x;
   }
}