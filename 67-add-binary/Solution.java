public class Solution {
    public String reverse(String x){
        StringBuffer s = new StringBuffer(x);
        s.reverse();
        return s.toString();
    }
    
    public String addBinary(String a, String b) {
        int carry = 0;
        int i = 0;
        String x = reverse(a);
        String y = reverse(b);
        int t1, t2;
        StringBuffer r = new StringBuffer();
        while(i < x.length() || i < y.length() || carry != 0){
            t1 = 0;
            t2 = 0;
            if(i < x.length())
                t1 = x.charAt(i) - '0';
            if(i < y.length())
                t2 = y.charAt(i) - '0';
            int sum = t1 + t2 + carry;
            carry = sum/2;
            r.append(sum%2);
            i++;
        }
        r.reverse();
        return r.toString();
    }
}