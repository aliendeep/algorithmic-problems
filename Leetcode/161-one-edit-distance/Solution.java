/*
Given two strings S and T, determine if they are both one edit distance apart.
*/

public class Solution {
    public boolean isOneEditDistance(String s, String t) {
        int m = s.length();
        int n = t.length();
        for(int i=0; i<Math.min(m, n); i++){
            if(s.charAt(i) != t.charAt(i)){
                // if same length, only option is replacement
                if(m == n){
                    // rest of the char must match
                    return s.substring(i+1).equals(t.substring(i+1));
                }
                // Add char to s
                else if(m < n){
                    return s.substring(i).equals(t.substring(i+1));
                }
                // m > n
                else{
                    return s.substring(i+1).equals(t.substring(i));
                }
            }
        }
        // last character differs
        return Math.abs(m - n) == 1;    
   }
}