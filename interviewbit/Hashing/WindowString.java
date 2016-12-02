public class Solution {
  public String minWindow(String S, String T) {
        int[] target = new int[256];
        Set<Character> charsToCheck = new HashSet<>();
        for(int i=0; i<T.length(); i++){
            target[T.charAt(i)]++;
            charsToCheck.add(T.charAt(i));
        }
        
        int n = S.length();
        int start = 0;
        // Find the first minimum valid window        
        int end = 0;
        int[] cur = new int[256];
        int minLen = Integer.MAX_VALUE;
        int resultStart = 0;
        while(end < n){
            char c = S.charAt(end);
            cur[c]++;
            if(cur[c] >= target[c]){
                charsToCheck.remove(c);
            }
            // covered all characters
            if(charsToCheck.size() == 0){
                while(start < end && target[S.charAt(start)] < cur[ S.charAt(start)]){
                    cur[S.charAt(start)]--;
                    start++;
                }   
                if(minLen > end - start + 1){
                    minLen = end - start + 1;
                    resultStart = start;
                }
            }
            end++;
        }
        
        return minLen == Integer.MAX_VALUE? "" : S.substring(resultStart, resultStart+minLen);
  }
}
