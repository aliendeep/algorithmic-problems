public class Solution {
  public int lengthOfLongestSubstring(String s) {
      int n = s.length();
        int[] pos = new int[256];
        int maxLength = 0;
        for(int start = 0, end = 0; end < n; end++){
            int c = s.charAt(end);
            // Extend the range [start .. end]
            start = Math.max(start, pos[c]);
            maxLength = Math.max(maxLength, end - start+1);
            pos[c] = end+1;
        }
        return maxLength;
  }
}

class Solution2 {
  public int lengthOfLongestSubstring(String a) {
      int n = a.length();
      Map<Character, Integer> map = new HashMap<>();
      int maxLen = 1;
      int start = 0;
      int end = 0;
      while(end < n){
          char c = a.charAt(end);
          if(map.containsKey(c)){
              map.remove(a.charAt(start));
              start++;
          }
          else{
              map.put(c, end);
                ++end;
              maxLen = Math.max(maxLen, end - start);
          }
      }
      return maxLen;
  }
}