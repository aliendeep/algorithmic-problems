/*
Given a non-empty string check if it can be constructed by taking a substring 
of it and appending multiple copies of the substring together. You may assume 
the given string consists of lowercase English letters only and its length will 
not exceed 10000.

Example 1:
Input: "abab"

Output: True

Explanation: It's the substring "ab" twice.
Example 2:
Input: "aba"

Output: False
Example 3:
Input: "abcabcabcabc"

Output: True

Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)
*/
import java.util.*;

// divisor

public class Solution {
    public boolean repeatedSubstringPattern(String str) {
        int n = str.length();
        if(n <= 1)
            return false;
        List<Integer> a = new ArrayList<>();
        a.add(1);
        // The length of the repeating substring should be a divisor of total length
        for(int i=2; i*i<=n; i++){
            if(n % i == 0){
                a.add(i);
                a.add(n/i);
            }
        }
        
        for(int i=0; i<a.size(); ++i){
            int l = a.get(i);
            //System.out.println(l);
            String sub = str.substring(0, l);
            int k = 0;
            int j = l;
            for(; j<n; ++j){
                if(sub.charAt(k) != str.charAt(j))
                    break;
                k = (k+1) % l;
            }
            if(j == n)
                return true;
        }
        return false;
    }

    public static void main(String[] args){
      Solution ob = new Solution();
      System.out.println(ob.repeatedSubstringPattern("abab"));
    }
}