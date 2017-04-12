/*
Given a positive 32-bit integer n, you need to find the smallest 32-bit integer 
which has exactly the same digits existing in the integer n and is greater in 
value than n. If no such positive 32-bit integer exists, you need to return -1.

Example 1:
Input: 12
Output: 21
Example 2:
Input: 21
Output: -1
*/

public class Solution {
    public int nextGreaterElement(int n) {
        StringBuilder s = new StringBuilder(Integer.toString(n));
        int len = s.length();
        int i = 0;
        for(i=len-1; i>0; --i) {
            if(s.charAt(i-1) < s.charAt(i))
                break;
        }
        if(i == 0)      return -1;
        
        int smallerRightIndex = i;
        for(int j=i+1; j<len; ++j) {
            if(s.charAt(i-1) < s.charAt(j) && s.charAt(j) < s.charAt(smallerRightIndex)) {
                smallerRightIndex = j;
            }
        }
        
        // swap
        char t = s.charAt(i-1);
        s.setCharAt(i-1, s.charAt(smallerRightIndex));
        s.setCharAt(smallerRightIndex, t);
        
        char[] sorted = s.substring(i).toCharArray();
        Arrays.sort(sorted);
        long x = Long.parseLong(s.substring(0, i) + new String(sorted));
        return x > Integer.MAX_VALUE ? -1 : (int)x;
    }
}
