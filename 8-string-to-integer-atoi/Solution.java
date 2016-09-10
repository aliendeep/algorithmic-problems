public class Solution {
    public int myAtoi(String str) {
        if(str.length() == 0)
            return 0;
        
        int i = 0;
        while(str.charAt(i) == ' ')
            i++;
            
        int signFlag = 1;
        if(i < str.length() && (str.charAt(i)  == '+' || str.charAt(i)  == '-')){
            if(str.charAt(i) == '-') 
                signFlag = -1;
            i++;
        }

        double n = 0;
        while(i<str.length()){
            if(Character.isDigit(str.charAt(i)) == true)
                n = n*10 + (str.charAt(i) - '0');
            else
                break;
            if(n*signFlag >= Integer.MAX_VALUE)
                return Integer.MAX_VALUE;
            if(n*signFlag <= Integer.MIN_VALUE)
                return Integer.MIN_VALUE;
            i++;
        }
        
        return (int)(signFlag*n);
    }
}