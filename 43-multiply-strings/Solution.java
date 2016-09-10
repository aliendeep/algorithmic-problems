public class Solution {
    public String multiply(String num1, String num2) {
        if(num1.length() == 0 || num2.length() == 0) 
            return "0";
            
        StringBuilder x = new StringBuilder(num1);
        StringBuilder y = new StringBuilder(num2);
        x.reverse();
        y.reverse();
        
        char[] r = new char[num1.length() + num2.length()];
        Arrays.fill(r, '0');
        
        int carry  = 0;
        int a, b;
        for(int i=0; i<x.length(); i++){
            carry  = 0;
            a = x.charAt(i) - '0';
            for(int j=0; j<y.length() || carry != 0; j++){
                b = 0;
                if(j < y.length())
                    b = y.charAt(j) - '0';
                int mult = (r[i+j] - '0') + a*b + carry;
                carry = mult/10;
                mult = mult%10;
                r[i+j] = (char)(mult + '0');
            }    
        }    
        
        // remove 0 from the start
        int i = num1.length() + num2.length()-1;
        while(i > 0 && r[i] == '0'){
            i--;
        }

        StringBuilder t = new StringBuilder();
        for(int j=i; j>=0; j--){
            t.append(r[j]);
        }
        return t.toString();
    }
}