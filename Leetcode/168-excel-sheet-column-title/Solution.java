public class Solution {
    // 1 is mapped to A, not 0
    public String convertToTitle(int n) {
        StringBuffer r = new StringBuffer();
        while(n != 0){
            r.append((char)((n-1)%26 + 'A'));
            n = (n-1)/26;
        }
        r.reverse();
        return r.toString();
    }
}