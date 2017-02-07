/*
Given a string, find the length of the longest substring T that contains at most 2 distinct characters.

For example, Given s = “eceba”,

T is "ece" which its length is 3.
*/
/*
Test case:
"ccaabbb"
"eceba"
"llohhheheehllo"
"llohhheheehlloaabbbababbbabaca"
"ab"
"aaa"
"tcbbbbaaaaa"
"ccaabbb"
Sample Output:
5
3
8
14
2
3
9
5
*/

public class Solution {
    // HashMap solution
    // O(n)
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int n = s.length();
        if(n <= 2)
            return n;
        Map<Character, Integer> map = new HashMap<>();
        int start = 0;
        int end = 0;
        int maxLength = 0;
        while(end < n){
            char c = s.charAt(end);
            if(map.size() <= 2){
                map.put(c, end);
                end++;
            }
            if(map.size() > 2){
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

public class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int n = s.length();
        if(n <= 2)
            return n;
        Character p1 = s.charAt(0);
        Character p2 = s.charAt(1);
        // index of last seen p2
        int lastP1 = 0;
        int lastP2 = 1;
        if(s.charAt(0) == s.charAt(1)){
            lastP1 = 1;
            p2 = null;
            lastP2 = -1;
        }

        int maxLength = 2;
        int start = 0;
        int end = 2;
        while(end < n){
            char c = s.charAt(end);
            // See if we can extend current solution
            // current char is the same as any of the previous 2
            if(p2 == null){
                if(p1.charValue() == c)
                    lastP1 = end;
                // different character than p1 but p2 is not set yet
                else{
                    // set the p2
                    p2 = c;
                    lastP2 = end;
                }
            }
            else{
                if(p1.charValue() == c ){
                    lastP1 = end;
                }
                else if(p2.charValue() == c){
                    lastP2 = end;
                }
                else{
                    // We have found brand new character other than p1 and p2
                    if(maxLength < end - start){
                        maxLength  = end - start;    
                    }
                    if(lastP2 > lastP1){
                        start = lastP1 + 1;
                        // change both p1 and p2
                        p1 = p2;
                        p2 = c;
                        lastP1 = lastP2;
                        lastP2 = end;
                    }
                    else if(lastP1 > lastP2){
                        start = lastP2 + 1;
                        // Reset only p2
                        p2 = c;
                        lastP2 = end;
                    }
                }
            }
            end++;
        } 
        // last window
        if(maxLength < end - start){
            // end = n
            maxLength  = end - start;    
        }
        return maxLength;
    }
}

// Cleaner
public class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int n = s.length();
        if(n <= 2)    return n;
        int start = 0;
        Map<Character, Integer> map = new HashMap<>();
        int maxLen = 0;
        for(int end = 0; end < n; ++end){
            char c = s.charAt(end);
            map.put(c, map.getOrDefault(c, 0) + 1);
            
            while(start < end && map.size() > 2){
                char sc = s.charAt(start);
                int nc = map.get(sc) - 1;
                if(nc == 0)
                    map.remove(sc);
                else
                    map.put(sc, nc);
                start++;
            }
            maxLen = Math.max(maxLen, end - start + 1);
        }
        return maxLen;        
    }
}
