/*
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Find all strobogrammatic numbers that are of length = n.

For example,
Given n = 2, return ["11","69","88","96"].

Hint:

Try to use recursion and notice that it should recurse with n - 2 instead of n - 1.
*/

import java.util.*;
// Iterative Solution
public class Solution {
    public String addPrefixSuffix(char start, String mid, char end){
        StringBuilder t = new StringBuilder();
        t.append(start);
        t.append(mid);
        t.append(end);
        return t.toString();
    }

    public List<String> findStrobogrammatic(int n) {
        List<String> prev, cur;
        if(n % 2 == 0)
            prev = new ArrayList<>(Arrays.asList(""));
        else
            prev = new ArrayList<>(Arrays.asList("0", "1", "8"));          
        
        if(n < 2)
            return prev;

        for(; n>1; n-=2){
            cur = new ArrayList<>();
            for(String str : prev){
                if(n > 3)
                    cur.add(addPrefixSuffix('0', str, '0'));
                
                cur.add(addPrefixSuffix('1', str, '1'));
                cur.add(addPrefixSuffix('6', str, '9'));
                cur.add(addPrefixSuffix('8', str, '8'));
                cur.add(addPrefixSuffix('9', str, '6'));
            }
            prev = new ArrayList<>(cur);
        }     
        Collections.sort(prev);
        return prev;
    }
}