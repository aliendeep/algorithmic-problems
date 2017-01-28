/*
Given a non-empty string s and a dictionary wordDict containing a list of non-empty 
words, determine if s can be segmented into a space-separated sequence of one or 
more dictionary words. You may assume the dictionary does not contain duplicate words.

For example, given
s = "leetcode",
dict = ["leet", "code"].

Return true because "leetcode" can be segmented as "leet code".

UPDATE (2017/1/4):
The wordDict parameter had been changed to a list of strings (instead of a set of strings). 
Please reload the code definition to get the latest changes.
*/

public class Solution {
/*
    // TLE
    public boolean wordBreakHelper(int start, String s, Set<String> wordDict){
        // if prefix of a word is dictionary then move forward
        if(start == s.length())
            return true;
            
        for(int i=start+1; i<=s.length(); i++){
            String word = s.substring(start, i);
            if(!wordDict.contains(word))
                continue;
            if(wordBreakHelper(i, s, wordDict))
                return true;
        }
        return false;
    }
    
    public boolean wordBreak(String s, Set<String> wordDict) {
        return wordBreakHelper(0, s, wordDict);
    }
*/  
    // DP
    // iterative
    public boolean helper(String s, Set<String> wordDict) {
        int n = s.length();
        
        // initalized to false
        boolean[] dp = new boolean[n];

        for(int i=0; i<n; i++){
            // Initialization (right end is exclusive in the substring function)
            // Set dp[i] to true if s[0, i] can be found in the dict
            if(wordDict.contains(s.substring(0, i+1)))
                dp[i] = true;
            else{
                // See if it's possible to make dp[i] true by splitting it into multiple words
                for(int j=0; j<i; j++){
                    if(dp[j] && wordDict.contains(s.substring(j+1, i+1))){
                        dp[i] = true;
                        break;
                    }
                }
            }
        }        
        return dp[n-1];
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        return helper(s, dict);
    }
}

// Memoization
// No need to check whether the whole string is in dictionary
class Solution2 {
    public boolean helper(String s, Set<String> dict, Map<String, Boolean> dp){
        if(s.length() == 0)        
            return true;
        
        if(dp.containsKey(s)){
            return dp.get(s);
        }
        
        boolean flag = false;
        int n = s.length();
        for(int i=1; i<=n; i++){
            String first = s.substring(0, i);
            if(dict.contains(first)){
              flag = helper(s.substring(i), dict, dp);
              if(flag)
                break;
            }
        }
        
        dp.put(s, flag);
        return flag;
    }
    
    public boolean wordBreak(String s, List<String> wordDict) {
        Map<String, Boolean> dp = new HashMap<>();
        Set<String> dict = new HashSet<>(wordDict);
        return helper(s, dict, dp);
     }
}      


// Memoization [i..j]
class Solution3 {
    List<List<Boolean>> dp;
    int n;
    String s;
    Set<String> dict;
    
    public boolean helper(int i, int j){
        // empty String
        if(i >= j)   return true;
        if(j > n)   return false;
        if(dp.get(i).get(j-1) != null)
            return dp.get(i).get(j-1);
        
        boolean flag = false;
        for(int k=i+1; k<=j; ++k){
            String first = s.substring(i, k);
            if(dict.contains(first) && helper(k, j)){
                flag = true;
                break;
            }
        }
        dp.get(i).set(j-1, flag);
        return flag;
    }
    
    public boolean wordBreak(String str, List<String> wordDict) {
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
        return helper(0, n);
    }
}

// BFS 
// O(n^2)
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        Queue<Integer> Q = new LinkedList<>();
        Set<String> dict = new HashSet<>(wordDict);
        Q.add(0);
        boolean[] visited = new boolean[n];
        while(!Q.isEmpty()){
            int start = Q.remove();
            if(!visited[start]){
                for(int i=start; i<=n; ++i){
                    if(dict.contains(s.substring(start, i))){
                        if(i == n)
                            return true;
                        Q.add(i);
                    }
                }
                visited[start] = true;
            }
        }
        return false;
    }
}

// BFS 
// O(n^2)
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        Queue<Integer> Q = new LinkedList<>();
        Set<String> dict = new HashSet<>(wordDict);
        
        List<List<Integer>> graph = new ArrayList<>();
        for(int i=0; i<n; ++i){
            graph.add(new ArrayList<>());
        }
        // Construct Graph
        for(int start=0; start<n; ++start){
            for(int end=start+1; end<=n; ++end){
                if(dict.contains(s.substring(start, end))){
                    graph.get(start).add(end);                    
                }
            }
        }
        Q.add(0);
        boolean[] visited = new boolean[n];
        while(!Q.isEmpty()){
            int front = Q.remove();
            if(front == n)      return true;
            if(!visited[front]){
                visited[front] = true;
                List<Integer> adj = graph.get(front);
                if(adj != null){
                    for(int neighbor : adj){
                        if(neighbor == n)       return true;
                        if(!visited[neighbor])
                            Q.add(neighbor);
                    }
                }
            }
        }
        return false;
    }
}
