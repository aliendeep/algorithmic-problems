public class Solution {
    // Alternative
    public boolean helper(String s, Set<String> dict, Map<String, Boolean> dp){
        if(s.length() == 0)        
            return true;
        
        // whole word is a dictionary word
        if(dict.contains(s))
            return true;
            
        if(dp.containsKey(s)){
            return dp.get(s);
        }
        
      boolean flag = false;
      int n = s.length();
      for(int i=1; i<n; i++){
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

    public boolean wordBreak(String s, Set<String> wordDict) {
        Map<String, Boolean> dp = new HashMap<>();
        return helper(s, wordDict, dp);
  }        
}