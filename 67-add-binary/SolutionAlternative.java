public class Solution {
    public String reverse(String x){
        StringBuffer s = new StringBuffer(x);
        s.reverse();
        return s.toString();
    }
    
    public String addBinary(String a, String b) {
        int carry = 0;
        int t1, t2;
        int i = a.length()-1;
        int j = b.length()-1;
        StringBuffer r = new StringBuffer();
        while(i >= 0 || j >= 0 || carry != 0){
            t1 = 0;
            t2 = 0;
            if(i >= 0)
                t1 = a.charAt(i) - '0';
            if(j >= 0)
                t2 = b.charAt(j) - '0';
            int sum = t1 + t2 + carry;
            carry = sum/2;
            r.append(sum%2);
            i--;
            j--;
        }
        r.reverse();
        return r.toString();
    }
}