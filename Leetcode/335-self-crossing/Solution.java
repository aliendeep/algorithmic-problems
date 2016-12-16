/*
You are given an array x of n positive numbers. You start at point (0,0) and moves 
x[0] metres to the north, then x[1] metres to the west, x[2] metres to the south, 
x[3] metres to the east and so on. In other words, after each move your direction 
changes counter-clockwise.

Write a one-pass algorithm with O(1) extra space to determine, if your path crosses 
itself, or not.

Example 1:
Given x = [2, 1, 1, 2],
┌───┐
│   │
└───┼──>
    │

Return true (self crossing)
Example 2:
Given x = [1, 2, 3, 4],
┌──────┐
│      │
│
│
└────────────>

Return false (not self crossing)
Example 3:
Given x = [1, 1, 1, 1],
┌───┐
│   │
└───┼>

Return true (self crossing)
*/

public class Solution {
    /* 
        Explanation: https://discuss.leetcode.com/topic/38068/another-python
                b                              b
       +----------------+             +----------------+
       |                |             |                |
       |                |             |                | a
     c |                |           c |                |
       |                | a           |                |    f
       +----------->    |             |                | <----+
                d       |             |                |      | e
                        |             |                       |
                                      +-----------------------+
                                                   d        
        - if d crosses a, then length of a >= c
        - if f crosses a, then length of a >= c - e and f >= d - b
        - Both cases: d >= b (d should be at least as equal to b)
    */
    public boolean isSelfCrossing(int[] x) {
        int b = 0, c = 0, d = 0, e = 0, f = 0;
        for(int a : x){
            if((d >= b && b > 0) && (a >= c || ((a >= c-e && c-e >= 0) && (f >= d-b && d-b >= 0))))
                return true;
            f = e;
            e = d;
            d = c;
            c = b;
            b = a;
        }
        return false;
    }
}

class Solution2 {
    public boolean isSelfCrossing(int[] x) {
        if(x.length >= 5 && x[3] == x[1] && x[2] + x[4] >= x[0])
            return true;

        for(int i=3; i<x.length; ++i){
            if(x[i-2] <= x[i] && x[i-1] <= x[i-3])
                return true;
            else if(i >= 5 && x[i-2] >= x[i-4] && x[i-3] >= x[i-1] && x[i-1] + x[i-5] >= x[i-3] && x[i] + x[i-4] >= x[i-2])
                return true;
        }
        return false;
    }
}
