/*
Given a string containing only digits, restore it by returning all possible valid 
IP address combinations.

For example:
Given "25525511135",

return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
*/

public class Solution {
   // total # of IP addresses = 2^32. Time: O(1)
    boolean isValid(String s){
        if(s.length() > 3)    
            return false;
        if(s.charAt(0) == '0' && s.length() > 1)    
            return false;
        int val = Integer.parseInt(s);
        return val >= 0 && val <= 255;
    }
    public List<String> restoreIpAddresses(String s) {
        List<String> result =  new ArrayList<>();
        for(int i=1; i<4 && i<s.length(); i++){
            String first = s.substring(0, i);
            if(isValid(first)){
                for(int j=1; i + j < s.length() && j<4; j++){
                    String second = s.substring(i, i+j);
                    if(isValid(second)){
                        for(int k=1; i + j + k < s.length() && k<4; k++){
                            String third = s.substring(i+j, i+j+k);
                            String fourth = s.substring(i+j+k);
                            if(isValid(third) && isValid(fourth)){
                                result.add(new String(first + "." + second + "." + third + "." + fourth));
                            }
                        }
                    }
                }        
            }
        }
        return result;
    }
}

// Backtracking
// cleaner
// Backtracking
class Solution2 {
    List<String> r;
    String s;
    int n;
    public void gen(StringBuilder cur, int lev, int parts) {
        if(parts > 4)
            return;
        if(lev == n && parts == 4){
            // remove the last .
            cur.setLength(cur.length()-1);
            r.add(cur.toString());
            return;
        }
        for(int l=1; l<4 && lev+l<= n; ++l){
            String subnet = s.substring(lev, lev+l);
            int val = Integer.parseInt(subnet);
            if(val < 0 || val > 255)
                continue;
            // starts with 0 and length > 1
            if(subnet.charAt(0) == '0' && subnet.length() > 1)
                continue;
            int sl = cur.length();
            cur.append(subnet).append(".");
            gen(cur, lev+l, parts+1);
            cur.setLength(sl);
        }
    }
    public List<String> restoreIpAddresses(String str) {
        s = str;
        n = s.length();
        r = new ArrayList<>();
        gen(new StringBuilder(), 0, 0);
        return r;
    }
}
