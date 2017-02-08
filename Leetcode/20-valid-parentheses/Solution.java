/*
Given a string containing just the characters '(', ')', '{', '}', '[' and ']', 
determine if the input string is valid.

The brackets must close in the correct order, "()" and "()[]{}" are all valid but 
"(]" and "([)]" are not.
*/
// Stack
public class Solution {
    public boolean isValid(String s) {
        if(s.length() == 0)
            return true;
        HashMap<Character, Character> hmap = new HashMap<Character, Character>();
        hmap.put('(', ')');
        hmap.put('{', '}');
        hmap.put('[', ']');
        Deque<Character> stk = new LinkedList<Character>();
        int i = 0;
        while(i < s.length()){
            if(s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{')
                stk.addFirst(new Character(s.charAt(i)));
            else{
                if(stk.isEmpty())
                    return false;
                char c = (Character)stk.removeFirst();
                if(hmap.get(c) != s.charAt(i))
                    return false;
            }
            i++;
        }
        return stk.isEmpty() == true;
    }
}

class Solution {
    public boolean isValid(String s) {
        if(s.length() == 0)
            return true;
        Map<Character, Character> map = new HashMap<Character, Character>();
        map.put('(', ')');
        map.put('{', '}');
        map.put('[', ']');
        Deque<Character> stk = new LinkedList<Character>();
        for(char c : s.toCharArray()){
            if(c == '(' || c == '[' || c == '{')
                stk.push(c);
            else{
                if(stk.isEmpty())
                    return false;
                char t = stk.pop();
                if(map.get(t) != c)
                    return false;
            }
        }
        return stk.isEmpty() == true;
    }
}
