// Write a function to find the longest common prefix string amongst an array of strings.

public class Solution {
    // Solution (Divide & Conquer)
    private String commonPrefix(String x, String y) {
        for(int i=0; i<x.length(); ++i){
            if(i >= y.length() || x.charAt(i) != y.charAt(i))
                return x.substring(0, i);
        }
        return x;
    }
    
    private String longestCommonPrefix(String[] strs, int l, int h) {
        if(l == h)       return strs[l];
        int mid = (l + h)/2;
        String left = longestCommonPrefix(strs, l, mid);
        String right = longestCommonPrefix(strs, mid+1, h);
        return commonPrefix(left, right);
    }
    
    public String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length == 0)
            return "";
        return longestCommonPrefix(strs, 0, strs.length-1);    
    }
}