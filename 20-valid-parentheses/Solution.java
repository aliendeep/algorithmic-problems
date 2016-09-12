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