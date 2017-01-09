/*
Given two binary strings, return their sum (also a binary string).

For example,
a = "11"
b = "1"
Return "100".
*/
public class Solution {
    public String addBinary(String A, String B) {
        int n = A.length();
        int m = B.length();
        int carry = 0;
        int x, y;
        StringBuilder a = new StringBuilder(A);
        a.reverse();
        StringBuilder b = new StringBuilder(B);
        b.reverse();
        
        StringBuilder r = new StringBuilder();
        for(int i=0; i<n || i<m || carry > 0; ++i){
            x  = 0;
            if(i < n)
                x = a.charAt(i) - '0';
            y  = 0;
            if(i < m)
                y = b.charAt(i) - '0';
            
            int sum = (x + y + carry);
            char c = (char)(sum%2 + '0');
            r.append(c);
            carry = sum/2;
        }
        r.reverse();
        return r.toString();
    }
}

// Withput reversing a and b
class Solution2 {
    public String addBinary(String a, String b) {
        int carry = 0;
        int i = a.length() - 1;
        int j = b.length() - 1;
        int x, y;
        StringBuilder r = new StringBuilder();
        while(i >= 0 || j >= 0 || carry != 0){
            x = 0;
            if(i >= 0)
                x = a.charAt(i) - '0';
            y = 0;
            if(j >= 0)
                y = b.charAt(j) - '0';
            int sum = x + y + carry;
            carry = sum/2;
            r.append(sum%2);
            i--;
            j--;
        }
        r.reverse();
        return r.toString();
    }
}
