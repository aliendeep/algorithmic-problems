/*
Given a positive integer num, write a function which returns True if num is a perfect square else False.

Note: Do not use any built-in library function such as sqrt.

Example 1:

Input: 16
Returns: True
Example 2:

Input: 14
Returns: False
*/

public class Solution {
    public boolean isPerfectSquare(int num) {
        if(num == 1) return true;
        int l = 0;
        int h = num/2;
        while(h - l > 3){
            double mid = (h-l)/2+l;
            if(mid == (num*1.0)/mid)
                return true;
            else if(mid < (num*1.0)/mid){
                l = (int)mid+1;
            }
            else{
                h = (int)mid-1;
            }
        }
        
        for(int i=l; i<=h; i++){
            if(i == (num*1.0)/i)
                return true;
        }
        return false;
    }
}