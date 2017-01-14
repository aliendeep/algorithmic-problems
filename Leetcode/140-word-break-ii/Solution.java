/*
Given a string s and a dictionary of words dict, add spaces in s to construct a 
sentence where each word is a valid dictionary word.

Return all such possible sentences.

For example, given
s = "catsanddog",
dict = ["cat", "cats", "and", "sand", "dog"].

A solution is ["cats and dog", "cat sand dog"].
*/

/*
// Backtracking - TLE
public class Solution {
    // Backtracking
    int n;
    Set<String> dict;

    public void helper(String s, int start, List<String> cur, List<String> result){
        if(start == n){
            StringBuilder t = new StringBuilder();
            for(String x : cur){
                t.append(x).append(" ");
            }
            result.add(t.toString().trim());
            return;
        }

        String full = s.substring(start);
        if(dict.contains(full)){
            cur.add(full);
            helper(s, n, cur, result);
            cur.remove(cur.size()-1);
        }

        for(int k=start+1; k<n; ++k){
            String first = s.substring(start, k);
            if(dict.contains(first)){
                cur.add(first);
                helper(s, k, cur, result);
                cur.remove(cur.size()-1);
            }
        }
    }

    public List<String> wordBreak(String s, List<String> wordDict) {
        dict = new HashSet<>(wordDict);
        n = s.length();
        List<String> r = new ArrayList<>();
        helper(s, 0, new ArrayList<>(), r);
        return r;
    }
}
*/
public class Solution {
    public Set<String> bktk(String s, Set<String> dict, Map<String, Set<String>> dp) {
        Set<String> result = new HashSet<>();
        if(s.length() == 0){
            return result;
        }
        
        if(dp.containsKey(s))
            return dp.get(s);
        
        for(int l=1; l<=s.length(); ++l){
            String start = s.substring(0, l);
            // if the dictionary contains the start
            if(dict.contains(start)){
                if(l == s.length()){
                    result.add(start);
                }
                else{
                    Set<String> rest = bktk(s.substring(l), dict, dp);
                    if(rest.size() > 0){
                        for(String word : rest){
                            StringBuilder t = new StringBuilder();
                            t.append(start).append(" ").append(word);
                            result.add(t.toString());
                        }
                    }
                }
            }
        }
        dp.put(s, result);
        return result;
    }

    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>();
        for(String w : wordDict)
            dict.add(w);
        Map<String, Set<String>> dp = new HashMap<>();
        return new ArrayList<>(bktk(s, dict, dp));
    }
}

// Cleaner
class Solution2 {
    Map<Integer, Set<String>> map;
    String s;
    int n;
    Set<String> wordDict;
    
    public Set<String> bktk(int k){
        Set<String> result = new HashSet<>();
        if(k <= 0){
            return result;
        }
        if(map.containsKey(k))
            return map.get(k);

        // whole word
        String sub = s.substring(0, k);
        if(wordDict.contains(sub)){
            result.add(sub);
        }

        for(int i=1; i<k; ++i){
            String end = s.substring(i, k);
            if(wordDict.contains(end)){
                Set<String> all = bktk(i);
                for(String str : all){
                    result.add(str + " " + end);
                }
            }            
        }
        map.put(k, result);
        return result;
    }
    
    public List<String> wordBreak(String s, Set<String> dict) {
        n = s.length();
        wordDict = dict;
        this.s = s;
        map = new HashMap<>();
        Set<String> r = bktk(n);
        return new ArrayList<>(r);
    }
}

public class Solution {
    int n;
    // <Word, all possible valid constructions of that word>
    Map<String, List<String>> wordMap;

    public List<String> bktk(String s, Set<String> wordDict){
        List<String> result = new ArrayList<>();

        if(s.length() == 0){
            return result;
        }
        
        if(wordMap.containsKey(s)){
            return wordMap.get(s);
        }
            
        // full word is a dictionary word
        if(wordDict.contains(s)){
            result.add(s);            
        }
        
        for(int i=1; i<=s.length(); i++){
            String first = s.substring(0, i);
            if(wordDict.contains(first)){
                String rest = s.substring(i);
                List<String> candidates = bktk(rest, wordDict);
                for(String str : candidates){
                    result.add(first + " " + str);
                }
            }
        }
        wordMap.put(s, result);
        return result;
    }
    
    public List<String> wordBreak(String s, Set<String> wordDict) {
        this.n = s.length();
        wordMap = new HashMap<>();
        return bktk(s, wordDict);
    }
}

// Back end
class Solution4 {
    // Memoization
    // Word, all possible valid constructions of that word
    Map<String, List<String>> wordMap;
    
    public List<String> wordBreakHelper(String s, Set<String> wordDict) {
        List<String> result = new ArrayList<>();
        if(s.length() == 0)
            return result;
        if(wordMap.containsKey(s))
            return wordMap.get(s);
        
        // full word is a dictionary word
        if(wordDict.contains(s)){
            result.add(s);
        }
        
        for(int i=1; i<s.length(); i++){
            // if the rest of the strinsg starting from i is a valid dictionary word
            String rest = s.substring(i);
            if(wordDict.contains(rest)){
                String prevWord = s.substring(0, i);
                List<String> prev = wordBreakHelper(prevWord, wordDict);
                for(String str : prev){
                    result.add(str + " " + rest);
                }
            }    
        }
        
        wordMap.put(s, result);
        return result;            
    }
    
    public List<String> wordBreak(String s, Set<String> wordDict) {
        wordMap = new HashMap<String, List<String>>();
        return wordBreakHelper(s, wordDict);
    }
}

// Index Memoization [i..j]
class Solution4 {
    List<List<Set<String>>> dp;
    int n;
    String s;
    Set<String> dict;
    
    public Set<String> helper(int i, int j){
        Set<String> result = new HashSet<>();
        String x = s.substring(i, j);
        if(dict.contains(x)){
            result.add(x);
        }
        
        if(dp.get(i).get(j-1) != null)
            return dp.get(i).get(j-1);
        
        for(int k=i+1; k<j; ++k){
            String first = s.substring(i, k);
            if(dict.contains(first)){
                Set<String> rest = helper(k, j);
                if(rest.isEmpty())
                    continue;
                int len = rest.iterator().next().length();
                // >= n because of spaces
                if(k + len < n)    
                    continue;
                for(String t : rest){
                    result.add(first + " " + t);
                }
            }
        }
        dp.get(i).set(j-1, result);
        return result;
    }
    
    public List<String> wordBreak(String str, List<String> wordDict) {
        s = str;
        n = s.length();
        dict = new HashSet<>(wordDict);

        // init
        dp = new ArrayList<>();
        for(int i=0; i<n; ++i){
            dp.add(new ArrayList<>());
            for(int j=0; j<n; ++j){
                dp.get(i).add(null);
            }
        }
        return new ArrayList<>(helper(0, n));
        
    }
}
