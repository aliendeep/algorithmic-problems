/*
Count the number of segments in a string, where a segment is defined to be a contiguous sequence of non-space characters.

For example,

Input: "Hello, my name is John"

Output: 5
*/
public class Solution {
    public int countSegments(String s) {
        int n = s.length();
        if(n == 0)
            return 0;
        
        int cnt = 0;
        for(int i=0; i<n; ++i){
            if(s.charAt(i) != ' ')
                continue;
            if(i > 0 && s.charAt(i-1) != ' ')
                cnt++;
        }
        // last word
        if(s.charAt(n-1) != ' ')
            cnt++;
        return cnt;
    }
}