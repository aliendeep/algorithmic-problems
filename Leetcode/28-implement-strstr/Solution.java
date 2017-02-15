/*
Implement strStr().

Returns the index of the first occurrence of needle in haystack, or -1 if needle 
is not part of haystack.
*/

public class Solution {
    // Rabin Karp
    // O(m + n)
    final static int kBase = 26;
    final static int kMod = 997;

    public int strStr(String haystack, String needle) {
        if(needle.length() > haystack.length())
            return -1;

        int hayHash = 0;
        int needleHash = 0;
        int power = 1;
        for(int i=0; i<needle.length(); i++){
            power = (i == 0) ? 1 : power*kBase % kMod;
            hayHash = (hayHash*kBase + haystack.charAt(i)) % kMod;
            needleHash = (needleHash*kBase + needle.charAt(i)) % kMod;
        }
        
            
        for(int i=needle.length(); i<haystack.length(); i++){
            String x = haystack.substring(i - needle.length(), i);
            if(hayHash == needleHash && x.compareTo(needle) == 0)
                return i - needle.length();
            
            // Removing the i - needle.size() th char
            hayHash -= (haystack.charAt(i - needle.length())*power) % kMod;
            if(hayHash < 0){
                hayHash += kMod;    
            }
            // Addding the ith char
            hayHash = (hayHash*kBase + haystack.charAt(i)) % kMod;
        }
        // last string
        String x = haystack.substring(haystack.length() - needle.length());
        if(hayHash == needleHash && x.compareTo(needle) == 0)
            return haystack.length() - needle.length();
        return -1;
    }
}

// Time complexity: O(m+n) 
// where m is length of text and n is length of pattern
// Space complexity - O(n)
public class Solution {
    // Knuth–Morris–Pratt algorithm 
    // Compute suffix-prefix array to maintain size of suffix which is also prefix 
    public int[] computeLPSArray(String pattern){
        int n = pattern.length();
        int[] lps = new int[n];
        int index = 0;
        int i = 1;
        while(i < n){
            if(pattern.charAt(i) == pattern.charAt(index)){
                lps[i] = index + 1;
                i++;
                index++;
            }
            else{
                if(index != 0){
                    index = lps[index-1];
                }
                else{
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }
    
    // Haystack - text, needle - pattern
    public int strStr(String haystack, String needle) {
        int n = haystack.length();
        int m = needle.length();
        if(n == 0 && m == 0)
            return 0;
        if(m == 0)
            return 0;
        // Compute the lps array for the pattern haystack
        int[] lps = computeLPSArray(needle);
        int i = 0, j = 0;
        while(i < n && j < m){
            if(haystack.charAt(i) == needle.charAt(j)){
                i++;
                j++;
                // first occurrence of needle in haystack
                if(j == m)
                    return i - m;
            }
            else{
                if(j != 0){
                    j = lps[j-1];
                }
                else{
                    i++;
                }
            }
        }
        return -1;
    }
}
