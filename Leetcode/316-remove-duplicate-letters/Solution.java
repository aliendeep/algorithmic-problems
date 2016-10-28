/*
Given a string which contains only lowercase letters, remove duplicate letters so that every letter appear once and only once. 
You must make sure your result is the smallest in lexicographical order among all possible results.

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