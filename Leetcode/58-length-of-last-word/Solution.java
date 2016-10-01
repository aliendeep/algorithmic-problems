public class Solution {
    public int lengthOfLastWord(String s) {
        if(s.length() == 0)       
            return 0;
        int i = s.length() - 1;
        // skip the trailing spaces
        while(i >= 0 && s.charAt(i) == ' ')    i--;

        int end = i;
        while(i >= 0 && s.charAt(i) != ' '){
            i--;
        }
        return end - i;
    }
}