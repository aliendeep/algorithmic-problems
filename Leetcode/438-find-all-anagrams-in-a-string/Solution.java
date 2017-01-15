/*
Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

Strings consists of lowercase English letters only and the length of both strings 
s and p will not be larger than 20,100.

The order of output does not matter.

Example 1:

Input:
s: "cbaebabacd" p: "abc"

Output:
[0, 6]

Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".
Example 2:

Input:
s: "abab" p: "ab"

Output:
[0, 1, 2]

Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".
*/
public class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> r = new ArrayList<>();
        int n = p.length();
        if(s.length() < p.length())
            return r;
            
        int[] pcnt = new int[256];
        int[] scnt = new int[256];
        for(int i=0; i<n; ++i){
            pcnt[p.charAt(i)]++;
            scnt[s.charAt(i)]++;
        }
        
        int start = 0;
        for(int end=n; end<s.length(); ++end){
            int i;
            for(i=0; i<256; i++){
                if(pcnt[i] != scnt[i])
                    break;

            }
            if(i == 256){
                r.add(start);
            }
            scnt[s.charAt(start++)]--;
            scnt[s.charAt(end)]++;
       }
       
        // last window
        int i;
        for(i=0; i<256; i++){
            if(pcnt[i] != scnt[i])
                break;
        }
        if(i == 256){
            r.add(start);
        }
       return r;
    }
}

class Solution2 {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> r = new ArrayList<>();
        int n = s.length();
        int m = p.length();
        if(n == 0 || m == 0 || n < m)
            return r;
        int[] cnt = new int[256];
        for(char c : p.toCharArray()){
            cnt[c]++;
        }
        int start = 0, end = 0, count = m;
        while(end < n){
            if(cnt[s.charAt(end)] >= 1){
                count--;
            }
            cnt[s.charAt(end)]--;
            end++;

            // if all characters of p have been covered
            if(count == 0){
                r.add(start);
            }
            if(end - start == m){
                if(cnt[s.charAt(start)] >= 0)
                    count++;
                cnt[s.charAt(start)]++;
                start++;               
            }
        }
        return r;
    }
}
