/*
Given a string, find the length of the longest substring T that contains at most k distinct characters.

For example, Given s = “eceba” and k = 2,

T is "ece" which its length is 3.
*/

public class Solution {
    // Hashmap solution: extended from two distinct character solution
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int n = s.length();
        if(n <= k)      return n;
        Map<Character, Integer> map = new HashMap<>();
        int start = 0;
        int end = 0;
        int maxLength = 0;
        while(end < n){
            char c = s.charAt(end);
            if(map.size() <= k){
                map.put(c, end);
                end++;
            }
            if(map.size() > k){
                // Find the leftmost Index
                int leftIndex = n;
                for(int i : map.values()){
                    leftIndex = Math.min(leftIndex, i);
                }
                map.remove(s.charAt(leftIndex));
                start = leftIndex + 1;
            }
            maxLength = Math.max(maxLength, end - start);
        }
        return maxLength;
         
    }
}