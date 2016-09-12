public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n =  s.length();
        if(n <= 1)
            return n;

        HashSet<Character> map = new  HashSet<Character>();
        int start = 0, end = 0;
        int maxLength = 0;
        while(start < n && end < n){  
            char c = s.charAt(end);
            // remove elements from the start
            if(map.contains(c)){
                map.remove(s.charAt(start));
                start++;
            }
            else{
                map.add(c);
                end++;
                maxLength = Math.max(maxLength, end-start);
            }
        }
        return maxLength;
    }
}