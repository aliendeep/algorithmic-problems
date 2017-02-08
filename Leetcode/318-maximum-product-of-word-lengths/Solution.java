/*
Given a string array words, find the maximum value of length(word[i]) * length(word[j]) 
where the two words do not share common letters. You may assume that each word will 
contain only lower case letters. If no such two words exist, return 0.

Example 1:
Given ["abcw", "baz", "foo", "bar", "xtfn", "abcdef"]
Return 16
The two words can be "abcw", "xtfn".

Example 2:
Given ["a", "ab", "abc", "d", "cd", "bcd", "abcd"]
Return 4
The two words can be "ab", "cd".

Example 3:
Given ["a", "aa", "aaa", "aaaa"]
Return 0
No such pair of words.
*/

// O(n^2
public class Solution {
    // lower case letters - 26 bits required, So int is sufficient
    public int maxProduct(String[] words) {
        int n = words.length;
        if(n == 0)
            return 0;

        int[] map = new int[n];
        for(int i=0; i<n; i++){
            for(int j=0; j<words[i].length(); j++){
                // Set jth bit to 1 if ith letter exists
                map[i] |= 1<<(words[i].charAt(j) - 'a');
            }
        }
        
        int maxProd = 0;
        for(int i=1; i<n; i++){
            for(int j=0; j<i; j++){
                // no common letters between ith word and jth word
                if((map[i] & map[j]) == 0){
                    maxProd = Math.max(maxProd, words[i].length() * words[j].length());
                }
            }
        }        
        return maxProd;
    }
}

class Solution2 {
    // O(n^2)
    public int maxProduct(String[] words) {
        int n = words.length;
        if(n == 0)      return 0;
        boolean[][] cnt = new boolean[n][26];
        
        for(int k=0; k<n; ++k){
            String t = words[k];
            for(int i=0; i<t.length(); ++i){
                cnt[k][t.charAt(i) - 'a'] = true;
            }
        }
        
        int maxLen = 0;
        for(int i=0; i<n-1; ++i){
            for(int j=1; j<n; ++j){
                int k = 0;
                for(k=0; k<26; ++k){
                    // if common character
                    if(cnt[i][k] && cnt[j][k]){
                        break;
                    }
                }
                if(k == 26){
                    maxLen = Math.max(maxLen, words[i].length()*words[j].length());
                }
            }
        }
        return maxLen;
    }
}


// Alternative
//  O(n + N) variation, where n is the number of words 
//  N is the total number of characters in all words.
class Solution3 {
    class Info{
        int mask;
        // length of the word
        int length;
        public Info(int m, int l){
            mask = m;
            length = l;
        }
    }
    public int maxProduct(String[] words) {
        int n = words.length;
        if(n == 0)  return 0;
        List<Info> list = new ArrayList<>();
        for(int k=0; k<n; ++k){
            String word = words[k];
            int mask = 0;
            for(int i=0; i<word.length(); ++i){
                mask |= 1<<(word.charAt(i) - 'a');
            }
            list.add(new Info(mask, word.length()));
        }
        
        // for all possible mask (At most 2^52)
        int l = list.size();
        int maxLen = 0;
        for(int i=0; i<l; ++i){
            for(int j=i+1; j<l; ++j){
                Info a = list.get(i);
                Info b = list.get(j);
                if((a.mask & b.mask) == 0)
                    maxLen = Math.max(maxLen, a.length * b.length);
            }
        }
        return maxLen;
    }
}
