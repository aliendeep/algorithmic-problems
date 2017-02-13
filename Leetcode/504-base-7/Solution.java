/*
Given an integer, return its base 7 string representation.

Example 1:
Input: 100
Output: "202"
Example 2:
Input: -7
Output: "-10"
Note: The input will be in range of [-1e7, 1e7].
*/

public class Solution {
    public String convertTo7(int num) {
        if(num == 0)    return "0";
        StringBuilder r = new StringBuilder();
        boolean neg = false;
        if(num < 0){
            neg = true;
            num = -num;
        }
        while(num > 0){
            r.append(num % 7);
            num = num/7;
        }
        if(neg)
            r.append("-");
        r.reverse();
        return r.toString();
    }
}