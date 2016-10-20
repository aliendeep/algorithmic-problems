/*
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.

For example,
Given low = "50", high = "100", return 3. Because 69, 88, and 96 are three strobogrammatic numbers.

Note:
Because the range might be a large number, the low and high numbers are represented as string.
*/

public class Solution {
    public final static char[][] map = {{'0', '0'}, {'1', '1'}, {'6', '9'}, {'8', '8'}, {'9', '6'}};
    
    public int strobogrammaticInRange(String low, String high) {
        int[] cnt = new int[1];
        for(int len = low.length(); len <= high.length(); ++len){
            char[] cur = new char[len];
            strobogrammaticHelper(low, high, 0, len-1, cur, cnt);
        }
        return cnt[0];
    }
    
    public void strobogrammaticHelper(String low, String high, int start, int end, char[] cur, int[] count) {
        if(start > end){
            String t = new String(cur);
            if((low.length() == t.length() && t.compareTo(low) < 0) || (high.length() == t.length() && t.compareTo(high) > 0))
                return;
            
            count[0]++;
            return;
        }
        
        for(char[] pair : map){
            cur[start] = pair[0];    
            cur[end] = pair[1];
            
            // Can't start with 0 if number of digits is greater than 1
            if(cur.length != 1 && cur[0] == '0')
                continue;
            
            // 1 length, need to be same
            if(start == end && pair[0] != pair[1])
                continue;
            strobogrammaticHelper(low, high, start + 1, end - 1, cur, count);
        }
    }    
}