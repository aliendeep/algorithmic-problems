/*
Given a string which contains only lowercase letters, remove duplicate letters so 
that every letter appear once and only once. 
You must make sure your result is the smallest in lexicographical order among 
all possible results.

Example:
Given "bcabc"
Return "abc"

Given "cbacdcbc"
Return "acdb"
*/

// Stack Solution
public class Solution {
    // O(n) Solution
    // Space: O(n)
    public String removeDuplicateLetters(String s) {
        int[] cnt = new int[26];
        boolean[] visited = new boolean[26];
        
        for(int i=0; i<s.length(); ++i){
            cnt[s.charAt(i) - 'a']++;
        }
        
        Deque<Character> stk = new LinkedList<>();
        for(int i=0; i<s.length(); ++i){
            char c = s.charAt(i);
            // decrease count
            cnt[c - 'a']--;
            // if already visited
            if(visited[c - 'a'])
                continue;
            
            // More count of the stack top is available later
            while(!stk.isEmpty() && stk.peekFirst() > c && cnt[stk.peekFirst() - 'a'] > 0){
                // remove the character from the stack
                visited[stk.pop() - 'a'] = false;
            }   
            stk.push(c);
            visited[c - 'a'] = true;
        } 
        
        StringBuilder r = new StringBuilder();
        while(!stk.isEmpty()){
            r.append(stk.pop());
        }
        r.reverse();
        return r.toString();
    }
}

// StringBuilder Solution
class Solution2 {
    // O(n) Solution
    public String removeDuplicateLetters(String s) {
        int[] cnt = new int[26];
        boolean[] visited = new boolean[26];
        int n = s.length();
        for(int i=0; i<n; ++i){
            cnt[s.charAt(i) - 'a']++;
        }
        
        StringBuilder r = new StringBuilder();
        r.setLength(26);
        
        int len = 0;
        for(int i=0; i<n; ++i){
            char c = s.charAt(i);
            // decrease count
            cnt[c - 'a']--;
            // if already visited
            if(visited[c - 'a'])
                continue;
            
            // More count of the stack top is available later
            while(len > 0 && r.charAt(len-1) > c && cnt[r.charAt(len-1) - 'a'] > 0){
                // remove the character from the result
                visited[r.charAt(len-1) - 'a'] = false;
                // decrease len
                len--;
            }   
            r.setCharAt(len, c);
            // increase length of the result
            len++;
            visited[c - 'a'] = true;
        } 
        r.setLength(len);
        return r.toString();
   }
}

// Recursive
class Solution3 {
    public String removeDuplicateLetters(String s) {
        int n = s.length();
        if(n == 0)
            return "";
        int[] cnt = new int[26];
        for(int i=0; i<n; i++){
            cnt[s.charAt(i) - 'a']++;
        }
        int lowestPosition = 0;
        // Find the postion 
        for(int i=0; i<n; i++){
            if(s.charAt(i) < s.charAt(lowestPosition))
                lowestPosition = i;
            cnt[s.charAt(i) - 'a']--;
            if(cnt[s.charAt(i) - 'a'] == 0)
                break;
        }
        
        char firstChar = s.charAt(lowestPosition);
        // replace all: first parameter String
        String rest = s.substring(lowestPosition+1).replaceAll("" + firstChar, "");
        return  firstChar + removeDuplicateLetters(rest);             
    }
}
