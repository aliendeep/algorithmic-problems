/*
Given a string, find the length of the longest substring T that contains at 
most k distinct characters.

For example, Given s = “eceba” and k = 2,

T is "ece" which its length is 3.
*/
import java.util.*;

public class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int n = s.length();
        if(n <= k)      return n;         
        int maxLen = 0, distinct = 0;
        int start = 0;
        int[] visited  = new int[256];
        for(int i=0; i<n; ++i){
            int c = s.charAt(i);
            if(visited[c] == 0){
                distinct++;
            }
            visited[c]++;
            while(distinct > k){
                visited[s.charAt(start)]--;
                if(visited[s.charAt(start)] == 0)
                    distinct--;
                start++;                
            }
            maxLen = Math.max(maxLen, i-start+1);
        }
        return maxLen;
    }

    public static void main(String[] args){
        Solution ob = new Solution();
        Solution2 ob2 = new Solution2();
        Solution3 ob3 = new Solution3();
        
        System.out.println(ob.lengthOfLongestSubstringKDistinct("abaaaaadhajuriwjkewdsklflsfeceba", 10));        
        System.out.println(ob2.lengthOfLongestSubstringKDistinct("abaaaaadhajuriwjkewdsklflsfeceba", 10));        
        System.out.println(ob3.lengthOfLongestSubstringKDistinct("abaaaaadhajuriwjkewdsklflsfeceba", 10));        
        System.out.println(ob.lengthOfLongestSubstringKDistinct("xyztzabzzctjsds", 4));        
        System.out.println(ob2.lengthOfLongestSubstringKDistinct("xyztzabzzctjsds", 4));       
        System.out.println(ob3.lengthOfLongestSubstringKDistinct("xyztzabzzctjsds", 4));        
    }
}

class Solution2 {
    // O(nk) Solution
    // Space: O(k)
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

class Solution3 {
    // O(nlogk) Solution
    // Map + TreeMap to get 
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int n = s.length();
        if(n <= k)      return n;
        Map<Character, Integer> window = new HashMap<>();
        TreeMap<Integer, Character> leftMostMap = new TreeMap<>();
        int start = 0;
        int end = 0;
        int maxLength = 0;
        int resultStart = -1;;
        while(end < n){
            char c = s.charAt(end);
            if(window.containsKey(c)){
                // remove the obsolete index value from the map
                leftMostMap.remove(window.get(c));
            }
            leftMostMap.put(end, c);
            window.put(c, end);
            end++;              
            if(window.size() > k){
                // Find the leftmost index
                int leftMostIndex = leftMostMap.firstKey();
                // Get the corresponding character
                char lc = leftMostMap.get(leftMostIndex);
                // Remove this index and character from both map and set
                leftMostMap.remove(leftMostIndex);
                window.remove(lc);                
                start = leftMostIndex + 1;
            }
            int len = end - start;
            if(maxLength < len){
                maxLength = len;
                resultStart = start;
            }
        }
        return maxLength;
         
    }
}
