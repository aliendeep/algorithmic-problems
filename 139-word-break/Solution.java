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
    public boolean wordBreak(String s, Set<String> wordDict) {
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
}