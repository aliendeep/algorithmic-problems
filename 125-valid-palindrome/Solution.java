public class Solution {
    public boolean isPalindrome(String s) {
        if(s.length() == 0)
            return true;
        String str = s.toLowerCase();
        int n = str.length();
        for(int i=0, j=n-1; i<j; i++, j--){
            while(i < j && !Character.isLetterOrDigit(str.charAt(i)))
                i++;
            while(i < j && !Character.isLetterOrDigit(str.charAt(j)))
                j--;
            if(str.charAt(i) != str.charAt(j))
                return false;
        }
        return true;
    }
}