/*
Given a list of words, please write a program that returns all concatenated words 
in the given list of words.

A concatenated word is defined as a string that is comprised entirely of at least 
two shorter words in the given array.

Example:
Input: ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]

Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]

Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats"; 
 "dogcatsdog" can be concatenated by "dog", "cats" and "dog"; 
"ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".
Note:
The number of elements of the given array will not exceed 10,000
The length sum of elements in the given array will not exceed 600,000.
*/
public class Solution {
    boolean canSplit(String word, Set<String> dict){
        int n = word.length();
        if(n == 0)
            return false;
        boolean[] dp = new boolean[n];  

        for(int i=0; i<n; ++i){
            if(dict.contains(word.substring(0, i+1)) && i<n-1){
                dp[i] = true;
            }
            else{
                for(int j=0; j<i; ++j){
                    if(dp[j] == true && dict.contains(word.substring(j+1, i+1))){ 
                        dp[i] = true;
                        break;
                    }
                }
            }
        } 
        return dp[n-1];
    }    
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> r = new ArrayList<>();
        int n = words.length;
        if(n == 0)
            return r;
        Set<String> dict = new HashSet<>();
        for(String word : words){
            dict.add(word);
        }
        for(String word : dict){
            if(word.length() == 0)
                continue;
            if(canSplit(word, dict)){
                r.add(word);
            }
        }
        return r;
    }
}
